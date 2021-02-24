import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.jmeter.protocol.java.sampler.JUnitSampler;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jorphan.logging.LoggingManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;



public class etihadTest {
	
    private final static org.apache.log.Logger log = LoggingManager.getLoggerForClass();

    private static final String siteUnderTest = "http://nxc.co.il/demoaut/index.php";
    private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<DesiredCapabilities> capabilities = new ThreadLocal<>();
    private static ThreadLocal<ReportiumClient> reportiumClient = new ThreadLocal<>();
    static boolean runVitals = true;
    static boolean runReportium = true;
    static int timerThreshold = 30000;
    private static ThreadLocal<String> platform = new ThreadLocal<>();
    
    
    public etihadTest(String test) {
   	 this();
   	} 

    public etihadTest() {
      	 //this();
      	} 
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("test");
		   JUnitSampler sampler = new JUnitSampler();
	        String host = sampler.getThreadContext().getVariables().get("perfectoHost");
	        log.info(host);
	        String securityToken = sampler.getThreadContext().getVariables().get("perfectoSecurityToken");
	        platform.set(sampler.getThreadContext().getVariables().get("platformName"));
	        String deviceId = sampler.getThreadContext().getVariables().get("deviceId");

	        String caps = sampler.getThreadContext().getVariables().get("desiredCapabilities");
	        
	        

	        try {
	            timerThreshold = Integer.parseInt(sampler.getThreadContext().getVariables().get("timerThreshold"));
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	        }
	        runVitals = !"false".equalsIgnoreCase(sampler.getThreadContext().getVariables().get("runVitals"));
	        runReportium = !"false".equalsIgnoreCase(sampler.getThreadContext().getVariables().get("runReportium"));
	
//	        setInitialCapabilities(user, password);
	        DesiredCapabilities dcaps = new DesiredCapabilities("MobileOS", "", Platform.ANY);
	        dcaps.setCapability("securityToken", securityToken);
	        dcaps.setCapability("deviceName", deviceId);
	        System.out.println("Device ID Provided = " + deviceId);
	      //  dcaps.setCapability("password", password);
	        dcaps.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY, WindTunnelUtils.GEORGIA);
	        
	        dcaps.setCapability("windTunnelLocationAddress", "Bangalore, India");
	        //Overriding Persona background application for demo purpose
	        dcaps.setCapability("windTunnelBackgroundRunningApps", "YouTube,Messages,Maps,Calculator,Calendar,Google Play Store" );
	        dcaps.setCapability("windTunnelOrientation", "portrait");
	        
	        dcaps.setCapability("scriptName", "EtihadPerformance");
	        capabilities.set(dcaps);
