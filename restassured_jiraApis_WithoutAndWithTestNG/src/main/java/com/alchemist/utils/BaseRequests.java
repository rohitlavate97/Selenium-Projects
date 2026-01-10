package com.alchemist.utils;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public class BaseRequests {
	public static RequestSpecification reqSpec() {
		return given()
				   .baseUri("http://localhost:8081")
				   .header("Content-Type","application/json");
	}
	
//	public static RequestSpecification reqSpec() {
//	    return given()
//	      .baseUri("http://localhost:8081")
//	      .contentType(ContentType.JSON)
//	      .accept(ContentType.JSON);
//	  }
}
