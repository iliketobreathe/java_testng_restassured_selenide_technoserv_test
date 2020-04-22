package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public class GoogleSearchResultPage {

    public boolean isUrlInResults(String url) {
        ElementsCollection collection = $$(By.cssSelector("div.r > a"));
        boolean isUrl = false;
        for (SelenideElement element : collection) {
            if (element.getAttribute("href").equals(url)) {
                isUrl = true;
                break;
            }
        }
        return isUrl;
    }
}
