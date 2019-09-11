package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import framework.driverManagement.DriverDefinition;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class NavigatorPage {
	
	private AppiumDriver<MobileElement> driver;
	
	@AndroidFindBy(id="io.perfecto.expense.tracker:id/tv_name")
	MobileElement appUsername;
	
	@AndroidFindBy(xpath="//*[@text='Logout']")
	MobileElement logoutButton;
	
	@AndroidFindBy(id="android:id/button1")
	MobileElement okButton;
	
	public NavigatorPage( AppiumDriver<MobileElement> driver2){
	      
	      this.driver=driver2;
	      System.out.println("NavigatePage\n");
	      PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
	      //PageFactory.initElements(this.driver, this);
	}

	public void LogoutOfAppliction(){
		System.out.println("LogoutOfApplication\n");
		logoutButton.click();
		okButton.click();
		
	}
	
	

}
