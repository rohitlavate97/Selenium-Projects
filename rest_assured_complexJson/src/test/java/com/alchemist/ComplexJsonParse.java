package com.alchemist;

import org.testng.Assert;

import com.alchemist.utility.JsonUtility;
import com.alchemist.utility.Payloads;

public class ComplexJsonParse {

	public static void main(String[] args) {
		String payload = Payloads.complexJson();
		//Get the size of courses
		Integer numOfCourses = JsonUtility.getValueAsInteger(payload, "courses.size()");
		System.out.println(numOfCourses);
		//To print the purchase amount
		Integer purchaseAmount = JsonUtility.getValueAsInteger(payload, "dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		//Print the title of the first course
		String firstCourseTitle = JsonUtility.getValueAsString(payload, "courses[0].title");
		System.out.println(firstCourseTitle);
		//Print all courses title and their respective prices
		for(int i=0;i<numOfCourses;i++) {
			String courseTitle = JsonUtility.getValueAsString(payload, "courses["+i+"].title");
			Integer coursePrice = JsonUtility.getValueAsInteger(payload, "courses["+i+"].price");
			System.out.println(courseTitle+"  "+coursePrice);
		}
		//Print number of copies sold by RPA course-->As index may change in the future
		for(int i=0;i<numOfCourses;i++) {
			String title = JsonUtility.getValueAsString(payload, "courses["+i+"].title");
			if(title.equalsIgnoreCase("RPA")) {
				Integer copies = JsonUtility.getValueAsInteger(payload, "courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
		//Check if purchaseAmount is equal to sum of all the prices
		int sum = 0;
		for(int i=0;i<numOfCourses;i++) {
			Integer coursePrice = JsonUtility.getValueAsInteger(payload, "courses["+i+"].price");
			Integer copies = JsonUtility.getValueAsInteger(payload, "courses["+i+"].copies");
			int total = coursePrice*copies;
			sum +=total;
		}
		System.out.println(sum);
		Assert.assertEquals(purchaseAmount, sum);
		
	}

}