//	        addUserDefinedCapabilities(caps);
	        if (caps == null || caps.indexOf("=") < 0)
	            return;
	         dcaps = capabilities.get();
	         System.out.println("Normal = " + dcaps);
	        for (String capKeyValue : caps.split(","))
	            if (capKeyValue != null && capKeyValue.length() > 3 && capKeyValue.indexOf("=") > 0)
	                dcaps.setCapability(capKeyValue.split("=")[0], capKeyValue.split("=")[1]);
	        capabilities.set(dcaps);

	        boolean driverStarted;
	        StopWatch timer = new StopWatch();
	        timer.start();
	        do {
	        	 System.out.println("https://" + host + "/nexperience/perfectomobile/wd/hub");
	            driver.set(new RemoteWebDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities.get()));
	            driverStarted = true;
	        } while (!driverStarted && timer.getTime() < timerThreshold);
	        if (!runReportium)
	        	new ReportiumClientFactory().createLoggerClient();
	        else {
	        PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
	                .withProject(new Project(capabilities.get().getCapability("scriptName") + "", "Hackathon Demo Version 0.1"))
	                .withWebDriver(driver.get())
	                .build();
	       // return new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
	        reportiumClient.set(new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext));

	        }
	        reportiumClient.get().testStart(capabilities.get().getCapability("scriptName") +"", new TestContext("Hackathon", "Jeremy", "Mitch", "Evy", "Rick", "JMeter"));

	        driver.get().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	        log.info(driver.get().getCapabilities().toString());
	        Library lib = new Library(driver.get(), runVitals);
	        lib.vitalsStart();

	}

    @AfterClass
    public static String oneTimeTearDown(  ) {
        // do your one-time tear down here!
        JUnitSampler sampler = new JUnitSampler();
        String reportURL = "WindTunnel Report NOT Found!";
        if (driver.get() == null) return reportURL;
        try {
        	Library lib = new Library(driver.get(), runVitals);
            lib.vitalsStop();
            // Retrieve the URL of the Wind Tunnel Report, can be saved to your execution summary and used to download the report at a later point
            reportURL = (String) (driver.get().getCapabilities().getCapability(WindTunnelUtils.WIND_TUNNEL_REPORT_URL_CAPABILITY));
            log.info("WindTunnel Report URL:\n\t" + reportURL);
            String executionID = (String)(driver.get().getCapabilities().getCapability("executionId"));
            log.info("ExecutioID Report URL:\n\t" + executionID);
            sampler.setSuccess(reportURL);
            JMeterVariables vars = sampler.getThreadContext().getVariables();
            //vars.put("resamplerportURL", reportURL);
            vars.put("executionID", executionID);
            sampler.getThreadContext().setVariables(vars);
            reportiumClient.get().testStop(TestResultFactory.createSuccess());
            driver.get().close();
        } catch (Exception e) {
            sampler.setFailure(reportURL);
            if (reportiumClient.get() != null)
                reportiumClient.get().testStop(TestResultFactory.createFailure("Test stop failure.", e));
            e.printStackTrace();
        }
        
        driver.get().quit();
        log.info("Run ended, Driver closed/quit");
        System.out.println(platform.get() + " test completed");
        
        return reportURL;
    }



    @Test
    public void launchApp() throws InterruptedException {
        //reportiumClient.get().testStep("Launch Application");
    	Library lib = new Library(driver.get());
    	System.out.println("Current Platform = " + platform.get());
    	if(platform.get().equalsIgnoreCase("iOS")){
        	lib.clearHistoryiOS(driver.get());
        	lib.launchApplication("com.apple.mobilesafari");
        	lib.switchToContext("WEBVIEW");
            driver.get().get("http://expensetracker.perfectomobile.com/");
            Thread.sleep(2000);
        	lib.switchToContext("NATIVE_APP");
        	driver.get().findElementByXPath("//XCUIElementTypeOther[@label=\"Address\"]").click();
        	driver.get().findElementByXPath("//*[@label=\"Address\"]").sendKeys("https://www.etihad.com/en-us/book");
        	driver.get().findElementByXPath("//*[@label=\"Go\"]").click();
        	lib.switchToContext("WEBVIEW");
    	}
    	else {
        	driver.get().get("http://expensetracker.perfectomobile.com/");
        	Thread.sleep(2000);
        	driver.get().get("https://www.etihad.com/en-us/book");
    	}
    	
//        lib.switchToContext("WEBVIEW");
//        
//        driver.get().get("http://expensetracker.perfectomobile.com/");
//       
//        Thread.sleep(2000);
//
//        //Launch Web application
//        driver.get().get("https://www.etihad.com/en-us/book");
        
        lib.TextValidation("one Way");
        
        // Wind Tunnel: Measure UX timer 1 - Able to retrieve UX Timer value
        long AppLaunchTime = lib.step(30000, "App Launch Time", "AppLaunchTime");
        
        System.out.println("'Measured UX time for App launch time in " + platform.get() + " device is: " + AppLaunchTime);

    }

    @Test
    public void searchFlights() {

        //reportiumClient.get().testStep("Search Flights");
    	Library lib = new Library(driver.get());
    	
    	driver.get().findElementById("oneWay").click();
        
        //driver.findElementById("oneWayDestination").sendKeys("Abu Dhabi");
        
    	driver.get().findElementById("oneWayOrigin").click();
        
        lib.javaScriptSendkeys(driver.get().findElementById("oneWayOrigin"), "BLR");
        
        //driver.findElementById("oneWayOrigin").sendKeys("BLR");
        
        driver.get().findElementByXPath("//*[@id=\"rbt-menu-item-0\"]/a/div/div[1]/span[1]").click();
        
        driver.get().findElementById("oneWayDestination").sendKeys("Abu Dhabi");
        
        driver.get().findElementById("oneWayDestination").click();
        
        lib.javaScriptSendkeys(driver.get().findElementById("oneWayDestination"), "Abu Dhabi");
        
        driver.get().findElementByXPath("//*[@id=\"rbt-menu-item-0\"]/a/div/div[1]/span[1]").click();
        
        driver.get().findElementById("oneWayCalendarDepartDate").click();           
        
        driver.get().findElementByXPath("(//table[@class=\"CalendarMonth_table CalendarMonth_table_1\"])[4]/tbody/tr[2]/td[4]").click();

        if(platform.get().equalsIgnoreCase("Android")) {
            Map<String, Object> pars = new HashMap<>();
            pars.put("generateHarFile", "true");
            driver.get().executeScript("mobile:vnetwork:start", pars); 
        }
        
        
        driver.get().findElementByXPath("//*[@id=\"flightsearch\"]/div/form/div[4]/div[2]/div/button").click();
        
           lib.TextValidation("currency");
           
            // Wind Tunnel: Measure UX timer 1 - Able to retrieve UX Timer value
           long SearchFlight = lib.step(30000, "Search Flight", "SearchFlight");
           
           System.out.println("'Measured UX time for Search Flight time in " + platform.get() + " device is: " + SearchFlight);
         
            if(platform.get().equalsIgnoreCase("Android")) {
	            Map<String, Object> pars1 = new HashMap<>();
	            driver.get().executeScript("mobile:vnetwork:stop", pars1); 
            }
        
    }
    
}
