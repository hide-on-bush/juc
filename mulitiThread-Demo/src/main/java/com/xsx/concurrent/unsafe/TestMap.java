/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.unsafe;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:夏世雄
 * @Date: 2020/12/13 12:32
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 * //同理 java.util.ConcurrentModificationException
 **/
public class TestMap {

    public static void main(String[] args) {
        //Map<String,String> map = new HashMap<>();
        //Map<String,String> map = new Hashtable<>();
       //Map<String,String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String,String> map = new ConcurrentHashMap<>();
        for(int i = 1;i <= 10;i++){
            new Thread(()->{
                map.put(UUID.randomUUID().toString().substring(0,5),UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
