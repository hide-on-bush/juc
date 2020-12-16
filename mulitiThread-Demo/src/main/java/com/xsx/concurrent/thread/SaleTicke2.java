/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:夏世雄
 * @Date: 2020/12/12 17:02
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
public class SaleTicke2 {


    //并发：多个线程操作同一个资源类
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();
        new Thread(()->{ for (int i=1;i<40;i++) ticket.sale(); },"A").start();

        new Thread(()->{ for (int i=1;i<40;i++) ticket.sale(); },"B").start();

        new Thread(()->{ for (int i=1;i<40;i++) ticket.sale(); },"C").start();

    }


}

//三部曲

/**
 * 1.  new ReentrantLock();
 * 2.加锁 lock.lock();
 * 3.解锁 lock.unlock();
 */
class Ticket2{

    private Integer num=30;

    Lock lock = new ReentrantLock();

    public  void sale(){
        //加锁
        lock.lock();
        try{
            if (num > 0){
                System.out.println(Thread.currentThread().getName() + "卖出了" + (num--)+ "票，剩余：" + num);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //解锁
            lock.unlock();
        }

    }
}
