package org.zrclass.wechat.common.bean.message.template;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * 模版消息发送对象
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/9/10 - 11:27
 * <p>
 * Description:
 */
@Builder
@Getter
public class TemplateMessage implements Serializable {
    /**
     * 接收者openid
     */
    private final String touser;
    /**
     * 模版ID
     */
    private final String template_id;
    /**
     * 跳转URL
     */
    private final String url;
    /**
     * 模板数据
     */
    private final TemplateDate data;


    public String toJson() {
        return JSON.toJSONString(this);
    }

}