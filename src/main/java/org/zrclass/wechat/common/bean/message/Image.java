package org.zrclass.wechat.common.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;

import java.io.Serializable;

/**
 * 图片消息对象
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/10/18 - 19:15
 * <p>
 * Description:
 */
@Getter
@XStreamAlias("Image")
public class Image implements Serializable {
    @XStreamAlias("MediaId")
    private String mediaId;

    public Image(String mediaId) {
        this.mediaId = mediaId;
    }

    public Image() {

    }

    /**
     * OF 模式
     *
     * @param mediaId 素材ID
     * @return
     */
    public static Image of(String mediaId) {
        return new Image(mediaId);
    }
}