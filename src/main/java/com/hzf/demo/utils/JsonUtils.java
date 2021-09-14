package com.hzf.demo.utils;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Component
public class JsonUtils {
    private static ObjectMapper mapper;

    public JsonUtils(ObjectMapper mapper) {
        JsonUtils.mapper = mapper;
    }

    public static String toJsonString(Object object) throws IOException {
        return mapper.writeValueAsString(object);
    }

    public static <T> T parseObject(String json, Class<T> cls) throws IOException {
        return mapper.readValue(json, cls);
    }
}
