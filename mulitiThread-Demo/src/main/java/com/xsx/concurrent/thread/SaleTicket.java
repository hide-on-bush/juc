/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.thread;

import javax.naming.Name;

/**
 * @Author:夏世雄
 * @Date: 2020/12/12 16:15
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 基本的卖票例子
 * 真正的多线程开发，公司中的开发，降低耦合性
 * 线程就是一个单独的资源类，没有任何的附属的操作
 **/
public class SaleTicket {
    //并发：多个线程操作同一个资源类
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //@FunctionalInterface 函数式接口，jdk1.8 lambda ()->{代码}
        new Thread(()->{
            for (int i=1;i<40;i++){
                ticket.sale();
            }

        },"A").start();

        new Thread(()->{
             for (int i=1;i<40;i++){
                ticket.sale();
             }
        },"B").start();

        new Thread(()->{
            for (int i=1;i<40;i++){
                ticket.sale();
            }
        },"C").start();

    }


}


class Ticket{

    private Integer num=30;

    //传统的方式，使用synchronized
    public synchronized void sale(){
        if (num > 0){
            System.out.println(Thread.currentThread().getName() + "卖出了" + (num--)+ "票，剩余：" + num);
        }
    }
}
