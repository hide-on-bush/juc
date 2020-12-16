/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author:夏世雄
 * @Date: 2020/12/13 16:18
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 读写锁
 * 读和写 不能共存
 * 读和读 可以共存
 * 写和写 不能共存
 * 写锁 独占锁
 * 读锁 共享锁
 **/
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        //写入操作
        for (int i = 1; i<= 6;i++){
            final int temp = i;
            new Thread(()->{
                myCache.put(String.valueOf(temp), temp);
            },String.valueOf(i)).start();
        }

        //读取操作
        for (int i = 1; i <= 6;i++){
            final int temp = i;
            new Thread(()->{
                myCache.get(String.valueOf(temp));
            },String.valueOf(i)).start();
        }

    }
}

/**
 * 自定义缓存
 */
class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    //读写锁 ：更加细粒度的空值
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //存，写 同时只有一个线程能写入
    public void put(String key,Object value){
        try{
            readWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "写入OK!");
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }
    //取，读 可以多个线程同时读
    public Object get(String key){
        try{
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            Object obj = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取OK!" );
            return obj;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return null;
    }
}