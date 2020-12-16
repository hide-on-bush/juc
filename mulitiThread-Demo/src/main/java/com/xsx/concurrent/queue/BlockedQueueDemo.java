/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author:夏世雄
 * @Date: 2020/12/13 17:05
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 阻塞队列
 * 什么时候会使用阻塞队列：多线程并发处理和线程池
 **/
public class BlockedQueueDemo {


    public static void main(String[] args) {
        //test1();
        //test2();

//        try {
//            test3();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        test4();
    }

    /**
     * 抛出异常
     */
    public static void  test1(){
        //队列大小
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));

        System.out.println(arrayBlockingQueue.element());
        System.out.println("============入队=============");
        //java.lang.IllegalStateException: Queue full 队列已满异常
        //System.out.println(arrayBlockingQueue.add("d"));

        System.out.println("============出队============");
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());

        //java.util.NoSuchElementException 抛出异常 队列中没有原始去移除
        //System.out.println(arrayBlockingQueue.remove());
    }

    /**
     * 不抛出异常
     */
    public static void test2(){
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));
        System.out.println(arrayBlockingQueue.peek());

        //不抛出异常 返回false
        System.out.println(arrayBlockingQueue.offer("d"));

        System.out.println("==========出队=========");
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());

        //不抛出异常 返回null
        System.out.println(arrayBlockingQueue.poll());

    }

    /**
     * 等待  阻塞 （一直阻塞）
     */
    public static void test3() throws Exception{
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("b");
        arrayBlockingQueue.put("c");
        //arrayBlockingQueue.put("d");

        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());

        //没有这个元素 也会一直阻塞
        //System.out.println(arrayBlockingQueue.take());
    }

    /**
     * 超时等待
     */
    public static void test4(){
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));
        try {
            System.out.println(arrayBlockingQueue.offer("d",2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        try {
            System.out.println(arrayBlockingQueue.poll(2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
