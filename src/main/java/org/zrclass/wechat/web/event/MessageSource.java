package org.zrclass.wechat.web.event;

import org.zrclass.wechat.common.constant.EventType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 事件源处理类
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/10/25 - 10:57
 * <p>
 * Description:
 */
public class MessageSource implements EventSource {
    /**
     * 事件处理器集合
     */
    private List<EventListener> listeners = new ArrayList<>();
    /**
     * 处理参数
     */
    private Map<String, Object> param;
    /**
     * 监听器执行返回的的结果
     */
    private String message;

    @Override
    public void addListener(EventListener eventListener) {
        listeners.add(eventListener);
    }

    @Override
    public void notifyListener() {
        for (EventListener listener : listeners) {
            //获取参数中对应的事件类型
            String eventType = param.get("Event").toString();
            //获取对应类型的处理器
            if (EventType.eventType(eventType) == listener.getEvent()) {
                MessageEvent event = new MessageEvent();
                event.setParam(param);
                event.setSource(this);
                message = listener.handleEvent(event);
            }
        }
    }

    public String setParam(Map<String, Object> param) {
        this.param = param;
        notifyListener();
        return message;
    }
}