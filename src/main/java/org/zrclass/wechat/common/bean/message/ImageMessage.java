package org.zrclass.wechat.common.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import org.zrclass.wechat.common.util.XmlUtils;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/10/18 - 19:12
 * <p>
 * Description:
 */
@Data
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage {
    /**
     * 图片链接
     */
    @XStreamAlias("PicUrl")
    private String picUrl;
    /**
     * 图片素材ID
     */
    @XStreamAlias("MediaId")
    private String mediaId;

    @XStreamAlias("Image")
    private Image image;

    public static ImageMessage ofSend(Map<String, Object> param, Image image) {
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.init(param);
        imageMessage.setImage(image);
        imageMessage.setMsgType("image");
        String from = imageMessage.getFromUserName();
        imageMessage.setFromUserName(imageMessage.getToUserName());
        imageMessage.setToUserName(from);
        return imageMessage;
    }

    /**
     * 测试使用，接收什么图片返回什么图片
     *
     * @param param
     * @return
     */
    @Deprecated
    public static ImageMessage ofSend(Map<String, Object> param) {
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.init(param);
        imageMessage.setImage(Image.of(param.get("MediaId").toString()));
        imageMessage.setMsgType("image");
        String from = imageMessage.getFromUserName();
        imageMessage.setFromUserName(imageMessage.getToUserName());
        imageMessage.setToUserName(from);
        return imageMessage;
    }


    /**
     * 对象转XML
     *
     * @return
     */
    public String toXml() {
        return XmlUtils.beanToXml(this, ImageMessage.class);
    }
}
