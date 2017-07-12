import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.html5.*;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.Cookie.Builder;

/**
 * For programming samples and updated templates refer to the Perfecto GitHub at: https://github.com/PerfectoCode
 */
public class RemoteWebDriverTest implements Runnable {

	private String deviceID;
    public RemoteWebDriverTest(String deviceId) throws MalformedURLException, InterruptedException {
        
    	deviceID=deviceId;  
    }
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
           List<String> deviceIds = new LinkedList<>();
           deviceIds.add("1115FBD16FEF0303");
           deviceIds.add("C37BAE1934AE7DD0AE3355F77146C7A65579CAA3");
           ExecutorService executor = Executors.newFixedThreadPool(deviceIds.size());
           for (String deviceId : deviceIds) {
        	   RemoteWebDriverTest test = new RemoteWebDriverTest(deviceId);
        	   System.err.println("starting execute test "+deviceId);
                  executor.execute(test);
                  System.err.println("started execute test+deviceId");
           }      
           executor.shutdown();
    
}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String browserName = "mobileOS";
        DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
        String host = "demo.perfectomobile.com";
        capabilities.setCapability("user", "");
        capabilities.setCapability("password", "");

        //TODO: Change your device ID
        capabilities.setCapability("deviceName", deviceID);

        // Use the automationName capability to define the required framework - Appium (this is the default) or PerfectoMobile.
        // capabilities.setCapability("automationName", "PerfectoMobile");

        // Call this method if you want the script to share the devices with the Perfecto Lab plugin.
       // PerfectoLabUtils.setExecutionIdCapability(capabilities, host);

        // Add a persona to your script (see https://community.perfectomobile.com/posts/1048047-available-personas)
        //capabilities.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY, WindTunnelUtils.GEORGIA);

        // Name your script
        // capabilities.setCapability("scriptName", "RemoteWebDriverTest");

        RemoteWebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	      try
	      {
	        //#####Write the test code
	        //for example
	    	driver.get("bbc.co.uk");
	    	driver.findElementByXPath("//*[@id=\"idcta-link\"]").click();
	    	Thread.sleep(5000);
	    	driver.findElementByXPath("//*[@id=\"username-input\"]").sendKeys("aUser");
	    	driver.findElementByXPath("//*[@id=\"password-input\"]").sendKeys("apassword");
	    	
	      } catch (Exception e) {
	        e.printStackTrace();
	      } finally {
	        try {
	            // Retrieve the URL of the Single Test Report, can be saved to your execution summary and used to download the report at a later point
	            String reportURL = (String)(driver.getCapabilities().getCapability(WindTunnelUtils.SINGLE_TEST_REPORT_URL_CAPABILITY));
	
	            driver.close();
	
	            // In case you want to download the report or the report attachments, do it here.
	            // PerfectoLabUtils.downloadReport(driver, "pdf", "C:\\test\\report");
	            // PerfectoLabUtils.downloadAttachment(driver, "video", "C:\\test\\report\\video", "flv");
	            // PerfectoLabUtils.downloadAttachment(driver, "image", "C:\\test\\report\\images", "jpg");
	
	        	} catch (Exception e) {
	            e.printStackTrace();
	        	}
	        driver.quit();
	        System.err.println(deviceID+ "driver quit");
	      }
	}
    private static void switchToContext(RemoteWebDriver driver, String context) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        Map<String,String> params = new HashMap<String,String>();
        params.put("name", context);
        executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
    }

    private static String getCurrentContextHandle(RemoteWebDriver driver) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        String context =  (String) executeMethod.execute(DriverCommand.GET_CURRENT_CONTEXT_HANDLE, null);
        return context;
    }

    private static List<String> getContextHandles(RemoteWebDriver driver) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        List<String> contexts =  (List<String>) executeMethod.execute(DriverCommand.GET_CONTEXT_HANDLES, null);
        return contexts;
    }
}