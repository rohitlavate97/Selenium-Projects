package com.alchemist.utils;

public class Payloads {

    public static String addPlace(String name) {
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -33.8669710,\n" +
                "    \"lng\": 151.1958750\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"phone_number\": \"(02) 1234 5678\",\n" +
                "  \"address\": \"100 Fictional Street, Test City\",\n" +
                "  \"types\": [\"restaurant\", \"bar\"],\n" +
                "  \"website\": \"http://testplace.example.com\",\n" +
                "  \"language\": \"en-AU\"\n" +
                "}";
    }

    public static String updatePlace(String placeId, String newAddress) {
        return "{\n" +
                "  \"place_id\": \"" + placeId + "\",\n" +
                "  \"address\": \"" + newAddress + "\",\n" +
                "  \"key\": \"qaclick23\"\n" +
                "}";
    }

    public static String deletePlace(String placeId) {
        return "{\n" +
                "  \"place_id\": \"" + placeId + "\"\n" +
                "}";
    }
}
