package com.alchemist;

import static org.hamcrest.CoreMatchers.equalTo;

import java.io.File;

import com.alchemist.utils.BaseRequests;
import com.alchemist.utils.Payloads;
import com.alchemist.utils.Resources;

import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class JiraTest {

	public static void main(String[] args) {
		//Login scenarios
		SessionFilter session = new SessionFilter();
		String logres = BaseRequests.reqSpec()
				  .relaxedHTTPSValidation()
		          .body(Payloads.authPayload()).log().all().filter(session)
		.when()
		      .post("rest/auth/1/session")
		.then()
		      .log().all().assertThat().statusCode(200).extract().response().asString();
		      
		//Add Comment
		String response = BaseRequests.reqSpec()
		.pathParam("id", "RSA-3")
		      .body(Payloads.addComment("This comment is added by using REST Assured...")).filter(session)
		.when()
		      .post("rest/api/2/issue/{id}/comment")
		.then().log().all()
		      .assertThat().statusCode(201).body("author.emailAddress",equalTo("rohitlavate97@gmail.com")).extract().response().asString();
		
		//Add Attachement
		given()
		     .baseUri("http://localhost:8081")
		     .pathParam("issueKey","RSA-3")
		     .header("X-Atlassian-Token","no-check")
		     .header("Content-Type","multipart/form-data")
		     .multiPart("file",new File("D:\\Testing Prep\\Selenium Practice\\Projects\\jira.txt"))
		     .filter(session)
		 .when()
		     .post(Resources.ATTACH_FILE)
		 .then()
		     .log().all();
		
		//GET ISSUE
		String getRes = BaseRequests.reqSpec()
		      .pathParam("issueKey", "RSA-3")
		      .queryParam("fields", "comment")         //use this when we don't want entire response to store in variable
		      .filter(session)
		.when()
		      .get(Resources.GET_ISSUE)
		.then().assertThat().statusCode(200).extract().response().asPrettyString();
		System.out.println(getRes);
	}

}
