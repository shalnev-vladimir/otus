package pages;

import core.BaseTest;
import core.CourseCards;
import core.NewCourseCards;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
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
        System.out.println("BI-аналитика");
        List<String> listOfCoursesAfterFiltering =
                textFiltering(new MainPage().getCourseTitles(), "BI-аналитика");

        assertEquals(1, listOfCoursesAfterFiltering.size(),
                "Expected list size is 1, but actual is " + listOfCoursesAfterFiltering.size());
    }

    @Test
    void checkListSizeEqualsTwoTest() {
        assertEquals(2, new CourseCards().getLatestAndEarliestCourseNames().size());
    }

    @Test
    void newCheckListSizeEqualsTwoTest() throws ParseException {
        assertEquals(2, new NewCourseCards().getLatestAndEarliestCourseNames().size());
    }
}
