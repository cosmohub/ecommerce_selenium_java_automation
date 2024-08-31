package practiceautomation.shoppingApplication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practiceautomation.AbstractComponents.ReuseableComponents;

public class ProductCartPage extends ReuseableComponents{
 public WebDriver driver;
	public ProductCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[text()=\"Checkout\"]")
	WebElement checkOut;
	
	public String verifyProductNameAtCart(String productName) {
		driver.findElement(By.xpath("//h3[text()='"+productName+"']")).isDisplayed();
		String price = driver.findElement(By.xpath("(//h3[text()='"+productName+"']/../p)[2]")).getText();
		return price;
		
	}
	
	public PlaceOrderPage checkOutProduct() {
		waitForElementToAppear(checkOut);
		checkOut.click();
		PlaceOrderPage PlaceOrderPage = new PlaceOrderPage(driver);
		return PlaceOrderPage;
	}
}
