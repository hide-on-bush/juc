/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.review.juc;

import java.util.concurrent.TimeUnit;

/**
 * @Author:夏世雄
 * @Date: 2020/12/16 10:07
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 生产者和消费者 synchronized
 **/
public class ProviderAndConsumer {

    public static void main(String[] args) throws Exception{
        Product product = new Product();
        for (int i = 1;i <= 10;i++) {
            new Thread(()->{
                product.add();
            },"provider1").start();
        }

        TimeUnit.SECONDS.sleep(1);

        for (int i = 1;i <= 10;i++) {
            new Thread(()->{
                product.reduce();
            },"consumer1").start();
        }


    }
}



class Product {
    /*
     * 商品库存
     */
    private  int  total = 0;

    public synchronized void add(){
        while (total != 0) {
            try {
                this.wait();
                System.out.println("生产者" + Thread.currentThread().getName() + "休息一下，库存为：" + total);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        total++;
        this.notifyAll();
        System.out.println("通知消费者消费！");
    }

    public synchronized void reduce(){
        while(total == 0){
            try {
                this.wait();
                System.out.println("消费者" + Thread.currentThread().getName() + "休息一下，商品个数为：" + total);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        total--;
        this.notifyAll();
        System.out.println("通知生产者生产商品！");
    }
}