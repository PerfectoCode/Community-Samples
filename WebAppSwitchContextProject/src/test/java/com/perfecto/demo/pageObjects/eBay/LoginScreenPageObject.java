package com.perfecto.demo.pageObjects.eBay;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginScreenPageObject {

	@FindBy(xpath="//input[@placeholder='Email or username']")
	WebElement emailTextField;
	
	@FindBy(xpath="//input[@placeholder='Password'][1]")
	WebElement passwordTextField;
	
	@FindBy(id="sgnBt")
	WebElement signInBtn;
	
	@FindBy(id="errf")
	WebElement errorMessageTextField;
	
	public void logineBay()
	{
		emailTextField.clear();
		emailTextField.sendKeys("a@a.com");
		passwordTextField.sendKeys("abcd");
		signInBtn.click();
	}

	public void checkErrorMessage()
	{
		Assert.assertEquals(errorMessageTextField.getText(), "Oops, that's not a match.");
	}
	
}