package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = "a.ng-star-inserted")
	WebElement submit;

	@FindBy(css = ".ta-item:nth-child(3)")
	WebElement selectIndia;
	
	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {
		getActionObject().sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectIndia.click();
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}

}
