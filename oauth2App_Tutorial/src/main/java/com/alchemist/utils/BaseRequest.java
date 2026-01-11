package com.alchemist.utils;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public class BaseRequest {
	public static RequestSpecification reqSpec() {
		return given()
		  .baseUri("https://rahulshettyacademy.com/oauthapi");
	}
}
