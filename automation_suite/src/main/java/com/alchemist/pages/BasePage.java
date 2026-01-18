package com.alchemist.pages;

import org.openqa.selenium.WebDriver;
import com.alchemist.base.DriverFactory;

public abstract class BasePage {
    protected WebDriver driver = DriverFactory.getDriver();
}
