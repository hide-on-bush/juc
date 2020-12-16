/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author:夏世雄
 * @Date: 2020/12/13 11:57
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:  同理 ConcurrentModificationException
 **/
public class TestSet {

    public static void main(String[] args) {
        //Set<String> set = new HashSet();
        //Set<String> set = Collections.synchronizedSet(new HashSet());
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1;i <= 30;i++){
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();

        }
    }
}
