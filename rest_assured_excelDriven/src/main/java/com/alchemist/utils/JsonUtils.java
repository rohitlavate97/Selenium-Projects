package com.alchemist.utils;

import io.restassured.path.json.JsonPath;

public class JsonUtils {
    private JsonUtils(){}

    public static String getValueAsString(String json, String key) {
        return new JsonPath(json).getString(key);
    }
}
