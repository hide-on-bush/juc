/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @Author:夏世雄
 * @Date: 2020/12/13 11:02
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 8锁 就是关于锁的8个问题
 * 1.标准情况下，两个线程先执行发短信还是打电话？   先发短息，再打电话
 * 2.标准情况下，sendMsg() 延迟4秒钟，是先执行发短信还是打电话？ 先发短息，再打电话
 *
 *
 **/
public class Test3 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{phone.sendMsg();},"A").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{phone.call();},"B").start();
    }
}

class Phone {

    //synchronized 锁的对象是方法的调用者，两个方法用的是同一把锁，谁先拿到谁先执行
    //synchronized static方法锁的是Class，类锁
    public synchronized void sendMsg(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call(){
        System.out.println("打电话");
    }
}
