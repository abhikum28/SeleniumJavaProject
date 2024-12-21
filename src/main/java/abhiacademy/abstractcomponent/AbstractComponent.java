package abhiacademy.abstractcomponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abhiacademy.pageobjects.CartPage;
import abhiacademy.pageobjects.MyOrderPage;

public class AbstractComponent {
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='cart'] label")
	WebElement btnCart;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement btnMyOrders;
	
	@FindBy(xpath="//button[text()=' Sign Out ']")
	WebElement btnSignOut;

	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForelementToDisappear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void moveToELementAndClick(WebElement ele)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).click().perform();
	}
	
	public CartPage accessShoppingCart()
	{
		moveToELementAndClick(btnCart);
		return new CartPage(driver);
	}
	
	public MyOrderPage accessMyOrder()
	{
		moveToELementAndClick(btnMyOrders);
		return new MyOrderPage(driver);
	}
	
	public void signOut()
	{
		moveToELementAndClick(btnSignOut);
	}
}
