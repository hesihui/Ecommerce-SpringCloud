package com.ecommerce.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  <h1>通用响应对象定义 common response obj</h1>
 *  {
 *      "code": 0,
 *      "message": "",
 *      "data: {}
 *  }
 *  In our e-commerce system, all the responses are used common response obj
 * */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {
    /** Error code*/
    private Integer code;

    /** Error message*/
    private String message;

    /** Generic type response data*/
    private T data;

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
