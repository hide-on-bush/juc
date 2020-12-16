/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.singleton;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * @Author:夏世雄
 * @Date: 2020/12/15 14:34
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 饿汉式单例模式
 **/
public class LazyManSingleton {
    private volatile static  LazyManSingleton LAZY_MAN_SINGLETON ;
    private LazyManSingleton(){
        synchronized (LazyManSingleton.class) {
            if(LAZY_MAN_SINGLETON != null){
                throw new RuntimeException("不要试图使用反射来破坏单例模式！");
            }
        }
        System.out.println(Thread.currentThread().getName() + " ok");
    }
    /**
     * 双重检测锁模式的 懒汉式单例 dcl懒汉式
     * @return
     */
    private static  LazyManSingleton getInstance(){
        if (Objects.isNull(LAZY_MAN_SINGLETON)) {
            synchronized (LazyManSingleton.class) {
                if (Objects.isNull(LAZY_MAN_SINGLETON)) {
                    //不是一个原则性操作
                    /**
                     * ①分配内存空间；②执行构造方法，初始化对象；③把这个对象指向这个空间
                     */
                    LAZY_MAN_SINGLETON = new LazyManSingleton();
                }
            }
        }
        System.out.println(LAZY_MAN_SINGLETON);
        return LAZY_MAN_SINGLETON;
    }


    //反射可以破坏单例模式
    public static void main(String[] args) throws Exception{
        //LazyManSingleton instance1 = LazyManSingleton.getInstance();
        Constructor<LazyManSingleton> declaredConstructor  = LazyManSingleton.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyManSingleton instance1 = declaredConstructor.newInstance();
        LazyManSingleton instance2 = declaredConstructor.newInstance();
        System.out.println(instance1);
        System.out.println(instance2);
    }
}
