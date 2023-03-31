package core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import properties.PropertiesOperations;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    private final BrowserFactory browserFactory = new BrowserFactory();

    public final BrowserFactory getBrowserFactory() {
        return browserFactory;
    }

    @BeforeEach
    public final void beforeMethod() throws Exception {
        final long waitDuration = 9L;
        String browser = PropertiesOperations.getPropertyValueByKey("browser");
        String url = PropertiesOperations.getPropertyValueByKey("url");

        DriverFactory.getInstance().setDriver(getBrowserFactory().getInstance(browser));
        WebDriver driver = DriverFactory.getInstance().getDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(waitDuration, TimeUnit.SECONDS);
        driver.navigate().to(url);
    }

    @AfterEach
    public final void cleanUp() {
        DriverFactory.getInstance().closeBrowser();
    }
}
