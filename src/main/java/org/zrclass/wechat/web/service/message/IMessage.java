package org.zrclass.wechat.web.service.message;

import java.util.Map;

/**
 * 消息处理接口
 * Created by zhourui on 2020/10/18 19:38
 */
public interface IMessage {
    /**
     * 消息处理接口
     *
     * @param param 微信公众号推送的消息数据
     * @return 响应数据
     */
    String handler(Map<String, Object> param);

}
