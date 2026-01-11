package com.alchemist;

import static io.restassured.RestAssured.given;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.alchemist.pojo.AddPlaceRequest;
import com.alchemist.pojo.Location;
import com.alchemist.utils.BaseRequest;
import com.alchemist.utils.Resources;

import io.restassured.path.json.JsonPath;

public class MapsTest {

    @Test
    public void addMapTest() {

        AddPlaceRequest request = new AddPlaceRequest();

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        request.setLocation(location);
        request.setAccuracy(70);
        request.setName("Frontline");
        request.setPhoneNumber("(+91) 983 893 3937");
        request.setAddress("29, side layout, cohen 09");
        request.setTypes(Arrays.asList("shoe park", "shop"));
        request.setWebsite("http://google.com");
        request.setLanguage("French-IN");

        String response =
                given()
                    .spec(BaseRequest.reqSpec())
                    .body(request)
                    .log().all()
                .when()
                    .post(Resources.ADD_PLACE)
                .then()
                    .log().all()
                    .statusCode(200)   // now your test will FAIL if server returns 500
                    .extract().asString();

        JsonPath js = new JsonPath(response);
        String placeId = js.getString("place_id");
        Assert.assertNotNull(placeId, "place_id should not be null");
        System.out.println("place_id = " + placeId);
    }
}
