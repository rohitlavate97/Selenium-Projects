package com.alchemist.framework.specs;

import com.alchemist.framework.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

public class SpecBuilder {

    private SpecBuilder() {}

    public static void init() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(ConfigManager.baseUrl())
                .build();
    }
}
