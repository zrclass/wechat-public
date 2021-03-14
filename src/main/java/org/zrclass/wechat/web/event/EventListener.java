package org.zrclass.wechat.web.event;

import org.zrclass.wechat.common.constant.EventType;

/**
 * 事件监听接口
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/10/25 - 10:48
 * <p>
 * Description:
 */
public interface EventListener {

    /**
     * 事件处理方法
     *
     * @param event 事件
     * @return
     */
    String handleEvent(Event event);

    /**
     * 获取事件类型
     *
     * @return
     */
    EventType getEvent();

}