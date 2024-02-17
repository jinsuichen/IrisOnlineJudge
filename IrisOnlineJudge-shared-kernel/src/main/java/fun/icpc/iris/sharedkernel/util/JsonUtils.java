package fun.icpc.iris.sharedkernel.util;


import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class JsonUtils {

    private static final String LOCAL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSSSS";

    public static String toJson(Object object) {
        if (Objects.isNull(object)) {
            return "";
        }
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                    @Override
                    public JsonElement serialize(
                            LocalDateTime localDateTime,
                            Type type,
                            JsonSerializationContext jsonSerializationContext) {
                        return new JsonPrimitive(localDateTime.format(
                                DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT)));
                    }
                })
                .serializeNulls()
                .create();
        return gson.toJson(object);
    }

    public static String toJson(Object object, Type typeOfSrc) {
        if (Objects.isNull(object)) {
            return "";
        }
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                    @Override
                    public JsonElement serialize(
                            LocalDateTime localDateTime,
                            Type type,
                            JsonSerializationContext jsonSerializationContext) {
                        return new JsonPrimitive(localDateTime.format(
                                DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT)));
                    }
                })
                .serializeNulls()
                .create();
        return gson.toJson(object, typeOfSrc);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer() {
                    @Override
                    public LocalDateTime deserialize(
                            JsonElement json,
                            Type typeOfT,
                            JsonDeserializationContext context) throws JsonParseException {
                        return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(),
                                DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT));
                    }
                })
                .create();
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer() {
                    @Override
                    public LocalDateTime deserialize(
                            JsonElement json,
                            Type typeOfT,
                            JsonDeserializationContext context) throws JsonParseException {
                        return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(),
                                DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT));
                    }
                })
                .create();
        return gson.fromJson(json, typeOfT);
    }
}


