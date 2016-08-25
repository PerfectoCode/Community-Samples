package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PerfectoNativeRunner.PerfectoRunner;
import utilities.ClassHelper;

public class TestSystem extends ClassHelper {

	// Executes the Native Tests
	// define Perfecto and Script details
	// Params
	// @1 Perfecto Host
	// @2 Perfecto username
	// @3 Perfecto password
	// @4 Perfect script key "Private:yourscript or Private:directory/yourscript
	// @5 Device Id to execute the script
	// @6 Additional Params -- the format for the parameters
	// @7 Number of times to loop and wait for the completion of the script ---
	// suggested value in the 1000s
	// @8 Number of milliseconds to wait between each status check of the script
	// ---- suggest 5000 milliseconds
	@Test
	public void NativeTests() throws Exception {
		PerfectoRunner pr = new PerfectoRunner();

		// executes the script and gathers the test results

		// Executes the Native Tests
		// define Perfecto and Script details
		// Params
		// @1 Perfecto Host
		// @2 Perfecto username
		// @3 Perfecto password
		// @4 Perfect script key "Private:yourscript or
		// Private:directory/yourscript
		// @5 Device Id to execute the script
		// @6 Additional Params -- the format for the parameters
		// &param.url=m.newegg.com
		// @7 Number of times to loop and wait for the completion of the script
		// ---
		// suggested value in the 1000s
		// @8 Number of milliseconds to wait between each status check of the
		// script
		// ---- suggest 5000 milliseconds
		testResults = pr.executeScript(host, username, password, scriptKey, deviceId, additionalParams, cycles,
				waitForCycles);

	}
}
