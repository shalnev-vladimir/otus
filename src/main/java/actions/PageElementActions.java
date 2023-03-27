package actions;

import jdk.jfr.Description;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public final class PageElementActions {

    private PageElementActions() {
    }

    @Description("Фильтрует список по текту")
    public static List<String> textFiltering(final List<WebElement> elements, final String text) {
        return elements
                .stream()
                .map(WebElement::getText)
                .filter(e -> e.equalsIgnoreCase(text))
                .collect(Collectors.toList());
    }

}
