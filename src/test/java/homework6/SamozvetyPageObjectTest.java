package homework6;

import com.geekbrains.homework6.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SamozvetyPageObjectTest {
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
    @DisplayName("Добавление товара в избраннное с проверкой")
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

        driver.quit();
    }
}
