/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.concurrent_util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author:夏世雄
 * @Date: 2020/12/13 15:02
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
public class TestCallable {

    public static void main(String[] args) {
        FutureTask futureTask = new FutureTask(new MyThread());//适配类
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();//结果会被缓存，提高效率
        try {
            //获取返回值 可能会出现阻塞，放在最后或者通过异步的方式
            int result =(int) futureTask.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}


class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 123456;
    }
}
