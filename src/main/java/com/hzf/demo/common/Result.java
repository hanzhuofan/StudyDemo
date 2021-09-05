package com.hzf.demo.common;

import com.hzf.demo.utils.MessageUtils;
import lombok.*;

import java.io.Serializable;

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
        return of(ResultEnum.OK, data);
    }

    public static <T> Result<T> bad() {
        return of(ResultEnum.BAD);
    }

    public static <T> Result<T> bad(String msg) {
        return bad(ResultEnum.BAD.getCode(), msg);
    }

    public static <T> Result<T> bad(Integer code, String msg) {
        return new Result<>(code, MessageUtils.get(msg));
    }

    public static <T> Result<T> of(ResultEnum resultEnum) {
        return of(resultEnum, null);
    }

    public static <T> Result<T> of(ResultEnum resultEnum, T data) {
        return new Result<>(resultEnum.getCode(), MessageUtils.get(resultEnum.getMsg()), data);
    }
}
