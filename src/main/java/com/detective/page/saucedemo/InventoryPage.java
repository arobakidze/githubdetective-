package com.detective.page.saucedemo;

import com.detective.page.saucedemo.components.HeaderBar;
import com.detective.page.saucedemo.components.InventoryItem;
import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage extends AbstractPage {

    @FindBy(className = "header_container")
    private HeaderBar headerBar;

    @FindBy(className = "inventory_item")
    private List<InventoryItem> inventoryItems;

    @FindBy(className = "product_sort_container")
    private ExtendedWebElement sortDropdown;

    @FindBy(xpath = "//button[contains(@id,'add-to-cart')]")
    private List<ExtendedWebElement> addToCartButtons;

    public InventoryPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(Configuration.getRequired("saucedemo_inventory_url"));
    }

    public HeaderBar getHeaderBar() {
        return headerBar;
    }

    public void waitUntilItemsPresent() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(200))
                .until(d -> isInventoryListingPage(d.getCurrentUrl())
                        && !getInventoryItems().isEmpty());
    }

    public void waitForCartBadgeCount(int expectedCount) {
        By badgeLocator = By.cssSelector(".shopping_cart_badge");
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(200))
                .until(ExpectedConditions.textToBe(badgeLocator, String.valueOf(expectedCount)));
    }

    public String getCartBadgeText() {
        By badgeLocator = By.cssSelector(".shopping_cart_badge");
        java.util.List<WebElement> badges = getDriver().findElements(badgeLocator);
        return badges.isEmpty() ? "" : badges.get(0).getText().trim();
    }

    public void addProductsToCartByIndex(int firstIndex, int secondIndex) {
        addToCartButtons.get(firstIndex).click();
        addToCartButtons.get(secondIndex).click();
    }

    public void returnToInventoryListing() {
        getDriver().get(Configuration.getRequired("saucedemo_inventory_url"));
        waitUntilItemsPresent();
    }

    private boolean isInventoryListingPage(String url) {
        return url.contains("/inventory.html") && !url.contains("inventory-item");
    }

    public List<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }

    public void sortByPriceLowToHigh() {
        new Select(sortDropdown.getElement()).selectByValue("lohi");
        waitUntilItemsPresent();
    }

    public ProductDetailPage openProductDetailsByIndex(int index) {
        InventoryItem item = getInventoryItems().get(index);
        item.clickNameLink();
        WebDriver driver = getDriver();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(d -> d.getCurrentUrl().contains("inventory-item"));
        } catch (TimeoutException timeoutException) {
            driver.get(String.format("https://www.saucedemo.com/inventory-item.html?id=%s", item.getProductId()));
        }
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        productDetailPage.waitUntilLoaded();
        return productDetailPage;
    }
}
