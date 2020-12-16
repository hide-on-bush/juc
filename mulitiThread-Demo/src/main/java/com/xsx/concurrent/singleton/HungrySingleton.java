/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.singleton;

/**
 * @Author:夏世雄
 * @Date: 2020/12/15 14:31
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 饿汉式单例模式
 **/
public class HungrySingleton {

    private final static HungrySingleton HUNGRYSINGLETON = new HungrySingleton();

    private HungrySingleton(){

    }

    public static HungrySingleton getInstance(){
        return HUNGRYSINGLETON;
    }

}
