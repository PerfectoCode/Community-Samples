package com.perfecto.demo.pageObjects.amazon;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeScreenPageObject {
	
	//Sign In - for both desktop browser and mobile browser
	@FindBy(xpath="//a[@id='nav-logobar-greeting'] | //span[text()='Hello. Sign in']")
	WebElement signInButton;
	
	//Click on Sign In link on desktop browser or mobile browser
	public void signInButtonClick()
	{
		signInButton.click();
	}	
}
