package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	
//	Actions actions = new Actions(driver);

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement animation;
	
//	We can't use page factory as page factory is exclusively for finding the elements
	By prodLists=By.cssSelector(".mb-3");
	By addToCartBtn = By.cssSelector(".card-body button:last-of-type");
	By toastMsg= By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(prodLists);
		return products;
	}
	
	public WebElement getProductByName(String name) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(name)).findFirst()
				.orElse(null);
		return prod;
	}
	
	public void addProductToCart(String name) {
		WebElement prod=getProductByName(name);
		WebElement addToCart = prod.findElement(addToCartBtn);
		getActionObject().moveToElement(addToCart).pause(Duration.ofMillis(200)).click().perform();
		waitForElementToAppear(toastMsg);
		waitForElementToDisappear(animation);


	}
	
	

}
