
/**
 * @author Lee Shoham
 * @date Aug 8, 2016
 */

import java.io.BufferedReader;
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

public class CommunityReadTxt {
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

			// Getting location of wanted element to scroll to and scrolling
			// down
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.linkText("Add lines")));
			Point elem = driver.findElement(By.linkText("Add lines")).getLocation();
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + (elem.getY() - 300) + ");");

			// Creating new BufferrdReader object
			BufferedReader reader = new BufferedReader(new FileReader("lottery.txt"));

			// Skipping first row of headers
			String line = reader.readLine();
			String[] lineStrArr = null;
			int rowNumber = -1;

			// Traversing through file's lines
			while ((line = reader.readLine()) != null) {

				// Storing row's number to use in the xpath
				++rowNumber;

				// Splitting String line into string array without whitespaces,
				// using regular expression
				lineStrArr = line.split("\\s+");

				// Traversing through row's 'columns' and inserting values to
				// input fields
				for (int i = 0; i < lineStrArr.length; ++i) {
					String xpath = "(//input[@id='lotto_playslip_line_" + rowNumber + "_pool_0_col_" + i + "'])[1]";
					driver.findElement(By.xpath(xpath)).sendKeys(lineStrArr[i]);
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
