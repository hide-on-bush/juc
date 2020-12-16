/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.concurrent_util;

import java.util.concurrent.CountDownLatch;

/**
 * @Author:夏世雄
 * @Date: 2020/12/13 15:26
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: CountDownLatch 减法计数器
 **/
public class CountDownLatchDemo {

    public static void main(String[] args) {
        //减法计数器  必须要执行任务的时候使用
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for(int i= 1;i <= 6;i++){
            new Thread(()->{

                System.out.println(Thread.currentThread().getName() + " 执行 完成！");
                countDownLatch.countDown();//-1
            },String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();//等待计数器归零，然后再向下执行
            System.out.println("close the door");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
