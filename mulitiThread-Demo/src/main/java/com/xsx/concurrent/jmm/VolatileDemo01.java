/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.jmm;

import java.util.concurrent.TimeUnit;

/**
 * @Author:夏世雄
 * @Date: 2020/12/15 11:08
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: java内存模型 volatile保证可见性
 **/
public class VolatileDemo01 {

    private static  int num = 0;

    public static void main(String[] args) {

        new Thread(()->{
            while(num == 0){

            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;
        System.out.println(num);
    }
}
