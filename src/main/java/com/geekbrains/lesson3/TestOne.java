package com.geekbrains.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
//Добавление товара из каталога в Избранное
public class TestOne {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-popup-blocking");
        WebDriver driver = new ChromeDriver();
        driver.get("https://самоцветы.рф/");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //WebElement loginButton = driver.findElement(By.xpath("//a[.='Войти']"));
        //loginButton.click();
        //На главной странице сайта в правом верхнем углу нажать кнопку "Войти"
        driver.findElement(By.xpath("//a[.='Войти']")).click();
        //Ввести авторизационные данные и нажать кнопку Войти
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginform-username")));
        driver.findElement(By.id("loginform-username")).sendKeys("test123@yandex.ru");
        driver.findElement(By.id("loginform-password")).sendKeys("123456789");
        driver.findElement(By.xpath("//button[.='Войти']")).click();
        Thread.sleep(3000);
        //Нажать кнопку "Каталог продукции"
        driver.findElement(By.xpath("//div[@id='mainMenu']/ul/li/a[.='Каталог продукции']")).click();
        //Найти раздел "Серьги" и нажать на него
        driver.findElement(By.xpath("//div/a[@title='Серьги']")).click();
        List<WebElement> catalogEarrings = driver.findElements(By.xpath("//a[contains(@href, 'sergi')]/ancestor::div[@data-controller='CatalogItem']"));
        //Найти товар "Золотые серьги с ситал хризолитом и фианитом арт. 12390сх" и нажать на него
        catalogEarrings.stream().filter(f -> f.getText().contains("Золотые серьги с ситал хризолитом и фианитом арт. 12390сх")).findFirst().get().click();
        //Нажать кнопку "Добавить в избранное"
        driver.findElement(By.xpath("//span[@data-controller='Fav']")).click();
        //Нажать кнопку "Избранное" в правом верхнем углу страницы
        driver.findElement(By.xpath("//a[contains(@href, 'favorite')]")).click();
        Thread.sleep(10000);
        driver.quit();
    }
}
