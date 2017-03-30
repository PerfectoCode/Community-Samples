using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Diagnostics;
using System.Xml.Linq;
using Tests.Entities;

namespace Tests
{
    [TestClass]
    public class CmdLineTests : BaseTest
    {

       string GetElementValue(XDocument xml, string Element)
        {
            return xml.Element("body").Element(Element).Value;
        }


        [TestMethod]
        public void Test()
        {
            string command = " test /serverurl=" + ServerUrl + " /username=" + AdminUser + " /password=" + AdminPassword +
               " /project=" + StandardProject + " /domain=" + AlmDomain;
       
            XDocument xml = XDocument.Parse(ExecuteCmd(command)[0]);
            Assert.AreEqual("Test", GetElementValue(xml, "operation"));
            Assert.AreEqual("Success", GetElementValue(xml, "status"));

        }

        [TestMethod]
        public void CreateTestSet()
        {
            TestSetCommand tsCommand = new TestSetCommand(EntityCommand.verb.createtestset, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            tsCommand.AddDestinationPath("Root\\UnitTests");
            tsCommand.AddTestSetName(DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);
       
            XDocument xml = XDocument.Parse(ExecuteCmd(tsCommand.CommandString)[0]);
            Assert.AreEqual("CreateTestSet", GetElementValue(xml, "operation"));
            Assert.AreEqual("Success", GetElementValue(xml, "status"));
            Assert.AreNotEqual(0, GetElementValue(xml, "entityid"));
        }

        [TestMethod]
        public void CreateTestSetAdditionalFields()
        {
            TestSetCommand tsCommand = new TestSetCommand(EntityCommand.verb.createtestset, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            tsCommand.AddDestinationPath("Root\\UnitTests");
            tsCommand.AddTestSetName(DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);
            tsCommand.AddAdditionalFields("CY_USER_01;;a");

            XDocument xml = XDocument.Parse(ExecuteCmd(tsCommand.CommandString)[0]);
            Assert.AreEqual("CreateTestSet", GetElementValue(xml, "operation"));
            Assert.AreEqual("Success", GetElementValue(xml, "status"));
            Assert.AreNotEqual(0, GetElementValue(xml, "entityid"));
        }

        [TestMethod]
        public void CreateTestSetInvalidPath()
        {
            TestSetCommand tsCommand = new TestSetCommand(EntityCommand.verb.createtestset, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            tsCommand.AddDestinationPath("Root\\UnitxTests");
            tsCommand.AddTestSetName(DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);
            tsCommand.AddAdditionalFields("CY_USER_01;;a");

            XDocument xml = XDocument.Parse(ExecuteCmd(tsCommand.CommandString)[0]);
            Assert.AreEqual("CreateTestSet", GetElementValue(xml, "operation"));
            Assert.AreEqual("Failed", GetElementValue(xml, "status"));
        }

        [TestMethod]
        public void CopyTestSet()
        {
            TestSetCommand tsCommand = new TestSetCommand(EntityCommand.verb.copytestset, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            tsCommand.AddDestinationPath("Root\\UnitTests");
            tsCommand.AddTestSetName(DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);
            tsCommand.AddTemplateTestSetId(531);

            XDocument xml = XDocument.Parse(ExecuteCmd(tsCommand.CommandString)[0]);
            Assert.AreEqual("CopyTestSet", GetElementValue(xml, "operation"));
            Assert.AreEqual("Success", GetElementValue(xml, "status"));
        }

        [TestMethod]
        public void AddTestToTestSet()
        {
            TestSetCommand tsCommand = new TestSetCommand(EntityCommand.verb.createtestset, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            tsCommand.AddDestinationPath("Root\\UnitTests");
            tsCommand.AddTestSetName(DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            XDocument xml = XDocument.Parse(ExecuteCmd(tsCommand.CommandString)[0]);

            tsCommand = new TestSetCommand(EntityCommand.verb.addtesttotestset, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            tsCommand.AddTestSetId(int.Parse(GetElementValue(xml, "entityid")));
            tsCommand.AddTestConfigId(1002);

            xml = XDocument.Parse(ExecuteCmd(tsCommand.CommandString)[0]);

            Assert.AreEqual("AddTestToTestSet", GetElementValue(xml, "operation"));
            Assert.AreEqual("Success", GetElementValue(xml, "status"));
        }

        [TestMethod]
        public void AttachToTestSet()
        {
            TestSetCommand tsCommand = new TestSetCommand(EntityCommand.verb.createtestset, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            tsCommand.AddDestinationPath("Root\\UnitTests");
            tsCommand.AddTestSetName(DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            XDocument xml = XDocument.Parse(ExecuteCmd(tsCommand.CommandString)[0]);

            tsCommand = new TestSetCommand(EntityCommand.verb.attachtotestset, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            tsCommand.AddTestSetId(int.Parse(GetElementValue(xml, "entityid")));
            tsCommand.AddAttachmentPath(AttachmentPath);
            tsCommand.AddAttachmentType(ALMReportingBridge.ALMEntity.AttachmentType.file);
            tsCommand.AddAttachmentName("My file.txt");

            xml = XDocument.Parse(ExecuteCmd(tsCommand.CommandString)[0]);

            Assert.AreEqual("AttachToTestSet", GetElementValue(xml, "operation"));
            Assert.AreEqual("Success", GetElementValue(xml, "status"));
        }

        [TestMethod]
        public void UpdateTestSetField()
        {
            TestSetCommand tsCommand = new TestSetCommand(EntityCommand.verb.createtestset, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            tsCommand.AddDestinationPath("Root\\UnitTests");
            tsCommand.AddTestSetName(DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            XDocument xml = XDocument.Parse(ExecuteCmd(tsCommand.CommandString)[0]);

            tsCommand = new TestSetCommand(EntityCommand.verb.updatetestsetfield, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            tsCommand.AddFieldName("CY_USER_01");
            tsCommand.AddFieldValue("a");
            tsCommand.AddTestSetId(int.Parse(GetElementValue(xml, "entityid")));

            xml = XDocument.Parse(ExecuteCmd(tsCommand.CommandString)[0]);
            Assert.AreEqual("UpdateTestSetField", GetElementValue(xml, "operation"));
            Assert.AreEqual("Success", GetElementValue(xml, "status"));
        }

        [TestMethod]
        public void RecordRunResult()
        {
            TestSetCommand tsCommand = new TestSetCommand(EntityCommand.verb.createtestset, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            tsCommand.AddDestinationPath("Root\\UnitTests");
            tsCommand.AddTestSetName(DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            XDocument xml = XDocument.Parse(ExecuteCmd(tsCommand.CommandString)[0]);
            int TestSetId = int.Parse(GetElementValue(xml, "entityid"));

            tsCommand = new TestSetCommand(EntityCommand.verb.addtesttotestset, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            tsCommand.AddTestSetId(TestSetId);
            tsCommand.AddTestConfigId(1002);

            xml = XDocument.Parse(ExecuteCmd(tsCommand.CommandString)[0]);

            TestRunCommand trCommand = new TestRunCommand(EntityCommand.verb.recordrunresult, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            trCommand.AddTestSetId(TestSetId);
            trCommand.AddRunName("My Test");
            trCommand.AddTestConfigId(1002);
            trCommand.AddTestStatus(ALMReportingBridge.ALMEntity.TestStatus.Passed);

            xml = XDocument.Parse(ExecuteCmd(trCommand.CommandString)[0]);

            Assert.AreEqual("RecordRunResult", GetElementValue(xml, "operation"));
            Assert.AreEqual("Success", GetElementValue(xml, "status"));
            Assert.AreNotEqual(0, GetElementValue(xml, "entityid"));
        }

        [TestMethod]
        public void UpdateRunField()
        {
            TestSetCommand tsCommand = new TestSetCommand(EntityCommand.verb.createtestset, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            tsCommand.AddDestinationPath("Root\\UnitTests");
            tsCommand.AddTestSetName(DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            XDocument xml = XDocument.Parse(ExecuteCmd(tsCommand.CommandString)[0]);
            int TestSetId = int.Parse(GetElementValue(xml, "entityid"));

            tsCommand = new TestSetCommand(EntityCommand.verb.addtesttotestset, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            tsCommand.AddTestSetId(TestSetId);
            tsCommand.AddTestConfigId(1002);

            xml = XDocument.Parse(ExecuteCmd(tsCommand.CommandString)[0]);

            TestRunCommand trCommand = new TestRunCommand(EntityCommand.verb.recordrunresult, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            trCommand.AddTestSetId(TestSetId);
            trCommand.AddRunName("My Test");
            trCommand.AddTestConfigId(1002);
            trCommand.AddTestStatus(ALMReportingBridge.ALMEntity.TestStatus.Passed);

            xml = XDocument.Parse(ExecuteCmd(trCommand.CommandString)[0]);

            int RunId = int.Parse(GetElementValue(xml, "entityid"));

            trCommand = new TestRunCommand(EntityCommand.verb.updaterunfield, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            trCommand.AddRunId(RunId);
            trCommand.AddFieldName("RN_USER_01");
            trCommand.AddFieldValue("a");

            xml = XDocument.Parse(ExecuteCmd(trCommand.CommandString)[0]);
            Assert.AreEqual("UpdateRunField", GetElementValue(xml, "operation"));
            Assert.AreEqual("Success", GetElementValue(xml, "status"));
        }

        [TestMethod]
        public void AttachToRun()
        {
            TestSetCommand tsCommand = new TestSetCommand(EntityCommand.verb.createtestset, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            tsCommand.AddDestinationPath("Root\\UnitTests");
            tsCommand.AddTestSetName(DateTime.Now.ToString() + " - " + System.Reflection.MethodBase.GetCurrentMethod().Name);

            XDocument xml = XDocument.Parse(ExecuteCmd(tsCommand.CommandString)[0]);
            int TestSetId = int.Parse(GetElementValue(xml, "entityid"));

            tsCommand = new TestSetCommand(EntityCommand.verb.addtesttotestset, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            tsCommand.AddTestSetId(TestSetId);
            tsCommand.AddTestConfigId(1002);

            xml = XDocument.Parse(ExecuteCmd(tsCommand.CommandString)[0]);

            TestRunCommand trCommand = new TestRunCommand(EntityCommand.verb.recordrunresult, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            trCommand.AddTestSetId(TestSetId);
            trCommand.AddRunName("My Test");
            trCommand.AddTestConfigId(1002);
            trCommand.AddTestStatus(ALMReportingBridge.ALMEntity.TestStatus.Passed);

            xml = XDocument.Parse(ExecuteCmd(trCommand.CommandString)[0]);

            int RunId = int.Parse(GetElementValue(xml, "entityid"));

            trCommand = new TestRunCommand(EntityCommand.verb.attachtorun, ServerUrl, AdminUser, AdminPassword, AlmDomain, StandardProject);
            trCommand.AddRunId(RunId);
            trCommand.AddAttachmentPath(AttachmentPath);
            trCommand.AddAttachmentType(ALMReportingBridge.ALMEntity.AttachmentType.File);
            trCommand.AddAttachmentName("My super awesome attachment.txt");
            trCommand.AddAttachmentDescription("this is a description for an attachment");

            xml = XDocument.Parse(ExecuteCmd(trCommand.CommandString)[0]);
            Assert.AreEqual("AttachToRun", GetElementValue(xml, "operation"));
            Assert.AreEqual("Success", GetElementValue(xml, "status"));
        }

        string[] ExecuteCmd(string Arguments = default(string))
        {
            string[] returnValue = new string[2];
            string cmd = " /c \"\""  + AppDomain.CurrentDomain.BaseDirectory + "\\ALMReportingBridge.exe\"";
            Process process = new Process();
            process.StartInfo.FileName = "cmd.exe";
            
            if(Arguments != default(string))
            {
                cmd = cmd + Arguments;
            }

            cmd = cmd + "\"";

            Console.Out.WriteLine(cmd);

            process.StartInfo.Arguments = cmd;
            process.StartInfo.UseShellExecute = false;
            process.StartInfo.RedirectStandardOutput = true;
            process.StartInfo.RedirectStandardError = true;
            process.Start();
            //* Read the output (or the error)
            string output = process.StandardOutput.ReadToEnd();
            Console.WriteLine(output);
            string err = process.StandardError.ReadToEnd();
            Console.WriteLine(err);
            process.WaitForExit();

            returnValue.SetValue(output, 0);
            returnValue.SetValue(err, 1);

            return returnValue;
        }
    }

}
