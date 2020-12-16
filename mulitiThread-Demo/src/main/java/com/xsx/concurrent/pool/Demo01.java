/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:夏世雄
 * @Date: 2020/12/14 10:50
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: Executors ：工价类 3大方方
 **/
public class Demo01 {

    public static void main(String[] args) {
        //单个线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();

        //创建一个固定的线程大小的线程池
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);

        //可伸缩的，遇强则强，遇弱则弱
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //创建定时执行线程
        //ExecutorService threadPool =Executors.newSingleThreadScheduledExecutor();
        //使用线程池创建线程
        try {
            for(int i = 1;i <= 100;i++){
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "ok");
                });
            }
            //线程池用完要关闭线程池
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
