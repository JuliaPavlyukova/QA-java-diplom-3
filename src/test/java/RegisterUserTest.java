import browser.Base;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class RegisterUserTest extends Base {
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private final UserApi userApi = new UserApi();
    Faker faker = new Faker();
    private final String email = faker.internet().emailAddress();
    private final String name = faker.name().firstName();
    private final String password = "111356";
    private boolean userWasCreated = false;

    @Before
    public void setUpPage() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        registerPage.open();
    }

    @After
    public void deleteUser() {
        if (userWasCreated) {
            userApi.deleteUser(email, password);
            System.out.println("удалили "+ email +" " + password);
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрации пользователя с валидными данными")
    public void userCanRegisterSuccessfullyTest() {
        registerPage.register(name, email, password);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginHeader()));
        userWasCreated = true;
        // проверяем наличие текста заголовка 'Вход'
        assertTrue(driver.findElement(loginPage.getLoginHeader()).isDisplayed());
    }

    @Test
    @DisplayName("Пароль короче 6 символов")
    @Description("Ошибка при регистрации с паролем короче 6 символов")
    public void userNotRegisterWithShortPasswordTest() {
        registerPage.register(name, email, "12345");
        assertTrue(driver.getPageSource().contains("Некорректный пароль"));
    }
}
