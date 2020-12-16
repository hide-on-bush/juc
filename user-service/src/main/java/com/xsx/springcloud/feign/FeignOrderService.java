package com.xsx.springcloud.feign;/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */

import com.xsx.springcloud.common.ResponseEntity;
import com.xsx.springcloud.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:夏世雄
 * @Date: 2020/12/10 11:05
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@FeignClient(value = "order-service")
public interface FeignOrderService {

    @PostMapping("/api/order/create")
    ResponseEntity createOrder(@RequestBody Order order);


    @GetMapping("/api/order/update/status/")
    boolean updateOrderStatus(@RequestParam(name = "userId") Long userId);
}
