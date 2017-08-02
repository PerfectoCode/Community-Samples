package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class MainPg extends expPages {

	public MainPg(AndroidDriver<WebElement> d) {
		super(d);
		WebElement expenseBtn = null;
		// validate that we are on the main page
		// looking for the Expense Button and that it is displayed
		do {
			try  {
				driver.context("WEBVIEW");       	
				expenseBtn = driver.findElement(expBtn);
				adClick = false;
			} catch (NoSuchElementException ne) {
				adClick = checkAdvert(driver, 1);
				if (!adClick) throw ne;
			}
		} while (adClick);
		if (!expenseBtn.isDisplayed()) throw new NoSuchElementException("Not on the main page");
	}

}
