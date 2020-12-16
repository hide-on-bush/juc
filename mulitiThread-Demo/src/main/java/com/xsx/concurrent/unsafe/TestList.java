/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author:夏世雄
 * @Date: 2020/12/13 11:29
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 * //java.util.ConcurrentModificationException 并发修改异常
 **/
public class TestList {
    public static void main(String[] args) {
        //并发下arrayList不安全
        //List<String> list = new ArrayList<>();
        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        //写入时复制 cow 计算机程序设计领域的一种优化策略
        //多个线程调用的时候。list读取的时候，固定的，写入（覆盖）
        //在写入的时候避免覆盖，造成数据问题
        //读写分离
        //CopyOnWriteArrayList 比 Vector好在哪？
        //CopyOnWriteArrayList底层使用的是lock锁而Vector使用的是synchronized
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1 ;i <= 10;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString());
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
