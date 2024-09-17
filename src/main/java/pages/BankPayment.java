package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BankPayment {
    WebDriver driver;
    public BankPayment(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public BankPayment(){}

    @FindBy(xpath = "//button[contains(normalize-space(),'Pay now')]")
    WebElement payNowButton;

    public void clickPayNowButton(){
        payNowButton.click();
    }

    @FindBy(className = "iframe-3ds")
    WebElement bankPopup;

    @FindBy(xpath = "//h1[contains(text(),'Issuing Bank')]")
    WebElement bankHeader;

    @FindBy(xpath = "//p[@id='merchant_name']")
    WebElement merchantName;

    @FindBy(xpath = "//p[@id='txn_amount']")
    WebElement amount;

    @FindBy(xpath = "//p[@id='txn_time']")
    WebElement time;

    @FindBy(xpath = "//p[@id='card_number']")
    WebElement cardNumber;

    public void switchToBankFrame(){driver.switchTo().frame(bankPopup);}
    public boolean verifyBankHeader(){return bankHeader.isDisplayed();}
    public boolean verifyMerchant(){return merchantName.isDisplayed();}
    public boolean verifyAmount(){return amount.isDisplayed();}
    public boolean verifyTime(){return time.isDisplayed();}
    public boolean verifyCardNumber(){return cardNumber.isDisplayed();}

    public static List<By> bankDetails = List.of(
            By.xpath("//h1[@class='left']"),
            By.xpath("//p[@id='merchant_name']"),
            By.xpath("//p[@id='txn_amount']"),
            By.xpath("//p[@id='txn_time']"),
            By.xpath("//p[@id='card_number']")
    );

    public static boolean verifyBankDetails(WebDriver driver, List<By> bankDetails){
        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(5));
        return bankDetails.stream()
                .map(locator -> {
                    try{
                        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                        return element.isDisplayed();
                    }catch (Exception e){
                        return false;
                    }
                }).reduce(true, (a, b) -> a && b);
    }

    @FindBy(name = "otp")
    WebElement OTP;

    @FindBy(name = "ok")
    WebElement okButton;

    @FindBy(name = "cancel")
    WebElement cancelButton;

    @FindBy(xpath = "//div[contains(normalize-space(),'Payment successful')]")
    WebElement success;

    public void enterValidOTP(){
        OTP.sendKeys("112233");
    }

    public void enterInvalidOTP(){
        OTP.sendKeys("123123");
    }

    public void clickOK(){
        okButton.click();
    }

    public void clickCancel(){
        cancelButton.click();
    }

    public boolean verifyPaymentSuccessful(){return success.isDisplayed();}

    @FindBy(xpath = "//iframe[@id='snap-midtrans']")
    WebElement failedPopup;

    @FindBy(xpath = "//div[@class='prompt-modal-title']")
    WebElement orderFailed;

    public boolean verifyOrderFailure(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.visibilityOf(failedPopup));
        driver.switchTo().frame(failedPopup);
        return orderFailed.isDisplayed();}

}
