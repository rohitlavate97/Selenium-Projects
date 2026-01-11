package com.alchemist.utils;

public class Payload {

    private Payload(){}

    public static String addBook(String isbn, String aisle) {
        return "{\n" +
                "  \"name\":\"Learn Appium Automation with Java\",\n" +
                "  \"isbn\":\"" + isbn + "\",\n" +
                "  \"aisle\":\"" + aisle + "\",\n" +
                "  \"author\":\"John foe\"\n" +
                "}";
    }

    public static String deleteBook(String id) {
        return "{\n" +
                "  \"ID\":\"" + id + "\"\n" +
                "}";
    }
}
