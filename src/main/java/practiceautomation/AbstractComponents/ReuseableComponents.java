package practiceautomation.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ReuseableComponents {
	
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	
	public ReuseableComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
   
	public void waitForElementToAppear(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToDisappear(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	
	

}
