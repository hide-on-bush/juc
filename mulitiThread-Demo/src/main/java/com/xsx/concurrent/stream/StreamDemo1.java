/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:夏世雄
 * @Date: 2020/12/14 16:01
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 * 题目要求：一分钟完成此题，只能用一行代码实现！
 *   现有5个用户，筛选：
 * ①ID必须是偶数；
 * ②年龄必须大于23岁；
 * ③用户名转为大写；
 * ④用户名字母倒着排序；
 * ⑤只输出一个用户
 *
 **/
public class StreamDemo1 {

    public static void main(String[] args) {
        User user1 = new User(1,"a",21);
        User user2 = new User(2,"b",22);
        User user3 = new User(3,"s",23);
        User user4 = new User(4,"d",24);
        User user5 = new User(6,"e",25);
        List<User> users  = Arrays.asList(user1,user2,user3,user4,user5);

        System.out.println("================第一步===============");
        users.stream().filter((x)->{return x.getId() % 2 == 0;}).forEach(u->{
            System.out.println(u);
        });

        System.out.println("================第二步===============");
        users.stream().filter((x)->{return x.getAge()>23;}).forEach(System.out::println);

        System.out.println("================第三步===============");
        users.stream().map((u)->{return u.getName().toUpperCase();}).forEach(System.out::println);

        System.out.println("================第四步===============");
        users.stream().sorted(Comparator.comparing(User::getId).reversed()).forEach(System.out::println);

        System.out.println("================第五步===============");
        users.stream().sorted(Comparator.comparing(User::getId).reversed()).limit(1).forEach(System.out::println);

        System.out.println("================第六步，合并===============");

        //lambda表达式、链式编程、流式计算、函数式编程
        users.stream().filter((x)->{return x.getId() % 2 == 0;})
                .filter((y)->{return y.getAge() > 23;})
                .map(z->{return z.getName().toUpperCase();})
                .sorted((u1,u2)->{return u2.compareTo(u1);})
                //.sorted(Comparator.comparing(User::getId).reversed())
                .limit(1)
                .forEach(System.out::println);

    }
}



