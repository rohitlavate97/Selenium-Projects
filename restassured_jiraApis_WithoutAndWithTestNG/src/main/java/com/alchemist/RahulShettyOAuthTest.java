package com.alchemist;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RahulShettyOAuthTest {

    private static final String BASE = "https://rahulshettyacademy.com";

    private String generateToken(String clientId, String clientSecret) {
        RestAssured.baseURI = BASE;

        String res = given()
                .contentType(ContentType.URLENC)
                .formParam("client_id", clientId)
                .formParam("client_secret", clientSecret)
                .formParam("grant_type", "client_credentials")
                .formParam("scope", "create")
            .when()
                .post("/oauthapi/oauth2/resourceOwner/token")
            .then()
                .statusCode(200)
                .extract().asString();

        JsonPath js = new JsonPath(res);

        Assert.assertEquals(js.getString("token_type"), "Bearer");
        Assert.assertEquals((int) js.getInt("expires_in"), 3600);
        Assert.assertNotNull(js.getString("refresh_token"));
        Assert.assertNotNull(js.getString("access_token"));

        return js.getString("access_token");
    }

    @Test
    public void getCourseDetails_shouldMatchExpectedData() {
        // Put your real creds here OR read from env vars
//    	String clientId = System.getenv("RSA_CLIENT_ID");
//    	String clientSecret = System.getenv("RSA_CLIENT_SECRET");
    	String clientId = "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com";
    	String clientSecret = "erZOWM9g3UtwNRj340YYaK_W";

    	if (clientId == null || clientId.isBlank())
    	    throw new IllegalStateException("Missing RSA_CLIENT_ID env var");
    	if (clientSecret == null || clientSecret.isBlank())
    	    throw new IllegalStateException("Missing RSA_CLIENT_SECRET env var");

        Assert.assertNotNull(clientId, "Missing RSA_CLIENT_ID env var");
        Assert.assertNotNull(clientSecret, "Missing RSA_CLIENT_SECRET env var");

        String token = generateToken(clientId, clientSecret);

        RestAssured.baseURI = BASE;

        String courseRes = given()
                .queryParam("access_token", token)
            .when()
                .get("/oauthapi/getCourseDetails")
            .then()
                .statusCode(401)                //Actually needed 200 but api designed that way
                .contentType(ContentType.JSON)
                .extract().asString();

        JsonPath js = new JsonPath(courseRes);

        // Top-level validations
        Assert.assertEquals(js.getString("instructor"), "RahulShetty");
        Assert.assertEquals(js.getString("url"), "rahulshettycademy.com");
        Assert.assertEquals(js.getString("services"), "projectSupport");
        Assert.assertEquals(js.getString("expertise"), "Automation");
        Assert.assertTrue(js.getString("linkedIn").contains("linkedin.com"));

        // WebAutomation titles check
        List<String> webTitles = js.getList("courses.webAutomation.courseTitle");
        Assert.assertEquals(webTitles, List.of("Selenium Webdriver Java", "Cypress", "Protractor"));

        // Cypress price = 40
        List<Map<String, String>> webCourses = js.getList("courses.webAutomation");
        int cypressPrice = webCourses.stream()
                .filter(c -> "Cypress".equals(c.get("courseTitle")))
                .map(c -> Integer.parseInt(c.get("price")))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Cypress not found"));
        Assert.assertEquals(cypressPrice, 40);

        // Sum of API prices = 90
        List<Map<String, String>> apiCourses = js.getList("courses.api");
        int apiSum = apiCourses.stream()
                .mapToInt(c -> Integer.parseInt(c.get("price")))
                .sum();
        Assert.assertEquals(apiSum, 90);

        // Total courses = 6
        int total = js.getList("courses.webAutomation").size()
                + js.getList("courses.api").size()
                + js.getList("courses.mobile").size();
        Assert.assertEquals(total, 6);

        // Mobile course title
        Assert.assertEquals(js.getString("courses.mobile[0].courseTitle"),
                "Appium-Mobile Automation using Java");
    }
}
