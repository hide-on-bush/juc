/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.locks;

/**
 * @Author:夏世雄
 * @Date: 2020/12/15 17:07
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: synchronized 默认是非公平锁、可重入
 **/
public class Demo01 {

    public static void main(String[] args) {
         Phone phone = new Phone();
         new Thread(()->{
             phone.sendMsg();
         },"A").start();
         new Thread(()->{
            phone.sendMsg();
         },"B").start();
    }
}

class Phone{
    public synchronized void sendMsg(){
        System.out.println(Thread.currentThread().getName() + "sendMsg");
        call();//这里也有锁
    }

    public synchronized void call(){
        System.out.println(Thread.currentThread().getName() + "call");
    }
}