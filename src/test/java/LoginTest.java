import api.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.User;

import static org.junit.Assert.assertTrue;

/* Вход
Проверь:
вход по кнопке «Войти в аккаунт» на главной,
вход через кнопку «Личный кабинет»,
вход через кнопку в форме регистрации,
вход через кнопку в форме восстановления пароля.*/

public class LoginTest extends BaseTest {
    private final UserApi apiHelper = new UserApi();
    private MainPage mainPage;
    private LoginPage loginPage;
    Faker faker = new Faker();
    private User user;

    @Before
    public void setUpPage() {
        user = new User(faker.internet().emailAddress(), "123456", faker.name().firstName());
        apiHelper.createUser(user);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
    }

    @After
    public void deleteUser() {
        apiHelper.deleteUser(user);
    }

    @Test
    @DisplayName("Войти в аккаунт")
    @Description("Войти через кнопку 'Войти в аккаунт' на главной странице")
    public void loginFromMainPageTest() {
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Личный кабинет")
    @Description("Войти через кнопку 'Личный кабинет' на главной странице")
    public void loginFromPersonalAccountTest() {
        mainPage.open();
        mainPage.clickPersonalAccount();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Cсылка из формы регистрации")
    @Description("Войти по ссылке из формы регистрации")
    public void loginFromRegisterFormTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.clickLoginLink();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Восстановления пароля")
    @Description("Вход по ссылке из формы восстановления пароля")
    public void loginFromForgotPasswordTest() {
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.openForgotPasswordPage();
        loginPage.clickLoginFromForgotPassword();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isOrderButtonVisible());
    }
}
