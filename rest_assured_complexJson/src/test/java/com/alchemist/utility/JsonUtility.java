package com.alchemist.utility;

import io.restassured.path.json.JsonPath;

public class JsonUtility {
	public static String getValueAsString(String response, String key) {
        return new JsonPath(response).getString(key);
    }
	
	public static Integer getValueAsInteger(String response, String key) {
        return new JsonPath(response).getInt(key);
    }
}
