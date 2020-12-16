/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author:夏世雄
 * @Date: 2020/12/15 16:31
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 原子引用：带版本号的原子操作 可以解决ABA问题
 **/
public class CASRefrence {

    public static void main(String[] args) {
        //AtomicStampedReference 主要如果泛型是一个包装类，注意对象的引用问题
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,1);

        new Thread(()->{
            //获得版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println("a1=>" + stamp);
            try {
                TimeUnit.SECONDS.sleep(12);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

            System.out.println("a2=>" + atomicStampedReference.getStamp());
            System.out.println(atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

            System.out.println("a3=>" + atomicStampedReference.getStamp());
        },"A").start();

        //乐观锁的原理相同
        new Thread(()->{
            //获得版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println("b1=>" + atomicStampedReference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(13);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(1, 6,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("b2=>" + atomicStampedReference.getStamp());
        },"B").start();
    }
}
