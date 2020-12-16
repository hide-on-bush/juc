/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.springcloud.service.impl;

import com.xsx.springcloud.common.ResponseEntity;
import com.xsx.springcloud.domain.Order;
import com.xsx.springcloud.domain.User;
import com.xsx.springcloud.feign.FeignOrderService;
import com.xsx.springcloud.mapper.UserMapper;
import com.xsx.springcloud.service.UserIntegralService;
import com.xsx.springcloud.service.UserService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:夏世雄
 * @Date: 2020/12/10 10:42
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FeignOrderService feignOrderService;

    @Autowired
    private UserIntegralService userIntegralService;

    //@Autowired
   // private IdGeneratorSnowflake idGeneratorSnowflake;


    @Override
    public User checkUser(String account, String password) {
        log.info("校验用户信息，请求参数account：{}，password：{}",account,password);
        return userMapper.checkUser(account,password);
    }

    /**
     * 用户购物   订单服务下订单-->  减库存 --> 扣款 -->改订单状态 --> 加积分
     * @param order
     * @return
     */
    @Override
    @GlobalTransactional(name = "fps-user-createOrder",rollbackFor = Exception.class)
    public ResponseEntity shopping(Order order){

        log.info("=======================用户购物开始=========================");

        log.info("=======================调用订单服务，创建订单=========================");
        ResponseEntity orderResult = feignOrderService.createOrder(order);
        if (orderResult.getCode() != 666)  {
            return orderResult;

        }
        log.info("========================调用库存服务，扣减库存===================");

        log.info("========================扣减库存成功===================");

        log.info("========================调用账号微服务，扣款===================");

        log.info("========================调用账号微服务，扣款完成===================");

        log.info("========================调用账号微服务，扣款完成===================");

        log.info("=======================调用订单服务，修改订单状态为已完成=========================");
        if (!feignOrderService.updateOrderStatus(order.getUserId())) {
            throw new RuntimeException("修改订单状态失败！");
        }

        log.info("=======================调用用户服务服务，加积分=========================");
        if (!userIntegralService.addIntegral(order.getMoney().longValue(),order.getUserId())) {
            return new ResponseEntity("添加积分失败",444);
        }

        log.info("=======================用户购物完成=========================");
        return new ResponseEntity("下单成功",666);
    }
}
