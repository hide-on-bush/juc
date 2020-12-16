/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.locks;

import jdk.internal.org.objectweb.asm.tree.MultiANewArrayInsnNode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @Author:夏世雄
 * @Date: 2020/12/15 18:03
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
public class DeadLock {

    public static void main(String[] args) {
        new Thread(new MyThread("lockA","lockB"),"T1").start();
        new Thread(new MyThread("lockB","lockA"),"T2").start();
    }
}

@Data
@AllArgsConstructor
class MyThread implements Runnable {
    private String lockA;
    private String lockB;
    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "LOCK:" + lockA + "=>GET " + lockB);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "LOCK:" + lockB + "=>GET " + lockA);
            }
        }
    }
}