
package org.zrclass.wechat.common.domain;


import com.alibaba.fastjson.JSONObject;

/**
 * 异常编码 0成功、-1熔断、 -2 标准参数校验不通过 -3会话超时
 * 前两位:服务标识
 * 中间两位:模块标识
 * 后两位:异常标识
 *
 * @blame Android Team
 */
public enum CommonErrorCode implements ErrorCode {

    /**
     * 系统繁忙错误码
     */
    CODE_ERROR(500, "错误码未定义", SYSTEM_CODE),
    /**
     * 透传的枚举，对于底层存在异常的情况，如果不太好疏捋，可以使用此 CODE 来透传到上层 code 由上层的参数传递 code + message
     */
    THROW(501, "%s%s", SYSTEM_CODE),
    /**
     * 参数 空检查
     */
    ARGUMENT_NONEMPTY(502, "%s 参数不能为空", SYSTEM_CODE),
    /**
     * 参数 null
     */
    ARGUMENT_NONNULL(502, "%s 参数不能为null", SYSTEM_CODE),
    /**
     * 参数小于特定值
     */
    ARGUMENT_NONLT(503, "%s 参数值[%s]不能大于%s", SYSTEM_CODE),
    /**
     * 参数小于等于特定值
     */
    ARGUMENT_NONLE(504, "%s 参数值[%s]不能大于等于%s", SYSTEM_CODE),
    /**
     * 参数大于特定值
     */
    ARGUMENT_NONGT(505, "%s 参数值[%s]不能小于%s", SYSTEM_CODE),
    /**
     * 参数大于等于特定值
     */
    ARGUMENT_NONGE(506, "%s 参数值[%s]不能小于等于%s", SYSTEM_CODE),
    /**
     * 数值不在范围内
     */
    ARGUMENT_NONRANGE(507, "%s 参数值[%s]不在%s范围内", SYSTEM_CODE),
    /**
     * 参数是否存在于数组能
     */
    ARGUMENT_NONIN(508, " %s 参数值[%s]必须在[%s]中选择", SYSTEM_CODE),
    /**
     * 参数格式错误
     */
    ARGUMENT_FORMAT_ERROR(509, " %s 参数值[%s]格式错误，正确格式[%s]", SYSTEM_CODE),
    /**
     * 参数的数据类型错误
     */
    ARGUMENT_CAST_ERROR(510, "%s 参数[%s]不能CAST[%s]", SYSTEM_CODE),

    /**
     * 资源未找到
     */
    RESOURCE_NOT_FOUND(511, "[%s]资源未找到", SYSTEM_CODE),
    /**
     * 未知异常，必须传一个异常描述传，最好把异常栈也传递
     */
    UNKNOWN(999, "%s", SYSTEM_CODE),

    SYSTEM_BUSY(-1, "系统繁忙，此时请开发者稍候再试", "access_token"),

    SUCCESS(0, "请求成功", "access_token"),

    APP_SECRET_ERROR(40001, "AppSecret错误或者AppSecret不属于这个公众号，请开发者确认AppSecret的正确性", "access_token"),

    GRANT_TYPE_ERROR(40002, "请确保grant_type字段值为client_credential", "access_token"),

    APP_ID_ERROR(40013, "无效的appId", "access_token"),

    IP_WHITE(40164, "调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置", "access_token"),

    IP_ERROR(89503, "此IP调用需要管理员确认,请联系管理员", "access_token"),

    IP_ERROR_24(89506, "24小时内该IP被管理员拒绝调用两次，24小时内不可再使用该IP调用", "access_token"),

    IP_ERROR_1(89507, "1小时内该IP被管理员拒绝调用一次，1小时内不可再使用该IP调用", "access_token"),
    ;


    private int code;
    private String desc;
    private String module;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }


    private CommonErrorCode(int code, String desc, String module) {
        this.code = code;
        this.desc = desc;
        this.module = module;
    }


    public static CommonErrorCode setErrorCode(int code) {
        for (CommonErrorCode errorCode : CommonErrorCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }
        return null;
    }

    public static ErrorCode getStatus(int code) {
        for (CommonErrorCode item : CommonErrorCode.values()) {
            if (item.code == code) {
                return item;
            }
        }
        return CODE_ERROR;
    }

    private String toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", this.desc);
        jsonObject.put("code", this.code);
        jsonObject.put("module", this.module);
        return jsonObject.toJSONString();
    }

    public String getMessage(int code) {
        for (CommonErrorCode item : CommonErrorCode.values()) {
            if (item.code == code) {
                return item.toJson();
            }
        }
        return CODE_ERROR.toJson();
    }

}
