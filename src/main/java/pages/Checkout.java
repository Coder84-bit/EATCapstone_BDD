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

public class Checkout {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public Checkout(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//td[contains(normalize-space(), '20,000')]")
    WebElement amount;

    public boolean checkIfAmountIsShown(){return amount.isDisplayed();}

    @FindBy(xpath="//span[contains(normalize-space(), 'Shopping Cart')]")
    WebElement cart;

    public boolean checkIfCheckoutIsDisplayed(){return cart.isDisplayed();}

    public static List<By> details = List.of(
            By.xpath("//td[contains(normalize-space(),'Name')]"),
            By.xpath("//td[contains(normalize-space(),'Email')]"),
            By.xpath("//td[contains(normalize-space(),'Phone no')]"),
            By.xpath("//td[contains(normalize-space(),'City')]"),
            By.xpath("//td[contains(normalize-space(),'Address')]"),
            By.xpath("//td[contains(normalize-space(),'Postal Code')]")
    );

    public static boolean verifyDetailsOnCheckout(WebDriver driver, List<By> details){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        for (By locator: details){
            try{
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                if(!element.isDisplayed()){
                    return false;
                }
            }catch (Exception e){
                return false;
            }
        }return true;
    }

    @FindBy(xpath = "//input[@value='Budi']")
    WebElement nameTextBox;

    @FindBy(xpath = "//input[@value='budi@utomo.com']")
    WebElement emailTextBox;

    @FindBy(xpath = "//input[@value='081808466410']")
    WebElement phoneTextBox;

    @FindBy(xpath = "//input[@value='Jakarta']")
    WebElement cityTextBox;

    @FindBy(xpath = "//textarea[@data-reactid='.0.0.1.0.3.0.0.4.1.0']")
    WebElement addressTextBox;

    @FindBy(xpath = "//input[@value='10220']")
    WebElement postalCodeTextBox;

    public void typeCustomerDetails(){
        nameTextBox.sendKeys("Customer");
        emailTextBox.clear();
        emailTextBox.sendKeys("customer@midtrans.com");
        phoneTextBox.sendKeys("7189171234");
        cityTextBox.sendKeys("New York");
        addressTextBox.sendKeys("123 John Doe Street");
        postalCodeTextBox.sendKeys("12345");
    }

    public void typeName(){nameTextBox.sendKeys("Customer");}

    public void clearEmail(){emailTextBox.clear();}

    public void typeEmail(){emailTextBox.sendKeys("customer@midtrans.com");}

    public void typePhone(){phoneTextBox.sendKeys("7189171234");}

    public void typeCity(){cityTextBox.sendKeys("New York");}

    public void typeAddress(){addressTextBox.sendKeys("123 John Doe Street");}

    public void typePostalCode(){postalCodeTextBox.sendKeys("12345");}

    @FindBy(xpath = "//div[@class='cart-checkout']")
    WebElement checkoutButton;

    public void clickCheckoutButton() {
        checkoutButton.click();
    }
}