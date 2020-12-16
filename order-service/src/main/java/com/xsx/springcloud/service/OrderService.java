package com.xsx.springcloud.service;/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */

import com.xsx.springcloud.domain.Order;

import java.util.List;

/**
 * @Author:夏世雄
 * @Date: 2020/12/9 16:20
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
public interface OrderService {


    boolean createOrder(Order order);

    Order getOrderById(Long id);

    List<Order> getCurrentUserOrders(Long userId);


    boolean updateOrder(Order order);

    boolean deleteOrderById(Long id);

    boolean updateOrderStatus(Long userId);
}
