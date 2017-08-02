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

namespace KeepRefVars
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

            var host = <your cloud>;
            capabilities.SetCapability("user", <username>);

            //TODO: Provide your password
            capabilities.SetCapability("password", <password>);

            //TODO: Provide your device ID
            capabilities.SetCapability("deviceName", <your device>);

            // Use this method if you want the script to share the devices with the Perfecto Lab plugin.
            capabilities.SetPerfectoLabExecutionId(host);

            // Use the automationName capability to defined the required framework - Appium (this is the default) or PerfectoMobile.
            //capabilities.SetCapability("automationName", "PerfectoMobile"); 


            // Application settings examples.
            // For Android:
            capabilities.SetCapability("appPackage", "com.google.android.keep");

            // adding a Wind Tunnel persona - Sara
            capabilities.SetCapability("windTunnelPersona", "Sara");

            var url = new Uri(string.Format("https://{0}/nexperience/perfectomobile/wd/hub", host));
            driver = new AndroidDriver<IWebElement>(url, capabilities);
            driver.Manage().Timeouts().ImplicitlyWait(TimeSpan.FromSeconds(15));
        }

        [TestCleanup]
        public void PerfectoCloseConnection()
        {
            driver.Close();

            // In case you want to download the report or the report attachments, do it here.
            try
            {
                driver.DownloadReport(DownloadReportTypes.pdf, "C:\\testCS\\report_KeepRefVar");
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
                By newList = By.XPath("//*[@resource-id='com.google.android.keep:id/new_list_button']");
                By titleF = By.XPath("//*[@resource-id='com.google.android.keep:id/editable_title']");
                By newItem = By.XPath("//*[@resource-id='com.google.android.keep:id/add_item_text_view']");
                By addItem = By.XPath("//*[@resource-id='com.google.android.keep:id/add_item_extra_text_view']");
                By descLine = By.XPath("//*[@resource-id='com.google.android.keep:id/description' and @focusable='true']");
                IWebElement field;

                //first point of interest
                WindTunnelUtils.PointOfInterest(driver, "Adding the list", PointOfInterestStatus.Success);
                String title = "Test Scripting";
                field = driver.FindElement(newList);
                field.Click();;
                // give the list a title - that can be checked on the main Note-board display
                field = driver.FindElement(titleF);
                field.SendKeys(title);
                // app is now ready to accept text for the first list item
                field = driver.FindElement(newItem);
                field.Click();
                field.SendKeys("Select app to test");
                // click the add field
                field = driver.FindElement(addItem);
                field.Click();
                // Enter the second line in the list
                field = driver.FindElement(newItem);
                field.SendKeys("Write the script");

                // click the add field
                field = driver.FindElement(addItem);
                field.Click();
                // return to the Note-board
                field = driver.FindElementByClassName("android.widget.ImageButton");
                field.Click();

                // Second POI - completed the list.
                WindTunnelUtils.PointOfInterest(driver, "Completed entry", PointOfInterestStatus.Success);

                try
                {
                    //Verify that new note appears on the Note-board
                    field = driver.FindElementByXPath("//*[@resource-id=\"com.google.android.keep:id/title\" and @text=\"" + title + "\"]");
                    WindTunnelUtils.PointOfInterest(driver, "Found list on NoteBoard", PointOfInterestStatus.Success);
                }
                catch (NoSuchElementException f)
                {
                    Trace.WriteLine("Did not find the note on the NoteBoard");
                    WindTunnelUtils.PointOfInterest(driver, "Failed to find list on NoteBoard", PointOfInterestStatus.Failure);
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
