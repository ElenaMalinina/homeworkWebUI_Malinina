import com.geekbrains.homework4.Formula;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriangleTest {
    private static Logger logger = LoggerFactory.getLogger("TriangleTest.class");
    @Nested
    @DisplayName("Тесты по параметрам треугольника")
    class MyTests {
        @Test
        @DisplayName("Треугольник с правильными параметрами")
        void createGoodTriangle() {
            new Formula(100, 333, 333);
            Formula formula = new Formula(100, 333, 333);
            Assertions.assertEquals(formula.formula(), 16461.242358947275);
            logger.info("log data");
        }
        @Test
        @DisplayName("Один из параметров треугольника - отрицательное число")
        void cantCreateGoodTriangle() {
            Assertions.assertThrows(IllegalArgumentException.class,() -> new Formula(100, 333, -333));
        }
    }
}
