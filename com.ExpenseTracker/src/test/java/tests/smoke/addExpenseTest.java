package tests.smoke;

import java.io.File;
import java.net.URL;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;

import Pages.AddExpensePage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.NavigatorPage;
import framework.driverManagement.DriverDefinition;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class addExpenseTest extends DriverDefinition{

	//private AppiumDriver<MobileElement> driver;
	//DriverDefinition df;
	//private ReportiumClient reportiumClient;

	@Test
	public void TC_001_Login() throws Exception{
		System.out.println("TC_001_Login\n");

		LoginPage loginPage = new LoginPage(getDriver());
		HomePage homePage = new HomePage(getDriver());
		NavigatorPage navigatorPage = new NavigatorPage(getDriver());
		
		try {
			if(this.getPerfectoDigitalZoomReportium()) {
				System.out.println("TC_001_Login 1: Logging Digital Zoom\n");
				this.getReportiumClient().testStart("TC_001_Login", new TestContext("Smoke", "LowPrice"));
			}

			//Here is your test scenario
			loginPage.loginToApp();
			homePage.clickHamburgerMenu();
			navigatorPage.LogoutOfAppliction();

			if(this.getPerfectoDigitalZoomReportium()) {
				System.out.println("TC_001_Login 2: Logging Digital Zoom\n");
				this.getReportiumClient().testStop(TestResultFactory.createSuccess());
			}
		}catch(Exception e){
			if(this.getPerfectoDigitalZoomReportium()) {
				System.out.println("TC_001_Login 3: Logging Digital Zoom\n");
				this.getReportiumClient().testStop(TestResultFactory.createFailure(e.getMessage(), e));
			}
			e.printStackTrace();
		}

	}

	@Test
	public void TC_002_AddExpense() throws Exception{
		System.out.println("TC_002_AddExpense\n");

		LoginPage loginPage = new LoginPage(getDriver());
		HomePage homePage = new HomePage(getDriver());
		AddExpensePage adexpensePage = new AddExpensePage(getDriver());
		NavigatorPage navigatorPage = new NavigatorPage(getDriver());
		
		try {
			if(this.getPerfectoDigitalZoomReportium()) {
				System.out.println("TC_002_AddExpense : Logging Digital Zoom\n");
				this.getReportiumClient().testStart("TC_002_AddExpense", new TestContext("Smoke", "LowPrice"));
			}

			//Here is your test scenario
			loginPage.loginToApp();
			homePage.navigaToExpenseScreen();
			adexpensePage.addExpense();
			homePage.clickHamburgerMenu();
			navigatorPage.LogoutOfAppliction();

			if(this.getPerfectoDigitalZoomReportium()) {
				System.out.println("TC_002_AddExpense 2: Logging Digital Zoom\n");
				this.getReportiumClient().testStop(TestResultFactory.createSuccess());
			}
		}catch(Exception e){
			if(this.getPerfectoDigitalZoomReportium()) {
				System.out.println("TC_002_AddExpense 3: Logging Digital Zoom\n");
				this.getReportiumClient().testStop(TestResultFactory.createFailure(e.getMessage(), e));
			}
			e.printStackTrace();
		}

	}

}
