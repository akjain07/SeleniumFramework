package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reusableComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> productTiles;

	@FindBy(css = ".totalRow button")
	WebElement checkOutEle;

	public boolean verifyProductDisplay(String name) {
		boolean match = productTiles.stream().anyMatch(produ -> produ.getText().equalsIgnoreCase(name));
		return match;

	}
	
	public CheckoutPage goToCheckout() {
		checkOutEle.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}

}
