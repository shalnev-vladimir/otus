package actions;

import core.DriverFactory;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.stream.Collectors;

public final class PageElementActions {

    public PageElementActions() {
    }

    @Description("Фильтрует список по текту")
    public static List<String> textFiltering(final List<WebElement> elements, final String text) {
        return elements
                .stream()
                .map(WebElement::getText)
                .filter(e -> e.equalsIgnoreCase(text))
                .collect(Collectors.toList());
    }

    public void rightClickAction(String selector) {
        WebElement rightButtonElement = DriverFactory.getInstance().getDriver().findElement(By.cssSelector(selector));
        Actions actions = new Actions(DriverFactory.getInstance().getDriver());

        actions.contextClick(rightButtonElement).build().perform();
    }

    public void moveMouseToElementAndClick(WebElement element) {
        Actions actions = new Actions(DriverFactory.getInstance().getDriver());
        actions.moveToElement(element).build().perform();
        actions.click(element).build().perform();
    }

}
