package com.alchemist.pojo;

public class WebAutomationCourseDetails {
	private String courseTitle;
	private String price;
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "CourseDetails [courseTitle=" + courseTitle + ", price=" + price + "]";
	}
	
}
