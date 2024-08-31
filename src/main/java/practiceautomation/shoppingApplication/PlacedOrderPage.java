package practiceautomation.shoppingApplication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practiceautomation.AbstractComponents.ReuseableComponents;

public class PlacedOrderPage extends ReuseableComponents{
	public WebDriver driver;
	public PlacedOrderPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1")
	WebElement orderSuccessMessage;
	
	@FindBy(xpath="(//label[contains(text(),\" Orders History Page\")]/../../..//label)[2]")
	WebElement orderID;
	
	public String verifyOrderSucess() {
		waitForElementToAppear(orderSuccessMessage);
		String message = orderSuccessMessage.getText();
		return message;
		
	}
	
	public String getOrderId() {
		waitForElementToAppear(orderSuccessMessage);
		String orderNumber = orderID.getText();
		orderNumber = orderNumber.split(" ")[1];
		System.out.println(orderNumber);
		return orderNumber;		
		
	}

}
