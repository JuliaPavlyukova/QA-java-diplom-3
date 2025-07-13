import browser.Base;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

    /* Раздел «Конструктор»
    Проверь, что работают переходы к разделам:
    «Булки»,
    «Соусы»,
    «Начинки».*/

    public class ConstructorTabsTest  extends Base {
        private MainPage mainPage;

        @Before
        public void setUpPage() {
            mainPage = new MainPage(driver);
            mainPage.open();
        }

        @Test
        @DisplayName("Переход на 'Булки'")
        @Description("Работает переход к разделу 'Булки'")
        public void userCanSwitchToBunTabTest() {
            mainPage.clickFillingTab();
            mainPage.clickBunTab();
            assertEquals("Булки", mainPage.getActiveTabText());
        }

        @Test
        @DisplayName("Пререход на 'Соусы'")
        @Description("Работает переход к разделу 'Соусы'")
        public void userCanSwitchToSauceTabTest() {
            mainPage.clickSauceTab();
            assertEquals("Соусы", mainPage.getActiveTabText());
        }

        @Test
        @DisplayName("Переход на 'Начинки'")
        @Description("Работает переход к разделу 'Начинки'")
        public void userCanSwitchToFillingTabTest() {
            mainPage.clickFillingTab();
            assertEquals("Начинки", mainPage.getActiveTabText());
        }
    }