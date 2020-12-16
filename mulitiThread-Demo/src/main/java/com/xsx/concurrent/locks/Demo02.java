/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:夏世雄
 * @Date: 2020/12/15 17:22
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
public class Demo02 {
    public static void main(String[] args) {
        Person person = new Person();
        new Thread(()->{
            person.eat();
        },"A").start();
        new Thread(()->{
            person.eat();
        },"B").start();
    }
}
class Person {
    private Lock lock = new ReentrantLock();
    public void eat(){
        //细节问题  lock、unlock必须配对使用，否则会造成死锁
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " eat");
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public  void sleep() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " sleep");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
