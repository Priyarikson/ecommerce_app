package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractClass1;

public class LandingPage extends AbstractClass1 {

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement login;

	// .ng-tns-c4-19.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	@FindBy(css = "[class*='flyInOut']") // regular expression
	WebElement errorMessage;

	public void goToUrl() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public ProductCataloge loginApplication(String email, String password) {

		userEmail.sendKeys(email);

		userPassword.sendKeys(password);

		login.click();

		ProductCataloge productCatalogePage = new ProductCataloge(driver);
		return productCatalogePage;

	}

	public String getErrorMessage() {
		waitForElementToAppear1(errorMessage);
		return errorMessage.getText();

	}

}
