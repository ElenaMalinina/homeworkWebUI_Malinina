package homework6;

import com.geekbrains.homework6.MainPage;
import com.geekbrains.lesson7.CustomLoggerNew;
import com.geekbrains.lesson7.CustomLoggerThirdSelenium;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Iterator;

@Story("Работа с разделом Контакты")
public class SamozvetyPageContactsTest {
    WebDriver driver;
    //EventFiringWebDriver eventFiringWebDriver;
    private final static String SAMOZVETY_URL = "https://самоцветы.рф";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void initDriver() {
        //driver = new ChromeDriver();
//        eventFiringWebDriver = new EventFiringWebDriver(new ChromeDriver());
//        eventFiringWebDriver.register(new CustomLoggerThirdSelenium()); 3 SELENIUM
        driver = new EventFiringDecorator(new CustomLoggerNew()).decorate(new ChromeDriver());
        driver.get(SAMOZVETY_URL);
    }

    @Test
    @Feature("В Контакте")
    @Description("Проверка перехода по ссылке на страницу магазина В Контакте")
    void goVKPageTest() throws InterruptedException {

        new MainPage(driver).clickLoginButton()
                .fillLogin("test123@yandex.ru")
                .fillPassword("123456789")
                .clickLoginButton()
                .clickContactsButton()
                .clickVkButton()
                .checkVKPage();


    }
    @AfterEach
    void killDriver() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);

        for (LogEntry log: logEntries) {
            Allure.addAttachment("Лог браузера", log.getMessage());
        }
        driver.quit();
    }
}
