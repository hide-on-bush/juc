package com.xsx.springcloud.mapper;/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */

import com.xsx.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:夏世雄
 * @Date: 2020/12/9 16:00
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Mapper
public interface OrderMapper {

    /**
     * 创建订单
     * @param order
     * @return
     */
    int createOrder(Order order);

    Order getOrderById(Long id);

    List<Order> getCurrentUserOrders(Long userId);


    int updateOrder(Order order);

    int deleteOrderById(Long id);

    int updateOrderStatus(@Param("userId") Long userId);
}
