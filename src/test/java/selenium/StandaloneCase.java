package selenium;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;

public class StandaloneCase {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();

		driver.manage().deleteAllCookies();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(5));

		String productName = "ZARA COAT 3";

		LandingPage landingPage = new LandingPage(driver);
		
		landingPage.launchLandingPage();

		ProductCatalogue proCatalogue=landingPage.loginApplication("ankitjain@gmail.com", "AnkitJain");

		List<WebElement> products = proCatalogue.getProductList();

//      Wait for all animations to stop
		wt.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

//		finding ZARA COAT 3 product
		WebElement prod = proCatalogue.getProductByName(productName);
		
		proCatalogue.addProductToCart(productName);
		
		CartPage cartPage=proCatalogue.goToCartPage();
	
		boolean match =cartPage.verifyProductDisplay(productName);

		Assert.assertTrue(match);

//		Clicking on Checkout button
		CheckoutPage checkoutPage =cartPage.goToCheckout();
		
		checkoutPage.selectCountry("india");
		
		ConfirmationPage confirmPage = checkoutPage.submitOrder();

//		Clicking the Place Order button
		checkoutPage.submitOrder();

		String confirmMsg = confirmPage.getMsg();

		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));

		driver.quit();

	}

}
