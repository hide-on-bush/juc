/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.porviderAndConsumer;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:夏世雄
 * @Date: 2020/12/13 9:46
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
public class ProductAndConsumer2 {


    public static void main(String[] args) {
        Provider1 provider = new Provider1();
        new Thread(()->{ for (int i=1;i<10;i++) {
            provider.addProduct();
        }},"A").start();
        new Thread(()->{ for (int i=1;i<10;i++) {
            provider.addProduct();
        }},"B").start();
        new Thread(()->{for (int i=1;i<10;i++) {provider.saleProduct();}},"C").start();
        new Thread(()->{for (int i=1;i<10;i++) {provider.saleProduct();}},"D").start();
    }


}
//判断等待、业务、通知
class Provider1{
    private Integer num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public  void addProduct(){
        try{
            lock.lock();
            while (num != 0) {
                condition.await();//等待
                System.out.println(Thread.currentThread().getName() + "===>"+num);
            }
            num ++;
            //通知消费者消费产品
            condition.signalAll();//唤醒全部线程
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public  void saleProduct(){
        try{
            lock.lock();
            //等待应该放在while中。放在if中会存在虚假唤醒
            while (num == 0) { //消费者停止消费产品，等待生产者生产商品         等待
                condition.await();//等待
                System.out.println(Thread.currentThread().getName() + "===>"+num);
            }
            num --;
            condition.signalAll();//通知生产者生产产品
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}