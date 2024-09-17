package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CardDetails {
    WebDriver driver;

    public CardDetails(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(text(), 'Credit/debit card')]")
    WebElement creditCard;

    @FindBy(xpath = "//span[contains(text(),'Credit/debit card')]")
    WebElement cardDetailsScreen;

    public void clickCreditCard(){
        creditCard.click();
    }

    public boolean verifyCardDetailsScreen(){
        creditCard.click();
        return cardDetailsScreen.isDisplayed();
    }

    @FindBy(xpath = "//input[@placeholder='1234 1234 1234 1234']")
    WebElement cardNumber;

    @FindBy(xpath = "//input[@placeholder='MM/YY']")
    WebElement expirationDate;

    @FindBy(xpath = "//input[@placeholder='123']")
    WebElement CVV;

    public void enterCardDetails(){
        cardNumber.sendKeys("4811111111111114");
        expirationDate.sendKeys("0225");
        CVV.sendKeys("123");
    }
}
