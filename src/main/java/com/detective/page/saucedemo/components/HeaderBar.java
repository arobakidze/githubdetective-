package com.detective.page.saucedemo.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderBar extends AbstractUIObject {

    @FindBy(className = "shopping_cart_link")
    private ExtendedWebElement cartLink;

    @FindBy(className = "shopping_cart_badge")
    private ExtendedWebElement cartBadge;

    public HeaderBar(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getCartLink() {
        return cartLink;
    }

    public void openCart() {
        cartLink.click();
    }

    public ExtendedWebElement getCartBadge() {
        return cartBadge;
    }

    public String getCartBadgeText() {
        return cartBadge.getText();
    }

    public boolean isCartBadgePresent() {
        return cartBadge.isElementPresent(1);
    }

    public void waitForCartBadgeCount(int expectedCount) {
        String expectedText = String.valueOf(expectedCount);
        By badgeLocator = By.className("shopping_cart_badge");
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(200))
                .until(d -> {
                    java.util.List<org.openqa.selenium.WebElement> badges = d.findElements(badgeLocator);
                    return !badges.isEmpty() && expectedText.equals(badges.get(0).getText());
                });
    }
}
