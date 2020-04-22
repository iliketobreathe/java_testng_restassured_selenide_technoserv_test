import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class UiTests {

    @BeforeTest
    public void openSite() {
        System.setProperty("selenide.browser", "Chrome");
        Configuration.startMaximized = true;
    }

    @Test
    public void googleTest() {

        open("https://www.google.com/");

        SelenideElement searchField = $(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input"));
        SelenideElement searchButton = $(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]"));
        String openUrl = "https://www.open.ru/";
        boolean isExist = false;

        searchField.sendKeys("Открытие");
        searchButton.submit();
        ElementsCollection collection = $$(By.cssSelector("div.r > a"));

        for (SelenideElement element : collection) {
            if (element.getAttribute("href").equals(openUrl)) {
                isExist = true;
            }
            break;
        }

        Assert.assertTrue(isExist);
    }

    @Test(dependsOnMethods = "googleTest")
    public void bankCurrencyExchangeTest() {
        open("https://www.open.ru/");

        Double usdBuy = Double.parseDouble($(By.xpath("//*[@id=\"main\"]/div/div/div[8]/section/div/div/div[1]" +
                "/div/div/div/div/div[2]/table/tbody/tr[2]/td[2]/div/span")).getText().replace(",", "."));

        Double usdSell = Double.parseDouble($(By.xpath("//*[@id=\"main\"]/div/div/div[8]/section/div/div/div[1]" +
                "/div/div/div/div/div[2]/table/tbody/tr[2]/td[4]/div/span")).getText().replace(",", "."));

        Double euroBuy = Double.parseDouble($(By.xpath("//*[@id=\"main\"]/div/div/div[8]/section/div/div/div[1]" +
                "/div/div/div/div/div[2]/table/tbody/tr[3]/td[2]/div/span")).getText().replace(",", "."));

        Double euroSell = Double.parseDouble($(By.xpath("//*[@id=\"main\"]/div/div/div[8]/section/div/div/div[1]" +
                "/div/div/div/div/div[2]/table/tbody/tr[3]/td[4]/div/span")).getText().replace(",", "."));

        Assert.assertTrue(usdSell > usdBuy);
        Assert.assertTrue(euroSell > euroBuy);
    }
}
