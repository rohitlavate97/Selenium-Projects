package com.alchemist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alchemist.utils.BaseRequests;
import com.alchemist.utils.JsonUtils;
import com.alchemist.utils.Payload;
import com.alchemist.utils.Resources;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class DynamicJsonTest {
	@Test
	public void addBook() {
		String response = BaseRequests.requestSpec()
		   .body(Payload.addBook("RS926","45"))
		 .when()
		    .post(Resources.ADD_BOOK)
		 .then()
		    .assertThat().statusCode(200)
		    .extract().asString();
		
		System.out.println(response);
		
		String id = JsonUtils.getValueAsString(response, "ID");
		System.out.println(id);
	}
	
	@DataProvider(name = "BooksData")
	public Object[][] getData(){
		return new Object[][] {
			{"RS189","67"},
			{"RSA98","34"},
			{"RSB03","45"}
		};	
	}
	
	@Test(dataProvider = "BooksData")
	public void addBookWithDataProvider(String isbn,String aisle) {
		String response = BaseRequests.requestSpec()
		    .body(Payload.addBook(isbn, aisle))
		.when()
		    .post(Resources.ADD_BOOK)
		.then()
		    .assertThat().statusCode(200).extract().asString();
		
		System.out.println(response);
		
		String id = JsonUtils.getValueAsString(response, "ID");
		System.out.println(id);
		System.out.println("----------------------------------");
	}
	
	@Test
	public void addPlaceTest() throws IOException {
		RestAssured.baseURI = "https://rahulshettyacademy.com";

        // Step 1: Read JSON file
        Path path = Paths.get("D:\\Testing Prep\\Selenium Practice\\Projects\\static_json.json");
        String payload = new String(Files.readAllBytes(path));

        // Step 2: Pass payload to Rest Assured
        given()
            .queryParam("key", "qaclick23")
            .header("Content-Type", "application/json")
            .body(payload)
        .when()
            .post("/maps/api/place/add/json")
        .then()
            .log().all()
            .assertThat().statusCode(200);
	}
	
	@Test
	public void addBookWithStaticJson() throws IOException {
		given()
		   .baseUri("https://rahulshettyacademy.com")
		   .header("Content-Type","application/json")
		   .body(new String(Files.readAllBytes(Paths.get("D:\\Testing Prep\\Selenium Practice\\Projects\\addbook_json.json"))))
		.when()
		   .post("Library/Addbook.php")
		.then().log().all()
		   .assertThat().statusCode(200);
	}
	
	/*
	 * we can create generateStringFromResource() from this line 
	 * new String(Files.readAllBytes(Paths.
	 * get("D:\\Testing Prep\\Selenium Practice\\Projects\\addbook_json.json")))
	 */
}
