/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.concurrent_util;

import java.util.concurrent.CyclicBarrier;

/**
 * @Author:夏世雄
 * @Date: 2020/12/13 15:39
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        //集齐七颗龙珠召唤神龙
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙成功！");//第二个参数为召唤神龙的线程
        });
        for (int i = 1;i <= 7;i++){
            //lambda能操作i吗？不能
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 收集了" + temp +"个龙珠！");
                try {
                    cyclicBarrier.await();//等待 任务线程到达构造函数中指定的数量，才接着执行
                } catch (Exception e){
                    e.printStackTrace();
                }
            }, String.valueOf(temp)).start();
        }
    }
}
