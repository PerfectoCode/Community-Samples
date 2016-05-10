using System;
using System.Diagnostics;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Remote;
using OpenQA.Selenium.Support.UI;

namespace WebDemoDotNet
{
    /// <summary>
    /// Summary description for MobileRemoteTest
    /// </summary>
    [TestClass]
    public class RemoteWebDriverTest
    {
        public TestContext TestContext { get; set; }

        public RemoteWebDriverExtended PerfectoOpenConnection(DesiredCapabilities capabilities)
        {
            //TODO: ADD USER NAME AND PASSWORD BELOW
            capabilities.SetCapability("user", "");
            capabilities.SetCapability("password", "");

            var host = "demo.perfectomobile.com";
            capabilities.SetPerfectoLabExecutionId(host);
            capabilities.SetCapability("openDeviceTimeout", 5);

            // Name your script
            capabilities.SetCapability("scriptName", TestContext.TestName);

            return new RemoteWebDriverExtended(
                new HttpAuthenticatedCommandExecutor(
                    new Uri(string.Format("http://{0}/nexperience/perfectomobile/wd/hub", host)), TimeSpan.FromSeconds(120))
                    , capabilities);
        }


        public void SearchWithGoogle(DesiredCapabilities dCaps)
        {
            var environment = dCaps.GetCapability("platformName") + " " + dCaps.GetCapability("browserName");

            var driver = PerfectoOpenConnection(dCaps);
            try
            {
                // Implicit wait set to zero so does not interfere with fluent waits
                driver.Manage().Timeouts().ImplicitlyWait(TimeSpan.Zero);
                
                // Maximize browser window
                driver.Manage().Window.Maximize();
                Debug.WriteLine(environment + " Browser Window was maximized");

                // Search Google
                const string searchKey = "Perfecto Mobile";
                Debug.WriteLine(environment + " Search " + searchKey + " in google");
                driver.Url = "http://www.google.com";
                var element = new WebDriverWait(driver, TimeSpan.FromSeconds(30)).Until(ExpectedConditions.ElementToBeClickable(By.Name("q")));
                Debug.WriteLine(environment + " Enter " + searchKey);
                element.SendKeys(searchKey);
                Debug.WriteLine(environment + " submit");
                element.Submit();

                new WebDriverWait(driver, TimeSpan.FromSeconds(30)).Until(d => d.Title.StartsWith(searchKey, StringComparison.CurrentCultureIgnoreCase));
                Debug.WriteLine(environment + " Got " + searchKey + " results");
            }
            finally
            {
                driver.PerfectoCloseConnectionGetReports();
            }
        }

        private DesiredCapabilities SetWebCapabilities(String browserName, String version, 
            String platformName = "Windows", String platformVersion = "8.1", String resolution = "1366x768", String location = "US East")
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability("platformName", platformName);
            capabilities.SetCapability("platformVersion", platformVersion);
            capabilities.SetCapability("browserName", browserName);
            capabilities.SetCapability("version", version);
            capabilities.SetCapability("resolution", resolution);
            capabilities.SetCapability("location", location);
            return capabilities;
        }

        private DesiredCapabilities SetMobileCapabilies(String platformName, String browserName = "MobileOS", String description = ".*")
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability("browserName", browserName);
            capabilities.SetCapability("platformName", platformName);
            capabilities.SetCapability("description", description);
            // Add a persona to your script (see https://community.perfectomobile.com/posts/1048047-available-personas)
            capabilities.SetCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY, WindTunnelUtils.EMPTY);
            return capabilities;
        }
        
        [TestMethod]
	    public void WindowsChromeTest()
        {
            SearchWithGoogle(SetWebCapabilities("Chrome", "47"));
	    }
        [TestMethod]
        public void WindowsFirefoxTest()
        {
            SearchWithGoogle(SetWebCapabilities("Firefox", "45"));
        }
        [TestMethod]
        public void WindowsIETest()
        {
            SearchWithGoogle(SetWebCapabilities("Internet Explorer", "11"));
        }
        [TestMethod]
        public void iOSSafariTest()
        {
            SearchWithGoogle(SetMobileCapabilies("iOS", "Safari", "Mitch.*"));
        }
        [TestMethod]
        public void AndroidChromeTest()
        {
            SearchWithGoogle(SetMobileCapabilies("Android", "Chrome", "Mitch.*"));
        }
    }
}
