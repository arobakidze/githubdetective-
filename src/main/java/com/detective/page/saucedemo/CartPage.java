package com.detective.page.saucedemo;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends AbstractPage {

    @FindBy(className = "inventory_item_name")
    private List<ExtendedWebElement> itemNames;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getItemNameTexts() {
        return itemNames.stream()
                .map(ExtendedWebElement::getText)
                .collect(Collectors.toList());
    }
}
