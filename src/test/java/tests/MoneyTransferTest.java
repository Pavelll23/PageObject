package tests;

import data.DataHelper;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.TransferOfFundPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {


    @BeforeEach
    void setUp() {
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

        int amount = 100;
        String cardNumber = DataHelper.getSecondCardInfo().getNumber();

        int balanceFirstCard = dashBoardPage.getFirstCardBalance();
        int balanceSecondCard = dashBoardPage.getSecondCardBalance();

        dashBoardPage.pushFirstCard();
        TransferOfFundPage.transfer(String.valueOf(amount), cardNumber);

        assertEquals(balanceFirstCard + amount, dashBoardPage.getFirstCardBalance());
        assertEquals(balanceSecondCard - amount, dashBoardPage.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyInSecondCard() {
        var dashBoardPage = new DashboardPage();
        int amount = 100;
        String cardNumber = DataHelper.getFirstCardInfo().getNumber();

        int balanceFirstCard = dashBoardPage.getFirstCardBalance();
        int balanceSecondCard = dashBoardPage.getSecondCardBalance();

        dashBoardPage.pushSecondCard();

        TransferOfFundPage.transfer(String.valueOf(amount), cardNumber);

        assertEquals(balanceFirstCard - amount, dashBoardPage.getFirstCardBalance());
        assertEquals(balanceSecondCard + amount, dashBoardPage.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyAmountZero() {
        var dashBoardPage = new DashboardPage();
        int amount = 0;
        String cardNumber = DataHelper.getFirstCardInfo().getNumber();

        int balanceFirstCard = dashBoardPage.getFirstCardBalance();
        int balanceSecondCard = dashBoardPage.getSecondCardBalance();

        dashBoardPage.pushSecondCard();

        TransferOfFundPage.transfer(String.valueOf(amount), cardNumber);

        assertEquals(balanceFirstCard - amount, dashBoardPage.getFirstCardBalance());
        assertEquals(balanceSecondCard + amount, dashBoardPage.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyMoreAccount() {
        var dashBoardPage = new DashboardPage();
        int amount = 999999;
        String cardNumber = DataHelper.getFirstCardInfo().getNumber();

        int balanceFirstCard = dashBoardPage.getFirstCardBalance();
        int balanceSecondCard = dashBoardPage.getSecondCardBalance();

        dashBoardPage.pushSecondCard();

        TransferOfFundPage.transfer(String.valueOf(amount), cardNumber);

        assertEquals(balanceFirstCard - amount, dashBoardPage.getFirstCardBalance());
        assertEquals(balanceSecondCard + amount, dashBoardPage.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyNegativeAmount() {

        var dashBoardPage = new DashboardPage();
        int amount = -100;
        String cardNumber = DataHelper.getFirstCardInfo().getNumber();

        int balanceFirstCard = dashBoardPage.getFirstCardBalance();
        int balanceSecondCard = dashBoardPage.getSecondCardBalance();

        dashBoardPage.pushSecondCard();

        TransferOfFundPage.transfer(String.valueOf(amount), cardNumber);

        assertEquals(balanceFirstCard - amount, dashBoardPage.getFirstCardBalance());
        assertEquals(balanceSecondCard + amount, dashBoardPage.getSecondCardBalance());
    }
}
