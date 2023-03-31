package highlight;

import static java.lang.Thread.sleep;

import core.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public final class HighLighter {

    private HighLighter() {
    }

    public static void highLightElement(final WebElement element) throws InterruptedException {
        final long waitDuration = 500L;
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
        js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);
        sleep(waitDuration);
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
    }

}
