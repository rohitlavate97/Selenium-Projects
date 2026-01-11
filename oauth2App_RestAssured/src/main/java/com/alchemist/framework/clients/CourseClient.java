package com.alchemist.framework.clients;

import com.alchemist.framework.config.ConfigManager;
import com.alchemist.framework.models.CourseDetailsResponse;

import static io.restassured.RestAssured.given;

public class CourseClient {

    public CourseDetailsResponse getCourseDetails(String accessToken) {
        return given()
                .queryParam("access_token", accessToken)
            .when()
                .get(ConfigManager.courseDetailsPath())
            .then()
                .statusCode(401)
                .extract().as(CourseDetailsResponse.class);
    }
}
