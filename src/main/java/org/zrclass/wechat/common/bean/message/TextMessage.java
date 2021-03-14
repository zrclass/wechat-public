package org.zrclass.wechat.common.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import org.zrclass.wechat.common.util.XmlUtils;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/10/17 - 10:01
 * <p>
 * Description:
 */
@Data
@XStreamAlias("xml")
public class TextMessage extends BaseMessage {

    @XStreamAlias("Content")
    private String content;

    public static TextMessage of(Map<String, Object> objectMap, String content) {
        TextMessage textMessage = new TextMessage();
        textMessage.init(objectMap);
        textMessage.setContent(content);
        return textMessage;
    }

    public static TextMessage ofSendMsg(Map<String, Object> objectMap, String content) {
        TextMessage textMessage = new TextMessage();
        textMessage.init(objectMap);
        textMessage.setContent(content);
        textMessage.setMsgType("text");
        String from = textMessage.getFromUserName();
        textMessage.setFromUserName(textMessage.getToUserName());
        textMessage.setToUserName(from);
        return textMessage;
    }

    public String toXml() {
        return XmlUtils.beanToXml(this, TextMessage.class);
    }
}