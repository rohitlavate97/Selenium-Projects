package com.alchemist.framework.clients;

import com.alchemist.framework.config.ConfigManager;
import com.alchemist.framework.models.TokenResponse;

import static io.restassured.RestAssured.given;

public class OAuthClient {

    public TokenResponse generateToken() {
        return given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("client_id", ConfigManager.clientId())
                .formParam("client_secret", ConfigManager.clientSecret())
                .formParam("grant_type", "client_credentials")
                .formParam("scope", ConfigManager.scope())
            .when()
                .post(ConfigManager.tokenPath())
            .then()
                .statusCode(200)
                .extract().as(TokenResponse.class);
    }
}
