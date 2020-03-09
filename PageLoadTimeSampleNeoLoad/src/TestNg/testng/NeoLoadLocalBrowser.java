package TestNg.testng;

import static com.neotys.selenium.proxies.NLWebDriverFactory.addProxyCapabilitiesIfNecessary;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.neotys.selenium.proxies.NLWebDriver;
import com.neotys.selenium.proxies.NLWebDriverFactory;

public class NeoLoadLocalBrowser {
//	static {
//		final String CHROME_DRIVER_PATH = "/Users/admin/Downloads/chromedriver";
//
//		final File file = new File(CHROME_DRIVER_PATH);
//		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
//	}
	static NLWebDriver driver;
	
	@BeforeClass
	public static void before() {
		
		final String CHROME_DRIVER_PATH = "/Users/admin/Downloads/chromedriver";

		final File file = new File(CHROME_DRIVER_PATH);
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

		//final ChromeDriver driver1 = new ChromeDriver();
		final ChromeDriver webDriver = new ChromeDriver(addProxyCapabilitiesIfNecessary(new DesiredCapabilities()));
		
		// projectPath used to open NeoLoad project, null to use currently opened Project.
		final String projectPath = "/Users/admin/neoload_projects/NewSample/NewSample.nlp";
		
		driver = NLWebDriverFactory.newNLWebDriver(webDriver, "SeleniumUserPath", projectPath);
	}
	@Test
	public void testSubmit() {
		driver.startTransaction("home1");
		driver.get("http://ushahidi.demo.neotys.com/");
		driver.stopTransaction();
		driver.startTransaction("reports");
		driver.findElement(By.id("mainmenu")).findElements(By.tagName("a")).get(1).click();
		driver.stopTransaction();
		driver.startTransaction("submit");
		driver.findElement(By.partialLinkText("SUBMIT")).click();
		driver.stopTransaction();
	}
	
	@Test
	public void testGetAlerts() {
		driver.startTransaction("home2");
		driver.get("http://ushahidi.demo.neotys.com/");
		driver.stopTransaction();
		driver.startTransaction("alerts");
		driver.findElement(By.partialLinkText("GET ALERTS")).click();
		driver.stopTransaction();
	}
	
	@AfterClass
	public static void after() {
		if (driver != null) {
			driver.quit();
		}
	}
}