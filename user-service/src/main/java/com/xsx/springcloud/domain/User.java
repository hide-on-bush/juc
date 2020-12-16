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
 * @Date: 2020/12/10 10:07
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    private Long id;
    private String username;
    private String password;
    /**
     * 账号：唯一 用于登陆
     */
    private String account;
    private String image;
    private String mobile;
    private String nickName;
    /**
     * 性别:  0:女 1:男
     */
    private int six;

    /**
     *  使用状态:  0:使用中 1:未使用
     */
    private int isUse;

    /**
     * 是否黑名单:  0:否 1:是
     */
    private int isBlack;
    private Date createTime;
    private Date updateTime;

    /**
     * 删除状态:  0:未删除 1:删除
     */
    private int deleteFlag;
}
