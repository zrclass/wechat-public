package org.zrclass.wechat.common.constant;

/**
 * 消息类型枚举
 * Created by zhourui on 2020/10/18 19:34
 */
public enum MessageType {
    /**
     * 文本类型
     */
    TEXT,
    /**
     * 图片类消息
     */
    IMAGE,
    /**
     * 语音消息
     */
    VOICE,
    /**
     * 视频消息
     */
    VIDEO,
    /**
     * 小视频消息
     */
    SHORTVIDEO,
    /**
     * 连接消息
     */
    LINK,
    /**
     * 事件消息
     */
    EVENT,
    /**
     * 位置消息
     */
    LOCATION,
    /**
     * 图文列表
     */
    NEWS;

    /**
     * 根据类型名称获取类型
     *
     * @param type 类型名称
     * @return 枚举类型
     */
    public static MessageType getType(String type) {
        for (MessageType item : MessageType.values()) {
            if (item.name().toLowerCase().equals(type.toLowerCase())) {
                return item;
            }
        }
        return TEXT;
    }
}
