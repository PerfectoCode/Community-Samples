package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {
	
	private AppiumDriver<MobileElement> driver;
	
	@AndroidFindBy(id="io.perfecto.expense.tracker:id/img_icon")
	MobileElement mobileLogo;
	
	@AndroidFindBy(id="io.perfecto.expense.tracker:id/login_email")
    MobileElement username;
	
	@AndroidFindBy(id="io.perfecto.expense.tracker:id/login_password")
	MobileElement password;
	
	@AndroidFindBy(id="io.perfecto.expense.tracker:id/login_login_btn")
	MobileElement loginButton;
	
	
	public LoginPage(AppiumDriver<MobileElement> driver) {
		this.driver=driver;
		System.out.println("LoginPage\n");
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
		//PageFactory.initElements(this.driver, this);
	}
	
	public void loginToApp(){
		System.out.println("loginToApp\n");
		username.sendKeys("test@perfecto.com");
	    password.sendKeys("test123");
	    loginButton.click();
	}

}
