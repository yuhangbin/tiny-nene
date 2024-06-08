package com.cboy.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

@Slf4j
public class JsonUtils {

    static ObjectMapper objectMapper = new ObjectMapper();
    // TODO
    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    }

    public static <T> String toJson(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Throwable e) {
            log.error("[JsonUtils:toJson] e:{}", ExceptionUtils.getStackTrace(e));
            return "";
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Throwable e) {
            log.error("[JsonUtils:fromJson] typeReference e:{}", ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Throwable e) {
            log.error("[JsonUtils:fromJson] clazz e:{}", ExceptionUtils.getStackTrace(e));
            return null;
        }
    }
}
