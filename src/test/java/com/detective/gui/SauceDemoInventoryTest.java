package com.detective.gui;

import com.detective.page.saucedemo.CartPage;
import com.detective.page.saucedemo.InventoryPage;
import com.detective.page.saucedemo.LoginPage;
import com.detective.page.saucedemo.ProductDetailPage;
import com.detective.page.saucedemo.components.InventoryItem;
import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class SauceDemoInventoryTest extends AbstractTest {

    @BeforeMethod
    public void loginBeforeEach() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.loginStandardUser();
    }

    @Test
    public void verifyInventoryShowsSixProducts() {
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.waitUntilItemsPresent();

        List<InventoryItem> items = inventoryPage.getInventoryItems();
        Assert.assertEquals(items.size(), 6, "Inventory page should display six products");
    }

    @Test
    public void verifySortByPriceLowToHigh() {
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.waitUntilItemsPresent();
        inventoryPage.sortByPriceLowToHigh();

        List<Double> prices = new ArrayList<>();
        for (InventoryItem item : inventoryPage.getInventoryItems()) {
            prices.add(parsePrice(item.getPriceText()));
        }

        for (int i = 1; i < prices.size(); i++) {
            Assert.assertTrue(prices.get(i) >= prices.get(i - 1),
                    "Product prices should be sorted from low to high");
        }
    }

    @Test
    public void verifyAddToCartUpdatesBadge() {
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.waitUntilItemsPresent();
        inventoryPage.addProductsToCartByIndex(0, 1);
        inventoryPage.waitForCartBadgeCount(2);

        Assert.assertEquals(inventoryPage.getCartBadgeText(), "2",
                "Cart badge should show two items after adding two products");
    }

    @Test
    public void verifyProductDetailRoundTrip() {
        WebDriver driver = getDriver();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.waitUntilItemsPresent();

        String expectedName = inventoryPage.getInventoryItems().get(0).getNameText();
        String expectedPrice = inventoryPage.getInventoryItems().get(0).getPriceText();

        ProductDetailPage productDetailPage = inventoryPage.openProductDetailsByIndex(0);
        Assert.assertEquals(productDetailPage.getNameText(), expectedName,
                "Product detail name should match inventory item name");
        Assert.assertEquals(productDetailPage.getPriceText(), expectedPrice,
                "Product detail price should match inventory item price");

        productDetailPage.clickBackToProducts();

        InventoryPage returnedInventoryPage = new InventoryPage(driver);
        returnedInventoryPage.returnToInventoryListing();
        Assert.assertEquals(returnedInventoryPage.getInventoryItems().size(), 6,
                "Inventory page should show six products after returning from detail page");
    }

    @Test
    public void verifyCartContainsAddedProducts() {
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.waitUntilItemsPresent();

        String firstProductName = inventoryPage.getInventoryItems().get(0).getNameText();
        String secondProductName = inventoryPage.getInventoryItems().get(1).getNameText();

        inventoryPage.getInventoryItems().get(0).clickAddToCart();
        inventoryPage.getInventoryItems().get(1).clickAddToCart();
        inventoryPage.getHeaderBar().openCart();

        CartPage cartPage = new CartPage(getDriver());
        List<String> cartItemNames = cartPage.getItemNameTexts();

        Assert.assertTrue(cartItemNames.contains(firstProductName),
                "Cart should contain the first added product");
        Assert.assertTrue(cartItemNames.contains(secondProductName),
                "Cart should contain the second added product");
    }

    @Test
    public void verifyAllInventoryItemsHaveNameAndPrice() {
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.waitUntilItemsPresent();

        List<InventoryItem> items = inventoryPage.getInventoryItems();
        SoftAssert softAssert = new SoftAssert();

        for (InventoryItem item : items) {
            softAssert.assertFalse(item.getNameText().isEmpty(),
                    "Inventory item name should not be empty");
            softAssert.assertTrue(item.getPriceText().startsWith("$"),
                    "Inventory item price should start with $");
            softAssert.assertTrue(item.isAddToCartButtonPresent(),
                    "Add to cart button should be present on inventory item");
        }

        softAssert.assertAll();
    }

    private double parsePrice(String priceText) {
        return Double.parseDouble(priceText.replace("$", ""));
    }
}
