import browser.Base;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class ConstructorTest  extends Base {
    Faker faker = new Faker();
    private final String email = faker.internet().emailAddress();
    private final String name = faker.name().firstName();
    private final String password = "123456";
    private final UserApi apiHelper = new UserApi();
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;

    @Before
    public void setUpPage() {
        apiHelper.createUser(email, password, name);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.login(email, password);
        mainPage.clickPersonalAccount(); // переходим в профиль
    }

    @After
    public void deleteUser() {
        apiHelper.deleteUser(email, password);
    }

    @Test
    @DisplayName("Перехода в конструктор по кнопке 'Конструктор'")
    @Description("Переход в конструктор по кнопке 'Конструктор' в личном кабинете")
    public void userNavigateToConstructorButtonTest() {
        profilePage.clickConstructorButton();
        assertTrue("Не отобразилась кнопка 'Оформить заказ'",
                mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Переход в конструктор по логотипу")
    @Description("Проверка перехода в конструктор по клику на логотип 'Stellar Burgers' в личном кабинете")
    public void userNavigateToConstructorLogoTest() {
        profilePage.clickLogo();
        assertTrue("Не отобразилась кнопка 'Оформить заказ'",
                mainPage.isOrderButtonVisible());
    }
}
