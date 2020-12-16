/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author:夏世雄
 * @Date: 2020/12/14 10:19
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 同步队列 和其他的BlockedQueue不一样 SynchronousQueue不存储元素
 * put了一个元素，必须从里面take取出来，否则不能再put进去
 **/
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        //同步队列 最多只能存放一个元素
        SynchronousQueue<String>  synchronousQueue = new SynchronousQueue();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName() + " put 2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName() + " put 3");
                synchronousQueue.put("3");
                //synchronousQueue.put("bbb");//没有取出来，会一直阻塞，放不起第二个元素
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "take" + synchronousQueue.take());
                System.out.println(Thread.currentThread().getName() + "take" + synchronousQueue.take());
                System.out.println(Thread.currentThread().getName() + "take" + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();

    }
}
