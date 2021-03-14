package org.zrclass.wechat.web.event;

/**
 * 事件源接口
 * Created by zhourui on 2020/10/25 10:51
 */
public interface EventSource {
    /**
     * 增加事件监听器
     *
     * @param eventListener 事件监听
     */
    void addListener(EventListener eventListener);

    /**
     * 通知事件监听器
     */
    void notifyListener();
}
