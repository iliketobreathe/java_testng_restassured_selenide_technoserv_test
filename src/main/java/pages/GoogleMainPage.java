package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class GoogleMainPage {

    private final SelenideElement SEARCH_FIELD = $(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input"));
    private final SelenideElement SEARCH_BUTTON = $(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]"));

    public void search(String searchString) {
        SEARCH_FIELD.sendKeys(searchString);
        SEARCH_BUTTON.submit();
    }
}
