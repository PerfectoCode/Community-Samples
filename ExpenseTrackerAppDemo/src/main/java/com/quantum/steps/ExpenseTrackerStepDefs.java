package com.quantum.steps;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.interactions.Actions;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.utils.DeviceUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

@QAFTestStepProvider
public class ExpenseTrackerStepDefs {

	
	QAFExtendedWebDriver driver = DeviceUtils.getQAFDriver();
	
	String appPackage = (String) ConfigurationManager.getBundle().getProperty("appPackageType");
	String identifier = (String) ConfigurationManager.getBundle().getProperty(appPackage);


	@And("^I add expense with head \"([^\"]*)\" , amount \"([^\"]*)\" , \"([^\"]*)\" currency and category \"([^\"]*)\"$")
	public void adddbasicExpense(String Head, String Amount, String Currency, String Category) {

		if (ConfigurationManager.getBundle().getProperty("appType").equals("Hybrid")
				|| ConfigurationManager.getBundle().getProperty("appType").equals("Web")) {

			if (ConfigurationManager.getBundle().getProperty("appType").equals("Web")) {
				CommonStep.click("add.buttonweb");
				
				if(ConfigurationManager.getBundle().getProperty("remote.server").toString().contains("fast")){
				CommonStep.sendKeys("C:\\temp\\example.png", "attachReceipt.button");
				}
			} else {
				CommonStep.click("add.buttonmobile");
			}
			
			
			

			CommonStep.click("head.dropdown");
			DeviceUtils.getQAFDriver().findElementByXPath("//*[contains(@class,'alert-radio-label sc-ion-alert') and text()='"+Head+"']").click();
			CommonStep.click("ok.button");

			if (ConfigurationManager.getBundle().getProperty("appType").equals("Web")) {

				CommonStep.sendKeys(Amount, "amount.text.boxW");
			} else {
				
				if (DeviceUtils.getDeviceProperty("os").contains("Android")) {
					CommonStep.sendKeys(Amount, "amount.text.box");
				
				}else{
					CommonStep.sendKeys(Amount, "amount.text.boxW");
				
				}
			}

			CommonStep.click("currency.dropdown");
			DeviceUtils.getQAFDriver().findElementByXPath(
					"//*[contains(@class,\"alert-radio-label sc-ion-alert\") and contains(text(),'"+Currency+"')]")
					.click();

			CommonStep.click("ok.button");
			CommonStep.click("category.dropdown");
			DeviceUtils.getQAFDriver().findElementByXPath(
					"//*[contains(@class,\"alert-radio-label sc-ion-alert\") and contains(text(),'"+Category+"')]")
					.click();
			CommonStep.click("ok.button");

			if (ConfigurationManager.getBundle().getProperty("appType").equals("Hybrid")) {
				DeviceUtils.swipe("50%,90%", "50%,50%");
			}

			CommonStep.sendKeys("Client Visit Bill", "details.text.box");

		} else {

			CommonStep.click("add.label");
			// added new step
			if (DeviceUtils.getDeviceProperty("model").contains("iPhone-6")){
				
				CommonStep.click("attachment.link");
				DeviceUtils.startImageInjection("PUBLIC:ExpenseTracker/Images/CoffeeReceipt.jpg", identifier, "identifier");
				CommonStep.click("camera.button");

				if (ConfigurationManager.getBundle().getProperty("appType").equals("Native")) {
					if (DeviceUtils.getDeviceProperty("os").contains("iOS")) {
						try {
							CommonStep.click("Ok.button");
						} catch (Exception e) {
							;
						}
						CommonStep.click("capture.button");
						CommonStep.click("usePhoto.button");
						
						DeviceUtils.stopImageInjection();
						
					}
				}
			}
			CommonStep.click("head.text.box");

			if (DeviceUtils.getDeviceProperty("os").contains("Android")) {
				DeviceUtils.getQAFDriver().findElementByXPath("//*[@text='" + Head + "']").click();
			} else {

				DeviceUtils.setPickerWheel("pickerWheel1", "next", Head);
				

			}
			CommonStep.clear("amount.text.box");
			CommonStep.sendKeys(Amount, "amount.text.box");
			CommonStep.click("currency.text.box");

			if (DeviceUtils.getDeviceProperty("os").contains("Android")) {
				DeviceUtils.getQAFDriver().findElementByXPath("//*[contains(@text,'"+Currency.replaceAll("\\s", "")+"')]").click();
				DeviceUtils.hideKeyboard();
			}

			else {

				DeviceUtils.setPickerWheel("pickerWheel1", "next", Currency);
			}

			new QAFExtendedWebElement("category.text.box").click();
			if (DeviceUtils.getDeviceProperty("os").contains("Android")) {
				DeviceUtils.getQAFDriver().findElementByXPath("//*[@text='" + Category + "']").click();

			} else  {

				DeviceUtils.setPickerWheel("pickerWheel1", "next", Category);
				CommonStep.click("date.text.box");

			}

		}

	}

