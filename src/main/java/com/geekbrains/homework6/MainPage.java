package com.geekbrains.homework6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//a[.='Войти']")
    private WebElement loginButton;

    public  LoginPage clickLoginButton() {
        loginButton.click();
        return new LoginPage(driver);
    }

    @FindBy(xpath = "//div[@id='mainMenu']/ul/li/a[.='Каталог продукции']")
    private WebElement catalogButton;

    public  MainPage clickCatalogButton() {
        catalogButton.click();
        return this;
    }

    @FindBy(xpath = "//li[@class='nav-item']/a[.='Контакты']")
    private WebElement contactsButton;

    public MainPage clickContactsButton() {
        contactsButton.click();
        return this;
    }
    @FindBy(xpath = "//div[@class='social-networks-item']/a[contains(@href, 'vk.com')]")
    private WebElement vkButton;

    public MainPage clickVkButton() {
        vkButton.click();
        return this;
    }
    private final static String VK_PAGE = "//a[.='https://самоцветы.рф']";
    @FindBy (xpath = VK_PAGE)
    private WebElement goToVKPage;
    public void checkVKPage() {
        assertThat(goToVKPage, isDisplayed());
    }

    @FindBy(xpath = "//div/a[@title='Серьги']")
    private WebElement earringsButton;

    public  MainPage clickEarringsButton() {
        earringsButton.click();
        return this;
    }
    @FindBy(xpath = "//div/a[@title='Значки']")
    private WebElement badgeButton;

    public  MainPage clickBadgeButton() {
        badgeButton.click();
        return this;
    }

    @FindBy(xpath = "//a[contains(@href, 'sergi')]/ancestor::div[@data-controller='CatalogItem']")
    private List<WebElement> catalogEarrings;
    @FindBy(xpath = "//a[contains(@href, 'znachki')]/ancestor::div[@data-controller='CatalogItem']")
    private List<WebElement> catalogBadge;

    public ProductPage clickProductByName(String productName) {
        catalogEarrings.stream().filter(f -> f.getText().contains(productName)).findFirst().get().click();
        return new ProductPage(driver);
    }

    public SetProductPage clickProductByNameTwo(String productNameTwo) {
        catalogBadge.stream().filter(f -> f.getText().contains(productNameTwo)).findFirst().get().click();
        return new SetProductPage(driver);
    }





    @FindAll({@FindBy(xpath = "//div"),
    @FindBy(xpath = "//a")})
    private List<WebElement> testList;

    @FindBys({
            @FindBy(xpath = "//div"),
            @FindBy(xpath = "//a")
    })
    private WebElement test;

}
