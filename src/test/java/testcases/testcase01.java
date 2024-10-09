package testcases;

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

//import frameworkdesign.seleniumframework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class testcase01 {
public static void main(String[] args) {
		
		
		
		String productName1 = "ZARA COAT 3";
		String countryName = "India";
		// https://bonigarcia.dev/webdrivermanager/
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		//LandingPage object1 = new LandingPage(driver);
		
		//implicitlyWait() command 
		//implicitWait 
		//global wait
		//all the webelements
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		//password -priyaPP77
		//priyaPP@gmail.com
		//1231231234
		//priyanka
		//raju
		//Student
		
		
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("priyaPP@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("priyaPP77");
		driver.findElement(By.id("login")).click();

		//explicit wait
		//until() command
		//visibilityOfElementLocated()command		
		//it is not global wait
		//it is web element specific wait >>only wait for the element you mentioned
		//wait is the object created here
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		//An expectation for checking that an element is present on the DOM of a page and visible.
		//waiting for (By.cssSelector(".mb-3")) webelement classname >>>".mb-3"
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		//WebElement is an interface in selenium which will be a representation of any object we see on a web page.
		//For example button, input box, link, text, image, etc.,
		WebElement prod = products.stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName1)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//An expectation for checking that all elements present on the web page that match the locator are visible.
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("toast-container")));	
		//An expectation for checking that an element, known to be present on the DOM of a page, is visible.
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//An expectation for checking the element to be invisible
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName1));
		Assert.assertTrue(match);
		//totalRow - classname
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//auto suggestive dropdown that when you start typing the letters then options will appear.
		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), countryName).build().perform();

		//xpath class for the autosuggestive dropdowns - ta-results list-group ng-star-inserted

		// "//Section[@class='ta-results list-group ng-star-inserted']/button //span[text()='India']"
		//Parameters:
		//locator used to find the element
		//Returns:
		//the WebElement once it is located and visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("//Section[@class='ta-results list-group ng-star-inserted']/button //span[text()=' "+countryName+"']")).click();

		System.out.println("Entered Shipping country "+countryName);

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btnn.action__submit")));

		driver.findElement(By.cssSelector(".btnn.action__submit")).click();

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		//driver.close();
		
		
		
	}
}
