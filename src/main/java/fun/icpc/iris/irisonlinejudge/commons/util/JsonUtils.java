package fun.icpc.iris.irisonlinejudge.commons.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

    private static final Gson gson = new GsonBuilder().create();

    // 将对象转换为 JSON 字符串
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    // 将 JSON 字符串转换为对象
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }
}
