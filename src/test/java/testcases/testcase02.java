package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import abstractcomponents.OrderPage;
import org.testng.AssertJUnit;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import pageobjects.CartPage;
import pageobjects.CheckOutPage;
import pageobjects.ConfirmationPage;
import pageobjects.ProductCataloge;
import testcomponents.BaseTest;

public class testcase02 extends BaseTest {

	String productName = "ZARA COAT 3";
	
//submitOrder test :1 end to end work flow
	@Test
	public void submitOrder() throws Exception {

		ProductCataloge productCatalogePage = landingPage.loginApplication("priyaPP@gmail.com", "priyaPP77");

		productCatalogePage.addProductToCart(productName);
		CartPage cartPage = productCatalogePage.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.checkout();

		checkOutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {

		ProductCataloge productCatalogePage = landingPage.loginApplication("priyaPP@gmail.com", "priyaPP77");
		OrderPage ordersPage = productCatalogePage.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));

	}
	@DataProvider
	public Object[][] getData() throws Exception {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
				+ "//src/test/java/data/PurchaseFile.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
