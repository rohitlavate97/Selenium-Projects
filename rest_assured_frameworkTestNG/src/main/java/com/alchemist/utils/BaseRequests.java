package com.alchemist.utils;

import static io.restassured.RestAssured.given;

import io.restassured.specification.RequestSpecification;

public class BaseRequests {
	public static RequestSpecification requestSpec() {
        return given()
                .baseUri("https://rahulshettyacademy.com")
                .queryParam("key", "qaclick23")
                .header("Content-Type", "application/json");
    }
	
	public static RequestSpecification requestSpecForLibrary() {
        return given()
                .baseUri("https://rahulshettyacademy.com")
                .header("Content-Type", "application/json");
    }
}
