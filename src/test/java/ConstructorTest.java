import api.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.User;

import static org.junit.Assert.assertTrue;

public class ConstructorTest  extends BaseTest {
    Faker faker = new Faker();
    private final User user = new User(faker.internet().emailAddress(), "123456", faker.name().firstName());
    private final UserApi apiHelper = new UserApi();
    private MainPage mainPage;
    private ProfilePage profilePage;

    @Before
    public void setUpPage() {
        apiHelper.createUser(user);
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        mainPage.open();
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAccount(); // переходим в профиль
    }

    @After
    public void deleteUser() { apiHelper.deleteUser(user); }

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
