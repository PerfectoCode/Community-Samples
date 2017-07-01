/**
 * 
 */
package com.quantum.steps.AmazonSearch;

import java.util.HashMap;
import java.util.Map;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.steps.PerfectoApplicationSteps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@QAFTestStepProvider
public class AmazonSearch {
	 QAFExtendedWebDriver driver = new WebDriverTestBase().getDriver();
	 QAFExtendedWebElement we;

	
	
	@Then("^I go to amazon site$")
	public void goToAmazon(String tone)
	{
		driver.get("http://www.amazon.com");
		we = (QAFExtendedWebElement) driver.findElement("search.input");
		we.waitForPresent(20000);
	
	}
	
	@Then("^I search for \"([^\"]*)\"$")
	public void searchFor(String searchText)
	{
		we = (QAFExtendedWebElement) driver.findElement("search.input");
		we.click();
		we.sendKeys(searchText);
		we = (QAFExtendedWebElement) driver.findElement("submit.button");
		we.click();
	}

}
