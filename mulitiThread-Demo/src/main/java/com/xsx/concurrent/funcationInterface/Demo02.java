/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.funcationInterface;

import java.util.function.Predicate;

/**
 * @Author:夏世雄
 * @Date: 2020/12/14 15:34
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: Predicate 断定型接口，有一个输入参数，返回值为boolean
 **/
public class Demo02 {

    public static void main(String[] args) {
        //判断字符串是否为空
        Predicate<String> predicate = new Predicate<String>(){
            @Override
            public boolean test(String str) {
                return str.isEmpty();
            }
        };
        System.out.println(predicate.test("123"));

        Predicate<String> predicate2 = str->{return str.isEmpty();};
        System.out.println(predicate2.test(""));
    }
}
