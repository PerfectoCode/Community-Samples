package Pages;

import org.openqa.selenium.support.PageFactory;

import framework.driverManagement.DriverDefinition;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AddExpensePage {
	
	private AppiumDriver<MobileElement> driver;
	
	@AndroidFindBy(id="io.perfecto.expense.tracker:id/input_layout_head")
	MobileElement headDropDown;
	
	@AndroidFindBy(xpath="//*[@text='Taxi']")
	MobileElement headOption;
	
	@AndroidFindBy(id="io.perfecto.expense.tracker:id/add_amount")
	MobileElement amountField;
	
	@AndroidFindBy(id="io.perfecto.expense.tracker:id/signup_currency")
	MobileElement CurrencyDropdown;
	
	@AndroidFindBy(xpath="//*[@text=\"INR-₹\"]")
	MobileElement currency;
	
	@AndroidFindBy(id="io.perfecto.expense.tracker:id/til_category")
	MobileElement categoryDropDown;
	
	@AndroidFindBy(xpath="//*[@text='Personal']")
	MobileElement categoryOption;
	
	@AndroidFindBy(id="io.perfecto.expense.tracker:id/add_save_btn")
	MobileElement saveButton;
	
	public AddExpensePage(AppiumDriver<MobileElement> driver){
		
		this.driver=driver;
		System.out.println("AddExpensePage\n");
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
		//PageFactory.initElements(this.driver, this);
	}
	
	public void addExpense(){
		System.out.println("addExpense\n");
		headDropDown.click();
		headOption.click();
		amountField.sendKeys("3000");
		try {
			((AppiumDriver<MobileElement>)driver).hideKeyboard();
		} catch (Exception e) {
			;//don't do any thing here
		} 
		CurrencyDropdown.click();
		currency.click();
		categoryDropDown.click();
		categoryOption.click();
		saveButton.click();
	}

}
