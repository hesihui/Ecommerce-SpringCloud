package com.ecommerce.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1> 登陆用户信息</h1>
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserInfo {

    /**
     * user id
     * */
    private Long id;

    /**
     * 用户名
     * */
    private String username;
}
