package org.zrclass.wechat.web.service.message;

import org.springframework.stereotype.Service;
import org.zrclass.wechat.common.bean.message.ImageMessage;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/10/18 - 19:23
 * <p>
 * Description:
 */
@Service
public class ImageMessageService implements IMessage {
    /**
     * 测试方法
     *
     * @param param 接收到参数
     * @return
     */
    @Override
    public String handler(Map<String, Object> param) {
        return ImageMessage.ofSend(param).toXml();
    }
}