package com.alchemist.utils;

public class Payloads {
	public static final String authPayload() {
		return "{\n    \"username\": \"rohitl97\",\n    \"password\": \"admin@123\"\n}";
	}
	
	public static final String createIssue(String summary) {
		return  "{\n" +
			    	    "  \"fields\": {\n" +
			    	    "    \"project\": { \"key\": \"RES\" },\n" +
			    	    "    \"summary\": \"" + summary + "\",\n" +   // ✅ quotes around summary value
			    	    "    \"description\": \"Practice issue\",\n" +
			    	    "    \"issuetype\": { \"name\": \"Task\" }\n" +
			    	    "  }\n" +
			    	    "}";
	}
	
	public static String addComment(String comment) {
	    // Optional: escape " to keep JSON valid
	    String safeComment = comment == null ? "" : comment.replace("\"", "\\\"");

	    return "{\n" +
	           "  \"body\": \"" + safeComment + "\",\n" +
	           "  \"visibility\": {\n" +
	           "    \"type\": \"role\",\n" +
	           "    \"value\": \"Administrators\"\n" +
	           "  }\n" +
	           "}";
	}

}
