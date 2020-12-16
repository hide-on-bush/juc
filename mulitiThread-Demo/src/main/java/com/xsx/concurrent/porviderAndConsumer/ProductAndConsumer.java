/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.porviderAndConsumer;

import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author:夏世雄
 * @Date: 2020/12/12 17:35
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 生产者和消费者问题
 * 线程之间的通讯 wait()、notify()、notifyAll()
 * 线程交替执行 A、B线程操作同一个一个变量 num=0
 * A num+1
 * B num-1
 **/
@Slf4j
public class ProductAndConsumer {

    public static void main(String[] args) {
        Provider provider = new Provider();
        new Thread(()->{ for (int i=1;i<10;i++) {
            provider.addProduct();
        }},"provider1").start();
        new Thread(()->{ for (int i=1;i<10;i++) {
            provider.addProduct();
        }},"provider2").start();
        new Thread(()->{for (int i=1;i<10;i++) {provider.saleProduct();}},"consumer1").start();
        new Thread(()->{for (int i=1;i<10;i++) {provider.saleProduct();}},"consumer2").start();
    }


}
//判断等待、业务、通知
class Provider{
    private Integer num = 0;

    public synchronized void addProduct(){
        //等待应该放在while中。放在if中会存在虚假唤醒
        while (num != 0) {
            //生产者停止生产商品，等待消费者消费产品       等待
            try{
                this.wait();
                System.out.println(Thread.currentThread().getName() + "===>"+num);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        num ++;
        //通知消费者消费产品
        this.notifyAll();
    }

    public synchronized void saleProduct(){
        //等待应该放在while中。放在if中会存在虚假唤醒
        while (num == 0) {
            //消费者停止消费产品，等待生产者生产商品         等待
            try{
                this.wait();
                System.out.println(Thread.currentThread().getName() + "===>"+num);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        num --;
        //通知生产者生产产品
        this.notifyAll();
    }
}