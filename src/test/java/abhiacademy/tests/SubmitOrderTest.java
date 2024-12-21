package abhiacademy.tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import abhiacademy.pageobjects.CartPage;
import abhiacademy.pageobjects.ConfirmationPage;
import abhiacademy.pageobjects.MyOrderPage;
import abhiacademy.pageobjects.PaymentPage;
import abhiacademy.pageobjects.ProductCataloguePage;
import abhiacademy.testcomponents.BaseTest;
import abhiacademy.testcomponents.Retry;


public class SubmitOrderTest extends BaseTest {

	private static String[] orderId = new String[2];
	
	@Test(dataProvider = "getOrderData", groups = {"PurchaseOrder"})
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		
		String countryName = "United States";
		
		//Login
		ProductCataloguePage productCatalogue = landingPage.loginApplication(input.get("userName"), input.get("userPassword"));
		
		//Product Catalogue
		productCatalogue.addProductToCart(input.get("productName"));
		Thread.sleep(5000);
		CartPage cartPage = productCatalogue.accessShoppingCart();

		//Shopping Cart
		boolean match =	cartPage.checkProductInCart(input.get("productName"));
		Assert.assertTrue(match);
		PaymentPage paymentPage = cartPage.checkOutFromCart();
		
		//Payments Page
		paymentPage.selectShipngCntry("unit", countryName);
		ConfirmationPage confirmationPage = paymentPage.submitPayment();
	
		//Confirmation Page
		boolean mssgMatch = confirmationPage.validateConfirmationMssg("THANKYOU FOR THE ORDER.");
		Assert.assertTrue(mssgMatch);
	
		orderId[Integer.parseInt(input.get("iterationNum"))] = confirmationPage.getOrderId();
		confirmationPage.signOut();		
	}
	
	@Test(dependsOnMethods = {"submitOrder"}, dataProvider = "getOrderData", groups = {"PurchaseOrder"}, retryAnalyzer = Retry.class)
	public void validateOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		
		//String productName = "Zara Coat 3";
		
		//Login
		ProductCataloguePage productCatalogue = landingPage.loginApplication(input.get("userName"), input.get("userPassword"));
		
		MyOrderPage myOrderPage = productCatalogue.accessMyOrder();
		
		myOrderPage.checkOrderHisAndDel(orderId[Integer.parseInt(input.get("iterationNum"))], input.get("productName"));
		myOrderPage.signOut();		
	}
	
	@DataProvider
	public Object[][] getOrderData()
	{
		HashMap<String, String> map0 = new HashMap<String, String>();
		map0.put("userName", "abhikumar@outlook.com");
		map0.put("userPassword", "Abhiacademy$15");
		map0.put("productName", "Zara Coat 3");
		map0.put("iterationNum", "0");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("userName", "abhikumar@outlook.com");
		map1.put("userPassword", "Abhiacademy$15");
		map1.put("productName", "ADIDAS ORIGINAL");
		map1.put("iterationNum", "1");
		
		return new Object[][] {{map0}, {map1}};
	}
	
}
