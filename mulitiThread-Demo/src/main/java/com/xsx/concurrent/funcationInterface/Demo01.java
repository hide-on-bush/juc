/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.funcationInterface;

import java.util.function.Function;

/**
 * @Author:夏世雄
 * @Date: 2020/12/14 15:24
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: Function 函数型接口
 **/
public class Demo01 {

    public static void main(String[] args) {
        Function<String, String> function = new Function<String, String>() {
            @Override
            public String apply(String str) {
                return str;
            }
        };
        System.out.println(function.apply("123"));

        Function<String, String> function2 = (str)->{return str;};
        System.out.println(function2.apply("abc"));
    }
}
