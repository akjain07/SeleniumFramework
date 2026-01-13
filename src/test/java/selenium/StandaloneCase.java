package selenium;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.OrderPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;

public class StandaloneCase extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test
	public void submitOrder() throws IOException {

		ProductCatalogue proCatalogue = landingPage.loginApplication("ankitjain@gmail.com", "AnkitJain");

//		finding ZARA COAT 3 product		
		proCatalogue.addProductToCart(productName);

		CartPage cartPage = proCatalogue.goToCartPage();

		boolean match = cartPage.verifyProductDisplay(productName);

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

}
