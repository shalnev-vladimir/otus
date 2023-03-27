package core;

import org.openqa.selenium.WebDriver;

public final class DriverFactory {

    private DriverFactory() {
    }

    private static DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance() {
        return instance;
    }

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    public void setDriver(final WebDriver driverParams) {
        driver.set(driverParams);
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.get().quit();
        }
    }

}
