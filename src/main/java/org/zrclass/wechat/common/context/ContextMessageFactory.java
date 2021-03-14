package org.zrclass.wechat.common.context;

import org.zrclass.wechat.common.constant.MessageType;
import org.zrclass.wechat.web.service.message.IMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息处理工厂类
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/10/18 - 19:42
 * <p>
 * Description:
 */
public class ContextMessageFactory {
    /**
     * 消息处理集合类key:消息类型 value:对应消息类型的处理方法
     */
    private Map<MessageType, IMessage> messageMap = new HashMap<>();

    public Map<MessageType, IMessage> getMessageMap() {
        return messageMap;
    }

    /**
     * setter
     *
     * @param messageMap 消息处理集合
     */
    public void setMessageMap(Map<MessageType, IMessage> messageMap) {
        this.messageMap = messageMap;
    }

    /**
     * builder
     *
     * @param messageType 消息类型
     * @param message     对应消息类型处理类
     */
    public ContextMessageFactory builder(MessageType messageType, IMessage message) {
        this.messageMap.putIfAbsent(messageType, message);
        return this;
    }

    /**
     * 消息处理方式
     *
     * @param messageType 消息类型
     * @param param       处理参数（数据）
     * @return
     */
    public String doAction(MessageType messageType, Map<String, Object> param) {
        if (this.messageMap.get(messageType) != null) {
            return this.messageMap.get(messageType).handler(param);
        }
        return "success";
    }
}