package org.zrclass.wechat.common.domain;

/**
 * 通用响应对象
 *
 * @param <T>
 */
public class RestResponse<T> {
    /**
     * 状态码
     */
    private int code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 返回结果信息
     */
    private T result;

    public static <T> RestResponse<T> success() {
        return new RestResponse<T>();
    }

    public static <T> RestResponse<T> success(T result) {
        RestResponse<T> response = new RestResponse<T>();
        response.setResult(result);
        return response;
    }

    public static <T> RestResponse<T> validfail(String msg) {
        RestResponse<T> response = new RestResponse<T>();
        response.setCode(-2);
        response.setMsg(msg);
        return response;
    }

    public static <T> RestResponse<T> of(int code, String msg) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static <T> RestResponse<T> of(ErrorCode errorCode) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode(errorCode.getCode());
        response.setMsg(errorCode.getDesc());
        return response;
    }


    public RestResponse() {
        this(0, "");
    }

    public RestResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RestResponse [code=" + code + ", msg=" + msg + ", result="
                + result + "]";
    }

}
