package homework6;

import com.geekbrains.homework6.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SamozvetyPageOrderTest {
    WebDriver driver;
    private final static String SAMOZVETY_URL = "https://самоцветы.рф";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        driver.get(SAMOZVETY_URL);
    }

    @Test
    @DisplayName("Добавление товара в корзину с проверкой содержимого корзины")
    void SetOrderTest() throws InterruptedException {

        new MainPage(driver).clickLoginButton()
                .fillLogin("test123@yandex.ru")
                .fillPassword("123456789")
                .clickLoginButton()
                .clickCatalogButton()
                .clickBadgeButton()
                .clickProductByNameTwo("Звезда на погоны из золота арт. 74122")
                .addProductCart()
                .cartProduct()
                .checkSetProduct();
    }
    @AfterEach
    void killDriver() {

        driver.quit();
    }
}
