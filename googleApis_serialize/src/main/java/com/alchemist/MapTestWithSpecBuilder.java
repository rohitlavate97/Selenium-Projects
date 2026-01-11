package com.alchemist;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.alchemist.pojo.AddPlaceRequest;
import com.alchemist.pojo.Location;
import com.alchemist.utils.Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

public class MapTestWithSpecBuilder {

    @Test
    public void addPlace() {

        AddPlaceRequest request = new AddPlaceRequest();

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        request.setLocation(location);
        request.setAccuracy(70);
        request.setName("Testing SpecBuilder");
        request.setPhoneNumber("(+91) 983 893 3934");
        request.setAddress("29, side layout, cohen 09");
        request.setTypes(Arrays.asList("shoe park", "shop"));
        request.setWebsite("http://google.com");
        request.setLanguage("French-IN");

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON)
                .addHeader("Accept", "application/json")
                .build();

        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        ValidatableResponse spec =
                given()
                    .spec(reqSpec)
                    .body(request)          // ✅ MISSING IN YOUR CODE
                    .log().all()
                .when()
                    .post(Resources.ADD_PLACE)
                .then()
                    .log().all()
                    .spec(resSpec);

        String response = spec.extract().asString();
        System.out.println(response);
    }
}
