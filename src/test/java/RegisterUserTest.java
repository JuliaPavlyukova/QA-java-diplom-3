import api.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.User;

import static org.junit.Assert.assertTrue;

public class RegisterUserTest extends BaseTest {
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private PageHelpers pageHelpers;
    private final UserApi userApi = new UserApi();
    Faker faker = new Faker();
    private User user;
    private final String password = "111356";
    private boolean userWasCreated = false;

    @Before
    public void setUpPage() {
        registerPage = new RegisterPage(driver);
        pageHelpers = new PageHelpers(driver);
        loginPage = new LoginPage(driver);
        registerPage.open();
        user = new User(faker.internet().emailAddress(), password, faker.name().firstName());
    }

    @After
    public void deleteUser() {
        if (userWasCreated) {
            userApi.deleteUser(user);
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрации пользователя с валидными данными")
    public void userCanRegisterSuccessfullyTest() {
        registerPage.register(user.getName(), user.getEmail(), password);
        pageHelpers.waitForElement(loginPage.getLoginHeader());
        userWasCreated = true;
        // проверяем наличие текста заголовка 'Вход'
        assertTrue(driver.findElement(loginPage.getLoginHeader()).isDisplayed());
    }

    @Test
    @DisplayName("Пароль короче 6 символов")
    @Description("Ошибка при регистрации с паролем короче 6 символов")
    public void userNotRegisterWithShortPasswordTest() {
        registerPage.register(user.getName(), user.getEmail(), "12345");
        assertTrue(driver.getPageSource().contains("Некорректный пароль"));
    }
}
