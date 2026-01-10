package com.alchemist.utils;

public class PayloadModified {

  public static String authPayload() {
    return "{\n" +
        "  \"username\": \"rohitl97\",\n" +
        "  \"password\": \"admin@123\"\n" +
        "}";
  }

  public static String createIssue(String summary) {
    String safeSummary = summary == null ? "" : summary.replace("\"", "\\\"");
    return "{\n" +
        "  \"fields\": {\n" +
        "    \"project\": { \"key\": \"RES\" },\n" +
        "    \"summary\": \"" + safeSummary + "\",\n" +
        "    \"description\": \"Practice issue\",\n" +
        "    \"issuetype\": { \"name\": \"Task\" }\n" +
        "  }\n" +
        "}";
  }

  public static String addComment(String comment) {
    String safeComment = comment == null ? "" : comment.replace("\"", "\\\"");
    return "{\n" +
        "  \"body\": \"" + safeComment + "\",\n" +
        "  \"visibility\": {\n" +
        "    \"type\": \"role\",\n" +
        "    \"value\": \"Administrators\"\n" +
        "  }\n" +
        "}";
  }

  public static String updateComment(String comment) {
    String safeComment = comment == null ? "" : comment.replace("\"", "\\\"");
    return "{\n" +
        "  \"body\": \"" + safeComment + "\"\n" +
        "}";
  }
}
