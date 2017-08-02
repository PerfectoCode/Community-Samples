package pages;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public abstract class expPages {
	protected final AndroidDriver<WebElement> driver;
	boolean adClick;
	By expBtn = By.xpath("//*[text()='Expense']");
	
	public expPages(AndroidDriver<WebElement> d) {
		this.driver = d;
	}
	
	public addExpense addExp() {
		do {
			try  {
				driver.context("WEBVIEW");       	
				driver.findElement(expBtn).click();
				adClick = false;
			} catch (NoSuchElementException ne) {
				adClick = checkAdvert(driver, 1);
				if (!adClick) throw ne;
			}
		} while (adClick);
		return new addExpense(driver);
	}
	
    protected boolean checkAdvert(AppiumDriver<WebElement> driver, int timeout) {
        By adTree = By.xpath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]");
    	Map<String, Object> adParams = new HashMap<>();
		//trying to wait for the ad to come up and then click the Expense button
		
    	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.context("NATIVE_APP");       	
    	FluentWait<WebDriver> await = new FluentWait<WebDriver> (driver)
				.withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		try {
			await.until(ExpectedConditions.visibilityOf(driver.findElement(adTree)));
    		// go BACK and try again
    		adParams.clear();
    		adParams.put("keySequence", "BACK");
    		driver.executeScript("mobile:presskey", adParams);
    		System.out.println("Press the back button to get out of ad");
    		return true;
		} catch (Exception t) {
			System.out.println("no ad showed up");
			return false;
		}
    	
    }
}
