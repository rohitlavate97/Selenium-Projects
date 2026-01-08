package com.alchemist;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BasicsPOST {

    public static void main(String[] args) {

        // Validate Add Place API is working as expected

        // Given --> all input details
        // When  --> Submit the API (resource + HTTP method)
        // Then  --> Validate the response

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        Response response =
        given()
            .queryParam("key", "qaclick23")
            .header("Content-Type", "application/json")
            .body("{\n" +
                    "  \"location\": {\n" +
                    "    \"lat\": -33.8669710,\n" +
                    "    \"lng\": 151.1958750\n" +
                    "  },\n" +
                    "  \"accuracy\": 50,\n" +
                    "  \"name\": \"Test Place from Rest Assured\",\n" +
                    "  \"phone_number\": \"(02) 1234 5678\",\n" +
                    "  \"address\": \"100 Fictional Street, Test City\",\n" +
                    "  \"types\": [\"restaurant\", \"bar\"],\n" +
                    "  \"website\": \"http://testplace.example.com\",\n" +
                    "  \"language\": \"en-AU\"\n" +
                    "}")
        .when()
            .post("/maps/api/place/add/json")
        .then()
            .assertThat().statusCode(200)
            .extract().response();
//        .then()
//        .log().all()
//        .assertThat().statusCode(200);

        System.out.println(response.asString());
    }
}
