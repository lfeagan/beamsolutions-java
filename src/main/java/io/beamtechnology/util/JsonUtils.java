package io.beamtechnology.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.util.Locale;
import java.util.TimeZone;

public class JsonUtils {

    public static String toJson(Object object) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//    om.setDateFormat(new StdDateFormat().withTimeZone(TimeZone.getTimeZone("Z")));
        om.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        String json = om.writeValueAsString(object);
        return json;
    }

    public static <T> T fromJson(Class<T> clazz, String json) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T instance = (T) om.readValue(json, clazz);
        return instance;
    }


}
