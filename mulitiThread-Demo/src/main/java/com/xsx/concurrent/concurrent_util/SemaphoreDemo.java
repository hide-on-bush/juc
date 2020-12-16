/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.concurrent_util;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author:夏世雄
 * @Date: 2020/12/13 15:56
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 6个车 只有 3个停车位
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        //线程数量 ： 理解为停车位
        Semaphore semaphore = new  Semaphore(3);
        for(int i = 1;i <= 6; i++){
            new Thread(()->{
                try {
                    semaphore.acquire(); //得到
                    System.out.println(Thread.currentThread().getName() + "抢到车位！");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开车位！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();//释放
                }
            }, String.valueOf(i)).start();
        }
    }
}
