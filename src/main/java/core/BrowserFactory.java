package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class BrowserFactory {

    public WebDriver getInstance(final String browserName) {
        WebDriver driver = null;
        switch (browserName.toUpperCase()) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "OPERA":
                WebDriverManager.operadriver().setup();
                ChromeOptions operaOptions = new ChromeOptions();
                operaOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(operaOptions);
                break;
            default:
                new RuntimeException("Invalid browser name");
                break;
        }
        return driver;
    }

}
