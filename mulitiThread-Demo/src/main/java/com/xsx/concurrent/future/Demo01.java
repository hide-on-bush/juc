/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author:夏世雄
 * @Date: 2020/12/15 9:58
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 异步调用 CompletableFuture
 *  异步执行
 *  成功回调
 *  失败回调
 **/
public class Demo01 {
    public static void main(String[] args) throws Exception {
        //没有返回值的异步回调runAsync()
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "异步调用，没有返回值！");
        });
        System.out.println(Thread.currentThread().getName() + "成功执行");
        completableFuture.get();//获取执行结果
        //有返回值的异步回调
        //成功获取返回值
        //错误返回错误信息
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(()-> {
            System.out.println(Thread.currentThread().getName() + "有返回值异步回调===>supplyAsync");
            int age = 10 / 0;
            return 1024;
        } );
        completableFuture1.whenComplete((t,u)->{
            System.out.println("t=>" + t); //成功的正常的返回结果
            //失败时的错误信息
            System.out.println("u=>" + u);//java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
        }).exceptionally((e)->{
            System.out.println(e.getMessage());
            return 555; //可以获取到错误的返回结果
        });
        /**
         * success code 200
         * error code 404、500
         */
        //System.out.println(completableFuture1.get());
    }
}
