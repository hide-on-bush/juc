/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.springcloud.service;

import com.xsx.springcloud.common.ResponseEntity;
import com.xsx.springcloud.domain.Order;
import com.xsx.springcloud.domain.User;

/**
 * @Author:夏世雄
 * @Date: 2020/12/10 10:41
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
public interface UserService {

    User checkUser(String account, String password);

    ResponseEntity shopping(Order order);
}
