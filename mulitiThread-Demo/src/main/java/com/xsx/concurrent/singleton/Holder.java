/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @Author:夏世雄
 * @Date: 2020/12/15 14:49
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 静态内部类实现单例模式
 **/
public class Holder {

    private Holder(){

    }

    private static Holder getInstance(){
        return Inner.HOLDER;
    }


    public static class Inner{
        private static final Holder HOLDER = new Holder();
    }

    public static void main(String[] args) throws Exception{
        Field field = Holder.Inner.class.getDeclaredField("HOLDER");
        field.setAccessible(true);
        Holder h1 = null;
        if (field.getType() ==  Holder.class) {
            h1 = (Holder)field.get(Inner.HOLDER);
            System.out.println(h1);
        }
        System.out.println(field);
        System.out.println(Holder.getInstance());

    }
}
