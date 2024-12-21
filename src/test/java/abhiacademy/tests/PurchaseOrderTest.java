package abhiacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import abhiacademy.pageobjects.CartPage;
import abhiacademy.pageobjects.ProductCataloguePage;
import abhiacademy.testcomponents.BaseTest;

public class PurchaseOrderTest extends BaseTest {
		
	@Test(dataProvider = "getPurOrderData", groups = {"PurchaseOrder"})
	public void productOrderVal(HashMap<String, String> input) throws InterruptedException, IOException {
		
		//Login
		ProductCataloguePage productCatalogue = landingPage.loginApplication(input.get("userName"), input.get("userPassword"));
		
		//Product Catalogue
		productCatalogue.addProductToCart(input.get("productName"));
		Thread.sleep(5000);
		CartPage cartPage = productCatalogue.accessShoppingCart();

		//Shopping Cart
		boolean match =	cartPage.checkProductInCart(input.get("productName"));
		Assert.assertTrue(match);		
	}
	
	@DataProvider
	public Object[][] getPurOrderData() throws IOException
	{
		List<HashMap<String, String>> orderData = 
		readJsonData(System.getProperty("user.dir")+"//src//test//java//abhiacadeny//data//purchaseorderdata.json");
		return new Object[][] {{orderData.get(0)}, {orderData.get(1)}};
	}

}
