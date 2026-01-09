package parsing_json;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class ParsingJson {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given()
		   .queryParam("key","qaclick23")
		   .header("Content-Type","application/json")
		   .body("{\n  \"location\": {\n    \"lat\": -33.8669710,\n    \"lng\": 151.1958750\n  },\n  \"accuracy\": 50,\n  \"name\": \"Test Place 1\",\n  \"phone_number\": \"(02) 1234 5678\",\n  \"address\": \"100 Fictional Street, Test City\",\n  \"types\": [\"restaurant\", \"bar\"],\n  \"website\": \"http://testplace.example.com\",\n  \"language\": \"en-AU\"\n}")
		.when()
		   .post("/maps/api/place/add/json")
		.then()
		   .log().all().assertThat().statusCode(200).header("Server","Apache/2.4.52 (Ubuntu)").body("status",equalTo("OK"))
		   .extract().response().asString();
		
		System.out.println("The Response is :"+response);
		
		//If we want to extract place_id, use JsonPath class-->String as input,converts to Json
		JsonPath js = new JsonPath(response);
		//js.getString(path);    --->If we provide path it will directly go to json and extract data
		String place_id = js.getString("place_id");
		System.out.println("The PlaceID is :"+place_id);
		
//		String updateResponse = given()
//		  .queryParam("key","qaclick23")
//		  .header("Content-Type","application/json")
//		  .body("{\n  \"place_id\": \"{{place_id}}\",\n  \"address\": \"70 Summer walk, USA\",\n  \"key\": \"qaclick23\"\n}")
//		.when()
//		  .put("/maps/api/place/update/json")
//		.then()
//		.assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().response().asString();
//		
//		System.out.println(updateResponse);
//		
//		String getResponse = given()
//		   .queryParam("key", "qaclick23")
//		   .queryParam("place_id",place_id)
//		   //.header("Content-Type","application/json")
//		.when()
//		   .get("/maps/api/place/get/json")
//		.then()
//		   .assertThat().statusCode(200).body("name", equalTo("Test Place 1")).extract().response().asString();
//		
//		System.out.println(getResponse);
		
		String delResponse = given()
		   .queryParam("key","qaclick23")
		   .header("Content-Type","application/json")
		   .body("{\n" +
			          "  \"place_id\": \"" + place_id + "\"\n" +
			          "}")
		.when()
		   .delete("/maps/api/place/delete/json")
		.then()
		   .assertThat().statusCode(200).body("status",equalTo("OK")).extract().response().asString();
		
		System.out.println("The Response for Delete Operation is :"+delResponse);
		
		JsonPath js1 = new JsonPath(delResponse);
		String status = js.getString("status");
		System.out.println(status);
	}

}
