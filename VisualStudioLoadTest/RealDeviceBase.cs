using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Xml;
using System.Net;
using System.Threading;
using System.Configuration;
using System.Web;

namespace PerfectoLoadTest
{
    [TestClass]
    public class RealDeviceBase
    {
        protected TestContext testContextInstance;
        String executionId;
        String reportKey;
        String errorMessage;
        String PerfectoScriptName = ConfigurationManager.AppSettings["ScriptPath"];
        String PerfectoCloud = ConfigurationManager.AppSettings["PerfectoCloud"];
        String PerfectoUserID = ConfigurationManager.AppSettings["PerfectoUsername"];
        String PerfectoPassword = ConfigurationManager.AppSettings["PerfectoPassword"];
        String PerfectoDeviceDUT;
        String PerfectoMoreParameters;

        [TestMethod]
        public void ExecuteTest(String deviceId, String DUTName)
        {
            PerfectoDeviceDUT = deviceId;
            ValidateInput();
            ExecutePerfectoScript();
            CheckExecutionStatus();
            AnalyzePerfectoReport(DUTName);
        }

        private void ValidateInput()
        {
            //Validate inputs are not empty
            if (PerfectoScriptName.Length == 0)
            {
                Assert.Fail("ScriptPath is empty");
            }

            if (PerfectoUserID.Length == 0)
            {
                Assert.Fail("PerfectoUsername is empty");
            }

            if (PerfectoPassword.Length == 0)
            {
                Assert.Fail("PerfectoPassword is empty");
            }

            if (PerfectoCloud.Length == 0)
            {
                Assert.Fail("PerfectoCloud is empty");
            }

            //Format script name
            if (!PerfectoScriptName.EndsWith(".xml"))
            {
                PerfectoScriptName = PerfectoScriptName + ".xml";
            }

            //PerfectoScriptName = HttpUtility.UrlEncode(PerfectoScriptName);
            PerfectoPassword = HttpUtility.UrlEncode(PerfectoPassword);
            PerfectoUserID = HttpUtility.UrlEncode(PerfectoUserID);
        }

        private void ExecutePerfectoScript()
        {
            String requestURL = "https://" + PerfectoCloud + "/services/executions?operation=execute&scriptKey=" + PerfectoScriptName + "&user=" + PerfectoUserID + "&password=" + PerfectoPassword + "&responseFormat=xml&param.DUT=" + PerfectoDeviceDUT;
            XmlDocument xml = CallRestService(requestURL);
            TestContext.WriteLine(xml.SelectSingleNode("//executionId").InnerText);
            executionId = xml.SelectSingleNode("//executionId").InnerText;
            reportKey = xml.SelectSingleNode("//reportKey").InnerText;
            String errorMessage = "";
            try
            {
                errorMessage = xml.SelectSingleNode("//errorMessage").InnerText;
            }
            catch (NullReferenceException ex)
            {

            }

            if (errorMessage.Contains("item not found"))
            {
                TestContext.WriteLine(errorMessage);
                Assert.Fail(errorMessage);
            }
            TestContext.WriteLine(TestContext.Properties.ToString());

        }

        private void CheckExecutionStatus()
        {
            TestContext.WriteLine("Report Key: " + reportKey);
            TestContext.WriteLine("Execution ID: " + executionId);

            string requestURL = "https://" + PerfectoCloud + "/services/executions/" + executionId + "?operation=status&user=" + PerfectoUserID + "&password=" + PerfectoPassword + "&responseFormat=xml";
            TestContext.WriteLine(requestURL);
            XmlDocument xml = CallRestService(requestURL);

            if (xml.SelectNodes("//completionDescription[text()='Device " + PerfectoDeviceDUT + " does not exist']").Count > 0)
            {
                TestContext.WriteLine("Device " + PerfectoDeviceDUT + " does not exist");
                Assert.Fail("Device " + PerfectoDeviceDUT + " does not exist");
            }

            int counter = 0;

            while (xml.SelectNodes("//completionDescription[contains(text(),'device is in use')]").Count > 0 && counter < 3)
            {
                Thread.Sleep(3000);
                TestContext.WriteLine("Device is busy. Retrying...");
                ExecutePerfectoScript();
                counter++;
            }


            while (!CallRestService(requestURL).SelectSingleNode("//status").InnerText.Equals("Completed"))
            {
                Thread.Sleep(1000);
            }

            if (CallRestService(requestURL).SelectSingleNode("//flowEndCode").InnerText.Equals("Failed"))
            {
                Assert.Fail("Test execution failure");
            }
        }

        private void AnalyzePerfectoReport(String DUTName)
        {
            String requestURL = "https://" + PerfectoCloud + "/services/reports/" + reportKey + "?operation=download&user=" + PerfectoUserID + "&password=" + PerfectoPassword + "&responseformat=xml";
            TestContext.WriteLine(requestURL);

            XmlDocument xml = CallRestService(requestURL);
            XmlNodeList transactionsList = xml.SelectNodes("//description[contains(text(),'Validation passed: Value of ux timer')]");

            String transactionName;
            int transactionTime;
            foreach (XmlNode transactionNode in transactionsList)
            {
                transactionName = GetSubstringByString("ux timer ", " is", transactionNode.InnerText);
                transactionTime = int.Parse(GetSubstringByString(" is ", " milliseconds", transactionNode.InnerText));
                TestContext.WriteLine(transactionName + "|" + transactionTime);

                if (TestContext.Properties.Contains("$LoadTestUserContext"))
                {
                    TestContext.BeginTimer(DUTName + "_" + transactionName);
                    Thread.Sleep(transactionTime);
                    TestContext.EndTimer(DUTName + "_" + transactionName);
                }
            }

        }

        private XmlDocument CallRestService(String requestURL)
        {
            XmlDocument xml = new XmlDocument();
            try
            {
                xml.Load(requestURL);
            }

            catch (Exception ex)
            {
                if (ex is WebException)
                {
                    TestContext.WriteLine("Bad credentials or invalid script path");
                    TestContext.WriteLine(ex.StackTrace.ToString());
                    Assert.Fail("Bad cloud credentials or invalid script path; Request: " + requestURL);
                }
            }

            return xml;
        }

        public TestContext TestContext
        {
            get { return testContextInstance; }
            set { testContextInstance = value; }
        }

        public string GetSubstringByString(string a, string b, string c)
        {
            return c.Substring((c.IndexOf(a) + a.Length), (c.IndexOf(b) - c.IndexOf(a) - a.Length));
        }
    }
}
