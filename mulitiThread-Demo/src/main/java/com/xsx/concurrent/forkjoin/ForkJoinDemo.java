/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.concurrent.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @Author:夏世雄
 * @Date: 2020/12/14 17:07
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 求和计算的任务
 *1.fork join
 *   如何使用fork join？
 *   ①forkjoinPool通过它来执行
 *   ②计算任务forkjoinPool.execute(ForkJoinTask task);
 *   ③计算类继承RecursiveTask，重写compute()
 *   ForkJoinTask 的实现类有：RecursiveTask 递归任务，有返回值； RecursiveAction递归事件，没有返回值
 *
 *
 * stream 并行流 更优方案
 **/
public class ForkJoinDemo extends RecursiveTask<Long> {

    /**
     * 开始值
     */
    private Long start;

    /**
     * 结束值
     */
    private Long end;

    /**
     * 临界值
     */
    private Long temp = 1000000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }




    /**
     *
     *
     * 计算方法
     */
    @Override
    protected Long compute() {

        if (end - start > temp){
            //大于临界值，使用fork join 分支合并计算
            //中间值
            long middle = (start + end) / 2;
            ForkJoinDemo task1 = new ForkJoinDemo(start,middle);
            //拆分任务end,把任务押入线程队列
            task1.fork();
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1L,end);
            task2.fork();
            return task1.join() + task2.join();
        } else {
            long sum =0L;
            for(long i = start;i <= end; i++){
                sum += i;
            }
            return sum;
        }

    }
}
