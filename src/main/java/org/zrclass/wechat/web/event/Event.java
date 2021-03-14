package org.zrclass.wechat.web.event;

import java.util.Map;

/**
 * 事件接口
 * Created by zhourui on 2020/10/25 10:48
 */
public interface Event {
    /**
     * 获取事件源
     *
     * @return
     */
    Object getSource();

    /**
     * 获取事件处理的数据
     *
     * @return
     */
    Map<String, Object> getMessage();

    /**
     * 执行回调
     *
     * @param message 回调消息
     * @return
     */
    String callback(String message);

}
