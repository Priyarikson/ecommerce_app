package testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import org.testng.Assert;

import pageobjects.CartPage;
import pageobjects.ProductCataloge;
import testcomponents.BaseTest;

public class testcase002 extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test
	public void submitOrder() throws Exception {

		landingPage.loginApplication("pyaPP@gmail.com", "priyaPP77");// wrong
		AssertJUnit.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCataloge productCatalogePage = landingPage.loginApplication("priyaPP@gmail.com", "priyaPP77");

		productCatalogePage.addProductToCart(productName);
		CartPage cartPage = productCatalogePage.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
