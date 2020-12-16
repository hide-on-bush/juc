/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author:夏世雄
 * @Date: 2020/12/15 17:36
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 自旋锁
 **/
public class SqinLock {
    AtomicReference<Thread> atomicReference = new AtomicReference();
    //加锁
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "加锁 ==>myLock");
        while(!atomicReference.compareAndSet(null, thread)){
        }
    }

    //解锁
    public void unMyLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "解锁 ==>myLock");
        atomicReference.compareAndSet(thread,null);
    }
}

class TestMyLock{
    public static void main(String[] args) {
        SqinLock sqinLock = new SqinLock();
        new Thread(()->{
            sqinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                sqinLock.unMyLock();
            }
        },"T1").start();
        new Thread(()->{
            sqinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                sqinLock.unMyLock();
            }
        },"T2").start();
    }
}
