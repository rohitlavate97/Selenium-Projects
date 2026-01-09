package com.alchemist.utils;

import io.restassured.path.json.JsonPath;

public class JsonUtils {
	public static String getValueAsString(String payload,String key) {
		JsonPath js = new JsonPath(payload);
		return js.getString(key);
	}
	
	public static Integer getValueAsInteger(String payload,String key) {
		JsonPath js = new JsonPath(payload);
		return js.getInt(key);
	}
}
