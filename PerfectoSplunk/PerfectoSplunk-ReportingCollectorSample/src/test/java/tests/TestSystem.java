package tests;

import java.lang.reflect.Method;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.perfecto.splunk.ReportingCollectorFactory;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import pages.*;
import utilities.*;
import utilities.Library.byFields;

public class TestSystem extends ClassHelper {
	private AmazonHome homePage;

	public void setPagesAndHelpers(Library lib) throws IOException {
		// sets up the homePage class
		homePage = new AmazonHome(lib);
	}

	@Test
	public void OrderBook() throws Exception {
		// sets the RemoteWebDriver and initial library settings
		setPagesAndHelpers(lib);
		

		String step = "Step_" + lib.getStep();
		// sets the start of a transaction
		// Params
		// @1 short description of the step
		// @2 a more detailed description of the step
		getCollector().startTransaction(step, "goto Amazon.com");
		lib.goToPage("http://amazon.com", "Amazon.com");
		lib.waitForElement(30, byFields.xpath, "//*[contains(text(),'Your Lists')]");
		// sets the end of a transaction
		// Params
		// parameter 1 is the same short description as the start transaction
		// parameter 2 is the Perfecto timer you retrieved to validate against
		// the SLA
		getCollector().endTransaction(step, lib.getUXTimer());

		step = "Step_" + lib.getStep();
		// sets the start of a transaction
		// Params
		// @1 short description of the step
		// @2 a more detailed description of the step
		getCollector().startTransaction(step, "Enter book into search box");
		homePage.searchForItem("Army of darkness volume one", 60);
		Map<String, Object> params = new HashMap<>();
		params.put("content", "Army of darkness volume one");
		Object result = lib.getDriver().executeScript("mobile:text:find", params);
		// sets the end of a transaction
		// Params
		// parameter 1 is the same short description as the start transaction
		// parameter 2 is the Perfecto timer you retrieved to validate against
		// the SLA
		getCollector().endTransaction(step, lib.getUXTimer());

	}
}
