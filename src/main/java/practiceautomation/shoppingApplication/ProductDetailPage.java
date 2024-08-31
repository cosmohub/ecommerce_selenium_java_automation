package practiceautomation.shoppingApplication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practiceautomation.AbstractComponents.ReuseableComponents;

public class ProductDetailPage extends ReuseableComponents{
	public WebDriver driver;

	public ProductDetailPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);		
		
	}
	
	@FindBy(xpath="//div[@aria-label='Product Added To Cart']")
	WebElement successToastMessage;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement goToCartButton;
	
		
	public String addProductToCart(String productName) throws InterruptedException {
		Thread.sleep(5);
		String price = driver.findElement(By.xpath("//b[text()='"+ productName +"']/../..//div[@class='text-muted']")).getText();
		driver.findElement(By.xpath("//b[text()='"+ productName +"']/../..//button[contains(text(),'Add To Cart')]")).click();
		waitForElementToAppear(successToastMessage);
		price = "MRP "+price;
		return price;
		
	}
	
	public ProductCartPage goToCart() {
		waitForElementToAppear(goToCartButton);
		goToCartButton.click();
		ProductCartPage ProductCartPage = new ProductCartPage(driver);
		return ProductCartPage;
	}
}
