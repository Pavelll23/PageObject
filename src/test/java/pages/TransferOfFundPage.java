package pages;


import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class TransferOfFundPage {
   private static SelenideElement fieldAmount = $("[data-test-id=amount] input");
   private static SelenideElement fieldFrom = $("[data-test-id=from] input");
   private static SelenideElement buttonActionTransfer = $("[data-test-id=action-transfer]");
   private static SelenideElement buttonActioncancel = $("[data-test-id=action-cancel]");



   public  TransferOfFundPage transfer(String amount, String cardFrom){
      fieldAmount.setValue(amount);
      fieldFrom.setValue(cardFrom);
      buttonActionTransfer.click();
      return new TransferOfFundPage();
   }


   public DashboardPage transferCancel(String amount,String cardFrom){
      fieldAmount.setValue(amount);
      fieldFrom.setValue(cardFrom);
      buttonActioncancel.click();
      return new DashboardPage();
   }
}

