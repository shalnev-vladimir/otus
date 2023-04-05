package core;

import static java.lang.Integer.parseInt;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Это первая вариант класса, который нужен, чтобы получить самый ранний и самый позний курс.
 * Она так себе.
 * Лучше смотреть версию NewCourseCards.
 */
public class CourseCards extends BasePage<CourseCards> implements Comparable<CourseCards> {

    private final String courseTitles = ".lessons__new-item-title";
    private final String courseStartDate = ".lessons__new-item-start";

    public CourseCards() {

    }

    // получаем список названий курсов
    public List<String> getCourseTitlesList() {
        List<String> courseTitlesList = new ArrayList<>();
        List<WebElement> courseNames = getWebElementsList(By.cssSelector(courseTitles));
        for (WebElement name : courseNames) {
            courseTitlesList.add(name.getText());
        }
        return courseTitlesList;
    }

    // получаем список текстов начала курса
    public List<String> getStartDatesList() {
        List<WebElement> webElementList = getWebElementsList(By.cssSelector(courseStartDate));
        List<String> webElementsListString = getDatesOnCard(webElementList);
        return findIntegers(webElementsListString);
    }

    // получаем список текстов начала курса (именно число)
    public List<Integer> getStartMonthList() {
        List<WebElement> webElementList = getWebElementsList(By.cssSelector(courseStartDate));
        List<String> webElementsListString = getDatesOnCard(webElementList);
        List<Integer> monthsListString = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            monthsListString.add(convertMonthToNumber(getLastWordFromString(webElementsListString.get(i))));
        }
        return monthsListString;
    }

    public List<WebElement> cardsList() {
        return getWebElementsList(By.cssSelector(courseTitles));
    }

    private String courseName;
    private Integer day;
    private Integer month;

    public CourseCards(String courseName, int day, int month) {
        this.courseName = courseName;
        this.day = day;
        this.month = month;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "CourseCard{"
                +
                "courseName='" + courseName + '\''
                +
                ", day=" + day
                +
                ", month=" + month
                +
                '}';
    }

    // получаем название месяца
    public String getLastWordFromString(String line) {
        return line.substring(line.lastIndexOf(" ") + 1);
    }

    public Integer convertMonthToNumber(String month) {
        int courseStartMonth;
        switch (month.trim().toLowerCase().replaceAll("[^а-я]", "")) {
            case "января":
                courseStartMonth = 1;
                break;
            case "февраля":
                courseStartMonth = 2;
                break;
            case "марта":
                courseStartMonth = 3;
                break;
            case "апреля":
                courseStartMonth = 4;
                break;
            case "мая":
                courseStartMonth = 5;
                break;
            case "июня":
                courseStartMonth = 6;
                break;
            case "июля":
                courseStartMonth = 7;
                break;
            case "августа":
                courseStartMonth = 8;
                break;
            case "сентября":
                courseStartMonth = 9;
                break;
            case "октября":
                courseStartMonth = 10;
                break;
            case "ноября":
                courseStartMonth = 11;
                break;
            case "декабря":
                courseStartMonth = 12;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + month);
        }
        return courseStartMonth;
    }

    //---------------------------------------------------------------------------

    // получаем текст даты из карточки курса
    public static List<String> getDatesOnCard(List<WebElement> dateOnCard) {
        List<String> dates = new ArrayList<>();
        for (WebElement date : dateOnCard) {
            dates.add(date.getText());
        }
        return dates;
    }

    // находим цифры в текте
    static List<String> findIntegers(List<String> stringsToSearch) { // findIntegers(getDatesOnCard(courseStartDate))
        List<String> integerList = new ArrayList<>();
        for (String s : stringsToSearch) {
            Pattern integerPattern = Pattern.compile("-?\\d+");
            Matcher matcher = integerPattern.matcher(s);

            while (matcher.find()) {
                integerList.add(matcher.group());
            }
        }
        return integerList;
    }

    //---------------------------------------------------------------------------

    public List<String> getLatestAndEarliestCourseNames() {
        List<CourseCards> courseCardsList = new ArrayList<>();
        List<String> latestAndEarliestCourseNames = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            // добавляем число
            setDay(parseInt(getStartDatesList().get(i)));
            // добавляем месяц
            setMonth(getStartMonthList().get(i));
            // добавляем имя
            setCourseName(getCourseTitlesList().get(i));
            // возвращаем объект
            courseCardsList.add(new CourseCards(getCourseName(), getDay(), getMonth()));
        }
        // сортируем список по возрастанию (по дате и месяцу)
        courseCardsList.sort((o1, o2) -> new CompareToBuilder()
                .append(o1.getMonth(), o2.getMonth())
                .append(o1.getDay(), o2.getDay())
                .toComparison());

        // добавляем в список первый и последний курс
        latestAndEarliestCourseNames.add(courseCardsList.get(0).getCourseName());
        latestAndEarliestCourseNames.add(courseCardsList.get(courseCardsList.size() - 1).getCourseName());

        return latestAndEarliestCourseNames;
    }

    @Override
    public int compareTo(CourseCards o) {
        if (o == null) {
            return -1;
        }
        CompareToBuilder builder = new CompareToBuilder();
        return builder
                .append(this.month, o.month)
                .append(this.day, o.day)
                .toComparison();
    }

}