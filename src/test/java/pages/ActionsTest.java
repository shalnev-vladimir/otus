package pages;

import actions.PageElementActions;
import core.BaseTest;
import core.DriverFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionsTest extends BaseTest {

    @Test
    void rightClickActionTest() throws InterruptedException {
        String rgbaColorBefore =
                DriverFactory.getInstance().getDriver()
                        .findElement(By.cssSelector(".button_white"))
                        .getCssValue("background-color");
        assertEquals("rgba(255, 255, 255, 1)", rgbaColorBefore);

        new PageElementActions()
                .rightClickAction(".button_white");

        String rgbaColorAfter =
                DriverFactory.getInstance().getDriver()
                        .findElement(By.cssSelector(".button_white"))
                        .getCssValue("background-color");
        assertEquals("rgba(0, 0, 0, 0)", rgbaColorAfter);
    }

    @Test
    void checkBIAnalyticPageTitle() {
        new MainPage()
                .searchingCourseCardByNameAndClickOnIt("BI-аналитика");

        assertEquals("BI-аналитика", new BIAnalyticsPage().getPageTitle());
    }
}
