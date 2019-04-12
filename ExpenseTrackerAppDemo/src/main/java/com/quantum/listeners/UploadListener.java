package com.quantum.listeners;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.qmetry.qaf.automation.ui.webdriver.QAFWebDriverCommandAdapter;

public class UploadListener extends QAFWebDriverCommandAdapter {
	@Override
	public void beforeInitialize(Capabilities desiredCapabilities) {

		Map<String, Object> resourceArgs = new HashMap<>();
		resourceArgs.put("timeout", 120);
		String[] myfiles = new String[] { "example.png" };
		resourceArgs.put("fileslist", myfiles);
		((DesiredCapabilities) desiredCapabilities).setCapability("customizationScript", "pm-upload-files.yml");
		((DesiredCapabilities) desiredCapabilities).setCapability("customizationScriptArgs", resourceArgs);
	}

}