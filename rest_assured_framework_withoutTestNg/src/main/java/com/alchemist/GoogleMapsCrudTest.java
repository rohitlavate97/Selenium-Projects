package com.alchemist;

import com.alchemist.utils.BaseRequest;
import com.alchemist.utils.JsonUtils;
import com.alchemist.utils.Payloads;
import com.alchemist.utils.Resources;

import static org.hamcrest.CoreMatchers.equalTo;

public class GoogleMapsCrudTest {

    public static void main(String[] args) {

        // ADD
        String addResponse =
                BaseRequest.requestSpec()
                        .body(Payloads.addPlace("Test Place 1"))
                .when()
                        .post(Resources.ADD_PLACE)
                .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo("OK"))
                        .extract().asString();

        String placeId = JsonUtils.getValue(addResponse, "place_id");
        System.out.println("Place ID: " + placeId);

        // UPDATE
//        String updateResponse =
//                BaseRequest.requestSpec()
//                        .body(Payloads.updatePlace(placeId, "70 Summer walk, USA"))
//                .when()
//                        .put(Resources.UPDATE_PLACE)
//                .then()
//                        .log().all()
//                        .statusCode(200)
//                        .body("msg", equalTo("Address successfully updated"))
//                        .extract().asString();
//
//        System.out.println(updateResponse);

        // GET
//        String getResponse =
//                BaseRequest.requestSpec()
//                        .queryParam("place_id", placeId)
//                .when()
//                        .get(Resources.GET_PLACE)
//                .then()
//                        .log().all()
//                        .statusCode(200)
//                        .body("name", equalTo("Test Place 1"))
//                        .extract().asString();
//
//        System.out.println(getResponse);

        // DELETE
        String delResponse =
                BaseRequest.requestSpec()
                        .body(Payloads.deletePlace(placeId))
                .when()
                        .delete(Resources.DELETE_PLACE)
                .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo("OK"))
                        .extract().asString();

        System.out.println(delResponse);
    }
}
