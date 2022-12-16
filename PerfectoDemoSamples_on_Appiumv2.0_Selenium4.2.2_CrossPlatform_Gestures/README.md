Perfecto with Appium v2.0 & Selenium v4.x : Demo Framework.
Author : Raghavendra K :  

	Usage of samples : in all code snippet's, kindly replace cloudName from "demo" or "partners" to your cloudName and "<SecurityToken>" to your security Token.
	Kindly refer respective sample test example for below categories which are have respective suffix names as mentioned below :
		- Android             : Native(_AN.java), Hybrid(_AH.java), Web apps (_AW.java).
		- Android Emulator 	: Native(_AN_Emulator.java)
		- iOS                 : Native(_IN.java), Hybrid(_IH.java), Web apps(_IW.java).
		- iOS Simulator		: Native(_IN_Simulator.java)
		- Desktop Browsers    : 
			- MAC OS          : Safari(_MS.java), Chrome(_MC.java), Firefox(_MFF.java) on OS version of MAC are "macOS Ventura", "macOS Monterey", "macOS Big Sur", "macOS Catalina"
			- Windows OS      : Chrome(_WC.java), Firefox(_WFF.java), Edge(_WE.java), Internet Explorer(_WIE.java) on OS version of Windows are "11"(supported Edge not IE), "10"(supported Edge not IE), "8.1" (Supported IE not Edge), "7" (Supported IE not Edge)
	
	-------------------------------------------------------
	
	Currently 3 categories of capabilities : as a user has to have capabilities to be written and target for specific Platform, OS, Browser respectively.
	we have mentioned here.
	
		Part 1 : W3C capabilities , which does not have any prefix for capabilities, like "<CapabiltyName>"
		Part 2 : Appium Capabilities , which has prefix of "appium:", like "appium:<CapabiltyName>"
		Part 3 : Vendor Specific Capabilities , which has prefix of "perfecto:", like "perfecto:<CapabiltyName>"
	
		1. W3C capabilities : browserName, browserVersion, platformName, acceptInsecureCerts, timeouts, etc
		2. Appium capabilities : platformVersion, deviceName, automationName, etc
				
				- Android
					- UIAutomator1
					- UIAutomator2
					- Espresso
				
				- iOS Only
					- iOS Only, using XCUITest
				
				- Other Driver has been supportted on appium, which are out of scope.
				
		3. Perfecto Capabilities : securityToken, sensorInstrument, autoInstrument, iOSResign, automationVersion, appiumVersion, etc
			documentation link : https://help.perfecto.io/perfecto-help/content/perfecto/automation-testing/supported_appium_capabilities.htm#Appium 
			Regarding perfecto:automationVersion & perfecto:appiumVersion
			
			- For iOS     : 3.25.0, 3.33.1, or 3.59.0
			- For Android : 1.56.2, 1.61.2, or 1.70.1
			Determines which version of the specified driver should be used. If this capability is set, it overrides the perfecto:appiumVersion capability.
				
				For iOS:
				
					Appium 1.22.3 version has internal driver version 3.59.0.
					Appium 1.20.2 version has internal driver version 3.33.1.
					Appium 1.18.3 version has internal driver version 3.25.0.
				
				For Android:
				
					Appium 1.22.3 version has internal driver version 1.70.1.
					Appium 1.20.2 version has internal driver version 1.61.2.
					Appium 1.18.3 version has internal driver version 1.56.2.
	
	-------------------------------------------------------
	
	Perfecto capabilities & Extensions : kindly refer below link which has mentioned below.
			- https://help.perfecto.io/perfecto-help/content/perfecto/automation-testing/supported_appium_capabilities.htm?Highlight=%22Perfecto%20extensions%22
			
	-------------------------------------------------------
	
	Appium 2.0 : Core new features : you may going ahead and use on your local devices
	
	Appium use under beneath Selenium and selenium is using W3C protocol standard.
 
	1. In app authentication using Face-ID : as "Perfecto has FaceID/TouchID usage by using FingerPrint injection"
	
	2. Appium Events API : as "Perfecto has Reportium API/SDK to log respective steps and time takes according in  report level for each test cases level."
	
	3. Video Streaming : as "Perfecto has Video Streaming & recording Video for report and user can download video for each test cases"
	
	4. Actions API’s : as "Perfecto is supporting W3C standards and Gestures as per provided sample here"
		
	5. Data Matcher : "This is on Espresso driver"
		
	for any question/queries reach out to Perfecto Support.
	
	-------------------------------------------------------
	
		
		
