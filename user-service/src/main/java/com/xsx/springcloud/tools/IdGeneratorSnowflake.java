/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.springcloud.tools;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author:夏世雄
 * @Date: 2020/12/8 17:29
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 雪花算法
 **/
@Slf4j
@Component
public class IdGeneratorSnowflake {

    private Long workerId = 0L;
    private Long datacenterId =1L;

    private Snowflake snowflake = IdUtil.createSnowflake(workerId,datacenterId);

    @PostConstruct
    public void init(){
        try{
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前服务器的workerId：{}",workerId);
        } catch (Exception e) {
            log.warn("当前服务器的workerId获取失败",e);
            workerId =(long) NetUtil.getLocalhostStr().hashCode();
        }

    }

    public synchronized long snowflake(){
        return snowflake.nextId();
    }

    public synchronized long snowflake(Long workerId,Long datacenterId){
        snowflake = IdUtil.createSnowflake(workerId,datacenterId);
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        IdGeneratorSnowflake idGeneratorSnowflake = new IdGeneratorSnowflake();
        System.out.println(idGeneratorSnowflake.snowflake());
    }
}
