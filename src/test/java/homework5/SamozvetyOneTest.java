package homework5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SamozvetyOneTest {
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
    }


    @Test
    @DisplayName("Добавление товара в избранное")
    void FavoriteProductTest() throws InterruptedException {
        Cookie cookie = new Cookie("_identity", "4a28d4b1c003eb592178c9b581b719e9a93773b4b946ae1531de2cacae" +
                "4e381ba%3A2%3A%7Bi%3A0%3Bs%3A9%3A%22_identity%22%3Bi%3A1%3Bs%3A49%3A%22%5B9789%2C%222qu8qkxWFbJvn2rnSw" +
                "Dxe0mNMtBKXAND%22%2C2592000%5D%22%3B%7D");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
        Thread.sleep(3000);
        actions.moveToElement(driver.findElement(By.xpath("//input[@placeholder='Поиск']")))
                .click()
                .sendKeys("ложки")
                .build()
                .perform();
        driver.findElement(By.xpath("//button[@class='search-icon']")).click();
        driver.findElement(By.xpath("//div[contains(@data-sku-list, '3424528')]")).click();
        driver.findElement(By.xpath("//span[@data-controller='Fav']")).click();
        driver.findElement(By.xpath("//a[contains(@href, 'favorite')]")).click();
        driver.findElement(By.xpath("//div[contains(@data-sku-list, '3424528')]")).click();
        assertTrue(driver.getCurrentUrl().contains("200424441"));
        Thread.sleep(3000);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
