package com.geekbrains.homework6;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[.='Войти']")
    private WebElement loginFrame;

    @Step("Переключиться во фрейм авторизации")
    public LoginPage switchToLoginFrame() {
        driver.switchTo().frame(loginFrame);
        return this;
    }
    private final static String LOGIN_INPUT_LOCATOR_BY_ID = "loginform-username";

    @FindBy(id = LOGIN_INPUT_LOCATOR_BY_ID)
    public WebElement loginInput;
    @FindBy(id = "loginform-password")
    public WebElement passwordInput;
    @FindBy(xpath =  "//button[.='Войти']")
    public WebElement loginButton;

    @Step("Заполнить поле логина")
    public LoginPage fillLogin(String login) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LOGIN_INPUT_LOCATOR_BY_ID)));
        loginInput.sendKeys(login);
        return this;
    }
    @Step("Заполнить поле пароля")
    public LoginPage fillPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }
    @Step("Клик на кнопку Войти")
    public MainPage clickLoginButton() throws InterruptedException {
        loginButton.click();
        Thread.sleep(5000);
        return new MainPage(driver);

    }
}
