import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageHelpers {
    private final WebDriver driver;

    public PageHelpers(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидать появление элемента")
    public void waitForElement(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}