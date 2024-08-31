package practiceautomation.shoppingApplication;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import practiceautomation.TestComponents.BaseTest;

public class Ecommerce_application_purchase_product_Test extends BaseTest {
	
	@Test(dataProvider="getTestData")
	public void purchaseProduct(HashMap<String, String> input, ITestContext context) throws IOException, InterruptedException {
		ProductDetailPage ProductDetailPage = LoginPage.loginApplication(input.get("username"), input.get("password"));
		String Price = ProductDetailPage.addProductToCart(input.get("product"));
		ProductCartPage ProductCartPage = ProductDetailPage.goToCart();
		String CartPrice = ProductCartPage.verifyProductNameAtCart(input.get("product"));
		assertEquals(Price, CartPrice);
		PlaceOrderPage PlaceOrderPage = ProductCartPage.checkOutProduct();
		PlaceOrderPage.selectCountry(input.get("country"));
		PlacedOrderPage PlacedOrderPage = PlaceOrderPage.placeOrder();
		String message = PlacedOrderPage.verifyOrderSucess();
		assertTrue(message.equalsIgnoreCase("Thankyou for the order."));		
		context.setAttribute("OrderNumber", PlacedOrderPage.getOrderId());
		LoginPage.logoutApplication();
	}
	
	@Test(dataProvider="getTestData")
	public void verifyOrderHistory(HashMap<String,String> input, ITestContext context) {
	    String orderNumber = (String) context.getAttribute("OrderNumber");
		LoginPage.loginApplication(input.get("username"), input.get("password"));
		OrderDetailPage OrderDetailPage = LoginPage.goToOrders();
		assertTrue(OrderDetailPage.verifyOrderInTable(orderNumber));	
		LoginPage.logoutApplication();
		
	}
	
	@DataProvider
	public Object[][] getTestData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\practiceautomation\\testdata\\purchaseOrder.json");
		return new Object[][] {{data.get(0)}};
	}
	

}
