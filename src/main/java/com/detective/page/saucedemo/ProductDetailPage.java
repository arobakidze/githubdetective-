package com.detective.page.saucedemo;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailPage extends AbstractPage {

    @FindBy(className = "inventory_details_name")
    private ExtendedWebElement name;

    @FindBy(className = "inventory_details_price")
    private ExtendedWebElement price;

    @FindBy(id = "back-to-products")
    private ExtendedWebElement backToProductsButton;

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilLoaded() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(200))
                .until(d -> d.getCurrentUrl().contains("inventory-item")
                        && name.isElementPresent(3));
    }

    public ExtendedWebElement getName() {
        return name;
    }

    public String getNameText() {
        return name.getText();
    }

    public ExtendedWebElement getPrice() {
        return price;
    }

    public String getPriceText() {
        return price.getText();
    }

    public void clickBackToProducts() {
        backToProductsButton.click();
    }
}
