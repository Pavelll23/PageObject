package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import data.DataHelper;
import lombok.val;


import static com.codeborne.selenide.Condition.id;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement buttonActionDeposit = $("[data-test-id=action-deposit]");
    private SelenideElement actionReload = $("[data-test-id=action-reload]");
    private ElementsCollection cards = $$(".list__item div");
    private SelenideElement buttonFirstCard = $$("[data-test-id='action-deposit']").first();
    private SelenideElement buttonSecondCard = $$("[data-test-id='action-deposit']").last();
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }

    public TransferOfFundPage pushFirstCard() {
        buttonFirstCard.click();
        return new TransferOfFundPage();
    }

    public TransferOfFundPage pushSecondCard() {
        buttonSecondCard.click();
        return new TransferOfFundPage();
    }

    public int getCardsBalance(DataHelper.CardInfo cardInfo) {
        var text = cards.findBy(text(cardInfo.getNumber().substring(15))).getText();
        return extractBalanceCard(text);
    }

    private int extractBalanceCard(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}
