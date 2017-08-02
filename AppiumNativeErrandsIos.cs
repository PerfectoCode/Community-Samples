using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Appium.iOS;
using OpenQA.Selenium.Remote;
using System.Threading;
using System.Diagnostics;

namespace PrefectoLabTemplateProject
{
    [TestClass]
    public class AppiumTest
    {
        private IOSDriver<IWebElement> driver;

        [TestInitialize]
        public void PerfectoOpenConnection()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities(string.Empty, string.Empty, new Platform(PlatformType.Any));

            var host = <your cloud>;
            // Upload the application file to the Repository
            PrefectoLabExtension.UploadMedia(host, "yaacovw@perfectomobile.com", "sas17dec", 
                "C:\\test\\applications\\Errands.ipa", "PRIVATE:applications/Errands.ipa");

            capabilities.SetCapability("user", <username>);
            capabilities.SetCapability("password", <password>);

            // Provide your device ID
            capabilities.SetCapability("deviceName", "E0F246F08B30E68780F75B511A52C9B346D7D7B6");
            // Use this method if you want the script to share the devices with the Perfecto Lab plugin.
            capabilities.SetPrefectoLabExecutionId(host);

            // Use the automationName capability to defined the required framework - Appium (this is the default) or PerfectoMobile.
            //capabilities.SetCapability("automationName", "PerfectoMobile"); 


            // Application settings examples.
            capabilities.SetCapability("app", "PRIVATE:applications/Errands.ipa");
            // For iOS:
            capabilities.SetCapability("bundleId", "com.yoctoville.errands");
            capabilities.SetCapability("autoLaunch", true);
            capabilities.SetCapability("fullReset", true);



            var url = new Uri(string.Format("https://{0}/nexperience/perfectomobile/wd/hub", host));
            //driver = new AndroidDriver<IWebElement>(url, capabilities);
            driver = new IOSDriver<IWebElement>(url, capabilities);
            driver.Manage().Timeouts().ImplicitlyWait(TimeSpan.FromSeconds(10));
        }

        [TestCleanup]
        public void PerfectoCloseConnection()
        {
            driver.Close();

            // In case you want to down the report or the report attachments, do it here.
            try
            {
                driver.DownloadReport(DownloadReportTypes.pdf, "C:\\testCS\\reportErrands");
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
                // press Don't Allow button on Notifications screen if it appears
                try
                {
                    //IWebElement alert = driver.FindElementByXPath("//UIAAlert[contains(@name, \"Send You Notifications\")]");
                    driver.Manage().Timeouts().ImplicitlyWait(TimeSpan.FromSeconds(2));
                    driver.FindElementByName("Don't Allow").Click();
                }
                catch (NoSuchElementException n1)
                {
                    Trace.WriteLine("No notifications popup, just plow ahead!");
                }

                // press OK button on Welcome popup if it appears
                try
                {
                    IWebElement header = driver.FindElementByName("Welcome");
                    IWebElement okBtn = driver.FindElementByName("OK");
                    okBtn.Click();
                }
                catch (NoSuchElementException n1)
                {
                    Trace.WriteLine("No welcome popup, just plow ahead!");
                }

                // Press "New Note" button (on right end of header)
                driver.FindElementByName("add os7").Click();

                // Verify that arrive at the New Task screen
                try
                {
                    IWebElement nTask = driver.FindElementByName("New Task");
                }
                catch (NoSuchElementException n)
                {
                    Trace.WriteLine("Did not reach New Task window!");
                    throw n;
                }

                // Enter a title for the new task
                IWebElement title = driver.FindElementByIosUIAutomation("UIATarget.localTarget().frontMostApp().mainWindow().textFields()[0]");
                title.SendKeys("Prepare the script for Native Application");

                // Enter text into the Details field
                IWebElement detail = driver.FindElementByIosUIAutomation(".textFields()[1]");
                detail.SendKeys("Select an application, upload, and use Object Spy");

                // Click "Done" to go to next stage
                driver.FindElementByName("Done").Click();

                // Click on the Due Date field to select the due date
                try
                {
                    driver.FindElementByXPath("//UIAStaticText[@name='Due Date']").Click();
                }
                catch (NoSuchElementException n)
                {
                    Trace.WriteLine("Not showing second stage display!");
                    throw n;
                }
                // select that task is due next week
                driver.FindElementByName("+1 Week").Click();

                // Click "Done" to complete the task definition
                driver.FindElementByName("Done").Click();

                // Verify that task is listed
                try
                {
                    IWebElement nTask = driver.FindElementByName("Prepare the script for Native Application");
                }
                catch (NoSuchElementException n)
                {
                    Trace.WriteLine("The task is not listed!");
                    throw n;
                }


            }
            catch (Exception e)
            {
                Trace.WriteLine("Failed due to an exception:" + e.Message);
            }
        }
    }
}
