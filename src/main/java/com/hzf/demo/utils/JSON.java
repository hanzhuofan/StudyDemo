package com.hzf.demo.utils;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Component
public class JSON {
    private static ObjectMapper mapper;

    public JSON(ObjectMapper mapper) {
        JSON.mapper = mapper;
    }

    public static String toJSONString(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static <T> T parseObject(String json, Class<T> cls) throws IOException {
        return mapper.readValue(json, cls);
    }
}
