package org.zrclass.wechat.web.event;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/10/25 - 10:53
 * <p>
 * Description:
 */
public class MessageEvent implements Event {

    /**
     * 事件源
     */
    private Object source;
    /**
     * 事件处理参数
     */
    private Map<String, Object> param;

    @Override
    public Object getSource() {
        return source;
    }

    @Override
    public Map<String, Object> getMessage() {
        return param;
    }

    @Override
    public String callback(String message) {
        return message;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }
}