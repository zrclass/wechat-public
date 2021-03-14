package org.zrclass.wechat.common.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import org.zrclass.wechat.common.constant.WeChatConstant;

import java.io.Serializable;
import java.util.Map;

/**
 * 公共消息参数处理类
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/10/17 - 09:56
 * <p>
 * Description:
 */
@Data
public class BaseMessage implements Serializable {
    /**
     * 消息ID 64位整型
     */
    @XStreamAlias("MsgId")
    private String msgId;
    /**
     * 开发者微信号
     */
    @XStreamAlias("ToUserName")
    private String toUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    @XStreamAlias("FromUserName")
    private String fromUserName;
    /**
     * 消息创建时间 （整型）
     */
    @XStreamAlias("CreateTime")
    private String createTime;
    /**
     * 消息类型，文本为text
     */
    @XStreamAlias("MsgType")
    private String msgType;

    void init(Map<String, Object> map) {
        this.createTime = String.valueOf(map.get(WeChatConstant.CREATE_TIME));
        this.fromUserName = String.valueOf(map.get(WeChatConstant.FROM_USER_NAME));
        this.toUserName = String.valueOf(map.get(WeChatConstant.TO_USER_NAME));
        this.msgType = String.valueOf(map.get(WeChatConstant.MSG_TYPE));
        this.msgId = String.valueOf(map.get(WeChatConstant.MSG_ID));
    }
}