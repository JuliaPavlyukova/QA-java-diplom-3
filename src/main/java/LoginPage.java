import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// Успешная регистрация

public class LoginPage {
    private final WebDriver driver;

    // Локаторы
    private final By loginHeader = By.xpath("//h2[contains(text(),'Вход')]");
    private final By emailL = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordL = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By forgotPasswordLink = By.linkText("Восстановить пароль");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заголовок Вход")
    public By getLoginHeader() {
        return  loginHeader;
    }

    @Step("Нажать кнопку  Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    @Step("Открыть страницу восстановления пароля")
    public void openForgotPasswordPage() { driver.findElement(forgotPasswordLink).click(); }

    @Step("Нажать по ссылке 'Войти' на странице восстановления пароля")
    public void clickLoginFromForgotPassword() {
        driver.findElement(By.linkText("Войти")).click();
    }

    @Step("Авторизация пользователя")
    public void login(String email, String password) {
        driver.findElement(emailL).sendKeys(email);
        driver.findElement(passwordL).sendKeys(password);
        clickLoginButton();
    }
}
