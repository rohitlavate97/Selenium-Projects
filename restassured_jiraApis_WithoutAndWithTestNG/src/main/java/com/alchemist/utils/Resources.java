package com.alchemist.utils;

public class Resources {
	public static final String AUTH_URL = "rest/auth/1/session";
	public static final String CREATE_ISSUE = "rest/api/2/issue";
	public static final String ADD_COMMENT = "rest/api/2/issue/RSA-3/comment";
	//public static final String ADD_COMMENT = "rest/api/2/issue/{issueKey}/comment";
	public static final String UPDATE_COMMENT = "rest/api/2/issue/RSA-3/comment/10002";
	//public static final String UPDATE_COMMENT= "/rest/api/2/issue/{issueKey}/comment/{commentId}";
	public static final String DELETE_ISSUE  = "/rest/api/2/issue/{issueKey}";
	public static final String ATTACH_FILE = "/rest/api/2/issue/{issueKey}/attachments";
	public static final String GET_ISSUE = "/rest/api/2/issue/{issueKey}";
}
