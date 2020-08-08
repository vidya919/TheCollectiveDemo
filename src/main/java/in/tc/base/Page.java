package in.tc.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import in.tc.utilities.ExcelReader;
import in.tc.utilities.ExtentManager;
import in.tc.utilities.Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Page {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\in\\tc\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static TopMenu menu;

	public Page() {

		if (driver == null) {

			try {
				fis = (FileInputStream) new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\in\\tc\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Congfig file loaded!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = (FileInputStream) new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\in\\tc\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (config.getProperty("browser").equals("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equals("chrome")) {

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("-disable-extentions");
				options.addArguments("-disable-infobars");

				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);
				log.debug("Chrome launched!!");
			} else if (config.getProperty("browser").equals("ie")) {

				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to:" + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);

			menu = new TopMenu(driver);
		}
	}
		

	public static void quit(){
		
		driver.quit();
	}
	
	
	
	public static void click(String locator) {

		if (locator.endsWith("_CSS")) {

			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {

			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicking on: " + locator);
	}

	public static void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			
		driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {

			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		}
		test.log(LogStatus.INFO, "Typing in: " + locator + "Entered value as : " + value);
	}

	static WebElement dropdown;

	public static void select(String locator, String value) {

		if (locator.endsWith("_CSS")) {

			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));

		} else if (locator.endsWith("_XPATH")) {

			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		}

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		test.log(LogStatus.INFO, "Selecting dropdown: " + locator + "Value is: " + value);

	}
	
	
	public static void mousehover(String locator) {

		Actions actions = new Actions(driver);
		
		if (locator.endsWith("_CSS")) {

			WebElement menuOption = driver.findElement(By.cssSelector(OR.getProperty(locator)));
			actions.moveToElement(menuOption).perform();

		} else if (locator.endsWith("_XPATH")) {

			WebElement menuOption = driver.findElement(By.xpath(OR.getProperty(locator)));
			actions.moveToElement(menuOption).perform();
		}

		test.log(LogStatus.INFO, "Mouse hover on: " + locator);

	}

	public boolean isElementPresent(By by) {

		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;
		}
	}

	public static void verifyEquals(String expected, String actual) throws IOException {

		try {
			Assert.assertEquals("", "");
		} catch (Throwable t) {

			// TestNg
			Utilities.CaptureScreenshot();
			Reporter.log("<br>" + "Verification failure: " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + "><img src=" + Utilities.screenshotName
					+ " height=200 weight=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// ExtentReports
			test.log(LogStatus.FAIL, "Verification failed with exception:" + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));

		}
	}

}
