/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @Author:夏世雄
 * @Date: 2020/12/14 17:35
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:  test求和任务
 * 三六九等
 * 三等 普通常规操作
 * 六等 fork join
 * 九等 stream 并行流
 **/
public class Test {

    public static void main(String[] args) throws Exception{
        testThirdClass();//262
        testSixthClass();//90
        testNinthClass();//
    }

    //普通程序员
    public static void testThirdClass(){
        long sum = 0L;
        long startTime = System.currentTimeMillis();
        for (long i = 1; i<=10_0000_0000L;i++){
            sum += i;
        }

        long endTime = System.currentTimeMillis();

        System.out.println("sum =" + sum + " 所用时间为：" + (endTime - startTime));
    }

    //会使用forkjoin
    public static void testSixthClass() throws Exception{
        long startTime = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0L,10_0000_0000L);
        //RecursiveTask<Long> submit =(RecursiveTask<Long>) forkJoinPool.submit(task);
        ForkJoinTask<Long>  submit = forkJoinPool.submit(task);
        long sum = submit.get();
        long endTime = System.currentTimeMillis();

        System.out.println("sum =" + sum + " 所用时间为：" + (endTime - startTime));

    }

    public static void testNinthClass(){
        long startTime = System.currentTimeMillis();
        //stream并行流
        long sum = LongStream.rangeClosed(0L,10_0000_0000L).parallel().reduce(0L,Long::sum);

        long endTime = System.currentTimeMillis();

        System.out.println("sum =" + sum + " 所用时间为：" + (endTime - startTime));

    }
}
