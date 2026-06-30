package com.detective.page.saucedemo.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class InventoryItem extends AbstractUIObject {

    @FindBy(css = "a[id*='title_link']")
    private ExtendedWebElement titleLink;

    @FindBy(className = "inventory_item_name")
    private ExtendedWebElement nameLabel;

    @FindBy(className = "inventory_item_price")
    private ExtendedWebElement price;

    @FindBy(xpath = ".//button[contains(@class,'btn_inventory')]")
    private ExtendedWebElement addToCartButton;

    public InventoryItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getNameLink() {
        return titleLink;
    }

    public String getNameText() {
        return nameLabel.getText();
    }

    public String getProductId() {
        String linkId = titleLink.getAttribute("id");
        return linkId.split("_")[1];
    }

    public void clickNameLink() {
        titleLink.scrollTo();
        titleLink.click();
    }

    public ExtendedWebElement getPrice() {
        return price;
    }

    public String getPriceText() {
        return price.getText();
    }

    public void clickAddToCart() {
        addToCartButton.click();
    }

    public boolean isAddToCartButtonPresent() {
        return addToCartButton.isElementPresent(1);
    }
}
