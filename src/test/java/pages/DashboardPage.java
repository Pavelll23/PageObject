package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import data.DataHelper;
import lombok.val;


import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement buttonActionDeposit = $("[data-test-id=action-deposit]");
    private SelenideElement actionReload = $("[data-test-id=action-reload]");
   // private ElementsCollection cards = $$(".list__item div");
   private SelenideElement firstCard = $$( ".list__item" ).first();
    private SelenideElement secondCard = $$( ".list__item" ).last();
    private SelenideElement buttonFirstCard = $$( "[data-test-id='action-deposit']" ).first();
    private SelenideElement buttonSecondCard = $$( "[data-test-id='action-deposit']" ).last();
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }
    public TransferOfFundPage pushFirstCard (){
        buttonFirstCard.click();
        return new TransferOfFundPage();
    }
    public TransferOfFundPage pushSecondCard(){
        buttonSecondCard.click();
        return new TransferOfFundPage();
    }
    public int getFirstCardBalance() {
        val text = firstCard.text();
        return extractBalanceFirstCard(text);
    }

    private int extractBalanceFirstCard(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public int getSecondCardBalance() {
        val text = secondCard.text();
        return extractBalanceSecondCard(text);
    }

    private int extractBalanceSecondCard(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
