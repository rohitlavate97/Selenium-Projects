package com.alchemist;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class AddPlaceWithHashMapTest {

    @Test
    public void addPlace_usingHashMapPayload() {

        // 1) Build nested JSON using HashMap
        Map<String, Object> location = new HashMap<>();
        location.put("lat", -38.383494);
        location.put("lng", 33.427362);

        Map<String, Object> body = new HashMap<>();
        body.put("location", location);
        body.put("accuracy", 50);
        body.put("name", "Frontline house");
        body.put("phone_number", "(+91) 983 893 3937");   // snake_case key
        body.put("address", "29, side layout, cohen 09");
        body.put("types", Arrays.asList("shoe park", "shop"));
        body.put("website", "http://google.com");
        body.put("language", "French-IN");

        // 2) Call API
        String response =
                given()
                    .baseUri("https://rahulshettyacademy.com")
                    .queryParam("key", "qaclick123")
                    .contentType(ContentType.JSON)
                    .body(body)
                    .log().all()
                .when()
                    .post("/maps/api/place/add/json")
                .then()
                    .log().all()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .extract().asString();

        // 3) Validate response
        JsonPath js = new JsonPath(response);
        String placeId = js.getString("place_id");
        String status = js.getString("status");

        Assert.assertNotNull(placeId, "place_id should not be null");
        Assert.assertEquals(status, "OK", "status should be OK");

        System.out.println("place_id = " + placeId);
    }
}
