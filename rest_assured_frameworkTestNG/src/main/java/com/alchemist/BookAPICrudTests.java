package com.alchemist;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.alchemist.utils.BaseRequests;
import com.alchemist.utils.JsonUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class BookAPICrudTests {
	
	/*
	 * public String getStringFromResource(Path path) throws IOException { return
	 * new String(Files.readAllBytes(path), StandardCharsets.UTF_8); }
	 */
	
	//latest
	public String getStringFromResource(Path path) throws IOException {
	    return Files.readString(path);
	}
	
	@Test
    public void addBookTest() throws IOException {

        Path path = Paths.get("D:\\Testing Prep\\Selenium Practice\\Projects\\addbook_json.json");
        String payload = getStringFromResource(path);

        Response response = BaseRequests.requestSpecForLibrary()
                .contentType("application/json")   // important
                .body(payload)
            .when()
                .post("Library/Addbook.php")
            .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        String id = JsonUtils.getValueAsString(response.asPrettyString(), "ID");
        System.out.println("Generated ID: " + id);

        Assert.assertNotNull(id, "ID should not be null");
        Assert.assertFalse(id.trim().isEmpty(), "ID should not be empty");
    }
	
	//Using Random
	@Test
	public void addBookTestGenerateISBN() {

	    String isbn = "bcd" + (System.currentTimeMillis() % 10000);
	    int aisle = (int)(Math.random() * 10000);

	    Map<String, Object> body = new HashMap<>();
	    body.put("name", "Learn Appium Automation with Java");
	    body.put("isbn", isbn);
	    body.put("aisle", String.valueOf(aisle));
	    body.put("author", "John foe");

	    given()
	        .baseUri("https://rahulshettyacademy.com")
	        .basePath("/Library")
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	        .log().all()
	        .body(body)
	    .when()
	        .post("/Addbook.php")
	    .then()
	        .log().all()
	        .statusCode(anyOf(is(200), is(201)))   // some docs show 200; some flows show 201
	        .body("ID", notNullValue());
	}
}
