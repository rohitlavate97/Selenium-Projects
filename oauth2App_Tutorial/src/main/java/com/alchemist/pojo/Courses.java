package com.alchemist.pojo;

import java.util.List;

public class Courses {
	private List<WebAutomationCourseDetails> webAutomation;
	private List<ApiCourseDetails> api;
	private List<MobileCourseDetails> mobile;
	public List<WebAutomationCourseDetails> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomationCourseDetails> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<ApiCourseDetails> getApi() {
		return api;
	}
	public void setApi(List<ApiCourseDetails> api) {
		this.api = api;
	}
	public List<MobileCourseDetails> getMobile() {
		return mobile;
	}
	public void setMobile(List<MobileCourseDetails> mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "Courses [webAutomation=" + webAutomation + ", api=" + api + ", mobile=" + mobile + "]";
	}
}
