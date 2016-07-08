package com.perfecto.demo.pageObjects.amazon;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginScreenPageObject {

	@FindBy(id = "ap_email")
	WebElement emailTextField;

	@FindBy(id = "ap_password")
	WebElement passwordTextField;

	@FindBy(id = "signInSubmit")
	WebElement signInBtn;

	@FindBy(xpath = "//*[@id='auth-error-message-box']//span")
	WebElement errorMessageTextField;

	// Method to login
	public void loginAmazon(String userID, String password) {
		// Clear the email field and enter text
		emailTextField.clear();
		emailTextField.sendKeys(userID);
		// Clear the password field and enter text
		passwordTextField.clear();
		passwordTextField.sendKeys(password);
		// Click on Sign In button
		signInBtn.click();
	}

	// Method to assert the error message
	public void assertErrorMessage() {
		if (errorMessageTextField.getText().trim().contains("Your email or password was incorrect. Please try again."))
			Assert.assertEquals(errorMessageTextField.getText().trim(),
					"Your email or password was incorrect. Please try again.");
		else
			Assert.assertEquals(errorMessageTextField.getText().trim(), "Internal Error. Please try again later.");
	}
}