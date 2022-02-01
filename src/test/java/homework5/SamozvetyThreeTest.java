package homework5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SamozvetyThreeTest {
    static WebDriver driver;
    static WebDriverWait webDriverWait;
    static Actions actions;
    private final static String SAMOZVETY_URL = "https://самоцветы.рф";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get(SAMOZVETY_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }


    @Test
    @DisplayName("Оформление заказа авторизованным пользователем")
    void FavoriteProductTest() throws InterruptedException {
        Cookie cookie = new Cookie("_identity", "4a28d4b1c003eb592178c9b581b719e9a93773b4b946ae1531de2cacae" +
                "4e381ba%3A2%3A%7Bi%3A0%3Bs%3A9%3A%22_identity%22%3Bi%3A1%3Bs%3A49%3A%22%5B9789%2C%222qu8qkxWFbJvn2rnSw" +
                "Dxe0mNMtBKXAND%22%2C2592000%5D%22%3B%7D");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();

        Thread.sleep(3000);
        actions.moveToElement(driver.findElement(By.xpath("//li[@class='nav-item menu-catalog-item active']/a[.='Каталог продукции']")))
                .click()
                .build()
                .perform();
        driver.findElement(By.xpath("//div/a[@title='Упаковка и уход']")).click();
        List<WebElement> catalogBadge = driver.findElements(By.xpath("//a[contains(@href, 'upakovka')]/ancestor::div[@data-controller='CatalogItem']"));
        catalogBadge.stream().filter(f -> f.getText().contains("Футляр арт. 7530100")).findFirst().get().click();
        driver.findElement(By.xpath("//a[.='В корзину']")).click();
        driver.findElement(By.xpath("//a[.='Перейти в корзину']")).click();
        driver.findElement(By.xpath("//a[.='Перейти к оформлению']")).click();
        driver.findElement(By.id("checkoutform-phone")).sendKeys("1111111111");
        driver.findElement(By.id("checkoutform-delivery")).click();
        driver.findElement(By.xpath("//option[.='Забрать в магазине']")).click();
        driver.findElement(By.id("checkoutform-delivery_department")).click();
        driver.findElement(By.xpath("//option[.='Богдана Хмельницкого, дом 103']")).click();
        driver.findElement(By.id("checkoutform-payment")).click();
        driver.findElement(By.xpath("//option[.='При получении']")).click();
        driver.findElement(By.id("checkoutform-policy")).click();
        driver.findElement(By.xpath("//button[.=' Завершить оформление ']")).click();
        Thread.sleep(3000);
        assertTrue(driver.getCurrentUrl().contains("order"));
        Thread.sleep(3000);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
