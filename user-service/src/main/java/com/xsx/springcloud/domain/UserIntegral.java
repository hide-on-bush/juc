/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author:夏世雄
 * @Date: 2020/12/10 10:12
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 用户积分
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserIntegral {

    private Long id;
    private Long userId;
    /**
     * 积分
     */
    private Long integral;
    private Date createTime;
    private Date updateTime;

    /**
     * 删除状态:  0:未删除 1:删除
     */
    private int deleteFlag;
}
