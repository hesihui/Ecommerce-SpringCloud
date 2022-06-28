package com.ecommerce.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1> 用户名和密码</h1>
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsernameAndPassword {
    private String username;
    private String password;
}
