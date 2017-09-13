package com.perfecto.demo.pageObjects.eBay;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeScreenPageObject {
	
	//User Icon
	@FindBy(xpath="//a[@title='Go to My eBay page']/span[@class='hdSpt']")
	WebElement userIcon;
	
	public void userIconClick()
	{
		userIcon.click();
	}	
}
