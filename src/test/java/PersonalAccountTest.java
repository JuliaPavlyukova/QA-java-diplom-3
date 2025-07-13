import browser.Base;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/* Регистрация
Проверь:
Успешную регистрацию.
Ошибку для некорректного пароля. Минимальный пароль — шесть символов.*/

public class PersonalAccountTest extends Base {
    Faker faker = new Faker();
    private final String email = faker.internet().emailAddress();
    private final String name = faker.name().firstName();
    private final String password = "123456";
    private final UserApi userApi = new UserApi();
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;

    @Before
    public void setUpPage() {
        userApi.createUser(email, password, name);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        // Вход
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.login(email, password);
    }

    @After
    public void deleteUser() {
        userApi.deleteUser(email, password);
    }

    @Test
    @DisplayName("Преход в личный кабинет")
    @Description("Переход в личный кабинет по клику на 'Личный кабинет'")
    public void userCanOpenPersonalAccountTest() {
        mainPage.clickPersonalAccount();
        assertTrue("Не отображается кнопка 'Выход'", profilePage.isLogoutButtonVisible());
    }
}