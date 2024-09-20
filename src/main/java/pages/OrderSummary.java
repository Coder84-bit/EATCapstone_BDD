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

public class OrderSummary {
    WebDriver driver;

    public OrderSummary(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(text(), '20.000')]")
    WebElement popup;

    @FindBy(id = "snap-midtrans")
    WebElement popupFrame;

    public void switchToOrderSummaryPopup(){
        driver.switchTo().frame(popupFrame);
    }

    public boolean verifyPopup() {
        driver.switchTo().frame(popupFrame);
        return popup.isDisplayed();
    }

    @FindBy(xpath = "//span[@class='header-detail']")
    WebElement details;

    @FindBy(xpath = "//span[contains(normalize-space(),'Midtrans Pillow')]")
    WebElement productName;

    @FindBy(xpath = "//td[@class='order-summary-content float-right']")
    WebElement price;

    public void clickDetails() {
        details.click();
    }

    public boolean verifyProductName() {
        return productName.isDisplayed();
    }

    public boolean verifyPriceIsDisplayed() {
        return price.isDisplayed();
    }

    @FindBy(xpath = "(//div[@class='close-snap-button clickable'])[1]")
    WebElement closeDetails;

    public void closeDetails() {
        closeDetails.click();
    }

    @FindBy(xpath = "//div[contains(text(), 'GoPay/GoPay Later')]")
    WebElement goPay;

    @FindBy(xpath = "//span[contains(text(), 'Virtual account')]")
    WebElement virtualAccount;

    @FindBy(xpath = "//div[contains(text(), 'Credit/debit card')]")
    WebElement creditDebitCard;

    @FindBy(xpath = "//div[contains(text(), 'ShopeePay/SPayLater')]")
    WebElement shopeePay;

    @FindBy(xpath = "//span[contains(text(), 'Dana')]")
    WebElement dana;

    @FindBy(xpath = "//div[contains(text(), 'QRIS')]")
    WebElement QRIS;

    @FindBy(xpath = "//div[contains(text(), 'Alfa Group')]")
    WebElement alfa;

    @FindBy(xpath = "//div[contains(text(), 'Indomaret')]")
    WebElement indomaret;

    @FindBy(xpath = "//span[contains(text(), 'Akulaku PayLater')]")
    WebElement akulaku;

    @FindBy(xpath = "//span[contains(text(), 'Kredivo')]")
    WebElement kredivo;

    public boolean verifyGoPay() {
        return goPay.isDisplayed();
    }

    public boolean verifyVirtualAccount() {
        return virtualAccount.isDisplayed();
    }

    public boolean verifyCreditDebitCard() {
        return creditDebitCard.isDisplayed();
    }

    public boolean verifyShopeePay() {
        return shopeePay.isDisplayed();
    }

    public boolean verifyDana() {
        return dana.isDisplayed();
    }

    public boolean verifyQRIS() {
        return QRIS.isDisplayed();
    }

    public boolean verifyIndomaret() {
        return indomaret.isDisplayed();
    }

    public boolean verifyAkulaku() {
        return akulaku.isDisplayed();
    }

    public boolean verifyAlfa() {
        return alfa.isDisplayed();
    }

    public boolean verifyKredivo() {
        return kredivo.isDisplayed();
    }

    public static List<By> payMethods = List.of(
            By.xpath("//div[contains(text(), 'GoPay/GoPay Later')]"),
            By.xpath("//span[contains(text(), 'Virtual account')]"),
            By.xpath("//div[contains(text(), 'Credit/debit card')]"),
            By.xpath("//div[contains(text(), 'ShopeePay/SPayLater')]"),
            By.xpath("//span[contains(text(), 'Dana')]"),
            By.xpath("//div[contains(text(), 'QRIS')]"),
            By.xpath("//div[contains(text(), 'Alfa Group')]"),
            By.xpath("//div[contains(text(), 'Indomaret')]"),
            By.xpath("//span[contains(text(), 'Akulaku PayLater')]"),
            By.xpath("//span[contains(text(), 'Kredivo')]")
    );

    public static boolean areAllPaymentMethodsDisplayed(WebDriver driver, List<By>payMethods) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return payMethods.stream()
                .map(locator -> {
                    try {
                        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                        return element.isDisplayed();
                    } catch (Exception e) {
                        return false;
                    }
                })
                .reduce(true, (a, b) -> a && b);
    }

    @FindBy(xpath = "//div[@class='apply-btn']")
    WebElement promoButton;

    @FindBy(xpath = "//span[contains(text(), 'Promo Flash Sale (Credit-Card)')]")
    WebElement promoRadioButton;

    @FindBy(xpath = "//button[@class='btn full primary']")
    WebElement useButton;

    @FindBy(xpath = "//div[contains(text(),'19.000')]")
    WebElement newAmount;

    public void clickPromo(){
        promoButton.click();
        promoRadioButton.click();
        useButton.click();
    }

    public boolean verifyDiscountApplied(){
        return newAmount.isDisplayed();
    }


}


