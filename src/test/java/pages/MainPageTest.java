package pages;

import core.BaseTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static actions.PageElementActions.textFiltering;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest extends BaseTest {

    @Test
    void checkHeaderLogoExist() {
        assertTrue(new MainPage()
                .isHeaderLogoExist(), "The header logo does not appear on the main page");
    }

    @Test
    void clickHeaderLogoWithHighlight() throws InterruptedException {
        new MainPage()
                .highlightSignInButtonBeforeClick();
    }

    @Test
    void checkCourseExist() {
        List<String> listOfCoursesAfterFiltering =
                textFiltering(new MainPage().getCourseTitles(), "BI-аналитика");

        assertEquals(1, listOfCoursesAfterFiltering.size(),
                "Expected list size is 1, bat actual is " + listOfCoursesAfterFiltering.size());
    }
}
