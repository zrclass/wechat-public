package org.zrclass.wechat.common.constant;

/**
 * 事件类型枚举
 * Created by zhourui on 2020/10/25 10:45
 */
public enum EventType {
    /**
     * 关注
     */
    SUBSCRIBE,
    /**
     * 取消关注
     */
    UNSUBSCRIBE,
    /**
     * 用户已关注时的事件推送
     */
    SCAN,
    /**
     * 上报地理位置事件
     */
    LOCATION,
    /**
     * 自定义菜单事件(点击菜单拉取消息时的事件推送)
     */
    CLICK,
    /**
     * 点击菜单跳转链接时的事件推送
     */
    VIEW,
    /**
     * 推送模版消息
     */
    TEMPLATESENDJOBFINISH;

    /**
     * 根据名称获取事件类型枚举对象
     *
     * @param eventType 事件名称
     * @return
     */
    public static EventType eventType(String eventType) {
        for (EventType type : EventType.values()) {
            if (type.name().toLowerCase().equals(eventType.toLowerCase())) {
                return type;
            }
        }
        return CLICK;
    }
}
