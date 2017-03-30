using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using ALMReportingBridge;

namespace Tests
{
    [TestClass]
    public class TestSetUpdate : BaseTest
    {
        [TestMethod]
        public void UpdateName()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsTrue(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, "CY_CYCLE", DateTime.Now.ToString() + " Renamed"));
        }

        [TestMethod]
        public void InvalidTS()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsFalse(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, 0, "CY_CYCLE", DateTime.Now.ToString() + " Renamed"));
        }

        [TestMethod]
        public void NoPermission()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsFalse(ReportingConnector.UpdateTestSetField(ServerUrl, ViewOnlyUser, ViewOnlyPassword, AlmDomain, StandardProject, testSetId, "CY_CYCLE", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name + "Renamed"));
        }

        [TestMethod]
        public void LookupListValid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);


            Assert.IsTrue(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, "CY_USER_01", "a"));
        }

        [TestMethod]
        public void LookupListInvalid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsFalse(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, "CY_USER_01", "d"));
        }

        [TestMethod]
        public void StringTest()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsTrue(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, "CY_USER_02", "This is my string"));
        }

        [TestMethod]
        public void UserListValid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsTrue(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, "CY_USER_03", "alm_rb_vo"));
        }

        [TestMethod]
        public void UserListInvalid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsFalse(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, "CY_USER_03", "alm_rb_v"));
        }

        [TestMethod]
        public void DateValid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsTrue(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, "CY_USER_04", "3/15/2017"));
        }

        [TestMethod]
        public void DateInvalid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsFalse(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, "CY_USER_04", "315a/2017"));
        }

        [TestMethod]
        public void StringMaskValid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsTrue(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, "CY_USER_05", "(954)455-5544"));
        }

        [TestMethod]
        public void StringMaskInvalid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsFalse(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, "CY_USER_05", "(9k4)455-5544"));
        }

        [TestMethod]
        public void Memo()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsTrue(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, "CY_USER_07", "<html><body><h1>Important!</h1><p>This is some information</p></body></html>"));
        }

        [TestMethod]
        public void NumberValid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsTrue(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, "CY_USER_06", "84847"));
        }

        [TestMethod]
        public void NumberInvalid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsFalse(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, "CY_USER_06", "test"));
        }

        [TestMethod]
        public void LookupListValidTransition()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsTrue(ReportingConnector.UpdateTestSetField(ServerUrl, TransitionUser, TransitionUserPass, AlmDomain, StandardProject, testSetId, "CY_USER_01", "a"));
            Assert.IsTrue(ReportingConnector.UpdateTestSetField(ServerUrl, TransitionUser, TransitionUserPass, AlmDomain, StandardProject, testSetId, "CY_USER_01", "b"));
        }

        [TestMethod]
        public void LookupListInvalidTransition()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsTrue(ReportingConnector.UpdateTestSetField(ServerUrl, TransitionUser, TransitionUserPass, AlmDomain, StandardProject, testSetId, "CY_USER_01", "a"));
            Assert.IsFalse(ReportingConnector.UpdateTestSetField(ServerUrl, TransitionUser, TransitionUserPass, AlmDomain, StandardProject, testSetId, "CY_USER_01", "c"));
        }

        [TestMethod]
        public void ReadOnlyField()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsFalse(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, "CY_CYCLE_ID", "8"));
        }

        [TestMethod]
        public void AddTest()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.AreNotEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003));
        }

        [TestMethod]
        public void AddTestWithLookupListValid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("TC_USER_01;;a", 0);

            Assert.AreNotEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, additional));
        }

        [TestMethod]
        public void AddTestWithLookupListInvalid()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("TC_USER_01;;d", 0);

            Assert.AreEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, additional));
        }

        [TestMethod]
        public void AddTestWithString()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("TC_USER_02;;This is my string.", 0);

            Assert.AreNotEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, additional));
        }

        [TestMethod]
        public void AddTestWithValidUserList()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("TC_USER_03;;alm_rb_vo", 0);

            Assert.AreNotEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, additional));
        }

        [TestMethod]
        public void AddTestWithInvalidUserList()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("TC_USER_03;;api_testx", 0);

            Assert.AreEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, additional));
        }

        [TestMethod]
        public void AddTestWithValidDate()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("TC_USER_04;;3/15/2017", 0);

            Assert.AreNotEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, additional));
        }

        [TestMethod]
        public void AddTestWithInvalidDate()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("TC_USER_04;;3/1g/2017", 0);

            Assert.AreEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, additional));
        }

        [TestMethod]
        public void AddTestWithValidNumber()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("TC_USER_05;;5676", 0);

            Assert.AreNotEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, additional));
        }

        [TestMethod]
        public void AddTestWithInvalidNumber()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("TC_USER_05;;5d676", 0);

            Assert.AreEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, additional));
        }

        [TestMethod]
        public void AddTestWithValidStringMask()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("TC_USER_06;;(954)455-5544", 0);

            Assert.AreNotEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, additional));
        }


        //ALM is not enforcing field validation on this API call
        [TestMethod]
        public void AddTestWithInvalidStringMask()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("TC_USER_06;;(954)h55-5544", 0);

            Assert.AreEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, additional));
        }

        [TestMethod]
        public void AddTestWithMemo()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("TC_USER_25;;<html><body><h1>Important!</h1><p>This is some information</p></body></html>", 0);

            Assert.AreNotEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, additional));
        }

        [TestMethod]
        public void AddTestWithMultipleFields()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[7];
            additional.SetValue("TC_USER_25;;<html><body><h1>Important!</h1><p>This is some information</p></body></html>", 0);
            additional.SetValue("TC_USER_06;;(954)455-5544", 1);
            additional.SetValue("TC_USER_05;;5676", 2);
            additional.SetValue("TC_USER_04;;3/15/2017", 3);
            additional.SetValue("TC_USER_03;;alm_rb_vo", 4);
            additional.SetValue("TC_USER_02;;This is my string.", 5);
            additional.SetValue("TC_USER_01;;c", 6);

            Assert.AreNotEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, additional));
        }

        [TestMethod]
        public void AddTestWithInvalidUserListNoRemoval()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            string[] additional = new string[1];
            additional.SetValue("TC_USER_03;;api_testx", 0);

            Assert.AreNotEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003, additional, false));
        }

        [TestMethod]
        public void AddTestToNonEmptyTestSet()
        {
            int tsId = ReportingConnector.CopyTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name, 531);
            Assert.AreNotEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, 1004));
        }

        [TestMethod]   
        public void UploadFileAttachment()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);
            Assert.IsTrue(ReportingConnector.AttachToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, ALMEntity.AttachmentType.File, AttachmentPath));   
        }

        [TestMethod]
        public void UploadFileAttachmentWithDesc()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);
            Assert.IsTrue(ReportingConnector.AttachToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, ALMEntity.AttachmentType.File, AttachmentPath, "Here is my description"));
        }

        [TestMethod]
        public void UploadFileAttachmentWithName()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);
            Assert.IsTrue(ReportingConnector.AttachToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, ALMEntity.AttachmentType.File, AttachmentPath, default(string), "My Attachment.txt"));
        }

        [TestMethod]
        public void UploadUrlAttachment()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);
            Assert.IsTrue(ReportingConnector.AttachToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, ALMEntity.AttachmentType.url, "http://www.perfectomobile.com"));

        }

        [TestMethod]
        public void UploadAttachmentInvalidTS()
        {
            Assert.IsFalse(ReportingConnector.AttachToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, 3, ALMEntity.AttachmentType.url, "http://www.perfectomobile.com"));

        }

        [TestMethod]
        public void UploadFileAttachmentBadPath()
        { 
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);
            Assert.IsFalse(ReportingConnector.AttachToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, ALMEntity.AttachmentType.File, "c:\nofile.txt"));
        }

        [TestMethod]
        public void UploadAttachmentNoPermission()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);
            Assert.IsFalse(ReportingConnector.AttachToTestSet(ServerUrl, ViewOnlyUser, ViewOnlyPassword, AlmDomain, StandardProject, tsId, ALMEntity.AttachmentType.url, "http://www.perfectomobile.com"));
        }
        /// <summary>
        ///
        /// </summary>
        [TestMethod]
        public void UpdateTSInvalidHost()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsFalse(ReportingConnector.UpdateTestSetField(InvalidHostUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, "CY_USER_01", "a"));
        }

        [TestMethod]
        public void UpdateTSInvalidUrlPath()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsFalse(ReportingConnector.UpdateTestSetField(InvalidPathUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, "CY_USER_01", "a"));
        }

        [TestMethod]
        public void UpdateTSBadUsername()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsFalse(ReportingConnector.UpdateTestSetField(ServerUrl, "baduser", AdminPassword, AlmDomain, StandardProject, testSetId, "CY_USER_01", "a"));
        }

        [TestMethod]
        public void UpdateTSBadPassword()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsFalse(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, "badpassword", AlmDomain, StandardProject, testSetId, "CY_USER_01", "a"));
        }

        [TestMethod]
        public void UpdateTSBadDomain()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsFalse(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, AdminPassword, "baddomain", StandardProject, testSetId, "CY_USER_01", "a"));
        }

        [TestMethod]
        public void UpdateTSBadProject()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsFalse(ReportingConnector.UpdateTestSetField(ServerUrl, AdminUser, AdminPassword, AlmDomain, "badproject", testSetId, "CY_USER_01", "a"));
        }

        [TestMethod]
        public void UpdateTSNoProjectAccess()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.IsFalse(ReportingConnector.UpdateTestSetField(ServerUrl, NoAccessUser, NoAccessPassword, AlmDomain, StandardProject, testSetId, "CY_USER_01", "a"));
        }

        [TestMethod]
        public void AddTestBadHost()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.AreEqual(0, ReportingConnector.AddTestToTestSet(InvalidHostUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003));
        }

        [TestMethod]
        public void AddTestBadPath()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.AreEqual(0, ReportingConnector.AddTestToTestSet(InvalidPathUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, testSetId, 1003));
        }

        [TestMethod]
        public void AddTestBadUser()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.AreEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, "baduser", AdminPassword, AlmDomain, StandardProject, testSetId, 1003));
        }

        [TestMethod]
        public void AddTestBadPassword()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.AreEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, "badpassword", AlmDomain, StandardProject, testSetId, 1003));
        }

        [TestMethod]
        public void AddTestBadDomain()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.AreEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, "baddomain", StandardProject, testSetId, 1003));
        }

        [TestMethod]
        public void AddTestBadProject()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.AreEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, "badproject", testSetId, 1003));
        }

        [TestMethod]
        public void AddTestNoProjectAccess()
        {
            int testSetId;
            testSetId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            Assert.AreEqual(0, ReportingConnector.AddTestToTestSet(ServerUrl, NoAccessUser, NoAccessPassword, AlmDomain, StandardProject, testSetId, 1003));
        }

        [TestMethod]
        public void UploadFileAttachmentInvalidHost()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);
            Assert.IsFalse(ReportingConnector.AttachToTestSet(InvalidHostUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, ALMEntity.AttachmentType.File, AttachmentPath));
        }

        [TestMethod]
        public void UploadFileAttachmentInvalidUrlPath()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);
            Assert.IsFalse(ReportingConnector.AttachToTestSet(InvalidPathUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, tsId, ALMEntity.AttachmentType.File, AttachmentPath));
        }

        [TestMethod]
        public void UploadFileAttachmentInvalidUser()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);
            Assert.IsFalse(ReportingConnector.AttachToTestSet(ServerUrl, "baduser", AdminPassword, AlmDomain, StandardProject, tsId, ALMEntity.AttachmentType.File, AttachmentPath));
        }

        [TestMethod]
        public void UploadFileAttachmentInvalidPassword()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);
            Assert.IsFalse(ReportingConnector.AttachToTestSet(ServerUrl, AdminUser, "badpassword", AlmDomain, StandardProject, tsId, ALMEntity.AttachmentType.File, AttachmentPath));
        }

        [TestMethod]
        public void UploadFileAttachmentInvalidDomain()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);
            Assert.IsFalse(ReportingConnector.AttachToTestSet(ServerUrl, AdminUser, AdminPassword, "baddomain", StandardProject, tsId, ALMEntity.AttachmentType.File, AttachmentPath));
        }

        [TestMethod]
        public void UploadFileAttachmentInvalidProject()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);
            Assert.IsFalse(ReportingConnector.AttachToTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, "badproject", tsId, ALMEntity.AttachmentType.File, AttachmentPath));
        }

        [TestMethod]
        public void UploadFileAttachmentNoProjectAccess()
        {
            int tsId = ReportingConnector.CreateTestSet(ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject, "Root\\UnitTests", DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);
            Assert.IsFalse(ReportingConnector.AttachToTestSet(ServerUrl, NoAccessUser, NoAccessPassword, AlmDomain, StandardProject, tsId, ALMEntity.AttachmentType.File, AttachmentPath));
        }
    }
}
