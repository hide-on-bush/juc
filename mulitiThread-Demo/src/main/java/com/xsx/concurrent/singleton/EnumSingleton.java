package com.xsx.concurrent.singleton;/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */

import java.lang.reflect.Constructor;

/**
 * @Author:夏世雄
 * @Date: 2020/12/15 15:08
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 枚举本身也是一个类
 **/
public enum EnumSingleton {

    INSTANCE;

    public EnumSingleton getInstance(){
        return INSTANCE;
    }
}

class Test{
    public static void main(String[] args) throws Exception{
        EnumSingleton e1 = EnumSingleton.INSTANCE;
        //java.lang.IllegalArgumentException: Cannot reflectively create enum objects
        Constructor<EnumSingleton> declaredConstructor  = EnumSingleton.class.getDeclaredConstructor(String.class,int.class);
        declaredConstructor.setAccessible(true);
        EnumSingleton e2  = declaredConstructor.newInstance();
        //Constructor<EnumSingleton> declaredConstructor2  = EnumSingleton.class.getDeclaredConstructor(null);
        //java.lang.NoSuchMethodException: com.xsx.concurrent.singleton.EnumSingleton.<init>()
        System.out.println(e1);
        System.out.println(e2);

    }
}