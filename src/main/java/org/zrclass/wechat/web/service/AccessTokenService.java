package org.zrclass.wechat.web.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zrclass.wechat.common.bean.AccessTokenBean;
import org.zrclass.wechat.common.config.WeChatConfig;
import org.zrclass.wechat.common.util.HttpClientUtils;

import java.io.IOException;

/**
 * 获取access token service服务
 *
 * @auther: zhourui
 * @blame: zhourui
 * @date: 2020-10-11 10:36
 * @Description:
 */
@Service
public class AccessTokenService {
    @Autowired
    private WeChatConfig chatConfig;
    /**
     * access token 对象
     */
    private AccessTokenBean accessTokenBean;

    private static final String URL = "https://api.weixin.qq.com/cgi-bin/token";

    /**
     * 获取access token
     *
     * @param appId
     * @param appSecret
     */
    private void refreshAccessToken(String appId, String appSecret) throws IOException {
        String s = HttpClientUtils.get(URL, AccessTokenBean.requestOf(appId, appSecret));
        JSONObject jsonObject = JSONObject.parseObject(s);
        long expiresIn = jsonObject.getLong("expires_in");
        String accessToken = jsonObject.getString("access_token");
        accessTokenBean = AccessTokenBean.responseOf(accessToken, expiresIn);
    }

    /**
     * 获取access token
     *
     * @return
     */
    public String getAccessToken() throws IOException {
        if (accessTokenBean == null || accessTokenBean.isExpired()) {
            refreshAccessToken(chatConfig.getAppId(), chatConfig.getAppSecret());
        }
        return accessTokenBean.getAccessToken();
    }

}
