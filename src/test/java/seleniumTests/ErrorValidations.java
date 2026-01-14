package seleniumTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;

public class ErrorValidations extends BaseTest {

	@Test(groups= {"smoke"})
	public void LoginErrorValidation() throws IOException {

		landingPage.loginApplication("ankitj@gmail.com", "AnkitJain");

		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());
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
