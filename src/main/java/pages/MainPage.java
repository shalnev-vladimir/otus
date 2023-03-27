package pages;

import core.BasePage;
import highlight.HighLighter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Otus MainPage.
 */
public final class MainPage extends BasePage<MainPage> {

    /**
     * Header logo selector.
     */
    private final String headerLogo = ".header3__logo-img";
    /**
     * Courses titles selector.
     */
    private final String courseTitles = ".lessons__new-item-title";
    /**
     * User notification selector.
     */
    private final String userNotification = ".header3__user-notifications-icon";
    /**
     * SignIn button selector.
     */
    private final String signInButton = ".header3__button-sign-in";

    /**
     * Getting titles of courses.
     * @return course title.
     */
    public List<WebElement> getCourseTitles() {
        return getWebElementsList(By.cssSelector(courseTitles));
    }

    /**
     * Checks if header logo exist.
     * @return boolean.
     */
    public boolean isHeaderLogoExist() {
        WebElement logo = getWebElement(By.cssSelector(headerLogo));
        return logo.isDisplayed();
    }

    /**
     * Highlights and click.
     * @throws InterruptedException
     */
    public void highlightSignInButtonBeforeClick() throws InterruptedException {
        HighLighter.highLightElement(getWebElement(By.cssSelector(signInButton)));
        getWebElement(By.cssSelector(signInButton)).click();
    }

}
