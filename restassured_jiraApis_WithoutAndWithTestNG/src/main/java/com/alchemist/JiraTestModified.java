package com.alchemist;

import static org.hamcrest.CoreMatchers.equalTo;

import com.alchemist.utils.BaseRequests;
import com.alchemist.utils.JsonUtils;
import com.alchemist.utils.PayloadModified;
import com.alchemist.utils.Payloads;
import com.alchemist.utils.Resources;

import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;

public class JiraTestModified {

  public static void main(String[] args) {

    SessionFilter session = new SessionFilter();

    // 1) LOGIN
    BaseRequests.reqSpec()
        .filter(session)
        .body(PayloadModified.authPayload())
        .log().all()
      .when()
        .post(Resources.AUTH_URL)
      .then()
        .log().all()
        .statusCode(200);

    // 2) CREATE ISSUE (extract key)
    String summary = "Issue created via Rest Assured - " + System.currentTimeMillis();

    Response createRes = BaseRequests.reqSpec()
        .filter(session)
        .body(PayloadModified.createIssue(summary))
        .log().all()
      .when()
        .post(Resources.CREATE_ISSUE)
      .then()
        .log().all()
        .statusCode(201)
        .extract().response();

    String issueKey = createRes.jsonPath().getString("key");
    System.out.println("Created issueKey: " + issueKey);

    // 3) ADD COMMENT (extract commentId)
    Response commentRes = BaseRequests.reqSpec()
        .filter(session)
        .pathParam("issueKey", issueKey)
        .body(PayloadModified.addComment("This comment is added using REST Assured..."))
        .log().all()
      .when()
        .post(Resources.ADD_COMMENT)
      .then()
        .log().all()
        .statusCode(201)
        .body("author.emailAddress", equalTo("rohitlavate97@gmail.com"))
        .extract().response();

    String commentId = commentRes.jsonPath().getString("id");
    System.out.println("Created commentId: " + commentId);

    // 4) UPDATE COMMENT (optional)
    BaseRequests.reqSpec()
        .filter(session)
        .pathParam("issueKey", issueKey)
        .pathParam("commentId", commentId)
        .body(PayloadModified.updateComment("Updated comment by Rest Assured"))
        .log().all()
      .when()
        .put(Resources.UPDATE_COMMENT)
      .then()
        .log().all()
        .statusCode(200)
        .body("id", equalTo(commentId));
  }
}
