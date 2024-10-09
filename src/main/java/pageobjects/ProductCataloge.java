package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractClass1;

public class ProductCataloge extends AbstractClass1 {

	WebDriver driver;

	public ProductCataloge(WebDriver driver) {

		super(driver);// from child class to parent class also, you can send the variables with the
						// super keyword,sending driver here.
		this.driver = driver;
		PageFactory.initElements(this.driver, driver);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By prodList = By.cssSelector(".mb-3");
	By prodNames= By.cssSelector("b");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessageContainer = By.id("toast-container");
	
	@FindBy(css = ".ng-animating")
	WebElement toastMessage;


	public List<WebElement> getProductList() {

		waitForElementToAppear(prodList);
		return products;

	}
	public WebElement getProductByName(String productName){
		
		WebElement productName0 = getProductList().stream().filter(product -> 
		product.findElement(prodNames).getText().equals(productName)).findFirst().orElse(null);
		return productName0;
		
	}
	
	public void addProductToCart(String productName){
		
		WebElement productName1 = getProductByName(productName);
		
		productName1.findElement(addToCart).click();
		waitForElementToAppear(toastMessageContainer);
		waitForElementToAppear1(toastMessage);
		waitForElementToDisAppear(toastMessage);
		
	}

}