	@And("^I login to Expense Tracker app with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void loginToInvoiceApp(String username, String password) throws InterruptedException {

		if (ConfigurationManager.getBundle().getProperty("appType").equals("Hybrid")
				|| ConfigurationManager.getBundle().getProperty("appType").equals("Native")) {
			if (ConfigurationManager.getBundle().getProperty("appType").equals("Hybrid")) {
				DeviceUtils.switchToContext("WEBVIEW_1");
				System.out.println(DeviceUtils.getCurrentContext());
			}
			if (ConfigurationManager.getBundle().getProperty("appType").equals("Hybrid")) {
				Thread.sleep(3000);
				if(DeviceUtils.getDeviceProperty("OS").equalsIgnoreCase("iOS")){
				CommonStep.clear("username.text.box");
				CommonStep.sendKeys(username, "username.text.box");
				CommonStep.sendKeys(password, "password.text.box");
				}else{
				
				
				Actions actions = new Actions(driver);
				CommonStep.click("username.text.box");
				actions.sendKeys(username).build().perform();
				
				
				CommonStep.click("password.text.box");
				actions.sendKeys(password).build().perform();
				}
			} else{
				
				CommonStep.clear("username.text.box");
				CommonStep.sendKeys(username, "username.text.box");
				CommonStep.sendKeys(password, "password.text.box");
				
			}
			
			if(DeviceUtils.getDeviceProperty("OS").equalsIgnoreCase("iOS")){
				if (ConfigurationManager.getBundle().getProperty("appType").equals("Native")){
			try{
				CommonStep.click("done.button");
			}catch(Exception e){
				;
			}
				}
			}
		    if(Integer.parseInt(DeviceUtils.getDeviceProperty("osVersion").substring(0, 1))==6){
		    	
		    	CommonStep.click("login.button");
		    	
		    }else{
			CommonStep.click("touchID_toggle");
			
			CommonStep.click("login.button");

			DeviceUtils.setFingerprint("identifier", identifier, "fail", "authFailed");
			Thread.sleep(1000);
			if (DeviceUtils.getDeviceProperty("os").contains("iOS")) {
				try {
					CommonStep.click("Ok.button");
					Thread.sleep(1000);
				} catch (Exception e) {
					;
				}
				
				CommonStep.click("login.button");
			}
			DeviceUtils.setFingerprint("identifier", identifier, "success", "authFailed");
		    }
			

		} else if (ConfigurationManager.getBundle().getProperty("appType").equals("Web")) {
			driver.manage().window().maximize();
			driver.get("http://expensetracker.perfectomobile.com");
			CommonStep.sendKeys(username, "username.text.box");
			CommonStep.sendKeys(password, "password.text.box");
			CommonStep.click("login.button");

		}

	}

