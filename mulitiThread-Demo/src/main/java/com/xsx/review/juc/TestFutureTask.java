/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.review.juc;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * @Author:夏世雄
 * @Date: 2020/12/16 11:23
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
public class TestFutureTask {

    public static void main(String[] args) {
        Resource resource = new Resource();
        resource.test1();
        resource.test2();
        System.out.println(LocalDateTime.now());
    }

}


class Resource {
    private   static  final ExecutorService threadPool = new ThreadPoolExecutor(
            4,
            Runtime.getRuntime().availableProcessors(),
            2,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(3),
            Executors.defaultThreadFactory(),
            //拒绝策略,线程池满了，还有线程任务进来，不处理此任务，抛出异常
            new ThreadPoolExecutor.AbortPolicy());



    public Integer getResult(){
        Callable<Integer> callable1 =(()->{
            m1();
            System.out.println("任务1耗时1秒");
            return 1;
        });


        Callable<Integer> callable2 = (()->{
            m2();
            System.out.println("任务2耗时2秒");
            return 2;
        });

        Callable<Integer> callable3 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                m3();
                System.out.println("任务3耗时3秒");
                return 3;
            }
        };

        FutureTask<Integer> task1 = new FutureTask<>(callable1);
        FutureTask<Integer> task2 = new FutureTask<>(callable2);
        FutureTask<Integer> task3 = new FutureTask<>(callable3);
        threadPool.submit(task1);
        threadPool.submit(task2);
        threadPool.submit(task3);
        int result = 0;
        try {
            result = task1.get() + task2.get() + task3.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }


    public int m1(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int m2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2;
    }


    public int m3(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 3;
    }


    public void test1() {
        long start = System.currentTimeMillis();
        int result = m1() +m2() +m3();
        long end = System.currentTimeMillis();
        System.out.println("串行执行耗时：" + (end -start) + "秒,计算结果为：" +result);
    }

    public void test2() {
        long start = System.currentTimeMillis();
        int result = getResult();
        long end = System.currentTimeMillis();
        System.out.println("futureTask执行耗时：" + (end -start) + "秒,计算结果为：" +result);
    }
}



