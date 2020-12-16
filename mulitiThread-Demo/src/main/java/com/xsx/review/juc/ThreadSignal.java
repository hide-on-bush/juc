/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.review.juc;

import jdk.internal.org.objectweb.asm.tree.MultiANewArrayInsnNode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:夏世雄
 * @Date: 2020/12/16 10:50
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 线程通信
 **/
public class ThreadSignal {

    public static void main(String[] args) {
        Source source = new Source();
        new Thread(()->{
            source.methodA();
        },"A").start();
        new Thread(()->{
            source.methodB();
        },"B").start();
        new Thread(()->{
            source.methodC();
        },"C").start();
    }



}


class Source {
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    //num = 1 执行
    public void methodA(){
        try {
            lock.lock();
            while (num != 1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() +" 开始执行methodA，num= " + num);
            num = 2;
            System.out.println(Thread.currentThread().getName() +" 执行methodA完成，num= " + num);
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //num = 2 执行
    public void methodB(){
        try {
            lock.lock();
            while (num != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() +" 开始执行methodB，num= " + num);
            num = 3;
            System.out.println(Thread.currentThread().getName() +" 执行methodB完成，num= " + num);
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //num = 3 执行
    public void methodC(){

        try {
            lock.lock();
            while (num != 3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() +" 开始执行methodC，num= " + num);
            num = 1;
            System.out.println(Thread.currentThread().getName() +" 执行methodC完成，num= " + num);
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}