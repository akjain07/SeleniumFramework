package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
//		This line is mandatory when using Page Factory in Selenium.
//		It initializes all @FindBy elements in your Page Object class.
		PageFactory.initElements(driver, this);
	}
	
//	WebElement userEmail = driver.findElement(By.id("userEmail"));
	
//	PageFactory is a Selenium support library, not a design pattern. It uses annotations like @FindBy
	@FindBy(id="userEmail")
	WebElement userEmail;
	
//	driver.findElement(By.id("userPassword"))
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	public ProductCatalogue loginApplication(String email, String passwd) {
		userEmail.sendKeys(email);
		password.sendKeys(passwd);
		submit.click();
		ProductCatalogue proCatalogue=new ProductCatalogue(driver);
		return proCatalogue;
	}
	
	public void launchLandingPage() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}

}
