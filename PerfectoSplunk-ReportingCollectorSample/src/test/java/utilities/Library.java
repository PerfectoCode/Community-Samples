package utilities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.common.base.Function;
import com.perfecto.splunk.ReportingCollectorFactory;

import utilities.Library.byFields;

import com.perfecto.splunk.ReportingCollectorFactory;

public class Library {

	public RemoteWebDriverExtended driver;
	private int step = 1;
	private String testName;

	public Library(RemoteWebDriverExtended driver) {
		this.driver = driver;
	}

	// returns the current step
	public int getStep() {
		return step;
	}

	// checks if element exists
	public Boolean elementExists(byFields by, String element) {

		try {
			driver.findElement(getBy(by, element));
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	// sets the byfield enum
	public enum byFields {
		id, name, css, tag, className, linkText, partialLinkText, xpath
	}

	// available driver contexts
	public enum availableContexts {
		WEBVIEW, NATIVE_APP, VISUAL
	}

	// used by testNG beforeMethod to set the testName
	public void setTestName(String test) {
		testName = test;
	}

	// returns the testName
	public String getTestName() {
		return testName;
	}

	// checks if current test is running on a device
	public Boolean isDevice() {
		try {
			if (driver.getCapabilities().getCapability("platformName").equals("Android")
					|| driver.getCapabilities().getCapability("platformName").equals("iOS")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	// returns the driver
	public RemoteWebDriverExtended getDriver() {
		return driver;
	}

	// switch driver method
	// switches dom between native and web and visual
	// "WEBVIEW_N", "NATIVE_APP" or "VISUAL"
	public RemoteWebDriverExtended switchToContext(availableContexts context) {

		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		Map<String, String> params = new HashMap<>();
		params.put("name", context.toString());
		executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
		return driver;
	}

	// get current driver context
	public String getCurrentContextHandle() {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		String context = (String) executeMethod.execute(DriverCommand.GET_CURRENT_CONTEXT_HANDLE, null);
		return context;
	}

	// get a list of all available contexts
	public List<String> getContextHandles() {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		List<String> contexts = (List<String>) executeMethod.execute(DriverCommand.GET_CONTEXT_HANDLES, null);
		return contexts;
	}

	// can exit driver during exception although afterTest from testNG should
	// handle this in almost all cases
	public void errorCleanup() {
		driver.close();
		driver.quit();
	}

	// will return the appropriate by object based on string
	public By getBy(byFields by, String element) {
		if (by == byFields.id) {
			return By.id(element);
		} else if (by == byFields.name) {
			return By.name(element);
		} else if (by == byFields.css) {
			return By.cssSelector(element);
		} else if (by == byFields.tag) {
			return By.tagName(element);
		} else if (by == byFields.className) {
			return By.className(element);
		} else if (by == byFields.linkText) {
			return By.linkText(element);
		} else if (by == byFields.partialLinkText) {
			return By.partialLinkText(element);
		} else if (by == byFields.xpath) {
			return By.xpath(element);
		} else {
			return By.id("");
		}
	}

	// gets the most recent Perfecto timer
	public long timerGet(String timerType) {
		String command = "mobile:timer:info";
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", timerType);
		long result = (long) driver.executeScript(command, params);
		return result;
	}

	// returns ux timer
	public long getUXTimer() {
		if (isDevice()) {
			return timerGet("ux");
		} else {
			return 0;
		}
	}

	// sets the initial page for the browser
	public void goToPage(String url, String title) {
		driver.get(url);
		waitForTitle(10, title);
		step++;
	}

	// will attempt to wait on the page to load and searches for the title
	// supplied
	public void waitForTitle(int seconds, String title) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.titleContains(title));
		} catch (Exception ex) {

		}
	}

	// sets the initial page for the browser
	public void goToPage(String url) {
		driver.get(url);
		step++;
	}

	// gets the current url
	public String getUrl() {
		String url = "can't find url";
		try {
			url = driver.getCurrentUrl().toString();
		} catch (Exception ex) {

		}
		return url;
	}

	// will attempt to wait on the page to load and searches for the element
	// supplied
	public WebElement waitForElement(int seconds, byFields by, String element) {
		WebElement we = null;
		if (seconds != 0) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, seconds);
				we = wait.until(ExpectedConditions.elementToBeClickable(getBy(by, element)));
			} catch (Exception ex) {
			}

		}
		return we;
	}

	// clears text field
	public void clearText(byFields by, String element, int timeOut) {
		try {
			waitForElement(timeOut, by, element);
			driver.findElement(getBy(by, element)).clear();
			step++;
		} catch (Exception ex) {

		}

	}

	// sets text field value and will clear the field prior to writing based on
	// the clear boolean
	public void setText(byFields by, String element, String data, Boolean clear, int timeOut) {
		try {
			waitForElement(timeOut, by, element);
			if (clear) {
				clearText(by, element, timeOut);
			}
			driver.findElement(getBy(by, element)).sendKeys(data);
			waitForElement(timeOut, by, element);
			step++;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	// gets the text of an element
	public String getText(byFields by, String element, int timeOut) {

		try {
			waitForElement(timeOut, by, element);
			driver.findElement(getBy(by, element)).getText().toString();
		} catch (Exception ex) {

		}
		return driver.findElement(getBy(by, element)).getText().toString();
	}

	// clicks and element
	public void clickElement(byFields by, String element, int timeOut) {
		if (timeOut != 0) {
			waitForElement(timeOut, by, element).click();
		} else {
			driver.findElement(getBy(by, element)).click();
		}

		step++;

	}

	// will submit a form based on an element
	public void submitElement(byFields by, String element, int timeOut) {
		try {
			waitForElement(timeOut, by, element);
			driver.findElement(getBy(by, element)).submit();
			waitForElement(timeOut, by, element);
			step++;
		} catch (Exception ex) {

		}

	}
}
