package com.alchemist;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.File;

import com.alchemist.utils.PayloadModified;
import com.alchemist.utils.Payloads;

public class JiraCompleteTest {

	public static void main(String[] args) {
		RestAssured.baseURI = "http://localhost:8081";
		SessionFilter session = new SessionFilter();
		//Get Session Token
		given()
		     .relaxedHTTPSValidation()
		     .header("Content-Type","application/json")
		     .body(PayloadModified.authPayload())
		     .filter(session)
		.when()
		     .post("rest/auth/1/session")
		.then()
		     .log().all().assertThat().statusCode(200);
		
		//Create Issue
		String createIssRes = given()
		    .header("Content-Type","application/json")
		    .filter(session)
		    .body(PayloadModified.createIssue("Adding new Issue using Rest Assured Java"))
		.when()
		    .post("rest/api/2/issue")
		.then().log().all()
		     .assertThat().statusCode(201).extract().response().asString();
		String issueId =new JsonPath(createIssRes).getString("id");
		
		//Add Comment
		String addCommentRes = given()
		     .pathParam("issueId", issueId)
		     .header("Content-Type","application/json")
		     .body(PayloadModified.addComment("This is newly added comment"))
		     .filter(session)
		.when()
		     .post("rest/api/2/issue/{issueId}/comment")
		.then().log().all()
		     .assertThat().statusCode(201).extract().response().asString();
		String commentId = new JsonPath(addCommentRes).getString("id");
		
		//Update Comment
		String updRes = given()
		   .pathParam("issueId",issueId)
		   .pathParam("commentId",commentId)
		   .header("Content-Type","application/json")
		   .body(PayloadModified.updateComment("This is Updated Comment..."))
		   .filter(session)
		.when()
		   .put("rest/api/2/issue/{issueId}/comment/{commentId}")
		.then().log().all()
		   .assertThat().statusCode(200).extract().response().asString();
		
		//Add Attachment
		String attachRes = given()
		    .pathParam("issueId",issueId)
		    .header("X-Atlassian-Token","no-check")
		    .header("Content-Type","multipart/form-data")
		    .multiPart("file",new File("D:\\Testing Prep\\Selenium Practice\\Projects\\jira.txt"))
		    .filter(session)
		 .when()
		    .post("rest/api/2/issue/{issueId}/attachments")
		 .then().log().all()
		    .assertThat().statusCode(200).body("[0].filename", equalTo("jira.txt")).extract().response().asString();
		
		//Get Issue
		String commentInfo = given()
		    .queryParam("fields", "comment")
		    .pathParam("issueId", issueId)
		    .header("Content-Type","application/json")
		    .filter(session)
		.when()
		    .get("rest/api/2/issue/{issueId}")
		.then()
		    .assertThat().statusCode(200).extract().response().asPrettyString();
		System.out.println(commentInfo);
		JsonPath js = new JsonPath(commentInfo);
		int commentsCount = js.getInt("fields.comment.comments.size()");
		for(int i=0;i<commentsCount;i++) {
			String issueCommentId = js.get("fields.comment.comments["+i+"].id").toString();
			if(issueCommentId.equalsIgnoreCase(commentId)) {
				System.out.println(js.get("fields.comment.comments["+i+"].body").toString());
			}
		}
		
		
		System.out.println("To Summarize test--> IssueId is: "+issueId+" CommentID is: "+commentId);
		
	}

}
