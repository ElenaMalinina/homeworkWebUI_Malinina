package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class DiaryTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    private final static String DIARY_URL = "https://diary.ru";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get(DIARY_URL);

    }
    @Test
    void diaryCookieTest() throws InterruptedException {
        Cookie cookie = new Cookie("_identity_", "d540853dd10da9b94235aba178f5575db0add98c321ea5a61c1a05b65d" +
                "9e1131a%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3562672%2C%22g9BiIahWHWyb1v" +
                "_6hcvERceHP7g9rb07%22%2C2592000%5D%22%3B%7D");
        driver.manage().addCookie(cookie);
        //driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        Thread.sleep(5000);
    }


    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
