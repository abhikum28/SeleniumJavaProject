package abhiacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import abhiacademy.abstractcomponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartItems;
	
	@FindBy(css=".totalRow button")
	WebElement btnCheckOut;
	
	public List<WebElement> getProductsFromCart()
	{
		return cartItems;
	}
	
	public boolean checkProductInCart(String productName)
	{
		boolean match =	getProductsFromCart().stream().anyMatch(cartItem ->
			cartItem.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public PaymentPage checkOutFromCart()
	{
		moveToELementAndClick(btnCheckOut);
		return new PaymentPage(driver);
	}

}
