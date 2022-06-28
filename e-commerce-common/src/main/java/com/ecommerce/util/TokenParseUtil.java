package com.ecommerce.util;

import com.alibaba.fastjson.JSON;
import com.ecommerce.constant.CommonConstant;
import com.ecommerce.vo.LoginUserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;

/**
 * <h1> JWT token 解析工具类</h1>
 * */
public class TokenParseUtil {
    /**
     * <h2> 从 JWT Token中解析 LoginUserInfo 对象</h2>
     * */
    public static LoginUserInfo parseUserInfoFromToken(String token) throws Exception {
        if (null == token) {
            return null;
        }
        Jws<Claims> claimsJws = parseToken(token, getPublicKey());
        Claims body = claimsJws.getBody();

        // 比对claim body中的时间
        // 如果token 已经过期了，返回null： 如果expiration time在当前时间之前，则已过期
        if (body.getExpiration().before(Calendar.getInstance().getTime())) {
            // 返回 Token 中保存的用户信息
            return null;
        }

        return JSON.parseObject(
                body.get(CommonConstant.JWT_USER_INFO_KEY).toString(),
                LoginUserInfo.class
        );
    }

    /**
     * <h2> 通过public key去解析 JWT token</h2>
     * */
    private static Jws<Claims> parseToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    /**
     * <h2> 根据本地存储的public key获取到 PublicKey object</h2>
     * */
    private static PublicKey getPublicKey() throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
          new BASE64Decoder().decodeBuffer(CommonConstant.PUBLIC_KEY)
        );
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }
}
