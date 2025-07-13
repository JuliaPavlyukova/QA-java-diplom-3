import browser.Base;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/* Вход
Проверь:
вход по кнопке «Войти в аккаунт» на главной,
вход через кнопку «Личный кабинет»,
вход через кнопку в форме регистрации,
вход через кнопку в форме восстановления пароля.*/

public class LoginTest extends Base {
    private final UserApi apiHelper = new UserApi();
    private MainPage mainPage;
    private LoginPage loginPage;
    Faker faker = new Faker();
    private final String email = faker.internet().emailAddress();
    private final String name = faker.name().firstName();
    private final String password = "123456";

    @Before
    public void setUpPage() {
        apiHelper.createUser(email, password, name);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
    }

    @After
    public void deleteUser() {
        apiHelper.deleteUser(email, password);
    }

    @Test
    @DisplayName("Войти в аккаунт")
    @Description("Войти через кнопку 'Войти в аккаунт' на главной странице")
    public void loginFromMainPageTest() {
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.login(email, password);
        assertTrue(mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Личный кабинет")
    @Description("Войти через кнопку 'Личный кабинет' на главной странице")
    public void loginFromPersonalAccountTest() {
        mainPage.open();
        mainPage.clickPersonalAccount();
        loginPage.login(email, password);
        assertTrue(mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Cсылка из формы регистрации")
    @Description("Войти по ссылке из формы регистрации")
    public void loginFromRegisterFormTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.clickLoginLink();
        loginPage.login(email, password);
        assertTrue(mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Восстановления пароля")
    @Description("Вход по ссылке из формы восстановления пароля")
    public void loginFromForgotPasswordTest() throws InterruptedException {
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.openForgotPasswordPage();
        loginPage.clickLoginFromForgotPassword();
        loginPage.login(email, password);
        assertTrue(mainPage.isOrderButtonVisible());
    }
}
