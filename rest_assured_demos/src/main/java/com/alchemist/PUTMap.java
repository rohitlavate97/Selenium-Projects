package com.alchemist;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;

public class PUTMap {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        given()
            .queryParam("key", "qaclick23")
            .header("Content-Type", "application/json")
            .body("{\n" +
                  "  \"place_id\": \"ee796e4c454d7aa33ba151dcaafd5e97\",\n" +
                  "  \"address\": \"70 Summer walk, USA\",\n" +
                  "  \"key\": \"qaclick23\"\n" +
                  "}")
        .when()
            .put("/maps/api/place/update/json")
        .then()
            .log().all()
            .assertThat().statusCode(200);
    }
}
