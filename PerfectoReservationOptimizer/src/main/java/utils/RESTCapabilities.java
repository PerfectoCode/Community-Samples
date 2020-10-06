package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.openqa.selenium.remote.DesiredCapabilities;

public class RESTCapabilities {
	public static Map<String, String> basicCapMap = new HashMap();

	public static void restMapConverter() {
		basicCapMap.put("deviceName", "deviceId");
		basicCapMap.put("deviceType", "NA");
		basicCapMap.put("description", "NA");
		basicCapMap.put("location", "location");
		basicCapMap.put("manufacturer", "manufacturer");
		basicCapMap.put("model", "model");
		basicCapMap.put("network", "operator.name");
		basicCapMap.put("openDeviceTimeout", "NA");
		basicCapMap.put("platformName", "os");
		basicCapMap.put("platformVersion", "osVersion");
		basicCapMap.put("resolution", "resolution");
		basicCapMap.put("screenshotFormat", "NA");
		basicCapMap.put("automationInfrastructure", "NA");
	}

	public static Map<String, String> getRESTCapabilities(DesiredCapabilities capabilities) throws IOException {
		restMapConverter();
		HashMap map = new HashMap();
		Map capMap = capabilities.asMap();
		Iterator arg3 = capMap.entrySet().iterator();

		while (arg3.hasNext()) {
			Entry entry = (Entry) arg3.next();
			if (!((String) capMap.get(entry.getKey())).equalsIgnoreCase("NA")
					&& !((String) capMap.get(entry.getKey())).isEmpty()) {
				map.put((String) basicCapMap.get(entry.getKey()), (String) entry.getValue());
			}
		}

		return map;
	}
}