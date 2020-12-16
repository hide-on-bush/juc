/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.porviderAndConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:夏世雄
 * @Date: 2020/12/13 10:13
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 三个线程A、B、C 按顺序执行  A执行完调用B，B执行完调用C，C执行完调用A
 **/
public class Test2 {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{data.printA();},"A").start();
        new Thread(()->{data.printB();},"B").start();
        new Thread(()->{data.printC();},"C").start();
    }
}

class Data {
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    //number = 1 A执行 ；number=2 B执行；number =3 C执行
    private int number = 1;

    public void printA(){
        try{
            lock.lock();
            //业务  判断 -》执行-》通知
            while(number != 1) { //等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>AAAAAAAAAAA");
            //唤醒 唤醒B 精准唤醒
            number = 2;
            condition2.signal();

        }catch (Exception e){
            e.printStackTrace();
        } finally {
           lock.unlock();
        }

    }

    public void printB(){
        try{
            lock.lock();
            while (number != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>BBBBBBBBBBBB");
            number = 3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printC(){

        try {
            lock.lock();
            while (number != 3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>CCCCCCCCCCCC");
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
