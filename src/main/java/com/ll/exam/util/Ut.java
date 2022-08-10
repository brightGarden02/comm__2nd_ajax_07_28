package com.ll.exam.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.exam.article.dto.ArticleDto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Ut {


    public static Map<String, Object> mapOf(Object... args) {
        int dataSize = args.length / 2;

        Map<String, Object> map = new LinkedHashMap<>();

        for(int i = 0; i < dataSize; i++){
            int keyIndex = i * 2 + 0;
            int valueIndex = i * 2 + 1;

            String key = (String)args[keyIndex];
            Object value = args[valueIndex];

            map.put(key, value);
        }

        return map;

    }

    public static class json {

        private static ObjectMapper om;

        static {
            om = new ObjectMapper();
        }

        public static String toStr(Object obj, String defaultValue){

            try {
                return om.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }

        public static <T> T toObj(String jsonStr, Class<T> cls, T defaultValue) {

            try {
                return om.readValue(jsonStr, cls);
            } catch (JsonProcessingException e){
                return defaultValue;
            }
        }


        public static <T> T toObj(String jsonStr, TypeReference<T> typeReference, T defaultValue) {
            try {
                return om.readValue(jsonStr, typeReference);
            } catch (JsonProcessingException e){
                return defaultValue;
            }
        }

    }
}
