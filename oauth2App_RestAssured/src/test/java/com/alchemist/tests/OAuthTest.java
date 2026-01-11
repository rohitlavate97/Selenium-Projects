package com.alchemist.tests;

import com.alchemist.framework.clients.CourseClient;
import com.alchemist.framework.models.CourseDetailsResponse;
import com.alchemist.framework.utils.TokenManager;
import com.alchemist.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class OAuthTest extends BaseTest {

    @Test
    public void getCourseDetails_shouldMatchExpectedData() {
        String token = TokenManager.getToken();
        CourseDetailsResponse res = new CourseClient().getCourseDetails(token);

        Assert.assertEquals(res.instructor, "RahulShetty");
        Assert.assertEquals(res.url, "rahulshettycademy.com");
        Assert.assertEquals(res.services, "projectSupport");
        Assert.assertEquals(res.expertise, "Automation");
        Assert.assertTrue(res.linkedIn.contains("linkedin.com"));

        List<String> webTitles = res.courses.webAutomation.stream().map(c -> c.courseTitle).toList();
        Assert.assertEquals(webTitles, List.of("Selenium Webdriver Java", "Cypress", "Protractor"));

        int cypressPrice = res.courses.webAutomation.stream()
                .filter(c -> "Cypress".equals(c.courseTitle))
                .mapToInt(c -> Integer.parseInt(c.price))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Cypress not found"));
        Assert.assertEquals(cypressPrice, 40);

        int apiSum = res.courses.api.stream().mapToInt(c -> Integer.parseInt(c.price)).sum();
        Assert.assertEquals(apiSum, 90);

        int total = res.courses.webAutomation.size() + res.courses.api.size() + res.courses.mobile.size();
        Assert.assertEquals(total, 6);

        Assert.assertEquals(res.courses.mobile.get(0).courseTitle,
                "Appium-Mobile Automation using Java");
    }
}
