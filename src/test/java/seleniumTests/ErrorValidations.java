package seleniumTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import pageObjects.CartPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;

public class ErrorValidations extends BaseTest {

	@Test(groups= {"smoke"},retryAnalyzer=testComponents.Retry.class)
	public void LoginErrorValidation() throws IOException {

		landingPage.loginApplication("ankitj@gmail.com", "AnkitJain");

		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMsg());
	}

	@Test
	public void ProductErrorValidation() throws IOException {

		String productName = "ZARA COAT 3";

		ProductCatalogue proCatalogue = landingPage.loginApplication("vartikajain@gmail.com", "Vartika@123");

//		finding ZARA COAT 3 product		
		proCatalogue.addProductToCart(productName);

		CartPage cartPage = proCatalogue.goToCartPage();

		boolean match = cartPage.verifyProductDisplay(productName);

		Assert.assertTrue(match);

	}

}
