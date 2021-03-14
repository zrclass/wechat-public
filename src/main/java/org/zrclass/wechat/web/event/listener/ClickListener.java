package org.zrclass.wechat.web.event.listener;

import org.zrclass.wechat.common.bean.message.TextMessage;
import org.zrclass.wechat.common.constant.EventType;
import org.zrclass.wechat.web.event.Event;
import org.zrclass.wechat.web.event.EventListener;

import java.util.Map;

/**
 * 点击事件处理类
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/10/25 - 11:06
 * <p>
 * Description:
 */
public class ClickListener implements EventListener {
    @Override
    public String handleEvent(Event event) {
        Map<String, Object> message = event.getMessage();
        String eventType = message.get("Event").toString();
        if (EventType.eventType(eventType) == EventType.CLICK) {
            return TextMessage.ofSendMsg(message, "执行了点击事件处理类").toXml();
        }
        return "success";
    }

    @Override
    public EventType getEvent() {
        return EventType.CLICK;
    }
}