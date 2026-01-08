package com.alchemist;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetMaps {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String placeId = "3e75a09f680d8979531d2528cadf055c"; // <-- replace with real place_id from ADD API response

        Response response =
            given()
                .queryParam("key", "qaclick23")
                .queryParam("place_id", placeId)
            .when()
                .get("/maps/api/place/get/json")
            .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        System.out.println("Response Body:\n" + response.asString());
    }
}
