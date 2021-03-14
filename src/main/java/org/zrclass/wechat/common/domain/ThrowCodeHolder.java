package org.zrclass.wechat.common.domain;

/**
 * 用于处理穿透类异常代理类
 *
 * @date 2020/3/31
 * @copyright 北京数字认证股份有限公司 (C) 2020
 * @blame Android Team
 * @since 1.0
 */
public class ThrowCodeHolder implements ErrorCode {

    /**
     * 异常码
     */
    private int errorCode;

    /**
     * 消息
     */
    private String message;


    /**
     * 构造一个 holder
     */
    public ThrowCodeHolder(Object... args) {
        assert null != args;
        this.errorCode = (Integer) args[0];
        this.message = String.valueOf(args[1]);
    }

    @Override
    public int getCode() {
        return errorCode;
    }

    @Override
    public String getDesc() {
        return message;
    }

}
