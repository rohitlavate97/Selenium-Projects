package com.alchemist;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import com.alchemist.utils.Resources;
import com.alchemist.utils.SpecificationFactory;

public class LibraryApiHashMapToJsonTest {
	@Test
	public void addBookHashMaptoJsonTest() {
		Map<String,Object> body = new HashMap<String,Object>();
		body.put("name", "Java");
		body.put("isbn", "isb12");
		body.put("aisle", "345");
		body.put("author", "Sunny");
		//we used hashmap so com.alchemist.utils.Payload class is not needed
		String response = given()
		     .spec(SpecificationFactory.reqSpec())
		     .body(body).log().all()
		.when()
		     .post(Resources.ADD_BOOK)
		.then()
		     .spec(SpecificationFactory.resSpec())
		     .extract().response().asString();
		
		System.out.println(response);
	}
}
