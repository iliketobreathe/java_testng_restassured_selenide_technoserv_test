package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BankMainPage {
    private  double usdBuy;
    private  double usdSell;
    private  double euroBuy;
    private  double euroSell;

    public void getCurrencyPricesInternet() {
        SelenideElement dropMenu = $(By.xpath("//*[@id=\"main\"]/div/div/div[8]/section/div/div/div[1]" +
                "/div/div/div/div/div[1]/div[1]/h2"));

        String currentOption = $(By.xpath("//*[@id=\"main\"]/div/div/div[8]/section/div/div/div[1]" +
                "/div/div/div/div/div[1]/div[1]/h2")).getText();

        dropMenu.scrollTo();

        if(!currentOption.equals("Курс обмена в интернет-банке")) {
            dropMenu.selectOption("В интернет-банке");
        }

        usdBuy = Double.parseDouble($(By.xpath("//*[@id=\"main\"]/div/div/div[8]/section/div/div/div[1]" +
                "/div/div/div/div/div[2]/table/tbody/tr[2]/td[2]/div/span")).getText().replace(",", "."));

        usdSell = Double.parseDouble($(By.xpath("//*[@id=\"main\"]/div/div/div[8]/section/div/div/div[1]" +
                "/div/div/div/div/div[2]/table/tbody/tr[2]/td[4]/div/span")).getText().replace(",", "."));

        euroBuy = Double.parseDouble($(By.xpath("//*[@id=\"main\"]/div/div/div[8]/section/div/div/div[1]" +
                "/div/div/div/div/div[2]/table/tbody/tr[3]/td[2]/div/span")).getText().replace(",", "."));

        euroSell = Double.parseDouble($(By.xpath("//*[@id=\"main\"]/div/div/div[8]/section/div/div/div[1]" +
                "/div/div/div/div/div[2]/table/tbody/tr[3]/td[4]/div/span")).getText().replace(",", "."));
    }

    public double getUsdBuy() {
        return usdBuy;
    }

    public double getUsdSell() {
        return usdSell;
    }

    public double getEuroBuy() {
        return euroBuy;
    }

    public double getEuroSell() {
        return euroSell;
    }
}
