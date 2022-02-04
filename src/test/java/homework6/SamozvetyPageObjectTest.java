package homework6;

import com.geekbrains.homework6.MainPage;
import com.geekbrains.lesson7.CustomLoggerNew;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;

@Story("Операции с товарами")
public class SamozvetyPageObjectTest {
    WebDriver driver;
    private final static String SAMOZVETY_URL = "https://самоцветы.рф";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void initDriver() {
        driver = new EventFiringDecorator(new CustomLoggerNew()).decorate(new ChromeDriver());
        driver.get(SAMOZVETY_URL);
    }

    @Test
    @Description("Проверка добавления товара в Избранное")
    void likeRandomProductTest() throws InterruptedException {

        new MainPage(driver).clickLoginButton()
                .fillLogin("test123@yandex.ru")
                .fillPassword("123456789")
                .clickLoginButton()
                .clickCatalogButton()
                .clickEarringsButton()
                .clickProductByName("Золотые серьги с ситал хризолитом и фианитом арт. 12390сх")
                .likeProduct()
                .favoriteProduct()
                .checkFavProduct();
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
