package com.geekbrains.homework6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    private final static String LIKE_BUTTON_LOCATOR_BY_XPATH = "//span[@data-controller='Fav']";


    @FindBy(xpath = LIKE_BUTTON_LOCATOR_BY_XPATH)
    private WebElement likeButton;

    public ProductPage likeProduct() {
        likeButton.click();
        return this;
    }
    @FindBy(xpath = "//a[contains(@href, 'favorite')]")
    private WebElement favoriteButton;

    public ProductPage favoriteProduct() {
        favoriteButton.click();
        return this;
    }
    private final static String ADDED_TO_FAV_XPATH_LOCATOR = "//a[.='Золотые серьги с ситал хризолитом и фианитом арт. 12390сх']";
    @FindBy(xpath = ADDED_TO_FAV_XPATH_LOCATOR)
    private WebElement addedToFavoriteElement;
    public void checkFavProduct() {
        assertThat(addedToFavoriteElement, isDisplayed());
    }




}
