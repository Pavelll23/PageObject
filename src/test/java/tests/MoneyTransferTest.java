package tests;

import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DashboardPage;
import pages.LoginPage;
import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.getFirstCardInfo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {


    @BeforeEach void setUp() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();

        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);


    }

    @Test
    void shouldTransferMoneyInFirstCard() {
        var dashBoardPage = new DashboardPage();
        var balanceFirstCard = dashBoardPage.getCardsBalance(DataHelper.getFirstCardInfo());
        var balanceSecondCard = dashBoardPage.getCardsBalance(DataHelper.getSecondCardInfo());
        int amount = 100;
        String cardNumber = DataHelper.getSecondCardInfo().getNumber();
        var transferOnCard = dashBoardPage.pushFirstCard();

        transferOnCard.transfer(String.valueOf(amount), cardNumber);

        assertEquals(balanceFirstCard + amount, dashBoardPage.getCardsBalance(DataHelper.getFirstCardInfo()));
        assertEquals(balanceSecondCard - amount, dashBoardPage.getCardsBalance(DataHelper.getSecondCardInfo()));
    }

    @Test
    void shouldTransferMoneyInSecondCard() {
        var dashBoardPage = new DashboardPage();
        var balanceFirstCard = dashBoardPage.getCardsBalance(DataHelper.getFirstCardInfo());
        var balanceSecondCard = dashBoardPage.getCardsBalance(DataHelper.getSecondCardInfo());
        int amount = 100;
        String cardNumber = getFirstCardInfo().getNumber();
        var transferOnCard = dashBoardPage.pushSecondCard();

        transferOnCard.transfer(String.valueOf(amount), cardNumber);

        assertEquals(balanceFirstCard - amount, dashBoardPage.getCardsBalance(DataHelper.getFirstCardInfo()));
        assertEquals(balanceSecondCard + amount, dashBoardPage.getCardsBalance(DataHelper.getSecondCardInfo()));
    }

    @Test
    void shouldTransferMoneyAmountZero() {
        var dashBoardPage = new DashboardPage();
        var balanceFirstCard = dashBoardPage.getCardsBalance(DataHelper.getFirstCardInfo());
        var balanceSecondCard = dashBoardPage.getCardsBalance(DataHelper.getSecondCardInfo());
        int amount = 0;
        String cardNumber = getFirstCardInfo().getNumber();
        var transferOnCard = dashBoardPage.pushSecondCard();

        transferOnCard.transfer(String.valueOf(amount), cardNumber);

        assertEquals(balanceFirstCard - amount, dashBoardPage.getCardsBalance(DataHelper.getFirstCardInfo()));
        assertEquals(balanceSecondCard + amount, dashBoardPage.getCardsBalance(DataHelper.getSecondCardInfo()));
    }

    @Test
    void shouldTransferMoneyMoreAccount() {
        var dashBoardPage = new DashboardPage();
        var balanceFirstCard = dashBoardPage.getCardsBalance(DataHelper.getFirstCardInfo());
        var balanceSecondCard = dashBoardPage.getCardsBalance(DataHelper.getSecondCardInfo());
        int amount = 999999;
        String cardNumber = getFirstCardInfo().getNumber();
        var transferOnCard = dashBoardPage.pushSecondCard();

        transferOnCard.transfer(String.valueOf(amount), cardNumber);
        transferOnCard.errorMassage("Ошибка");

        assertEquals(balanceFirstCard - amount, dashBoardPage.getCardsBalance(DataHelper.getFirstCardInfo()));
        assertEquals(balanceSecondCard + amount, dashBoardPage.getCardsBalance(DataHelper.getSecondCardInfo()));
    }

    @Test
    void shouldTransferMoneyNegativeAmount() {

        var dashBoardPage = new DashboardPage();
        var balanceFirstCard = dashBoardPage.getCardsBalance(DataHelper.getFirstCardInfo());
        var balanceSecondCard = dashBoardPage.getCardsBalance(DataHelper.getSecondCardInfo());
        int amount = -100;
        String cardNumber = getFirstCardInfo().getNumber();
        var transferOnCard = dashBoardPage.pushSecondCard();

        transferOnCard.transfer(String.valueOf(amount), cardNumber);

        assertEquals(balanceFirstCard + amount, dashBoardPage.getCardsBalance(DataHelper.getFirstCardInfo()));
        assertEquals(balanceSecondCard - amount, dashBoardPage.getCardsBalance(DataHelper.getSecondCardInfo()));
    }
}
