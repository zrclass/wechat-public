package org.zrclass.wechat.common.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.io.Serializable;

/**
 * 图文消息
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/10/18 - 19:58
 * <p>
 * Description:
 */
@Data
@XStreamAlias("item")
public class Articles implements Serializable {
    /**
     * 图文消息标题
     */
    @XStreamAlias("Title")
    private String title;
    /**
     * 图文消息内容
     */
    @XStreamAlias("Description")
    private String description;
    /**
     * 图片URL
     */
    @XStreamAlias("PicUrl")
    private String picUrl;
    /**
     * 点击消息跳转URL
     */
    @XStreamAlias("Url")
    private String url;

}