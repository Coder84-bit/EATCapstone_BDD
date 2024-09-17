package test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import setup.DriverFactory;
import setup.Util;

import static pages.BankPayment.bankDetails;


public class CancelTest {
    WebDriver driver;
    BasePage basePage;
    Checkout checkout;
    HomePage homePage;
    OrderSummary orderSummary;
    CardDetails cardDetails;
    BankPayment bankPayment;

    @BeforeClass(alwaysRun = true)
    public void tearUp() {
        driver = DriverFactory.getDriver(Util.getProperties("browserName"));
        basePage = new BasePage(driver);
        checkout = new Checkout(driver);
        homePage = new HomePage(driver);
        orderSummary = new OrderSummary(driver);
        cardDetails = new CardDetails(driver);
        bankPayment = new BankPayment(driver);
        basePage.launchApplication(Util.getProperties("url"));
        homePage.clickBuyButton();
        checkout.typeName();
        checkout.clearEmail();
        checkout.typeEmail();
        checkout.typePhone();
        checkout.typeCity();
        checkout.typeAddress();
        checkout.typePostalCode();
        checkout.clickCheckoutButton();
        orderSummary.switchToOrderSummaryPopup();
        cardDetails.clickCreditCard();
        cardDetails.enterCardDetails();
        bankPayment.clickPayNowButton();
        bankPayment.switchToBankFrame();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        Util.pauseExecutionForSeconds(3);
        basePage.closeBrowser();
    }

    @Test(priority=1, groups = {"regression"})
    public void verifyCancelButtonRedirectsToOrderFailure(){
        bankPayment.clickCancel();
        Assert.assertTrue(bankPayment.verifyOrderFailure());
    }

}
