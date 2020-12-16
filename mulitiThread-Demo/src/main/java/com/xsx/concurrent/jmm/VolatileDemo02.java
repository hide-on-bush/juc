/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:夏世雄
 * @Date: 2020/12/15 11:18
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:  volatile 不保证原子性
 **/
public class VolatileDemo02 {

    private static  AtomicInteger num = new AtomicInteger();

    public  static void add(){
        //AtomicInteger +1方法
        num.getAndIncrement();
    }

    //理论上num的结果应该为20000
    public static void main(String[] args) {
         for(int i = 1;i<=20;i++){
             new Thread(()->{
                 for(int j = 1;j<= 1000;j++){
                     add();
                 }
             }).start();
         }
        //main、gc  线程存活数量如果大于2
         while (Thread.activeCount() > 2){
             //Thread.yield() 方法，使当前线程由执行状态，变成为就绪状态，让出cpu时间，在下一个线程执行时候，
             // 此线程有可能被执行，也有可能没有被执行。
             Thread.yield();
         }
        System.out.println(num);
    }
}
