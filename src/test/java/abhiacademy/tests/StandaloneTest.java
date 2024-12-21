package abhiacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import abhiacademy.pageobjects.LandingPage;


public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		String productName = "Zara Coat 3";
		String countryName = "United States";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		
		//Login Screen
		driver.get("https://rahulshettyacademy.com/client");
		
		//Login
		
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("abhikumar@outlook.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Abhiacademy$15");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		
		//Product Catalogue
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement productToSelect = products.stream().filter(product ->
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		
		productToSelect.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		Thread.sleep(5000);
		
		WebElement btnCart = driver.findElement(By.cssSelector("button[routerlink*='cart'] label"));
		Actions actions = new Actions(driver);
		actions.moveToElement(btnCart).click().perform();
		
		//Shopping Cart
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match =	cartItems.stream().anyMatch(cartItem ->
		cartItem.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		
		WebElement btnCheckOut = driver.findElement(By.cssSelector(".totalRow button"));
		actions.moveToElement(btnCheckOut).click().perform();
		
		//Payments Page
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("unit");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.list-group")));
		
		List<WebElement> countriesList = driver.findElements(By.cssSelector("button.list-group-item span"));
		
		WebElement countryToSelect = countriesList.stream().filter(country -> country.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
		actions.moveToElement(countryToSelect).click().perform();
		//countryToSelect.click();
		
		driver.findElement(By.cssSelector("a.action__submit")).click();
		
		//Confirmation Page
		WebElement successMssg = driver.findElement(By.cssSelector("h1.hero-primary"));
		Assert.assertTrue(successMssg.getText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		String orderId = driver.findElement(By.cssSelector("tr.ng-star-inserted td.em-spacer-1 label")).getText().split(" ")[1];
		//System.out.println(orderId);
		
		
		WebElement myOrders = driver.findElement(By.cssSelector("button[routerlink*='myorders']"));
		actions.moveToElement(myOrders).click().perform();
		
		//My Orders page
		List<WebElement> ordersList = driver.findElements(By.cssSelector("tr.ng-star-inserted"));
		
		boolean orderFound = false;
		for(WebElement order : ordersList)
		{
			if(order.findElement(By.cssSelector("th")).getText().equalsIgnoreCase(orderId))
			{
				orderFound = true;
				Assert.assertTrue(order.findElement(By.cssSelector("td:nth-child(3)")).getText().equalsIgnoreCase(productName));
				order.findElement(By.cssSelector("button.btn-danger")).click();
			}
		}
		
		Assert.assertTrue(orderFound);
		
		//Sign Out
		WebElement signOut = driver.findElement(By.xpath("//button[text()=' Sign Out ']"));
		actions.moveToElement(signOut).click().perform();
		
	}
}
