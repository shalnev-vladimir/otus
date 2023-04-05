package core;

import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


/**
 * Это вторая версия класса, который нужен, чтобы получить самый ранний и самый позний курс.
 * Эта версия лучше.
 */
public class NewCourseCards extends BasePage<NewCourseCards> {

    String allDatesOnAllCardsOnTheMainPage
            = "//*[@class='lessons__new-item-bottom lessons__new-item-bottom_spec']"
            +
            "[1]/div[@class='lessons__new-item-time'] | //*[@class='lessons__new-item-start']";
    String coursesTitles = ".lessons__new-item-title";

    String courseName;
    Date courseStartDate;

    public NewCourseCards() {
    }

    public NewCourseCards(String courseName, Date courseStartDate) {
        this.courseName = courseName;
        this.courseStartDate = courseStartDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(Date courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    @Description("Переводим дату из формата String в формат Date")
    public Date convertStringToDate(String stringDateToParse) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        return simpleDateFormat.parse(stringDateToParse);
    }

    @Description("Получаем список дат начала курсов в формате String")
    public List<String> getDatesList() {
        List<WebElement> webEl = getWebElementsList(By.xpath(allDatesOnAllCardsOnTheMainPage));
        List<String> datesInStringFormat = new ArrayList<>();
        for (WebElement el : webEl) {
            datesInStringFormat.add(el.getText());
        }
        return datesInStringFormat;
    }

    @Description("Получаем список названий курсов в формате String")
    public List<String> getCoursesTitlesList() {
        List<WebElement> webEl = getWebElementsList(By.cssSelector(coursesTitles));
        List<String> titlesInStringFormat = new ArrayList<>();
        for (WebElement el : webEl) {
            titlesInStringFormat.add(el.getText());
        }
        return titlesInStringFormat;
    }

    @Description("Переводим русские названия месяцев в английские")
    public String convertMonthToEnglishName(String month) {
        String courseStartMonth;
        switch (month.trim().toLowerCase().replaceAll("[^а-я]", "")) {
            case "января":
            case "январе":
                courseStartMonth = "Jan";
                break;
            case "февраля":
            case "феврале":
                courseStartMonth = "Feb";
                break;
            case "марта":
            case "марте":
                courseStartMonth = "Mar";
                break;
            case "апреля":
            case "апреле":
                courseStartMonth = "Apr";
                break;
            case "мая":
            case "мае":
                courseStartMonth = "May";
                break;
            case "июня":
            case "июне":
                courseStartMonth = "Jun";
                break;
            case "июля":
            case "июле":
                courseStartMonth = "Jul";
                break;
            case "августа":
            case "августе":
                courseStartMonth = "Aug";
                break;
            case "сентября":
            case "сентябре":
                courseStartMonth = "Sep";
                break;
            case "октября":
            case "октябре":
                courseStartMonth = "Oct";
                break;
            case "ноября":
            case "ноябре":
                courseStartMonth = "Nov";
                break;
            case "декабря":
            case "декабре":
                courseStartMonth = "Dec";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + month);
        }
        return courseStartMonth;
    }

    @Description("Вытаскиваем дату из шаблона '29 марта 12 месяцев'")
    public String splitString(String str) {
        String[] ss = str.split(" ");
        ArrayList<String> wordsArray = new ArrayList<>(Arrays.asList(ss));
        int currentYear = LocalDate.now().getYear();
        return wordsArray.get(0) + "-" + convertMonthToEnglishName(wordsArray.get(1)) + "-" + currentYear;
    }

    @Description("Вытаскиваем дату мз шаблона 'В сентябре 2025 года 9 месяцев'")
    public String splitString1(String str) {
        String[] ss = str.split(" ");
        ArrayList<String> wordsArray = new ArrayList<>(Arrays.asList(ss));
        return 1 + "-" + convertMonthToEnglishName(wordsArray.get(1)) + "-" + wordsArray.get(2);
    }

    @Description("Вытаскиваем дату мз шаблона 'С 30 марта'")
    public String splitString2(String str) {
        String[] ss = str.split(" ");
        ArrayList<String> wordsArray = new ArrayList<>(Arrays.asList(ss));
        int currentYear = LocalDate.now().getYear();
        return wordsArray.get(1) + "-" + convertMonthToEnglishName(wordsArray.get(2)) + "-" + currentYear;
    }

    @Description("Получаем самый ранний и самый поздний курс по дате начала")
    public List<String> getLatestAndEarliestCourseNames() throws ParseException {
        List<NewCourseCards> dateWorkObjects = new ArrayList<>();
        List<String> names = getCoursesTitlesList();
        List<String> dates = getDatesList();
        List<String> resultList = new ArrayList<>();

        for (int i = 0; i < dates.size(); i++) {
            if (!dates.get(i).equalsIgnoreCase("О дате старта будет объявлено позже")) {
                if (dates.get(i).trim().startsWith("С ")) {
                    // устанавливаем имя курса в объект
                    setCourseName(names.get(i));
                    // устанавливаем дату в объект
                    String currentStartCourseDateInString2 = splitString2(dates.get(i));
                    setCourseStartDate(convertStringToDate(currentStartCourseDateInString2));
                    dateWorkObjects.add(new NewCourseCards(getCourseName(), getCourseStartDate()));
                } else if (dates.get(i).trim().startsWith("В ")) {
                    // устанавливаем имя курса в объект
                    setCourseName(names.get(i));
                    // устанавливаем дату в объект
                    String currentStartCourseDateInString1 = splitString1(dates.get(i));
                    setCourseStartDate(convertStringToDate(currentStartCourseDateInString1));
                    dateWorkObjects.add(new NewCourseCards(getCourseName(), getCourseStartDate()));
                } else {
                    // устанавливаем имя курса в объект
                    setCourseName(names.get(i));
                    // устанавливаем дату в объект
                    String currentStartCourseDateInString = splitString(dates.get(i));
                    setCourseStartDate(convertStringToDate(currentStartCourseDateInString));
                    dateWorkObjects.add(new NewCourseCards(getCourseName(), getCourseStartDate()));
                }
            }
        }
        dateWorkObjects.sort(Comparator.comparing(NewCourseCards::getCourseStartDate));
        resultList.add(dateWorkObjects.get(0).courseName);
        resultList.add(dateWorkObjects.get(dateWorkObjects.size() - 1).courseName);
        return resultList;
    }
}
