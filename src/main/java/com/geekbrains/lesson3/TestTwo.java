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

//Оформление заказа авторизованным пользователем

public class TestTwo {
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
        //Найти раздел "Значки" и нажать на него
        driver.findElement(By.xpath("//div/a[@title='Значки']")).click();
        List<WebElement> catalogBadge = driver.findElements(By.xpath("//a[contains(@href, 'znachki')]/ancestor::div[@data-controller='CatalogItem']"));
        //Найти товар "Звезда на погоны из золота" и нажать на него
        catalogBadge.stream().filter(f -> f.getText().contains("Звезда на погоны из золота арт. 74122")).findFirst().get().click();
        //Нажать кнопку "В корзину"
        driver.findElement(By.xpath("//a[.='В корзину']")).click();
        Thread.sleep(3000);
        //Нажать кнопку "Перейти в корзину"
        driver.findElement(By.xpath("//a[.='Перейти в корзину']")).click();
        //Нажать кнопку "Перейти к оформлению"
        driver.findElement(By.xpath("//a[.='Перейти к оформлению']")).click();
        //Ввести обязательные данные для оформления заказа
        driver.findElement(By.id("checkoutform-phone")).sendKeys("1111111111");
        Thread.sleep(3000);
        driver.findElement(By.id("checkoutform-delivery")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//option[.='Забрать в магазине']")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("checkoutform-delivery_department")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//option[.='Богдана Хмельницкого, дом 103']")).click();
        driver.findElement(By.id("checkoutform-payment")).click();
        driver.findElement(By.xpath("//option[.='При получении']")).click();
        driver.findElement(By.id("checkoutform-policy")).click();
        Thread.sleep(3000);
        //Нажать кнопку "Завершить оформление"
        driver.findElement(By.xpath("//button[.=' Завершить оформление ']")).click();
        Thread.sleep(5000);
        driver.quit();
    }
}

