package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import setup.DriverFactory;
import setup.Util;

import static pages.BankPayment.bankDetails;
import static pages.Checkout.details;
import static pages.OrderSummary.payMethods;

public class BDDTestSteps {

    WebDriver driver;
    BasePage basepage;
    HomePage homepage;
    Checkout checkout;
    OrderSummary orderSummary;
    CardDetails cardDetails;
    BankPayment bankPayment;

    @Before
    public void tearUp(){
        driver = DriverFactory.getDriver(Util.getProperties("browserName"));
        basepage = new BasePage(driver);
        homepage = new HomePage(driver);
        checkout = new Checkout(driver);
        orderSummary = new OrderSummary(driver);
        cardDetails = new CardDetails(driver);
        bankPayment = new BankPayment(driver);

    }

    @After
    public void tearDown(){
        Util.pauseExecutionForSeconds(3);
        basepage.closeBrowser();
    }

    @Given("Website is launched for testing")
    public void launchApplication(){
        Util.pauseExecutionForSeconds(3);
        basepage.launchApplication(Util.getProperties("url"));
    }

    @When("I click the Buy button")
    public void clickBuyButton(){
        homepage.clickBuyButton();
    }

    @Then("I will go to the Checkout page")
    public void verifyCheckoutPageIsDisplayed(){
        Assert.assertTrue(checkout.checkIfCheckoutIsDisplayed());
    }

    @When("I am on the Checkout page")
    public void CheckoutIsDisplayed(){
        homepage.clickBuyButton();
        checkout.checkIfCheckoutIsDisplayed();
    }

    @Then("The pillow is added to cart for 20000")
    public void verifyPillowAddedToCartFor20000(){
        Assert.assertTrue(checkout.checkIfAmountIsShown());
    }

    @Then("I can see the Customer Details")
    public void verifyCustomerDetailsOnCheckout(){
        Assert.assertTrue(Checkout.verifyDetailsOnCheckout(driver, details));
    }

    @Then("I can enter my details into the Customer Details fields")
    public void verifyCustomerDetailsAreEditable(){
        checkout.typeCustomerDetails();
    }

    @Given("I filled out the Customer Details")
    public void fillOutCustomerDetails(){
        homepage.clickBuyButton();
        checkout.typeCustomerDetails();
    }

    @When("I click the Checkout button")
    public void clickCheckoutButton(){
        checkout.clickCheckoutButton();
    }

    @Then("I will go to the Order Summary Page")
    public void verifyRedirectToOrderSummaryPage(){
        orderSummary.verifyPopup();
    }

    @Given("I have completed the Checkout Process")
    public void checkoutComplete(){
        basepage.launchApplication(Util.getProperties("url"));
        homepage.clickBuyButton();
        checkout.typeCustomerDetails();
        checkout.clickCheckoutButton();
    }

    @When("I am on the Order Summary page")
    public void goToOrderSummary(){
        orderSummary.verifyPopup();
    }

    @Then("I will see the Product Details")
    public void verifyProductDetailsOnOrderSummary(){
        orderSummary.clickDetails();
        Assert.assertTrue(orderSummary.verifyProductName());
        Assert.assertTrue(orderSummary.verifyPriceIsDisplayed());
        orderSummary.closeDetails();
    }

    @Then("I will see all of the payment methods")
    public void verifyPaymentMethodsOnOrderSummary(){
        Assert.assertTrue(OrderSummary.areAllPaymentMethodsDisplayed(driver, payMethods));
    }

    @And("I click the Promo link")
    public void clickPromoLink(){
        orderSummary.clickPromo();
    }

    @Then("The promotion will be applied to the price")
    public void verifyPromoAdded(){
        orderSummary.verifyDiscountApplied();
    }

    @And("I click the Credit Card button")
    public void clickCreditCardButton(){
        cardDetails.clickCreditCard();
    }

    @Then("I will be redirected to the Credit Card Details page")
    public void verifyCardDetailsPageIsDisplayed(){
        Assert.assertTrue(cardDetails.verifyCardDetailsScreen());
    }

    @When("I am on the Credit Card Details page")
    public void navigateToCardDetailsPage(){
        orderSummary.switchToOrderSummaryPopup();
        cardDetails.clickCreditCard();
    }

    @Then("I can enter credit card details into the fields")
    public void verifyCardDetailsAreEntered(){
        cardDetails.enterCardDetails();
    }

    @Given("I have entered my credit card details")
    public void completeCreditCardDetails(){
        basepage.launchApplication(Util.getProperties("url"));
        homepage.clickBuyButton();
        checkout.typeCustomerDetails();
        checkout.clickCheckoutButton();
        orderSummary.switchToOrderSummaryPopup();
        cardDetails.clickCreditCard();
        cardDetails.enterCardDetails();
    }

    @When("I click the Pay Now button after entering the credit card details")
    public void clickPayNowButton() {
        bankPayment.clickPayNowButton();
        bankPayment.switchToBankFrame();
    }

    @Then("I will be redirected to the Bank Payment page with the Transaction Details")
    public void verifyBankDetailsScreen_TC12(){
        Assert.assertTrue(BankPayment.verifyBankDetails(driver, bankDetails));
    }

    @Given("I am on the Bank Payment page")
    public void navigateToBankPaymentPage(){
        bankPayment.clickPayNowButton();
        bankPayment.switchToBankFrame();
    }

    @When("I enter a valid OTP into the Bank payment page")
    public void enterValidOTP(){
        bankPayment.enterValidOTP();
    }

    @Then("My order will be successful")
    public void clickOK(){
        bankPayment.clickOK();
        bankPayment.verifyPaymentSuccessful();
    }

    @When("I enter an invalid OTP into the Bank payment page")
    public void enterInvalidOTP(){
        bankPayment.enterInvalidOTP();
        bankPayment.clickOK();
    }

    @Then("My order will fail")
    public void verifyOrderFailed(){
        bankPayment.verifyOrderFailure();
    }

    @When("I click the Cancel button")
    public void clickCancelButton(){
        bankPayment.clickCancel();
    }



}
