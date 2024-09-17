package test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import setup.DriverFactory;
import setup.Util;

import static pages.Checkout.details;


public class CheckoutTest {
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
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        Util.pauseExecutionForSeconds(3);
        basePage.closeBrowser();
    }

    @Test(priority = 1, groups = {"smoke"})
    public void verifyClickingBuyNowButtonRedirectsToCheckout_TC2() {
        homePage.clickBuyButton();
        Assert.assertTrue(checkout.checkIfCheckoutIsDisplayed());
    }

    @Test(priority = 2, groups = {"smoke"})
    public void verifyPillowAddedToCartFor20000_TC1() {
        Assert.assertTrue(checkout.checkIfAmountIsShown());
    }

    @Test(priority = 3, groups = {"smoke"})
    public void verifyCustomerDetailsOnCheckout_TC3() {
        Assert.assertTrue(Checkout.verifyDetailsOnCheckout(driver, details));
    }

    @Test(priority = 4, groups = {"smoke"})
    public void verifyCustomerFieldsAreEditable_TC4() {
        checkout.typeName();
        checkout.clearEmail();
        checkout.typeEmail();
        checkout.typePhone();
        checkout.typeCity();
        checkout.typeAddress();
        checkout.typePostalCode();
    }

}