
/**
 * @author Lee Shoham
 * @date Aug 8, 2016
 */

import java.io.FileReader; 
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVReader;


public class CommunityReadCsv {
	public static void main(String[] args) throws MalformedURLException, IOException {
		System.out.println("Run started");

		String browserName = "mobileOS";
		DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
		String host = "cloud.perfectomobile.com";
		capabilities.setCapability("user", "user@perfectomobile.com");
		capabilities.setCapability("password", "password");

		// TODO: Change your device ID
		capabilities.setCapability("deviceName", "device#");

		// Use the automationName capability to define the required framework -
		// Appium (this is the default) or PerfectoMobile.
		capabilities.setCapability("automationName", "PerfectoMobile");

		RemoteWebDriver driver = new RemoteWebDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"),
				capabilities);

		try {
			// Navigating to website
			driver.get("https://www.national-lottery.co.uk/games/lotto");

			// Getting location of wanted element to scroll to and scrolling down
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.linkText("Add lines")));
			Point elem = driver.findElement(By.linkText("Add lines")).getLocation();
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + (elem.getY() - 300) + ");");

			// Creating a new CSVReader object
			CSVReader reader = new CSVReader(new FileReader("lottery.csv"));
			// nextLine[] is an array of values from the line
			String[] nextLine;

			// moving 1 line down to skip table's headers
			nextLine = reader.readNext();

			// iterating over the table lines and extracting values
			int rowNumber = -1;
			while ((nextLine = reader.readNext()) != null) {
				++rowNumber;
				for (int i = 0; i < nextLine.length; ++i) {
					String xpath = "(//input[@id='lotto_playslip_line_" + rowNumber + "_pool_0_col_" + i
							+ "'])[1]";
					driver.findElement(By.xpath(xpath)).sendKeys(nextLine[i]);
				}
			}
			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				driver.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			driver.quit();
		}

		System.out.println("Run ended");
	}
}
