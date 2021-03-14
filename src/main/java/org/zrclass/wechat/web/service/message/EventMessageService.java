package org.zrclass.wechat.web.service.message;

import org.springframework.stereotype.Service;
import org.zrclass.wechat.web.event.MessageSource;
import org.zrclass.wechat.web.event.listener.ClickListener;

import java.util.Map;

/**
 * 事件消息处理service类
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/10/25 - 10:46
 * <p>
 * Description:
 */
@Service
public class EventMessageService implements IMessage {
    @Override
    public String handler(Map<String, Object> param) {
        MessageSource source = new MessageSource();
        //其他事件进行add处理即可
        source.addListener(new ClickListener());
        return source.setParam(param);
    }
}