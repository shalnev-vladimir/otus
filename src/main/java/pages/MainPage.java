package pages;

import core.BasePage;
import core.DriverFactory;
import highlight.HighLighter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public final class MainPage extends BasePage<MainPage> {

    public MainPage() {
        super();
    }

    private final String headerLogo = ".header3__logo-img";
    private final String courseTitles = ".lessons__new-item-title";
    private final String userNotification = ".header3__user-notifications-icon";
    private final String signInButton = ".header3__button-sign-in";

    public List<WebElement> getCourseTitles() {
        return getWebElementsList(By.cssSelector(courseTitles));
    }

    public boolean isHeaderLogoExist() {
        WebElement logo = getWebElement(By.cssSelector(headerLogo));
        return logo.isDisplayed();
    }

    public void highlightSignInButtonBeforeClick() throws InterruptedException {
        HighLighter.highLightElement(getWebElement(By.cssSelector(signInButton)));
        getWebElement(By.cssSelector(signInButton)).click();
    }

    public void searchingCourseCardByNameAndClickOnIt(String courseName) {
        WebElement courseCard = getWebElement(By.xpath("//div[contains(text(), '" + courseName + "')]"));
        Actions actions = new Actions(DriverFactory.getInstance().getDriver());
        actions.moveToElement(courseCard).build().perform();
        actions.click(courseCard).build().perform();
    }

}
