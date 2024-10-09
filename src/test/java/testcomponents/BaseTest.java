package testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	// Users/priyankaraju/eclipse-workspace/ecommerce/src/test/java/resources/GlobalData.properties
	//Users/priyankaraju/eclipse-workspace/ecommerce >>>Until here you can dynamically generate the path.u need not hardcode your local system path,
//System.getProperty("user.dir") +
	
	public WebDriver initializeDriver() throws Exception {
//If you write any file name with a .properties extension,

//then using properties class in Java,

//you will be able to parse this file

//and extract all global parameter values.
		
		Properties pro = new Properties();//properties class can read the global properties.
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src/test/java/resources/GlobalData.properties");//which can convert your file into input stream object,
		pro.load(fis);//load file .properties file
		//the argument here inside it is expecting is in form of InputStream.
		String browserName = pro.getProperty("browser");

		if (browserName.contains("chrome")) {

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "/Users/priyanka//documents//geckodriver");
			driver = new FirefoxDriver();
			// Firefox
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Edge
			System.setProperty("webdriver.edge.driver", "edge.exe");
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //implicit wait
		driver.manage().window().maximize(); //maximize browser
		return driver;

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws Exception {

		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goToUrl();
		return landingPage;

	}

	@AfterMethod(alwaysRun = true)
	public void close() {
		driver.close();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws Exception {

		// read json to string
		String jsonContent = FileUtils.readFileToString(
				new File(filePath),StandardCharsets.UTF_8);
		;

		// String to HashMap- Jackson Databind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {});
		return data;

		// {map, map}
	}
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;//casting driver to screenshot mode
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
		
	}

}
