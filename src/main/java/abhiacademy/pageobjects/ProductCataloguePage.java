package abhiacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhiacademy.abstractcomponent.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCataloguePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement loadingIcon;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCartBy = By.cssSelector(".card-body button:last-of-type");
	By toastMessgBy = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement productToSelect = getProductList().stream().filter(product ->
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return productToSelect;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement productToSelect = getProductByName(productName);
		productToSelect.findElement(addToCartBy).click();
		waitForElementToAppear(toastMessgBy);
		waitForelementToDisappear(loadingIcon);
	}
	
}
