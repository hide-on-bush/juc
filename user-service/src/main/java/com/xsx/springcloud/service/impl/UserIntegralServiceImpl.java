/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.springcloud.service.impl;

import com.xsx.springcloud.mapper.UserIntegralMapper;
import com.xsx.springcloud.service.UserIntegralService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:夏世雄
 * @Date: 2020/12/10 10:46
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Service
@Slf4j
public class UserIntegralServiceImpl implements UserIntegralService {

    @Autowired
    private UserIntegralMapper userIntegralMapper;

    @Override
    public boolean addIntegral(Long integral, Long userId) {
        return userIntegralMapper.addIntegral(integral,userId) == 1;
    }
}
