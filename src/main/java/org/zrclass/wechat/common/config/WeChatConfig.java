package org.zrclass.wechat.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @auther: zhourui
 * @blame: zhourui
 * @date: 2020-10-11 10:37
 * @Description:
 */
@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WeChatConfig {
    /**
     * 用户唯一标识
     */
    private String appId;
    /**
     * 用户密钥
     */
    private String appSecret;
    /**
     * 加密token
     */
    private String token;

    /**
     * 监听队列
     */
    private String weChatQueue;
    /**
     * 交换机
     */
    private String weChatExchange;
    /**
     * 绑定的标示
     */
    private String directKey;
}
