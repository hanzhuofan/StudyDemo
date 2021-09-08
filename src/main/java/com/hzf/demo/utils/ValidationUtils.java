package com.hzf.demo.utils;

import com.hzf.demo.beans.vo.LoginUserVO;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhuofan.han
 * @date 2021/9/8
 */
public class ValidationUtils {
    /**
     * 线程安全
     */
    private static final Validator VALIDATOR;
    static {
        VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static List<String> valid(LoginUserVO loginUserVO) {
        return VALIDATOR.validate(loginUserVO).stream()
            .map(v -> parseProperty(v.getPropertyPath().toString(), v.getInvalidValue(), v.getMessage()))
            .collect(Collectors.toList());
    }

    public static String parseProperty(String field, Object rejectedValue, String message) {
        return MessageUtils.get("validator.tip.property", new Object[] {field, rejectedValue, message});
    }
}
