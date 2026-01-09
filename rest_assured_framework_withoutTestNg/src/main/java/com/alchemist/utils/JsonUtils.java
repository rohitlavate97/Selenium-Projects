package com.alchemist.utils;

import io.restassured.path.json.JsonPath;

public class JsonUtils {
    public static String getValue(String response, String key) {
        return new JsonPath(response).getString(key);
    }
}
