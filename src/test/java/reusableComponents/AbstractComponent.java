package reusableComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CartPage;
import pageObjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartIcon;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement OrdersIcon;
	

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementToAppear(By locator) {

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(5));
		wt.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForWebElementToAppear(WebElement element) {

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(5));
		wt.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToDisappear(WebElement element) {

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(5));
		wt.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public Actions getActionObject() {
		Actions act=new Actions(driver);
		return act;
	}
	
	public CartPage goToCartPage() {
		
//		Clicking on Cart button
		getActionObject().moveToElement(cartIcon).pause(Duration.ofMillis(200)).click().perform();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrderPage() {
		
//		Clicking on Cart button
		getActionObject().moveToElement(OrdersIcon).pause(Duration.ofMillis(200)).click().perform();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}

}
