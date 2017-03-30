using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using ALMReportingBridge;

namespace Tests
{
    [TestClass]
    public class TestRunCreate : BaseTest
    {
        [TestMethod]
        public void CreateRunPassed()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreNotEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false));
        }

        [TestMethod]
        public void CreateRunNA()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreNotEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.NA, "UnitTest", false));
        }

        [TestMethod]
        public void CreateRunNotCompleted()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreNotEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Not_Completed, "UnitTest", false));
        }

        [TestMethod]
        public void CreateRunNoRun()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreNotEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.No_Run, "UnitTest", false));
        }

        [TestMethod]
        public void CreateDraftRun()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreNotEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", true));
        }

        [TestMethod]
        public void CreateRunLookupListValid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("RN_USER_01;;a", 0);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreNotEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false, additional));
        }

        [TestMethod]
        public void CreateRunLookupListInvalid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("RN_USER_01;;d", 0);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false, additional));
        }

        [TestMethod]
        public void CreateRunInvalidField()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("RN_USER_99;;a", 0);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false, additional));
        }

        [TestMethod]
        public void CreateRunString()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("RN_USER_02;;This is a string", 0);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreNotEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false, additional));
        }

        [TestMethod]
        public void CreateRunUserListValid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("RN_USER_04;;alm_rb_vo", 0);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreNotEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false, additional));
        }

        [TestMethod]
        public void CreateRunUserListInvalid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("RN_USER_04;;alm_rb_o", 0);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false, additional));
        }

        [TestMethod]
        public void CreateRunStringMaskValid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("RN_USER_03;;(954)455-5544", 0);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreNotEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false, additional));
        }

        [TestMethod]
        public void CreateRunStringMaskInvalid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("RN_USER_03;;(95e)455-5544", 0);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false, additional));
        }

        [TestMethod]
        public void CreateRunNumberValid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("RN_USER_06;;8764", 0);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreNotEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false, additional));
        }

        [TestMethod]
        public void CreateRunNumberinvalid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("RN_USER_06;;jh7", 0);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false, additional));
        }

        [TestMethod]
        public void CreateRunDateValid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("RN_USER_05;;3/15/2017", 0);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreNotEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false, additional));
        }

        [TestMethod]
        public void CreateRunDateInvalid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("RN_USER_05;;3/1e/2017", 0);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false, additional));
        }

        [TestMethod]
        public void CreateRunMemo()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("RN_USER_13;;<html><body><h1>Important!</h1><p>This is some information</p></body></html>", 0);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreNotEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false, additional));
        }

        [TestMethod]
        public void CreateRunMultiple()
        {
            string[] additional = new string[7];
            additional.SetValue("RN_USER_01;;a", 0);
            additional.SetValue("RN_USER_02;;This is my string", 1);
            additional.SetValue("RN_USER_04;;alm_rb_vo", 2);
            additional.SetValue("RN_USER_05;;3/15/2017", 3);
            additional.SetValue("RN_USER_03;;(954)455-5544", 4);
            additional.SetValue("RN_USER_13;;<html><body><h1>Important!</h1><p>This is some information</p></body></html>", 5);
            additional.SetValue("RN_USER_06;;234", 6);

            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreNotEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false, additional));
        }

        [TestMethod]
        public void CreateRunNoPermission()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(ServerUrl, ViewOnlyUser, ViewOnlyPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false));

        }

        [TestMethod]
        public void CreateRunBadHost()
        {
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, 100, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false));
        }

        [TestMethod]
        public void CreateRunBadUrlPath()
        {
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(InvalidPathUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, 100, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false));
        }

        [TestMethod]
        public void CreateRunBadUsername()
        {
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(ServerUrl, "badusername", AdminPassword, AlmDomain, StandardProject, 100, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false));
        }

        [TestMethod]
        public void CreateRunBadPassword()
        {
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, "badpassword", AlmDomain, StandardProject, 100, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false));
        }

        [TestMethod]
        public void CreateRunBadDomain()
        {
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, "baddomain", StandardProject, 100, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false));
        }

        [TestMethod]
        public void CreateRunBadProject()
        {
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, "badproject", 100, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false));
        }

        [TestMethod]
        public void CreateRunNoProjectAccess()
        {
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(ServerUrl, NoAccessUser, NoAccessPassword, AlmDomain, StandardProject, 100, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false));
        }

        [TestMethod]
        public void CreateRunInvalidTestSetId()
        {
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, 3, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false));
        }

        [TestMethod]
        public void CreateRunInvalidTestConfigId()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 100, ALMEntity.TestStatus.Passed, "UnitTest", false));

        }

        [TestMethod]
        public void CreateRunMultipleTestConfigsInTestSet()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false));
        }

        [TestMethod]
        public void CreateRunMissingReqField()
        {
            string[] additional = new string[1];
            additional.SetValue("CY_USER_01;;a", 0);

            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, RequiredFieldsProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, additional);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, RequiredFieldsProject, testSetId, 1003);
            Assert.AreEqual(0, ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, RequiredFieldsProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false));
        }


    }
}
