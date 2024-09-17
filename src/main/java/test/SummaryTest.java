package test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import setup.DriverFactory;
import setup.Util;

import static pages.OrderSummary.payMethods;


public class SummaryTest {
    WebDriver driver;
    BasePage basePage;
    Checkout checkout;
    HomePage homePage;
    OrderSummary orderSummary;
    CardDetails cardDetails;
    BankPayment bankPayment;

    @BeforeClass (alwaysRun = true)
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
    }

    @AfterClass (alwaysRun = true)
    public void tearDown() {
        Util.pauseExecutionForSeconds(3);
        basePage.closeBrowser();
    }

    @Test(priority = 1, groups = {"sanity"})
    public void verifyPopupAfterClickingCheckout_TC5(){
        checkout.clickCheckoutButton();
        Assert.assertTrue(orderSummary.verifyPopup());
    }

    @Test(priority = 2, groups = {"sanity"})
    public void verifyProductDetailsOnOrderSummary_TC6(){
        orderSummary.clickDetails();
        Assert.assertTrue(orderSummary.verifyProductName());
        Assert.assertTrue(orderSummary.verifyPriceIsDisplayed());
        orderSummary.closeDetails();
    }

    @Test(priority = 3, groups = {"sanity"})
    public void verifyPaymentMethodsAreVisible_TC8() {
        Assert.assertTrue(OrderSummary.areAllPaymentMethodsDisplayed(driver, payMethods));
    }

    @Test(priority=4, groups = {"sanity"})
    public void verifyPromotionIsApplied_TC10(){
        Assert.assertTrue(orderSummary.verifyDiscountApplied());
    }

    @Test(priority=5, groups = {"sanity"})
    public void verifyCardDetailsScreen_TC9(){
        Assert.assertTrue(cardDetails.verifyCardDetailsScreen());
    }

    @Test(priority=6, groups = {"sanity"})
    public void verifyCardDetailsEntered_TC11(){
        cardDetails.enterCardDetails();
    }
}
