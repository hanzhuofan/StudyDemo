package com.hzf.demo.common;

/**
 * @author zhuofan.han
 * @date 2021/9/4
 */
public enum ResultEnum {
    /**
     * Custom return result: msg is i18n key
     */
    OK(0, "result.success"),
    BAD(500, "result.fail"),
    NO_RIGHT(1001,"login.not.right");
    private final Integer code;
    private final String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
