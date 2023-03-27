package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class BasePage<T> extends PageObject<T> {

    public final WebElement getWebElement(final By locator) {
        return DriverFactory.getInstance().getDriver().findElement(locator);
    }

    public final List<WebElement> getWebElementsList(final By locator) {
        return DriverFactory.getInstance().getDriver().findElements(locator);
    }
}
