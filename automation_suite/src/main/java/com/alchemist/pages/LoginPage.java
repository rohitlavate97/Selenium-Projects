package com.alchemist.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import com.alchemist.utils.SeleniumUtil;

public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(name = "pwd")
    private WebElement password;

    @FindBy(xpath = "//div[text()='Login ']")
    private WebElement loginBtn;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void login(String user, String pass) {
        SeleniumUtil.type(username, user);
        SeleniumUtil.type(password, pass);
        SeleniumUtil.click(loginBtn);
    }
}
