package abhiacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhiacademy.abstractcomponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='userEmail']")
	WebElement userEmail;
	
	@FindBy(xpath="//input[@id='userPassword']")
	WebElement userPassword;
	
	@FindBy(xpath="//input[@id='login']")
	WebElement submit;	
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;	
	
	public ProductCataloguePage loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		return new ProductCataloguePage(driver);
	}
	
	public void goTo(String url)
	{
		driver.get(url);
	}
	
	public String getErrorMssg()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
