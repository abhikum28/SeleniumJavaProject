package abhiacademy.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import abhiacademy.pageobjects.CartPage;
import abhiacademy.pageobjects.ConfirmationPage;
import abhiacademy.pageobjects.LandingPage;
import abhiacademy.pageobjects.MyOrderPage;
import abhiacademy.pageobjects.PaymentPage;
import abhiacademy.pageobjects.ProductCataloguePage;
import abhiacademy.testcomponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCataloguePage productCatalogue;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage;
	public MyOrderPage myOrderPage;
	private static String orderId;
	
	
	@Given("I landed on eCommerce page")
	public void i_landed_on_ecommerce_page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_password(String userName, String password)
	{
		productCatalogue = landingPage.loginApplication(userName, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		productCatalogue.addProductToCart(productName);
		Thread.sleep(5000);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_order(String productName)
	{
		cartPage = productCatalogue.accessShoppingCart();

		//Shopping Cart
		boolean match =	cartPage.checkProductInCart(productName);
		Assert.assertTrue(match);
		PaymentPage paymentPage = cartPage.checkOutFromCart();
		
		//Payments Page
		paymentPage.selectShipngCntry("unit", "United States");
		confirmationPage = paymentPage.submitPayment();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmation_page(String string)
	{
		//Confirmation Page
		boolean mssgMatch = confirmationPage.validateConfirmationMssg("THANKYOU FOR THE ORDER.");
		Assert.assertTrue(mssgMatch);
	
		orderId = confirmationPage.getOrderId();
		confirmationPage.signOut();	
		driver.quit();
	}
	
	@Then("{string} login error message is displayed")
	public void login_error_messg_displayed(String string)
	{
		Assert.assertEquals(landingPage.getErrorMssg(), string);
		driver.quit();
	}
	
	@When("access MyOrder page")
	public void access_MyOrder_page()
	{
		MyOrderPage myOrderPage = productCatalogue.accessMyOrder();
	}

	@Then("^(.+) displayed for Submitted order and delete$")
	public void displayed_submitted_order_delete(String productName)
	{
		myOrderPage.checkOrderHisAndDel(orderId, productName);
		myOrderPage.signOut();
		driver.quit();
	}
}
