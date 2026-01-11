package com.alchemist.framework.models;

import java.util.List;

public class CourseDetailsResponse {
    public String instructor;
    public String url;
    public String services;
    public String expertise;
    public Courses courses;
    public String linkedIn;

    public static class Courses {
        public List<Course> webAutomation;
        public List<Course> api;
        public List<Course> mobile;
    }
}
