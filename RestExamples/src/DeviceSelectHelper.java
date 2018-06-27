import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.perfectomobile.httpclient.Credentials;
import com.perfectomobile.httpclient.HttpClientException;
import com.perfectomobile.httpclient.ParameterValue;
import com.perfectomobile.httpclient.device.DeviceResult;
import com.perfectomobile.httpclient.device.DevicesHttpClient;

public class DeviceSelectHelper {

	private static final Map<String, String> CapabilityMap;

	static {
		Map<String, String> deviceSelectionMap = new HashMap<String, String>();
		deviceSelectionMap.put("deviceName", "deviceId");
		deviceSelectionMap.put("platformName", "os");
		deviceSelectionMap.put("platformVersion", "osVersion");
		deviceSelectionMap.put("manufacturer", "manufacturer");
		deviceSelectionMap.put("model", "model");
		deviceSelectionMap.put("location", "location");
		deviceSelectionMap.put("description", "description");
		deviceSelectionMap.put("network", "operator");
		deviceSelectionMap.put("resolution", "resolution");
		
		CapabilityMap = Collections.unmodifiableMap(deviceSelectionMap);
	}

	//** Translates Device-Selection specific capabilities to a list understood by the REST API
	private static List<ParameterValue> CapabilityToList(DesiredCapabilities cap) {

		List<ParameterValue> capabilityList = new LinkedList<>();

		// Iterate through all possible maps, then check if capability is set in
		// DesiredCapability.
		// If it is used, then extract its value, and add to new List-
		// pair.value and capability value
		Iterator<?> it = CapabilityMap.entrySet().iterator();
		System.out.println(System.getProperty("line.separator")
				+ "### Searching for device with capabilities: "+" ##############");
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			// System.out.println(pair.getKey() + " = " + pair.getValue());

			String key = (String) pair.getKey();
			String value = (String) cap.getCapability(key);

//			System.err.println("#####VALUE IS ### " + value);
			if (value != "" && value != null) {
				ParameterValue param = new ParameterValue((String) pair.getValue(), value);
				capabilityList.add(param);
				System.out.println("##### Capability: " + (String) pair.getValue() + " Value: " + value +" ##############");
			}
		}
		System.out.println("");

		return capabilityList;
	}

	//** Executes REST API call to check whether device is available
	//** PARAMETER List:  list containing device selection parameters created from capabilities
	//** PARAMETER DesiredCapabilities:  DesiredCapabilities
	//** PARAMETER DesiredCapabilities:  Host
	private static List<String> getDeviceAvailability(List deviceSelectionParams, DesiredCapabilities capabilities,
			String host) {
		
		DevicesHttpClient client = new DevicesHttpClient(host, null);
		// List inputParameters = new LinkedList<>();
		List<String> deviceavail = new ArrayList<String>();
		try {
			String user = capabilities.getCapability("user").toString();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.err.println(
					"###ERROR: No User specified, cannot check for availability. Please add user to Desired Capabilities. ####");
			return new ArrayList();
		}

		// Add availableTo as way to ensure 
		ParameterValue param = new ParameterValue("availableTo", capabilities.getCapability("user").toString());
		deviceSelectionParams.add(param);
		param = new ParameterValue("securityToken", capabilities.getCapability("securityToken").toString());
		deviceSelectionParams.add(param);
	
		List<DeviceResult> devicesId = null;
		try {
			devicesId = client.listDevices(deviceSelectionParams, false);
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
		for (DeviceResult device : devicesId) {
			String st1 = device.getResponseValue("deviceId");
			deviceavail.add(st1);
			System.out.println(st1);
		}

		return deviceavail;

	}

	public static boolean CheckIfDevicesAvailable(DesiredCapabilities capabilities, String host) {
		// TODO Auto-generated method stub
		
		List<ParameterValue> httpCapabilities = CapabilityToList(capabilities);
		List<String> s = getDeviceAvailability(httpCapabilities, capabilities, host);
		if (s.size() > 0) {
			System.out
					.println("### There are " + String.valueOf(s.size()) + " devices available. Attempting to connect.");

			return true;
		}

		else
		{
			System.err
					.println("### Error: There is NO devices available according to set Capabilities. Wait to become available or retry with different capabilities.");
			return false;
		}
	}
}
