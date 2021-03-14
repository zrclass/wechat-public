package org.zrclass.wechat.common.bean;

import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

/**
 * access token 对象
 *
 * @auther: zhourui
 * @blame: zhourui
 * @date: 2020-10-11 10:07
 * @Description:
 */
@Getter
public class AccessTokenBean implements Serializable {
    /**
     * grant_type 固定值，直接填写client_credential
     */
    private String grant_type = "client_credential";
    /**
     * appID 用户的唯一标识
     */
    private String appid;
    /**
     * secret 用户唯一凭证密钥
     */
    private String secret;
    /**
     * 获取到的access token
     */
    private String accessToken;
    /**
     * access token 过期时间
     */
    private long expiresTime;

    private AccessTokenBean(String appid, String secret, String accessToken, long expiresTime) {
        this.appid = appid;
        this.secret = secret;
        this.accessToken = accessToken;
        if (expiresTime > 0) {
            this.expiresTime = System.currentTimeMillis() + expiresTime * 1000;
        }
    }

    /**
     * 构建获取access token 请求对象
     *
     * @param appId  用户唯一标识
     * @param secret 用户唯一标识密钥
     * @return {@link HashMap}
     */
    public static HashMap<String, String> requestOf(String appId, String secret) {
        HashMap<String, String> request = new HashMap<>(16);
        request.put("appid", appId);
        request.put("secret", secret);
        request.put("grant_type", "client_credential");
        return request;
    }

    /**
     * 构建AccessToken 对象
     *
     * @param accessToken token
     * @param expiresTime 过期时间
     * @return {@link AccessTokenBean}
     */
    public static AccessTokenBean responseOf(String accessToken, long expiresTime) {
        return new AccessTokenBean("", "", accessToken, expiresTime);
    }

    /**
     * 判断token是否已经失效
     *
     * @return 是否失效 false 未失效 true 失效，需要重新申请
     */
    public boolean isExpired() {
        return System.currentTimeMillis() > expiresTime;
    }


}
