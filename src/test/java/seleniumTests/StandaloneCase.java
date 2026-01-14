package seleniumTests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.OrderPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;

public class StandaloneCase extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException {

		ProductCatalogue proCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

//		finding ZARA COAT 3 product		
		proCatalogue.addProductToCart(input.get("productName"));

		CartPage cartPage = proCatalogue.goToCartPage();

		boolean match = cartPage.verifyProductDisplay(input.get("productName"));

		Assert.assertTrue(match);

//		Clicking on Checkout button
		CheckoutPage checkoutPage = cartPage.goToCheckout();

		checkoutPage.selectCountry("india");

//		Clicking the Place Order button
		ConfirmationPage confirmPage = checkoutPage.submitOrder();

		String confirmMsg = confirmPage.getMsg();

		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));

	}

//	to verify that the Product we placed is available in Order History or not
	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue proCatalogue = landingPage.loginApplication("ankitjain@gmail.com", "AnkitJain");
		OrderPage orderPage = proCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));

	}
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {
//			{"ankitjain@gmail.com","AnkitJain","ZARA COAT 3"},
//			{"vartikajain@gmail.com","Vartika@123","ADIDAS ORIGINAL"}
//		};
//	}
	
//	@DataProvider
//	public Object[][] getData() {
//		HashMap<String, String> map=new HashMap<String,String>();
//		map.put("email", "ankitjain@gmail.com");
//		map.put("password", "AnkitJain");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String, String> map1=new HashMap<String,String>();
//		map1.put("email", "vartikajain@gmail.com");
//		map1.put("password", "Vartika@123");
//		map1.put("productName", "ADIDAS ORIGINAL");
//		
//		return new Object[][] {{map},{map1}};
//	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
