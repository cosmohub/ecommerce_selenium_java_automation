package practiceautomation.shoppingApplication;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import practiceautomation.AbstractComponents.ReuseableComponents;

public class OrderDetailPage extends ReuseableComponents{
	public WebDriver driver;
	
	public OrderDetailPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	public boolean verifyOrderInTable(String orderNumber) {
		if(driver.findElement(By.xpath("//th[contains(text(),'"+orderNumber+"')]")).isDisplayed()) {
			WebElement orderedProductName = driver.findElement(By.xpath("(//th[contains(text(),'"+orderNumber+"')]/../td)[2]"));
			orderedProductName.isDisplayed();
			String orderedDate = driver.findElement(By.xpath("(//th[contains(text(),'"+orderNumber+"')]/../td)[4]")).getText();
			String CurrentDate = new SimpleDateFormat("EEE MMM dd").format(new Date());
			orderedDate.equalsIgnoreCase(CurrentDate);		
			return true;
		}
		
		else {
			return false;
		}
		
	}

}
