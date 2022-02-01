package com.geekbrains.homework6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class SetProductPage extends BasePage {
    public SetProductPage(WebDriver driver) {
        super(driver);
    }
   private final static String CART_BY_XPATH = "//a[.='В корзину']";


    @FindBy(xpath = CART_BY_XPATH)
    private WebElement cartButton;

    public SetProductPage addProductCart() throws InterruptedException {
        cartButton.click();
        Thread.sleep(3000);
        return this;
    }
    @FindBy(xpath = "//a[.='Перейти в корзину']")
    private WebElement goToCartButton;

    public SetProductPage cartProduct() {
        goToCartButton.click();
        return this;
    }

    private final static String SET_PRODUCT_XPATH_LOCATOR = "//a[.='Звезда на погоны из золота']";
    @FindBy(xpath = SET_PRODUCT_XPATH_LOCATOR)
    private WebElement setProductElement;
    public void checkSetProduct() {
        assertThat(setProductElement, isDisplayed());
    }




}
