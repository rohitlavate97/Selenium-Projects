package com.alchemist;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class ValidateResponseAddApi {

	public static void main(String[] args) {
		/* Add test case for validation of property in the header and body */
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given()
		   .queryParam("key", "qaclick23")
		   .header("Content-Type","application/json")
		   .body("{\n" +
                    "  \"location\": {\n" +
                    "    \"lat\": -33.8669710,\n" +
                    "    \"lng\": 151.1958750\n" +
                    "  },\n" +
                    "  \"accuracy\": 50,\n" +
                    "  \"name\": \"Test Place from Rest Assured\",\n" +
                    "  \"phone_number\": \"(02) 1234 5678\",\n" +
                    "  \"address\": \"100 Fictional Street, Test City\",\n" +
                    "  \"types\": [\"restaurant\", \"bar\"],\n" +
                    "  \"website\": \"http://testplace.example.com\",\n" +
                    "  \"language\": \"en-AU\"\n" +
                    "}")
		  .when()
		     .post("/maps/api/place/add/json")
		  .then()
		    .log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)");
	}

}
