import api.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.User;

import static org.junit.Assert.assertTrue;

/* Регистрация
Проверь:
Успешную регистрацию.
Ошибку для некорректного пароля. Минимальный пароль — шесть символов.*/

public class PersonalAccountTest extends BaseTest {
    Faker faker = new Faker();
    private final UserApi userApi = new UserApi();
    private MainPage mainPage;
    private ProfilePage profilePage;
    private User user;

    @Before
    public void setUpPage() {
        user = new User(faker.internet().emailAddress(), faker.internet().password(6, 6), faker.name().firstName());
        userApi.createUser(user);
        mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        // Вход
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
    }

    @After
    public void deleteUser() {
        userApi.deleteUser(user);
    }

    @Test
    @DisplayName("Преход в личный кабинет")
    @Description("Переход в личный кабинет по клику на 'Личный кабинет'")
    public void userCanOpenPersonalAccountTest() {
        mainPage.clickPersonalAccount();
        assertTrue("Не отображается кнопка 'Выход'", profilePage.isLogoutButtonVisible());
    }
}