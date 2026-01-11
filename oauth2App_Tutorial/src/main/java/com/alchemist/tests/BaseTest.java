package com.alchemist.tests;

import com.alchemist.utils.BaseRequest;
import com.alchemist.utils.JsonUtility;
import com.alchemist.utils.Resources;

public class BaseTest {
	public static String accessToken() {
		String tokenResponse = BaseRequest.reqSpec()
		      .header("Content-Type","application/x-www-form-urlencoded")
		      .formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		      .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		      .formParam("grant_type", "client_credentials")
		      .formParam("scope", "trust")
		 .when()
		      .post(Resources.GET_TOKEN)
		 .then().log().all()
		      .assertThat().statusCode(200).extract().asString();
		
		String access_token = JsonUtility.getValueAsString(tokenResponse, "access_token");
		return access_token;
	}
}
