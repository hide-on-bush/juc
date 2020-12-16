/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.funcationInterface;

import java.util.function.Supplier;

/**
 * @Author:夏世雄
 * @Date: 2020/12/14 15:50
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: Supplier 供给型接口 ，没有参数，只有返回值
 **/
public class Demo04 {
    public static void main(String[] args) {
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "hide_on_bush";
            }
        };
        System.out.println(supplier.get());

        Supplier<String> supplier1 = ()->{return "faker";};
        System.out.println(supplier1.get());
    }
}
