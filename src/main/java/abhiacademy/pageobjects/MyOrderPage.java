package abhiacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import abhiacademy.abstractcomponent.AbstractComponent;

public class MyOrderPage extends AbstractComponent {
	
	WebDriver driver;

	public MyOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css="tr.ng-star-inserted")
	List<WebElement> ordersList;
	
	By orderBy = By.cssSelector("th");
	By productBy = By.cssSelector("td:nth-child(3)");
	By deleteBy = By.cssSelector("button.btn-danger");
	

	
	public List<WebElement> getOrdersList()
	{
		return ordersList;
	}
	
	public void checkOrderHisAndDel(String orderId, String productName)
	{
		boolean orderFound = false;
		List<WebElement> orders = getOrdersList();
		for(WebElement order : orders)
		{
			if(order.findElement(orderBy).getText().equalsIgnoreCase(orderId))
			{
				orderFound = true;
				Assert.assertTrue(order.findElement(productBy).getText().equalsIgnoreCase(productName));
				order.findElement(deleteBy).click();
			}
		}
		
		Assert.assertTrue(orderFound);
	}
}
