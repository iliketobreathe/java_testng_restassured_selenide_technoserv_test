import com.codeborne.selenide.Configuration;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BankMainPage;
import pages.GoogleMainPage;
import pages.GoogleSearchResultPage;

import static com.codeborne.selenide.Selenide.*;

public class UiTest {

    @BeforeTest
    public void openSite() {
        System.setProperty("selenide.browser", "Chrome");
        Configuration.startMaximized = true;
        open("https://www.google.com/");
    }

    @Test
    public void bankTest() {
        GoogleMainPage googleMainPage = new GoogleMainPage();
        GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage();
        BankMainPage bankMainPage = new BankMainPage();
        String url = "https://www.open.ru/";

        googleMainPage.search("Открытие");

        Assert.assertTrue(googleSearchResultPage.isUrlInResults(url));

        open(url);

        bankMainPage.getCurrencyPricesInternet();

        Assert.assertTrue(bankMainPage.getUsdSell() > bankMainPage.getUsdBuy());
        Assert.assertTrue(bankMainPage.getEuroSell() > bankMainPage.getEuroBuy());
    }
}
