package com.detective.page.saucedemo;

import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(id = "user-name")
    private ExtendedWebElement usernameInput;

    @FindBy(id = "password")
    private ExtendedWebElement passwordInput;

    @FindBy(id = "login-button")
    private ExtendedWebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(Configuration.getRequired("saucedemo_url"));
    }

    public InventoryPage login(String username, String password) {
        usernameInput.type(username);
        passwordInput.type(password);
        loginButton.click();
        return new InventoryPage(getDriver());
    }

    public InventoryPage loginStandardUser() {
        return login(
                Configuration.getRequired("saucedemo_username"),
                Configuration.getRequired("saucedemo_password")
        );
    }
}
