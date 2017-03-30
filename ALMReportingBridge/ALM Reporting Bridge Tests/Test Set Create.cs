using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using ALMReportingBridge;

namespace Tests
{
    [TestClass]
    public class TestSetCreate : BaseTest
    {
        [TestMethod]
        public void CreateTS()
        {
            Assert.AreNotEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name));
        }

        [TestMethod]
        public void InvalidPath()
        {
            Assert.AreEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\Tests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name));
        }

        [TestMethod]
        public void NoPermission()
        {
            testContextInstance.WriteLine("Username {0}; Password {1}", ViewOnlyUser, ViewOnlyPassword);
            Assert.AreEqual(0, ReportingConnector.CreateTestSet(ServerUrl, ViewOnlyUser, ViewOnlyPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name));
        }

        [TestMethod]
        public void LookupListValid()
        {
            string[] additional = new string[1];
            additional.SetValue("CY_USER_01;;a", 0);

            Assert.AreNotEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, additional));
        }

        [TestMethod]
        public void LookupListInvalid()
        {
            string[] additional = new string[1];
            additional.SetValue("CY_USER_01;;d", 0);

            Assert.AreEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, additional));
        }

        [TestMethod]
        public void StringTest()
        {
            string[] additional = new string[1];
            additional.SetValue("CY_USER_02;;This is my string", 0);

            Assert.AreNotEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, additional));
        }

        [TestMethod]
        public void InvalidField()
        {
            string[] additional = new string[1];
            additional.SetValue("CY_USER_99;;This is my string", 0);

            Assert.AreEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, additional));
        }

        [TestMethod]
        public void UserListValid()
        {
            string[] additional = new string[1];
            additional.SetValue("CY_USER_03;;alm_rb_vo", 0);

            Assert.AreNotEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, additional));
        }

        [TestMethod]
        public void UserListInvalid()
        {
            string[] additional = new string[1];
            additional.SetValue("CY_USER_03;;alm_rb_v", 0);

            Assert.AreEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, additional));
        }

        [TestMethod]
        public void DateValid()
        {
            string[] additional = new string[1];
            additional.SetValue("CY_USER_04;;3/15/2017", 0);

            Assert.AreNotEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, additional));
        }

        [TestMethod]
        public void DateInvalid()
        {
            string[] additional = new string[1];
            additional.SetValue("CY_USER_04;;315a/2017", 0);

            Assert.AreEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, additional));
        }

        [TestMethod]
        public void StringMaskValid()
        {
            string[] additional = new string[1];
            additional.SetValue("CY_USER_05;;(954)455-5544", 0);

            Assert.AreNotEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, additional));
        }

        //ALM doesn't enforce mask patterns via the API during creation of test set entities
        [TestMethod]
        public void StringMaskInvalid()
        {
            string[] additional = new string[1];
            additional.SetValue("CY_USER_05;;(9k4)455-5544", 0);

            Assert.AreEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, additional));
        }

        [TestMethod]
        public void Memo()
        {
            string[] additional = new string[1];
            additional.SetValue("CY_USER_07;;<html><body><h1>Important!</h1><p>This is some information</p></body></html>", 0);

            Assert.AreNotEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, additional));
        }

        [TestMethod]
        public void NumberValid()
        {
            string[] additional = new string[1];
            additional.SetValue("CY_USER_06;;74541", 0);

            Assert.AreNotEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, additional));
        }

        [TestMethod]
        public void NumberInvalid()
        {
            string[] additional = new string[1];
            additional.SetValue("CY_USER_06;;test", 0);

            Assert.AreEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, additional));
        }

        [TestMethod]
        public void Multiple()
        {
            string[] additional = new string[7];
            additional.SetValue("CY_USER_01;;a", 0);
            additional.SetValue("CY_USER_02;;This is my string", 1);
            additional.SetValue("CY_USER_03;;alm_rb_vo", 2);
            additional.SetValue("CY_USER_04;;3/15/2017", 3);
            additional.SetValue("CY_USER_05;;(954)455-5544", 4);
            additional.SetValue("CY_USER_07;;<html><body><h1>Important!</h1><p>This is some information</p></body></html>", 5);
            additional.SetValue("CY_USER_06;;234", 6);

            Assert.AreNotEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, additional));
        }

        [TestMethod]
        public void Copy()
        {
            Assert.AreNotEqual(0, ReportingConnector.CopyTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, 531));
        }

        [TestMethod] 
        public void CopyInvalidDestination()
        {
            Assert.AreEqual(0, ReportingConnector.CopyTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitxTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, 531));
        }

        [TestMethod]
        public void CopyInvalidTemplate()
        {
            Assert.AreEqual(0, ReportingConnector.CopyTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, 1));
        }

        [TestMethod]
        public void CopyNoPermission()
        {
            Assert.AreEqual(0, ReportingConnector.CopyTestSet(ServerUrl, ViewOnlyUser, ViewOnlyPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, 531));
        }

        [TestMethod]
        public void CopyNameTaken()
        {
            string tsName = DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name;
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", tsName);
            Assert.AreNotEqual(0, ReportingConnector.CopyTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", tsName, 531));
        }

        [TestMethod]
        public void CreateTSBadHost()
        {
            Assert.AreEqual(0, ReportingConnector.CreateTestSet(InvalidHostUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name));
        }

        [TestMethod]
        public void CreateTSInvalidUrlPath()
        {
            Assert.AreEqual(0, ReportingConnector.CreateTestSet(InvalidPathUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name));
        }

        [TestMethod]
        public void CreateTSBadUsername()
        {
            Assert.AreEqual(0, ReportingConnector.CreateTestSet(ServerUrl, "badname", AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name));
        }

        [TestMethod]
        public void CreateTSBadPassword()
        {
            Assert.AreEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, "badpassword", AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name));
        }

        [TestMethod]
        public void CreateTSNoProjectAccess()
        {
            Assert.AreEqual(0, ReportingConnector.CreateTestSet(ServerUrl, NoAccessUser, NoAccessPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name));
        }

        [TestMethod]
        public void CreateTSBadDomain()
        {
            Assert.AreEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, "baddomain", StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name));
        }

        [TestMethod]
        public void CreateTSBadProject()
        {
            Assert.AreEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, "badproject", "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name));
        }

        [TestMethod]
        public void CopyBadHost()
        {
            Assert.AreEqual(0, ReportingConnector.CopyTestSet(InvalidHostUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, 531));
        }

        [TestMethod]
        public void CopyInvalidUrlPath()
        {
            Assert.AreEqual(0, ReportingConnector.CopyTestSet(InvalidPathUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, 531));
        }

        [TestMethod]
        public void CopyInvalidUsername()
        {
            Assert.AreEqual(0, ReportingConnector.CopyTestSet(ServerUrl, "badusername", AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, 531));
        }

        [TestMethod]
        public void CopyInvalidPassword()
        {
            Assert.AreEqual(0, ReportingConnector.CopyTestSet(ServerUrl, AdminUser, "badpassword", AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, 531));
        }

        [TestMethod]
        public void CopyNoProjectAccess()
        {
            Assert.AreEqual(0, ReportingConnector.CopyTestSet(ServerUrl, NoAccessUser, NoAccessPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, 531));
        }

        [TestMethod]
        public void CopyBadDomain()
        {
            Assert.AreEqual(0, ReportingConnector.CopyTestSet(ServerUrl, AdminUser, AdminPassword, "baddomain", StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, 531));
        }

        [TestMethod]
        public void CopyBadProject()
        {
            Assert.AreEqual(0, ReportingConnector.CopyTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, "badproject", "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, 531));
        }

        [TestMethod]
        public void CreateTSMissingReqField()
        {
            Assert.AreEqual(0, ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, RequiredFieldsProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name));
        }
    }
}
