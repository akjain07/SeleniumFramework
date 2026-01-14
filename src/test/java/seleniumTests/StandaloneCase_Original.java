package seleniumTests;

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

public class StandaloneCase_Original {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();

		driver.manage().deleteAllCookies();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(5));

		String productName = "ZARA COAT 3";

		driver.get("https://rahulshettyacademy.com/client/#/auth/login");

		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("ankitjain@gmail.com");

		driver.findElement(By.id("userPassword")).sendKeys("AnkitJain");

		driver.findElement(By.cssSelector("#login")).click();

//		traditional way to print the name
//		List<WebElement> products = driver.findElements(By.cssSelector("div.card-body h5 b"));
//
//		for (WebElement pro : products) {
//			System.out.println(pro.getText());
//		}

//         Wait for products to load
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

//      Wait for all animations to stop
		wt.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

//      Re-locate product fresh from DOM
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

//		finding ZARA COAT 3 product
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		// Re-locate button JUST before clicking
		WebElement addToCart = prod.findElement(By.cssSelector(".card-body button:last-of-type"));

		// Real user click
		Actions actions = new Actions(driver);
		actions.moveToElement(addToCart).pause(Duration.ofMillis(200)).click().perform();

//		waiting for toast pop-up to appear before moving to cart page
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

//		waiting for blank screen to disappear
		wt.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

//		Cart Icon
		WebElement cartIcon = driver.findElement(By.cssSelector("button[routerlink*='cart']"));

//		Clicking on Cart button
		actions.moveToElement(cartIcon).pause(Duration.ofMillis(200)).click().perform();

//		products items on cart page
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean match = cartProducts.stream().anyMatch(produ->produ.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		
//		Clicking on Checkout button
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		actions.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		
//		Adding wait to let the countries options loaded
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.cssSelector(".ta-item:nth-child(3)")).click();
		
//		Clicking the Place Order button
		driver.findElement(By.cssSelector("a.ng-star-inserted")).click();
		
		String confirmMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
		
		driver.quit();

	}

}
