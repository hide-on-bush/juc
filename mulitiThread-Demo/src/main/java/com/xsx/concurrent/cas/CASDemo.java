/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:夏世雄
 * @Date: 2020/12/15 15:55
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);
        //CAS compareAndSet 比较并交换
        //public final boolean compareAndSet(int expect, int update)
        //expect 期望  update 更新
        //如果我们期望的值达到了，那么就更新，否则就不更新，CAS 是cpu的并发原语

        //========================捣乱的线程====================
        System.out.println(atomicInteger.compareAndSet(2020,2021));
        System.out.println(atomicInteger);

        System.out.println(atomicInteger.compareAndSet(2021,2020));
        System.out.println(atomicInteger.get());


        //==========================期望的线程=================
        System.out.println(atomicInteger.compareAndSet(2020,6666));
        System.out.println(atomicInteger.get());
    }
}
