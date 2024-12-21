package abhiacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abhiacademy.abstractcomponent.AbstractComponent;

public class PaymentPage extends AbstractComponent{
	
	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryDrpDwn;
	
	@FindBy(css="button.list-group-item span")
	List<WebElement> shipngCntryResult;
	
	@FindBy(css="a.action__submit")
	WebElement btnSubmit;
	
	By searchResBy = By.cssSelector("section.list-group");
	
	public void searchShipngCntry(String searchString)
	{
		countryDrpDwn.sendKeys(searchString);
		waitForElementToAppear(searchResBy);
	}
	
	public List<WebElement> getShipngCntryResult()
	{
		return shipngCntryResult;
	}
	
	public void selectShipngCntry(String searchString, String countryName)
	{
		searchShipngCntry(searchString);
		WebElement countryToSelect = getShipngCntryResult().stream().filter(country -> country.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
		moveToELementAndClick(countryToSelect);
	}
	
	public ConfirmationPage submitPayment()
	{
		btnSubmit.click();
		return new ConfirmationPage(driver);
	}
}
