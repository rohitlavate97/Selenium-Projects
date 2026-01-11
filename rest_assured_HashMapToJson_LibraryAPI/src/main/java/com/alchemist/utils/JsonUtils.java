package com.alchemist.utils;

import io.restassured.path.json.JsonPath;

public class JsonUtils {
	public static String getValueAsString(String response, String key) {
		return new JsonPath(response).getString(key);
	}
	
	public static Integer getValueAsInt(String response, String key) {
		return new JsonPath(response).getInt(key);
	}
}
