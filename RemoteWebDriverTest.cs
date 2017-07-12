using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.Threading;
using OpenQA.Selenium;
using OpenQA.Selenium.Remote;
using NUnit.Framework;
using System.IO;


namespace PerfectoLabSeleniumTestProject3
{
    /// <summary>
    /// Summary description for RemoteWebDriverTest
    /// 
    /// For programming samples and updated templates refer to the Perfecto GitHub at: https://github.com/PerfectoCode
    /// </summary>
    [TestFixture("Android", "6.0.1", "ShirPhilipp")]
    [TestFixture("iOS", "9.2.1", "Shir Philipp")]
    [Parallelizable(ParallelScope.Fixtures)]
    public class RemoteWebDriverTest
    {
        private RemoteWebDriverExtended driver;
        String deviceOS, deviceVersion, deviceDescription;
        String executionID;

        public RemoteWebDriverTest(String deviceOS, String deviceVersion, String deviceDescription)
        {
            this.deviceOS = deviceOS;
            this.deviceVersion = deviceVersion;
            this.deviceDescription = deviceDescription;
        }

        [SetUp]
        public void PerfectoOpenConnection()
        {
            var browserName = "mobileOS";
            var host = "demo.perfectomobile.com";

            DesiredCapabilities capabilities = new DesiredCapabilities(browserName, string.Empty, new Platform(PlatformType.Any));
            capabilities.SetCapability("user", "");

            //TODO: Provide your password
            capabilities.SetCapability("password", "");

            //TODO: Provide your device ID
            capabilities.SetCapability("platformName", deviceOS);
            Console.WriteLine(this.deviceOS + this.deviceVersion + this.deviceDescription);
            capabilities.SetCapability("platformVersion", deviceVersion);
            capabilities.SetCapability("description", deviceDescription);

            capabilities.SetPerfectoLabExecutionId(host);

            // Add a persona to your script (see https://community.perfectomobile.com/posts/1048047-available-personas)
            //capabilities.SetCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY, WindTunnelUtils.GEORGIA);

            // Name your script
            capabilities.SetCapability("scriptName", "VSSimpleFixture_"+deviceOS);

            var url = new Uri(string.Format("https://{0}/nexperience/perfectomobile/wd/hub", host));
            System.Threading.Thread.Sleep(2000);
            driver = new RemoteWebDriverExtended(new HttpAuthenticatedCommandExecutor(url), capabilities);

            driver.Manage().Timeouts().ImplicitlyWait(TimeSpan.FromSeconds(40));
            ICapabilities cap = driver.Capabilities;
           executionID = (String)cap.GetCapability("executionId");
            Console.WriteLine(executionID);
        }

        [TearDown]
        public void PerfectoCloseConnection()
        {
            // Retrieve the URL of the Single Test Report, can be saved to your execution summary and used to download the report at a later point
            string reportUrl = (string)(driver.Capabilities.GetCapability(WindTunnelUtils.SINGLE_TEST_REPORT_URL_CAPABILITY));
            Console.WriteLine(reportUrl);
            driver.Close();

            // In case you want to download the report or the report attachments, do it here.
            try
            {
                driver.DownloadReport(DownloadReportTypes.pdf, "C:\\reports\\report"+executionID);
            //    driver.DownloadAttachment(DownloadAttachmentTypes.video, "C:\\test\\report\\video", "flv");
            //    driver.DownloadAttachment(DownloadAttachmentTypes.image, "C:\\test\\report\\images", "jpg");
            }
            catch (Exception ex)
            {
                Trace.WriteLine(string.Format("Error getting test logs: {0}", ex.Message));
            }

            driver.Quit();
        }

        [TestCase("myuser", "mypassword", ExpectedResult = true)]
        [TestCase("user2", "mypassword", ExpectedResult = false)]
        public Boolean NavigatingToUrl(String appUsername, string appPassword)
        {


            driver.Navigate().GoToUrl("bbc.co.uk");
            System.Threading.Thread.Sleep(3000);
            driver.Context = "WEBVIEW";
            driver.FindElementByXPath("//*[@id=\"idcta-link\"]").Click();

            Dictionary<String, Object> params7 = new Dictionary<String, Object>();
            params7.Add("content", "Sign in");
            params7.Add("timeout", "30");
            Object result7 = driver.ExecuteScript("mobile:checkpoint:text", params7);
            
            driver.FindElementByXPath("//*[@id=\"username-input\"]").SendKeys(appUsername);
            driver.FindElementByXPath("//*[@id=\"password-input\"]").SendKeys(appPassword);
            driver.FindElementByXPath("//*[@id=\"submit-button\"]").Click();

            System.Threading.Thread.Sleep(3000);
            ReadOnlyCollection<IWebElement> elements1 = driver.FindElementsByXPath("//*[text()=\"Sorry, your account is locked\"]");

            Console.WriteLine("Is account locked? " +elements1.Count);

            return elements1.Count > 0;
        }
    }

    public class CsvReader : IDisposable
    {
        private string path;
        private string[] currentData;
        private StreamReader reader;

        public CsvReader(string path)
        {
            if (!File.Exists(path)) throw new InvalidOperationException("path does not exist");
            this.path = path;
            Initialize();
        }

        private void Initialize()
        {
            FileStream stream = new FileStream(path, FileMode.Open, FileAccess.Read);
            reader = new StreamReader(stream);
        }

        public bool Next()
        {
            string current = null;
            if ((current = reader.ReadLine()) == null) return false;
            currentData = current.Split(',');
            return true;
        }

        public string this[int index]
        {
            get { return currentData[index]; }
        }


        public void Dispose()
        {
            reader.Close();
        }
    }
}
