/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.funcationInterface;

import java.util.function.Consumer;

/**
 * @Author:夏世雄
 * @Date: 2020/12/14 15:41
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: Consumer 消费型接口
 **/
public class Demo03 {

    public static void main(String[] args) {
        Consumer<String> consumer =new Consumer<String>() {
            @Override
            public void accept(String str) {
                if (str.equals("ok")){
                    System.out.println("ok ok!");
                }else {
                    System.out.println("not ok!");
                }
            }
        };
        consumer.accept("ok");
        Consumer<String> consumer1 = (str)->{
            System.out.println(str);
        };
        consumer1.accept("123");
    }
}
