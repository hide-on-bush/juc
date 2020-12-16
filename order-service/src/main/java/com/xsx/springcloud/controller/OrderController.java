/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.springcloud.controller;

import com.xsx.springcloud.common.ResponseEntity;
import com.xsx.springcloud.domain.Order;
import com.xsx.springcloud.enums.StatusCode;
import com.xsx.springcloud.service.OrderService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:夏世雄
 * @Date: 2020/12/9 16:24
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestBody Order order){
        ResponseEntity result ;
        boolean isCreate = orderService.createOrder(order);
        if (isCreate) {
            result = new ResponseEntity("创建订单成功",666);
        } else {
            result = new ResponseEntity("创建订单失败",444);
        }
        return result;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id){
        ResponseEntity result;
        Order order = orderService.getOrderById(id);
        if (ObjectUtils.isEmpty(order)) {
            result = new ResponseEntity("未查到对应的订单记录！", 200);
        } else {
            result = new ResponseEntity("查询订单记录成功！", 666,order);
        }
        return result;
    }

    @GetMapping("/get/userOrders/{userId}")
    public ResponseEntity getCurrentUserOrders(@PathVariable("userId") Long userId){
        ResponseEntity result;
        List<Order> orders = orderService.getCurrentUserOrders(userId);
        if(orders.isEmpty()){
            result = new ResponseEntity("当前用户没有订单", StatusCode.ERROR.getCode());
        }else {
            result = new ResponseEntity("查询当前用户订单记录成功！", StatusCode.SUCCESS.getCode(),orders);
        }
        return result;
    }

    @GetMapping("/update/status/")
    public boolean updateOrderStatus(@RequestParam(name = "userId") Long userId){
        return orderService.updateOrderStatus(userId);
    }


}
