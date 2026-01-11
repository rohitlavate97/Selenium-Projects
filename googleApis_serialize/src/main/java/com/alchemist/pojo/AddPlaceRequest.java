package com.alchemist.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class AddPlaceRequest {
    private Location location;
    private Integer accuracy;
    private String name;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;
    private List<String> types;
    private String website;
    private String language;

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    public Integer getAccuracy() { return accuracy; }
    public void setAccuracy(Integer accuracy) { this.accuracy = accuracy; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public List<String> getTypes() { return types; }
    public void setTypes(List<String> types) { this.types = types; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}
