package org.zrclass.wechat.web.service.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zrclass.wechat.common.bean.message.TextMessage;
import org.zrclass.wechat.common.config.WeChatConfig;
import org.zrclass.wechat.common.constant.WeChatConstant;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/10/18 - 19:29
 * <p>
 * Description:
 */
@Service
@Slf4j
public class TextMessageService implements IMessage {

    private final WeChatConfig weChatConfig;

    public TextMessageService(WeChatConfig weChatConfig) {
        this.weChatConfig = weChatConfig;
    }


    @Override
    public String handler(Map<String, Object> stringObjectMap) {
        String content = "嗨咯你好呀!";
        if (stringObjectMap.get(WeChatConstant.CONTENT).toString().equals("登录")) {
            content = WeChatConstant.OAUTH2_AUTHORIZE.replace("APPID", weChatConfig.getAppId())
                    .replace("REDIRECT_URI", "http://192.168.31.123:8080/v1/weChar/getCode").replace("SCOPE", "snsapi_userinfo");
            System.out.println(content);
            content = "您好，请点击<a href='" + content + "'>登录</a>进行更多的操作。";
        }
        TextMessage textMessage = TextMessage.ofSendMsg(stringObjectMap, content);
        return textMessage.toXml();
    }

}