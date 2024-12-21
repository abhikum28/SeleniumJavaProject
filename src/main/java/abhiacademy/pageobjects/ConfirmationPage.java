package abhiacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import abhiacademy.abstractcomponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	
	WebDriver Driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.Driver = driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="h1.hero-primary")
	WebElement successMssgEle;
	
	@FindBy(css="tr.ng-star-inserted td.em-spacer-1 label")
	WebElement orderEle;
	
	public boolean validateConfirmationMssg(String successMssg)
	{
		return successMssgEle.getText().equalsIgnoreCase(successMssg);
	}
	
	public String getOrderId()
	{
		return orderEle.getText().split(" ")[1];
	}
	
}
