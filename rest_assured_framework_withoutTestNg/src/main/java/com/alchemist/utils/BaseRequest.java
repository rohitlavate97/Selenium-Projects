package com.alchemist.utils;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseRequest {

    public static RequestSpecification requestSpec() {
        return given()
                .baseUri("https://rahulshettyacademy.com")
                .queryParam("key", "qaclick23")
                .header("Content-Type", "application/json");
    }
}
