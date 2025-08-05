import browser.DriverHelper;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() throws IOException {
        DriverHelper driverHelper = new DriverHelper();
        driver = driverHelper.initDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
