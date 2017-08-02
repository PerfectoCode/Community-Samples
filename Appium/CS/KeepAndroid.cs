using System;
using System.Collections.Generic;
using System.Linq;
using System.Diagnostics;
using System.Text;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Appium.iOS;
using OpenQA.Selenium.Remote;
using OpenQA.Selenium.Appium.MultiTouch;
using System.Threading;
using System.Collections.ObjectModel;

namespace KeepAppium
{
    [TestClass]
    public class AppiumTest
    {
        private AndroidDriver<IWebElement> driver;
        //private IOSDriver<IWebElement> driver;

        [TestInitialize]
        public void PerfectoOpenConnection()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities(string.Empty, string.Empty, new Platform(PlatformType.Any));

            var host = "Your Cloud";
            capabilities.SetCapability("user", username);

            //TODO: Provide your password
            capabilities.SetCapability("password", password);

            //TODO: Provide your device ID
            capabilities.SetCapability("deviceName", deviceId);
            //capabilities.SetCapability("platformName", "Android");
            //capabilities.SetCapability("manufacturer", "Google");

            // Use this method if you want the script to share the devices with the Perfecto Lab plugin.
            capabilities.SetPerfectoLabExecutionId(host);

            // For Android:
            capabilities.SetCapability("appPackage", "com.google.android.keep");
            //capabilities.SetCapability("appActivity", ".activities.BrowseActivity");
            capabilities.SetCapability("autoLaunch", true);

            var url = new Uri(string.Format("https://{0}/nexperience/perfectomobile/wd/hub", host));
            driver = new AndroidDriver<IWebElement>(url, capabilities);
            //driver = new IOSDriver<IWebElement>(url, capabilities);
            driver.Manage().Timeouts().ImplicitlyWait(TimeSpan.FromSeconds(15));
        }

        [TestCleanup]
        public void PerfectoCloseConnection()
        {
            driver.Close();

            // In case you want to download the report or the report attachments, do it here.
            try
            {
                driver.DownloadReport(DownloadReportTypes.pdf, "C:\\testCS\\report_ListKeeper");
            //    driver.DownloadAttachment(DownloadAttachmentTypes.video, "C:\\test\\report\\video", "flv");
            //    driver.DownloadAttachment(DownloadAttachmentTypes.image, "C:\\test\\report\\images", "jpg");
            }
            catch (Exception ex)
            {
                Trace.WriteLine(string.Format("Error getting test logs: {0}", ex.Message));
            }

            driver.Quit();
        }

        [TestMethod]
        public void AppiumTestMethod()
        {
            //Write your test here
            try
            {
                // write your code here
                string title = "Test Scripting";
                IWebElement field;

                try
                {
                    field = driver.FindElementByXPath("//android.widget.TextView[@text='GET STARTED']");
                    field.Click();
                    Trace.WriteLine("Pressed the Get Started button to get into the application");
                }
                catch (NoSuchElementException n)
                {
                    Trace.WriteLine("No Welcome screen - just plow ahead");
                }
                field = driver.FindElementByXPath("//*[@resource-id='com.google.android.keep:id/new_list_button']");
                field.Click(); ;
                // give the list a title - that can be checked on the main Note-board display
                field = driver.FindElementByXPath("//*[@resource-id='com.google.android.keep:id/editable_title']");
                field.Click();
                field.SendKeys(title);
                // app is now ready to accept text for the first list item
                field = driver.FindElementByXPath("//android.widget.EditText[@resource-id='com.google.android.keep:id/description' and @text ='']");
                field.Click();
                field.SendKeys("Select app to test");
                // click the add field
                field = driver.FindElementByXPath("//android.widget.ImageView[parent::android.widget.LinearLayout[@content-desc='Add list item']]");
                field.Click();
                // Enter the second line in the list
                field = driver.FindElementByXPath("//android.widget.EditText[@resource-id='com.google.android.keep:id/description' and @text ='']");
                field.SendKeys("Write the script");

                // click the add field
                field = driver.FindElementByXPath("//android.widget.ImageView[parent::android.widget.LinearLayout[@content-desc='Add list item']]");
                field.Click();
                // return to the Note-board
                field = driver.FindElementByClassName("android.widget.ImageButton");
                field.Click();

                try
                {
                    //Verify that new note appears on the Note-board
                    field = driver.FindElementByXPath("//android.widget.TextView[@resource-id='com.google.android.keep:id/title' and @text='" + title + "']");
                }
                catch (NoSuchElementException f)
                {
                    Trace.WriteLine("Did not find the note on the NoteBoard");
                }
                // cleanup the application before closing the connection
                String cmnd = "mobile:application:clean";
                Dictionary<String, Object> pars = new Dictionary<String, Object>();
                pars.Add("name", "keep");
                driver.ExecuteScript(cmnd, pars);

            }
            catch (Exception e)
            {
                Trace.WriteLine("Script failed with the exception:" + e.Message);
            }
        }
    }
}
