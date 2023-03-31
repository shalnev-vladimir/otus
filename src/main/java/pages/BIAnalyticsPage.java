package pages;

import core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BIAnalyticsPage implements PagesOfCourses {

    private final String pageTitle = ".course-header2__title";

    @Override
    public String getPageTitle() {
        WebElement title = DriverFactory.getInstance().getDriver().findElement(By.cssSelector(pageTitle));
        return title.getText();
    }
}
