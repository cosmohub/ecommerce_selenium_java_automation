package practiceautomation.shoppingApplication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practiceautomation.AbstractComponents.ReuseableComponents;

public class PlaceOrderPage extends ReuseableComponents {
	public WebDriver driver;
	public PlaceOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryDropdown;
	
	@FindBy(xpath="//a[contains(text(),'Place Order')]")
	WebElement placeOrder;
	
	public void selectCountry(String country) throws InterruptedException {
		
		waitForElementToAppear(countryDropdown);
		countryDropdown.sendKeys(country);
		Thread.sleep(2);
		driver.findElement(By.xpath("//span[text()=' "+country+"']")).click();
	}
	
	public PlacedOrderPage placeOrder() {
		Actions a = new Actions(driver);
		a.moveToElement(placeOrder).click().perform();
		PlacedOrderPage PlacedOrderPage = new PlacedOrderPage(driver);
		return PlacedOrderPage;
		
	}

}
