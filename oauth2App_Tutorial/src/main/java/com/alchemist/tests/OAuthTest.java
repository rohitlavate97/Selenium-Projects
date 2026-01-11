package com.alchemist.tests;

import java.util.List;

import org.testng.annotations.Test;

import com.alchemist.pojo.ApiCourseDetails;
import com.alchemist.pojo.ResponseForCourseDetails;
import com.alchemist.utils.BaseRequest;
import com.alchemist.utils.Resources;

import io.restassured.parsing.Parser;

public class OAuthTest extends BaseTest{
	String token = accessToken();
	
	@Test
	public void client_cred_Test() {
		ResponseForCourseDetails courses = BaseRequest.reqSpec()
		    .queryParam("access_token", token)
		    .expect().defaultParser(Parser.JSON)  //we can avoid if your response header is JSON(In our case, text/html:charset=UTF-8)
		.when()
		    .get(Resources.GET_COURSE).as(ResponseForCourseDetails.class);
		//All Json values will be carried by variables in ResponseForCourseDetails class.
		//we can get value by calling getter method while deserializing everything created to Java object
		System.out.println(courses);
		System.out.println(courses.getInstructor());
		System.out.println(courses.getCourses());
		//Get Price of Course-->SOAP UI Webservices Testing 
		List<ApiCourseDetails> api = courses.getCourses().getApi();
		for(int i=0;i<api.size();i++) {
			ApiCourseDetails course = api.get(i);
			if(course.getCourseTitle().equals("SoapUI Webservices testing")) {
				System.out.println(course.getPrice());
				break;
			}
		}
	}
}
