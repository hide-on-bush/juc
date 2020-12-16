/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.xsx.springcloud.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:夏世雄
 * @Date: 2020/12/9 16:35
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity<T>  {

    private String message;
    private Integer code;
    private T data;

    public ResponseEntity(String message,Integer code){
        this(message,code,null);
    }


}
