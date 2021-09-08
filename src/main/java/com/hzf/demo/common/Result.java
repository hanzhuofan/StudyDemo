package com.hzf.demo.common;

import java.io.Serializable;

import com.hzf.demo.utils.MessageUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author zhuofan.han
 * @date 2021/9/4
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    @NonNull
    private Integer code;

    @NonNull
    private String msg;

    private T data;

    public static <T> Result<T> ok() {
        return of(ResultEnum.OK);
    }

    public static <T> Result<T> ok(T data) {
        return of(data, ResultEnum.OK);
    }

    public static <T> Result<T> of() {
        return of(ResultEnum.BAD);
    }

    public static <T> Result<T> of(String msg) {
        return of(ResultEnum.BAD.getCode(), msg);
    }

    public static <T> Result<T> of(Integer code, String msg) {
        return of(code, msg, new Object[0]);
    }

    public static <T> Result<T> of(String msg, Object[] args) {
        return of(ResultEnum.BAD.getCode(), msg, args);
    }

    public static <T> Result<T> of(Integer code, String msg, Object[] args) {
        return new Result<>(code, MessageUtils.get(msg, args));
    }

    public static <T> Result<T> of(T data, Integer code, String msg) {
        return of(data, code, msg, new Object[0]);
    }

    public static <T> Result<T> of(T data, Integer code, String msg, Object[] args) {
        return new Result<>(code, MessageUtils.get(msg, args), data);
    }

    public static <T> Result<T> of(ResultEnum resultEnum) {
        return of(null, resultEnum);
    }

    public static <T> Result<T> of(T data, ResultEnum resultEnum) {
        return of(data, resultEnum, new Object[0]);
    }

    public static <T> Result<T> of(ResultEnum resultEnum, Object[] args) {
        return of(null, resultEnum, args);
    }

    public static <T> Result<T> of(T data, ResultEnum resultEnum, Object[] args) {
        return of(data, resultEnum.getCode(), resultEnum.getMsg(), args);
    }
}
