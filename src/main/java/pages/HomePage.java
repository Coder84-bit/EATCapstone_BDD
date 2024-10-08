package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public static WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='btn buy']")
    WebElement buyButton;

    public boolean checkBuyButtonDisplayed(){
        return buyButton.isDisplayed();
    }

    public void clickBuyButton(){
        buyButton.click();
    }

}
