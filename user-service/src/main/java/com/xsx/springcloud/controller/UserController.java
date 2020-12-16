/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.springcloud.controller;

import cn.hutool.core.util.ObjectUtil;
import com.xsx.springcloud.common.ResponseEntity;
import com.xsx.springcloud.domain.Order;
import com.xsx.springcloud.domain.User;
import com.xsx.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:夏世雄
 * @Date: 2020/12/10 10:48
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/check")
    //@SentinelResource(value = "/check")
    public ResponseEntity checkUser(String account,String password){
        ResponseEntity result;
        User user = userService.checkUser(account,password);
        if (ObjectUtil.isEmpty(user)) {
            result = new ResponseEntity("账号或密码错误，请重新登陆",400);
        } else {
            result = new ResponseEntity("登陆成功",666,user);
        }
        return result;
    }

    @GetMapping("/shopping")
    public ResponseEntity shopping(Order order) {
        return userService.shopping(order);
    }
}
