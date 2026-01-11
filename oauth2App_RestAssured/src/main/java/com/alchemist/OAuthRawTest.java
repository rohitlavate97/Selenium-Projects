package com.alchemist;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class OAuthRawTest {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi/"; 

        String tokenResponse = 
                given()
                    .contentType(ContentType.URLENC)   //similar to .header("Content-Type", "application/x-www-form-urlencoded")
                    .formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                    .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                    .formParam("grant_type", "client_credentials")
                    .formParam("scope", "trust")
                .when()
                    .post("oauth2/resourceOwner/token")
                .then()
                    .log().all()
                    .assertThat().statusCode(200)
                    .extract().asString();
        JsonPath js = new JsonPath(tokenResponse);
        String token = js.getString("access_token");
        System.out.println("ACCESS TOKEN :\n" + token);
        
        given()
              .queryParam("access_token", token)
              //.header("Content-Type","application/json") -->not required for GET calls
        .when()
              .get("/getCourseDetails")
        .then().log().all()
               .assertThat().statusCode(401).extract().response().asString();
    }
}
