using ALMReportingBridge;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace Tests
{
    [TestClass]
    public class TestRunUpdate : BaseTest
    {

        [TestMethod]
        public void UpdateRunLookupListValid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);
            Assert.IsTrue(ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, "RN_USER_01", "a"));
        }

        [TestMethod]
        public void UpdateRunLookupListInvalid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);
            Assert.IsFalse(ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, "RN_USER_01", "d"));
        }

        [TestMethod]
        public void UpdateRunString()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);
            Assert.IsTrue(ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, "RN_USER_02", "This is a string."));
        }

        [TestMethod]
        public void UpdateRunUserListValid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);
            Assert.IsTrue(ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, "RN_USER_04", "alm_rb_vo"));
        }

        [TestMethod]
        public void UpdateRunUserListInvalid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);
            Assert.IsFalse(ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, "RN_USER_04", "almx_rb_vo"));
        }

        [TestMethod]
        public void UpdateRunStringMaskValid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);
            Assert.IsTrue(ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, "RN_USER_03", "(954)455-5544"));
        }

        [TestMethod]
        public void UpdateRunStringMaskInvalid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);
            Assert.IsFalse(ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, "RN_USER_03", "(954)d55-5544"));
        }

        [TestMethod]
        public void UpdateRunNumberValid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);
            Assert.IsTrue(ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, "RN_USER_06", "65433"));
        }

        [TestMethod]
        public void UpdateRunNumberInvalid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);
            Assert.IsFalse(ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, "RN_USER_06", "454fd3"));
        }

        [TestMethod]
        public void UpdateRunDateValid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);
            Assert.IsTrue(ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, "RN_USER_05", "3/15/2017"));
        }

        [TestMethod]
        public void UpdateRunDateInvalid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);
            Assert.IsFalse(ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, "RN_USER_05", "3/1d/2017"));
        }

        [TestMethod]
        public void UpdateRunMemo()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);
            Assert.IsTrue(ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, "RN_USER_13", "<html><body><h1>Important!</h1><p>This is some information</p></body></html>"));
        }

        [TestMethod]
        public void UpdateRunInvalidField()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);
            Assert.IsFalse(ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, "RN_USER_99", "3/1d/2017"));
    }

        [TestMethod]
        public void UpdateRunNoPermission()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);
            Assert.IsFalse(ReportingConnector.UpdateRunField(ServerUrl, ViewOnlyUser, ViewOnlyPassword, AlmDomain, StandardProject, runId, "RN_USER_06", "65433"));
        }

        [TestMethod]
        public void UpdateRunBadHost()
        {
            Assert.IsFalse(ReportingConnector.UpdateRunField(InvalidHostUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, 100, "RN_USER_06", "65433"));
        }

        [TestMethod]
        public void UpdateRunBadUrlPath()
        {
            Assert.IsFalse(ReportingConnector.UpdateRunField(InvalidPathUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, 100, "RN_USER_06", "65433"));
        }

        [TestMethod]
        public void UpdateRunBadUsername()
        {
            Assert.IsFalse(ReportingConnector.UpdateRunField(ServerUrl, "baduser", AdminPassword, AlmDomain, StandardProject, 100, "RN_USER_06", "65433"));
        }

        [TestMethod]
        public void UpdateRunBadPassword()
        {
            Assert.IsFalse(ReportingConnector.UpdateRunField(ServerUrl, AdminUser, "badpassword", AlmDomain, StandardProject, 100, "RN_USER_06", "65433"));
        }

        [TestMethod]
        public void UpdateRunBadDomain()
        {
            Assert.IsFalse(ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, "baddomain", StandardProject, 100, "RN_USER_06", "65433"));
        }

        [TestMethod]
        public void UpdateRunBadProject()
        {
            Assert.IsFalse(ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, AlmDomain, "badproject", 100, "RN_USER_06", "65433"));
        }

        [TestMethod]
        public void UpdateRunNoProjectAccess()
        {
            Assert.IsFalse(ReportingConnector.UpdateRunField(ServerUrl, NoAccessUser, NoAccessPassword, AlmDomain, StandardProject, 100, "RN_USER_06", "65433"));
        }

        [TestMethod]
        public void UpdateRunInvalidTestConfigId()
        {
            Assert.IsFalse(ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, 1, "RN_USER_06", "65433"));
        }

        [TestMethod]
        public void UploadAttachmentNoPermission()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);

            Assert.IsFalse(ReportingConnector.AttachToRun(ServerUrl, ViewOnlyUser, ViewOnlyPassword, AlmDomain, StandardProject, runId, ALMEntity.AttachmentType.File, AttachmentPath));
        }

        [TestMethod]
        public void UploadAttachmentBadHost()
        {
            Assert.IsFalse(ReportingConnector.AttachToRun(InvalidHostUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, 2, ALMEntity.AttachmentType.File, AttachmentPath));
        }

        [TestMethod]
        public void UploadAttachmentBadUrlPath()
        {
            Assert.IsFalse(ReportingConnector.AttachToRun(InvalidPathUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, 2, ALMEntity.AttachmentType.File, AttachmentPath));
        }

        [TestMethod]
        public void UploadAttachmentBadUsername()
        {
            Assert.IsFalse(ReportingConnector.AttachToRun(ServerUrl, "baduser", AdminPassword, AlmDomain, StandardProject, 2, ALMEntity.AttachmentType.File, AttachmentPath));
        }

        [TestMethod]
        public void UploadAttachmentBadPassword()
        {
            Assert.IsFalse(ReportingConnector.AttachToRun(ServerUrl, AdminUser, "badpassword", AlmDomain, StandardProject, 2, ALMEntity.AttachmentType.File, AttachmentPath));
        }

        [TestMethod]
        public void UploadAttachmentBadDomain()
        {
            Assert.IsFalse(ReportingConnector.AttachToRun(ServerUrl, AdminUser, AdminPassword, "baddomain", StandardProject, 2, ALMEntity.AttachmentType.File, AttachmentPath));
        }

        [TestMethod]
        public void UploadAttachmentBadProject()
        {
            Assert.IsFalse(ReportingConnector.AttachToRun(ServerUrl, AdminUser, AdminPassword, AlmDomain, "badproject", 2, ALMEntity.AttachmentType.File, AttachmentPath));
        }

        [TestMethod]
        public void UploadAttachmentNoProjectAccess()
        {
            Assert.IsFalse(ReportingConnector.AttachToRun(ServerUrl, NoAccessUser, NoAccessPassword, AlmDomain, StandardProject, 2, ALMEntity.AttachmentType.File, AttachmentPath));
        }

        [TestMethod]
        public void UploadFileAttachment()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);

            Assert.IsTrue(ReportingConnector.AttachToRun(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, ALMEntity.AttachmentType.File, AttachmentPath));
        }

        [TestMethod]
        public void UploadFileAttachmentWithDesc()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);

            Assert.IsTrue(ReportingConnector.AttachToRun(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, ALMEntity.AttachmentType.File, AttachmentPath, "This is a description"));
        }

        [TestMethod]
        public void UploadFileAttachmentWithName()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);

            Assert.IsTrue(ReportingConnector.AttachToRun(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, ALMEntity.AttachmentType.File, AttachmentPath, "", "My file.txt"));
        }

        [TestMethod]
        public void UploadUrlAttachment()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);

            Assert.IsTrue(ReportingConnector.AttachToRun(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, ALMEntity.AttachmentType.url, "http://www.perfectomobile.com"));
        }

        [TestMethod]
        public void UploadAttachmentInvalidRun()
        {
            Assert.IsFalse(ReportingConnector.AttachToRun(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, 2, ALMEntity.AttachmentType.File, AttachmentPath));
        }

        [TestMethod]
        public void UploadFileAttachmentBadPath()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);

            Assert.IsFalse(ReportingConnector.AttachToRun(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, ALMEntity.AttachmentType.File, "c:\\myfile.txt"));
        }

        [TestMethod]
        public void TestRunValidTransition()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);
            ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, "RN_USER_01", "a");
            Assert.IsTrue(ReportingConnector.UpdateRunField(ServerUrl, TransitionUser, TransitionUserPass, AlmDomain, StandardProject, runId, "RN_USER_01", "b"));
        }

        [TestMethod]
        public void TestRunInvalidTransition()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003);
            int runId = ReportingConnector.RecordRunResult(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, ALMEntity.TestStatus.Passed, "UnitTest", false);
            ReportingConnector.UpdateRunField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, runId, "RN_USER_01", "a");
            Assert.IsFalse(ReportingConnector.UpdateRunField(ServerUrl, TransitionUser, TransitionUserPass, AlmDomain, StandardProject, runId, "RN_USER_01", "c"));
        }
    }
}
