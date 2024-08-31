package practiceautomation.shoppingApplication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practiceautomation.AbstractComponents.ReuseableComponents;

public class LoginPage extends ReuseableComponents {
	public WebDriver driver;
	

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement loginButton;
	
	@FindBy(xpath="//button[contains(text(),'ORDERS')]")
	   WebElement ordersButton;
	
	@FindBy(xpath="//button[contains(text(),'Sign Out')]")
	WebElement signOutButton;
			
	public ProductDetailPage loginApplication(String username, String password) {
		waitForElementToAppear(userEmail);
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		loginButton.click();
		ProductDetailPage 	ProductDetailPage = new ProductDetailPage(driver);
		return ProductDetailPage;

	}

	public OrderDetailPage goToOrders() {
		waitForElementToAppear(ordersButton);
		ordersButton.click();
		OrderDetailPage OrderDetailPage = new OrderDetailPage(driver);
		return OrderDetailPage;
			}
	
	public void logoutApplication() {
		Actions a = new Actions(driver);
		waitForElementToAppear(signOutButton);
		a.moveToElement(signOutButton).click().perform();
		waitForElementToAppear(userEmail);
	}
}
