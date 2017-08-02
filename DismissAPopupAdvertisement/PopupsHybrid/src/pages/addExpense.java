package pages;

import java.util.List;

import org.openqa.selenium.*;

import io.appium.java_client.android.AndroidDriver;

public class addExpense extends expPages {

	By catMenu = By.xpath("(//select[@id='CategoryExpense'])");
	By subCatMenu = By.xpath("(//select[@id='SubCategoryExpense'])");
	By expDesc = By.xpath("(//input[@id='EDescription'])");
	By expAmnt = By.xpath("(//input[@id='Amountforex'])");
	By saveBtn = By.xpath("//*[text()='Save']");
	By confirm = By.xpath("//android.widget.TextView[@text='Expense added succesfully']");
	By okBtn = By.xpath("//android.widget.Button[@text='OK']");
	By pgHead = By.xpath("//*[contains(text(), 'Add Expense')]");
	
	public addExpense(AndroidDriver<WebElement> d) {
		super(d);
		// verify that we are on the add expense page
		try {
			driver.findElement(pgHead);
		} catch (NoSuchElementException e) {
			throw e;
		}
	}
	
	public void addCategory(String cat) {
    	// open the Expense category menu
		do {
			try  {
				driver.context("WEBVIEW");       	
	        	driver.findElement(catMenu).click();
	
	        	// select the expense category
	        	driver.context("NATIVE_APP");
	        	driver.scrollTo(cat).click();
				adClick = false;
			} catch (NoSuchElementException ne) {
				adClick = checkAdvert(driver, 1);
				if (!adClick) throw ne;
			}
		} while (adClick);
	}

	public void addSubCat(String subCat) {
		By subCatEntry = By.xpath(String.format("//android.widget.CheckedTextView[@text='" + "%s']", subCat));
		do {
			try  {
	        	driver.context("WEBVIEW");
	        	driver.findElement(subCatMenu).click();
	
	        	driver.context("NATIVE_APP");
	        	driver.findElement(subCatEntry).click();
				adClick = false;
			} catch (NoSuchElementException ne) {
				adClick = checkAdvert(driver, 1);
				if (!adClick) throw ne;
			}
		} while (adClick);
	}
	
	public void addExp(String desc, String amount) {
		do {
			try  {
	        	driver.context("WEBVIEW");
	        	driver.findElement(expDesc).sendKeys(desc);
	
	        	driver.findElement(expAmnt).sendKeys(amount);
				adClick = false;
			} catch (NoSuchElementException ne) {
				adClick = checkAdvert(driver, 1);
				if (!adClick) throw ne;
			}
		} while (adClick);
	}
	
	public boolean saveExp() {
		do {
			try  {
	        	driver.context("WEBVIEW");
	        	driver.findElement(saveBtn).click();
				adClick = false;
			} catch (NoSuchElementException ne) {
				adClick = checkAdvert(driver, 1);
				if (!adClick) throw ne;
			}
		} while (adClick);

    	// verify that Success Notification appears
    	driver.context("NATIVE_APP");
    	List<WebElement> elems = driver.findElements(confirm);
    	if (elems.isEmpty()) {
    		return false;
    	} else {
    		driver.findElement(okBtn).click();
    		return true;
    	}
		
	}
}
