package com.alchemist;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;

public class DeleteMaps {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given()
		   .queryParam("key", "qaclick23")
		   .header("Content-Type","application/json")
		   .body("{\n" +
				   "  \"place_id\": \"699ab9aa6c9f90ba17d764f5a47e7880\"\n" +
				   "}")
		.when()
		   .delete("/maps/api/place/delete/json")
		.then().log().all()
		   .extract().response().asString();
		
	}

}
