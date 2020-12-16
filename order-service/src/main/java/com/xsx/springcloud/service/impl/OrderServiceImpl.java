/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.springcloud.service.impl;

import com.xsx.springcloud.domain.Order;
import com.xsx.springcloud.mapper.OrderMapper;
import com.xsx.springcloud.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:夏世雄
 * @Date: 2020/12/9 16:21
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public boolean createOrder(Order order) {
        log.info("================创建订单start，参数order{}======================",order);
        return orderMapper.createOrder(order) == 1;
    }

    @Override
    public Order getOrderById(Long id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public List<Order> getCurrentUserOrders(Long userId) {
        return orderMapper.getCurrentUserOrders(userId);
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderMapper.updateOrder(order) == 1;
    }

    @Override
    public boolean deleteOrderById(Long id) {
        return orderMapper.deleteOrderById(id) == 1;
    }

    @Override
    public boolean updateOrderStatus(Long userId) {
        return orderMapper.updateOrderStatus(userId) == 1;
    }
}
