/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.review.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:夏世雄
 * @Date: 2020/12/16 10:27
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: Lock实现生产者消费者
 **/
public class PCWithLock {

    public static void main(String[] args) {
        Factory factory = new Factory();
        for (int i = 1;i <= 10;i++){
            new Thread(()->{
                factory.product();
            },"PROVIDER").start();
        }

        for (int i = 1;i <= 10;i++){
            new Thread(()->{
                factory.sale();
            },"CONSUMER").start();
        }
    }
}


class Factory {


    private int total = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void product(){
        try {
            lock.lock();
            while(total != 0){
                condition.await();
                System.out.println("生产者休息一下，库存为：" + total);
            }
            total++;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void sale(){
        try {
            lock.lock();
            while(total == 0){
                condition.await();
                System.out.println("消费者休息一下，商品个数为：" + total);
            }
            total--;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
