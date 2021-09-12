package com.hzf.demo.common.validation;

import com.hzf.demo.utils.MessageUtils;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhuofan.han
 * @date 2021/9/8
 */
@Component
public class ValidatorHelper {
    /**
     * 线程安全
     */
    private static Validator validator;

    public ValidatorHelper(Validator validator) {
        ValidatorHelper.validator = validator;
    }

    public static void valid(Object object, Class<?>... groups) {
        List<String> validationResult = validate(object, groups);
        if (!validationResult.isEmpty()) {
            throw new IllegalArgumentException(validationResult.toString());
        }
    }

    private static List<String> validate(Object object, Class<?>[] groups) {
        return validator.validate(object, groups).stream()
            .map(v -> parseProperty(v.getPropertyPath().toString(), v.getInvalidValue(), v.getMessage()))
            .collect(Collectors.toList());
    }

    public static String parseProperty(String field, Object rejectedValue, String message) {
        return MessageUtils.get("validator.tip.property", new Object[] {field, rejectedValue, message});
    }
}
