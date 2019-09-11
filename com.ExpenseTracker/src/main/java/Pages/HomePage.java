package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import framework.driverManagement.DriverDefinition;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {
	
	private AppiumDriver<MobileElement> driver;
	
	@AndroidFindBy(xpath="//*[@text='Expenses']")
	MobileElement ExpenseTrackerText;
	
	@AndroidFindBy(id="io.perfecto.expense.tracker:id/list_add_btn")
	MobileElement addExpenseButton;
	
	@AndroidFindBy(xpath="//*[@content-desc='Open Drawer']")
	MobileElement hamburgerMenu;
	
	
	public HomePage(AppiumDriver<MobileElement> driver2){
		
		this.driver=driver2;
		System.out.println("HomePage\n");
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
		//PageFactory.initElements(this.driver, this);
	}
	public void navigaToExpenseScreen(){
		System.out.println("navigateToExpenseScreen\n");
		addExpenseButton.click();
	}

	public void clickHamburgerMenu(){
		System.out.println("clickHamburgerMenu\n");
		hamburgerMenu.click();
	}
}
