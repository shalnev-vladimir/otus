package pages;

import core.BaseTest;
import core.CourseCards;
import org.junit.jupiter.api.Test;

import java.util.List;

import static actions.PageElementActions.textFiltering;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest extends BaseTest {

    @Test
    void checkHeaderLogoExistTest() {
        assertTrue(new MainPage()
                .isHeaderLogoExist(), "The header logo does not appear on the main page");
    }

    @Test
    void clickHeaderLogoWithHighlightTest() throws InterruptedException {
        new MainPage()
                .highlightSignInButtonBeforeClick();
    }

    @Test
    void checkCourseExistTest() {
        List<String> listOfCoursesAfterFiltering =
                textFiltering(new MainPage().getCourseTitles(), "BI-аналитика");

        assertEquals(1, listOfCoursesAfterFiltering.size(),
                "Expected list size is 1, bat actual is " + listOfCoursesAfterFiltering.size());
    }

    @Test
    void checkListSizeEqualsTwoTest() {
        assertEquals(2, new CourseCards().getLatestAndEarliestCourseNames().size());
    }
}
