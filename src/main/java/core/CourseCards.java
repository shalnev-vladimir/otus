package core;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.lang.System.out;

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

    // получаем список текстов начала курса (именно число)
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
        for (int i = 0; i < 6; i++) { // cardsList().size() getCourseTitlesList().size() - 1
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
        return "CourseCard{" +
                "courseName='" + courseName + '\'' +
                ", day=" + day +
                ", month=" + month +
                '}';
    }

    // получаем название месяца
    public String getLastWordFromString(String line) {
        return line.substring(line.lastIndexOf(" ") + 1);
    }

    // переводим название месяца в порядковый номер месяца (число)
    public Integer convertMonthToNumber(String month) {
        switch (month) {
            case "января":
                return 1;
            case "февраля":
                return 2;
            case "марта":
                return 3;
            case "апреля":
                return 4;
            case "мая":
                return 5;
            case "июня":
                return 6;
            case "июля":
                return 7;
            case "августа":
                return 8;
            case "сентября":
                return 9;
            case "октября":
                return 10;
            case "ноября":
                return 11;
            case "декабря":
                return 12;
            default:
                out.println("Ooops! There is no month " + month);
                break;
        }
        return null;
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

    // получаем минимальное и максимальное значение int
    public void getMinimumAndMaximumValue() {
        List<Integer> arrayList = Arrays.asList(5, 1, 6, 9, 9, 1);
        Integer min = arrayList.stream().reduce((sum, currant) -> sum > currant ? currant : sum).get();
        Integer max = arrayList.stream().reduce((sum, currant) -> sum < currant ? currant : sum).get();
        out.println(min);
        out.println(max);
    }

    public List<String> getLatestAndEarliestCourseNames() {
        List<CourseCards> courseCardsList = new ArrayList<>();
        List<String> latestAndEarliestCourseNames = new ArrayList<>();
        for (int i = 0; i < 6; i++) { // cardsList().size() getCourseTitlesList().size() - 1
            // добавляем число
//            Integer day = parseInt(getStartDatesList().get(i));
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