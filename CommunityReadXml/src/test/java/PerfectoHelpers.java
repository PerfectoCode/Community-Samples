import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * 
 */

/**
 * @author Lee Shoham
 * @date Aug 9, 2016
 */
public class PerfectoHelpers {

	public static DesiredCapabilities setUp(String browserName, String user, String password, String platformName,
			String automationName) {
		DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);

		capabilities.setCapability("user", user);
		capabilities.setCapability("password", password);

		// TODO: Change your device ID
		// capabilities.setCapability("deviceName", "5CDDB2F1");
		capabilities.setCapability("platformName", platformName);

		// Use the automationName capability to define the required framework
		// Appium (this is the default) or PerfectoMobile.
		capabilities.setCapability("automationName", automationName);

		return capabilities;
	}

	public static void downloadReport(RemoteWebDriver driver, String type, String fileName) throws IOException {
		try {
			String command = "mobile:report:download";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("type", type);
			String report = (String) driver.executeScript(command, params);
			File reportFile = new File(fileName + "." + type);
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(reportFile));
			byte[] reportBytes = OutputType.BYTES.convertFromBase64Png(report);
			output.write(reportBytes);
			output.close();
		} catch (Exception ex) {
			System.out.println("Got exception " + ex);
		}
	}

}
