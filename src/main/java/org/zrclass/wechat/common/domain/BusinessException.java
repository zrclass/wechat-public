

package org.zrclass.wechat.common.domain;


import java.util.Formatter;

/**
 * BusinessException
 *
 * @blame Android Team
 */
public class BusinessException extends RuntimeException {


    private static final long serialVersionUID = 5565760508056698922L;
    /**
     * 异常码
     */
    private ErrorCode errorCode;
    /**
     * 消息参数
     */
    private Object[] args;

    /**
     * 生成一个 sdk 异常对象
     *
     * @param errorCode 异常 code
     * @param args      异常 参数
     * @param cause     异常源
     * @return 异常对象
     */
    public static BusinessException of(ErrorCode errorCode, Object[] args, Throwable cause) {
        assert null != errorCode;
        return new BusinessException(errorCode, args, cause == CAUSE_NOOP ? null : cause);
    }

    /**
     * 构造一个sdk 异常
     *
     * @param errorCode 异常枚举
     * @param cause     异常原因
     * @param args      消息参数
     */
    private BusinessException(ErrorCode errorCode, Object[] args, Throwable cause) {
        super(formatMessage(errorCode, args), cause);
        this.errorCode = errorCode == CommonErrorCode.THROW ? new ThrowCodeHolder(args) : formatCommonErrorCode(errorCode, args);
        this.args = args;
    }


    /**
     * 获取异常参数
     *
     * @return 参数
     */
    public Object[] getArgs() {
        return args;
    }


    /**
     * 生成一个 sdk 异常对象
     *
     * @param errorCode 异常 code
     * @param args      异常 参数
     * @return 异常对象
     */
    public static BusinessException of(ErrorCode errorCode, Object... args) {
        assert null != errorCode;
        return new BusinessException(errorCode, args, null);
    }

    /**
     * 生成一个 sdk 异常对象
     *
     * @param errorCode 异常 code
     * @return 异常对象
     */
    @Deprecated
    public static BusinessException of(ErrorCode errorCode) {
        assert null != errorCode;
        return new BusinessException(errorCode, new String[]{""}, null);
    }


    /**
     * 生成一个 sdk 异常对象
     *
     * @param errorCode 异常 code
     * @param cause     异常源
     * @param args      异常 参数
     * @return 异常对象
     */
    public static BusinessException of(ErrorCode errorCode, Throwable cause, Object... args) {
        assert null != errorCode;
        return new BusinessException(errorCode, args, cause == CAUSE_NOOP ? null : cause);
    }

    /**
     * 通过 code  + message 生成，用于底层穿透场景
     *
     * @param code    异常码
     * @param message 消息
     * @return sdk ex
     */
    public static BusinessException of(String code, String message) {
        assert null != code;
        return new BusinessException(new ThrowCodeHolder(code, message), null, null);
    }

    /**
     * 格式化消息
     *
     * @return
     */
    private static String formatMessage(ErrorCode errorCode, Object[] args) {
        return errorCode == CommonErrorCode.THROW ? String.format("%s%s", args[0], args[1]) : new Formatter().format(errorCode.getDesc(), args).toString();
    }

    /**
     * 格式化消息
     *
     * @return
     */
    private static ErrorCode formatCommonErrorCode(ErrorCode errorCode, Object[] args) {
        return errorCode == CommonErrorCode.THROW ? new ThrowCodeHolder(args[0], args[1]) : new ThrowCodeHolder(errorCode.getCode(), new Formatter().format(errorCode.getDesc(), args).toString());
    }


    /**
     * 空异常，用于cause == null 时参数占位
     */
    public static final Throwable CAUSE_NOOP = new RuntimeException();

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
