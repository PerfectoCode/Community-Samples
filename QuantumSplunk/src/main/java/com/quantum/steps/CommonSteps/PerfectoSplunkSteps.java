/**
 * 
 */
package com.quantum.steps.CommonSteps;

import java.util.HashMap;
import java.util.Map;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.quantum.listerners.SplunkHelper;

import cucumber.api.java.en.Then;

@QAFTestStepProvider
public class PerfectoSplunkSteps {

	@Then("^I perform a Splunk transaction using the name \"([^\"]*)\" and description of \"([^\"]*)\" with an SLA of \"([^\"]*)\" - I'm also utilizing an OCR checkpoint for the word \"([^\"]*)\" with a timeout of \"([^\"]*)\" and threshold of \"([^\"]*)\"$")
	public void visualOCRTimer(String name, String desc, String SLA, String text, String timeout, String threshold)
			throws Exception {

		SplunkHelper.testStepStart(name, desc);

		Map<String, Object> params1 = new HashMap<>();
		params1.put("content", text);
		params1.put("source", "camera");
		params1.put("timeout", timeout);
		params1.put("measurement", "accurate");
		params1.put("threshold", threshold);
		params1.put("analysis", "automatic");
		Object result1 = SplunkHelper.getQAFDriver().executeScript("mobile:checkpoint:text", params1);

		if (result1.toString().contains("true")) {

		} else {

			throw new Exception("Text not found!");

		}

		SplunkHelper.testStepEnd(Long.parseLong(SLA), name);

	}

	@Then("^I perform a Splunk transaction using the name \"([^\"]*)\" and description of \"([^\"]*)\" with an SLA of \"([^\"]*)\" - I'm also utilizing an Image checkpoint for the image \"([^\"]*)\" with a timeout of \"([^\"]*)\" and threshold of \"([^\"]*)\"$")
	public void visualImageTimer(String name, String desc, String SLA, String repo, String timeout, String threshold)
			throws Exception {

		SplunkHelper.testStepStart(name, desc);

		Map<String, Object> params1 = new HashMap<>();
		params1.put("content", repo);
		params1.put("match", "identical");
		params1.put("source", "camera");
		params1.put("measurement", "accurate");
		params1.put("timeout", timeout);
		params1.put("threshold", threshold);
		Object result1 = SplunkHelper.getQAFDriver().executeScript("mobile:checkpoint:image", params1);

		
		if (result1.toString().contains("true")) {

		} else {

			throw new Exception("Image not found!");

		}

		SplunkHelper.testStepEnd(Long.parseLong(SLA), name);

	}

}