	@When("^I logout of application$")
	public void logout() {

		
		if (ConfigurationManager.getBundle().getProperty("appType").equals("Hybrid")
				|| ConfigurationManager.getBundle().getProperty("appType").equals("Web")) {

			if (ConfigurationManager.getBundle().getProperty("appType").equals("Web")) {
				try {
					Thread.sleep(10000);
				} catch (Exception e) {
					;
				}
				new QAFExtendedWebElement("logout.button").click();
			} else {

				Map<String, Object> params1 = new HashMap<>();
				if (DeviceUtils.getDeviceProperty("os").contains("Android")){
				params1.put("content", "PUBLIC:ExpenseTracker/Images/hamburger.png"); 
				}else{
					params1.put("content", "PUBLIC:ExpenseTracker/Images/hamburgerIOS.png");	
				}
				params1.put("timeout", "30");
				params1.put("threshold", "70");
				params1.put("match", "similar");
				Object result1 = driver.executeScript("mobile:image:select", params1);
				
				CommonStep.click("logout.button");
			}

		} else {

			if (DeviceUtils.getDeviceProperty("os").contains("Android")) {

				CommonStep.click("hamburger.menu.button");
				CommonStep.click("logout.button");
				CommonStep.click("ok.button");
			} else {
				CommonStep.click("hamburger.menu.button");
				CommonStep.click("logout.button");
			}

		}

	}

	@When("^i attach receipt$")
	public void attachReceipt() {

		if (ConfigurationManager.getBundle().getProperty("appType").equals("Hybrid")
				|| ConfigurationManager.getBundle().getProperty("appType").equals("Native")) {
			
		if(!DeviceUtils.getDeviceProperty("model").contains("iPhone-6")){
			
			CommonStep.click("attachment.link");
			DeviceUtils.startImageInjection("PUBLIC:ExpenseTracker/Images/CoffeeReceipt.jpg", identifier, "identifier");
			CommonStep.click("camera.button");

			if (ConfigurationManager.getBundle().getProperty("appType").equals("Native")) {
				if (DeviceUtils.getDeviceProperty("os").contains("iOS")) {
					try {
						CommonStep.click("Ok.button");
					} catch (Exception e) {
						;
					}
					CommonStep.click("capture.button");
					CommonStep.click("usePhoto.button");
					CommonStep.click("save.button");
					CommonStep.click("Ok.button");
				} else {
					CommonStep.click("ok.button");
					DeviceUtils.hideKeyboard();
					CommonStep.click("save.button");
				}
				

			} else {
				DeviceUtils.switchToContext("NATIVE_APP");
				if (ConfigurationManager.getBundle().getProperty("appType").equals("Hybrid")){
					
				if (DeviceUtils.getDeviceProperty("os").contains("Android")) {
					
					
				CommonStep.click("addAttachmentAllow.btn");
				CommonStep.click("addAttachmentAllow.btn");
				}else{
					try {
						CommonStep.click("Ok.button");
					} catch (Exception e) {
						;
					}
					CommonStep.click("capture.button");
					CommonStep.click("usePhoto.button");
				}
				}
				DeviceUtils.switchToContext("WEBVIEW");
				
				DeviceUtils.swipe("50%,90%", "50%,50%");
				CommonStep.click("save.button");
			}

			DeviceUtils.stopImageInjection();
		} else {
			
			if(ConfigurationManager.getBundle().getProperty("appType").equals("Native")){
				CommonStep.click("save.button");
				CommonStep.click("Ok.button");
			}else{
				
				CommonStep.click("attachment.link");
				DeviceUtils.startImageInjection("PUBLIC:ExpenseTracker/Images/CoffeeReceipt.jpg", identifier, "identifier");
				CommonStep.click("camera.button");
				DeviceUtils.switchToContext("NATIVE_APP");
				try {
					CommonStep.click("Ok.button");
				} catch (Exception e) {
					;
				}
				CommonStep.click("capture.button");
				CommonStep.click("usePhoto.button");
				DeviceUtils.stopImageInjection();
				DeviceUtils.swipe("50%,90%", "50%,50%");
				DeviceUtils.switchToContext("WEBVIEW");
				CommonStep.click("save.button");
			}
			
		}
		
		}else
		CommonStep.click("save.button");
		}
}
