package abhiacademy.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import abhiacademy.pageobjects.CartPage;
import abhiacademy.pageobjects.ProductCataloguePage;
import abhiacademy.testcomponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups = {"errorhandling"}, dataProvider = "getData")
	public void loginErrorValidation(String userName, String userPassword)
	{
		landingPage.loginApplication(userName, userPassword);
		Assert.assertEquals(landingPage.getErrorMssg(), "Incorrect email or password.");
	}
	
	@Test
	public void productOrderErrorVal() throws InterruptedException, IOException {
		
		String productName = "Zara Coat 3";
		
		//Login
		ProductCataloguePage productCatalogue = landingPage.loginApplication("nisha@hotmail.com", "Ni$ha789");
		
		//Product Catalogue
		productCatalogue.addProductToCart(productName);
		Thread.sleep(5000);
		CartPage cartPage = productCatalogue.accessShoppingCart();

		//Shopping Cart
		boolean match =	cartPage.checkProductInCart(productName+"3");
		Assert.assertFalse(match);		
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] invalidUserCred = new Object[2][2];
		invalidUserCred[0][0] = "abhikumar@outlook.com";
		invalidUserCred[0][1] = "Abhiacademy$151";
		invalidUserCred[1][0] = "abhikumar1@outlook.com";
		invalidUserCred[1][1] = "Abhiacademy$15";
		return invalidUserCred;
	}

}
