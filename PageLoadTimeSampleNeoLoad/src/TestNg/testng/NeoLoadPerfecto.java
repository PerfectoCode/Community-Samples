package TestNg.testng;

import static com.neotys.selenium.proxies.NLWebDriverFactory.addProxyCapabilitiesIfNecessary;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.neotys.selenium.proxies.NLWebDriver;
import com.neotys.selenium.proxies.NLWebDriverFactory;
import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;

public class NeoLoadPerfecto {

	 private NLWebDriver driver;
	 private ReportiumClient reportiumClient;
	 private RemoteWebDriver pDriver;
	 String SECURITY_TOKEN = "<<SECURITY_TOKEN>>";
	 String HOST = "<<CLOUD>>.perfectomobile.com";
	@Parameters({"platformName"})
	@BeforeClass
	public void before(String platformName) throws MalformedURLException {
		//final ChromeDriver webDriver = new ChromeDriver(addProxyCapabilitiesIfNecessary(new DesiredCapabilities()));
	
        System.out.println("Run started");

        String browserName = "mobileOS";
        DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
        String host = HOST;
        capabilities.setCapability("securityToken", SECURITY_TOKEN);
 
        if(platformName.equalsIgnoreCase("Windows")){
	        capabilities.setCapability("platformName", "Windows");
	        capabilities.setCapability("platformVersion", "10");
	        capabilities.setCapability("browserName", "Chrome");
	        capabilities.setCapability("browserVersion", "78");
	        capabilities.setCapability("location", "US East");
	        capabilities.setCapability("resolution", "1024x768");
        }else if(platformName.equalsIgnoreCase("iOS")) {
        	capabilities.setCapability("platformName", "iOS");
        }else  {
        	capabilities.setCapability("platformName", "Android");
        }
        System.out.println("platformName" + platformName);
        System.out.println("value received = " + System.getProperty("testngxml"));
        
        pDriver = new RemoteWebDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub/fast"), capabilities);

        String JOB_NAME = System.getProperty("reportium_job_name", "My Job"); 
        String JOB_NUM = System.getProperty("reportium_job_num", "45");
        String TAGS = System.getProperty("reportium_job_tags", "tag1"); 

        // Reporting client. For more details, see http://developers.perfectomobile.com/display/PD/Reporting
        PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
                .withProject(new Project("My Project", "1.0"))
                .withJob(new Job(JOB_NAME, Integer.parseInt(JOB_NUM)))
                .withContextTags(TAGS)
                .withWebDriver(pDriver)
                .build();
        this.reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);

		// projectPath used to open NeoLoad project, null to use currently opened Project.
		final String projectPath = "/Users/admin/neoload_projects/NewSample/NewSample.nlp";
		
		this.driver = NLWebDriverFactory.newNLWebDriver(pDriver, "SeleniumUserPath", projectPath);
	
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@Test
	public void testSubmit() {
        try {
			reportiumClient.testStart("NeoLoaDemo1", new TestContext("tag2", "tag3"));
			driver.startTransaction("home1");
			driver.get("http://ushahidi.demo.neotys.com/");
			driver.stopTransaction();
			driver.startTransaction("reports");
			driver.findElement(By.id("mainmenu")).findElements(By.tagName("a")).get(1).click();
			driver.stopTransaction();
			driver.startTransaction("submit");
			driver.findElement(By.partialLinkText("SUBMIT")).click();
			driver.stopTransaction();
            reportiumClient.testStop(TestResultFactory.createSuccess());

		} catch (Exception e) {
            reportiumClient.testStop(TestResultFactory.createFailure(e.getMessage(), e));
            e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAlerts() {
		try {
			reportiumClient.testStart("NeoLoaDemo2", new TestContext("tag2", "tag3"));

			driver.startTransaction("home2");
			driver.get("http://ushahidi.demo.neotys.com/");
			driver.stopTransaction();
			driver.startTransaction("alerts");
			driver.findElement(By.partialLinkText("GET ALERTS")).click();
			driver.stopTransaction();
            reportiumClient.testStop(TestResultFactory.createSuccess());

		} catch (Exception e) {
            reportiumClient.testStop(TestResultFactory.createFailure(e.getMessage(), e));
            e.printStackTrace();
		}
	}
	
	@AfterClass
	public  void after() {
		if (driver != null) {
			driver.quit();
		}
	}
}