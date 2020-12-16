/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.pool;


import java.util.concurrent.*;

/**
 * @Author:夏世雄
 * @Date: 2020/12/14 11:28
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 手动创建线程池
 * 4种拒绝策略：
 * ① new ThreadPoolExecutor.AbortPolicy() 默认，线程池满了，还有线程任务进来，不处理此任务，抛出
 *   java.util.concurrent.RejectedExecutionException异常；
 * ②new ThreadPoolExecutor.CallerRunsPolicy()哪来的去哪里，此例由main线程处理；
 * ③new ThreadPoolExecutor.DiscardPolicy() 队列满了，丢掉任务，不会抛出异常
 * ④new ThreadPoolExecutor.DiscardOldestPolicy() 队列满了，尝试去和最早的竞争，也不会抛出异常
 **/
public class MyThreadPool {

    public static void main(String[] args) {
        //获取核心数
        Integer care = Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                care,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                //拒绝策略,线程池满了，还有线程任务进来，不处理此任务，抛出异常
                new ThreadPoolExecutor.AbortPolicy());

        try {
            //超出最大承载 max+queue java.util.concurrent.RejectedExecutionException
            for (int i=0;i <= 9;i++){
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " ok ");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
