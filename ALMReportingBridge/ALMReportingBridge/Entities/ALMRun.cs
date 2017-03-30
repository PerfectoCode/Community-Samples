using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using TDAPIOLELib;

namespace ALMReportingBridge.Entities
{
    class ALMRun : ALMEntity
    {
        public ALMRun(string ServerUrl, string Username, string Password, string Domain, string Project)
            : base(ServerUrl, Username, Password, Domain, Project)
        { }


        public bool UpdateField(
            int RunId,
            string FieldName,
            string Value)
        {

            bool success = false;

            try
            {
                if (!Connect(ServerUrl, Username, Password, Domain, Project))
                {
                    return false;
                }

                if (!CanPerformAction("ac_run_manual_test"))
                {
                    rr.AddErrorLine("Error: The user does not have permission to execute or update tests");
                    return false;
                }

                RunFactory runFact = tdc.RunFactory;
                Run testRun = runFact[RunId];
                testRun[FieldName] = Value;
                testRun.Post();
                success = true;
            }
            catch (COMException ce)
            { rr.AddErrorLine(HandleException(ce)); }

            finally
            {
                Disconnect();
            }
            return success;
        }

        public bool Attach(
            int RunId,
            AttachmentType Type,
            string Path,
            string Description = default(string),
            string Name = default(string))
        {
            bool success = false;

            try
            {
                if (!Connect(ServerUrl, Username, Password, Domain, Project))
                {
                    return false;
                }

                RunFactory runFact = tdc.RunFactory;
                Run testRun = runFact[RunId];

                //ALM bug allows selection of invalid runid; use name to catch it
                string runName = testRun.Name;

                AttachmentFactory attachFact = testRun.Attachments;

                Attachment attach = attachFact.AddItem(DBNull.Value);
                attach.FileName = Path;

                TDAPI_ATTACH_TYPE attachType = TDAPI_ATTACH_TYPE.TDATT_FILE;

                switch (Type)
                {
                    case AttachmentType.File:
                    case AttachmentType.file:
                        attachType = TDAPI_ATTACH_TYPE.TDATT_FILE;
                        break;
                    case AttachmentType.URL:
                    case AttachmentType.url:
                        attachType = TDAPI_ATTACH_TYPE.TDATT_INTERNET;
                        break;
                }

                attach.Type = (int)attachType;

                if (Description != default(string))
                {
                    attach.Description = Description;
                }

                //Post will not fail if attachment is bad (different than attaching to test set)
                attach.Post();

                int attachId = 0;
                attachId = attach.ID;
                //Console.Out.WriteLine(attachId);

                if ((Name != default(string)) && (attachType == TDAPI_ATTACH_TYPE.TDATT_FILE) && attach.ID > 0)
                {
                    attach.Rename(Name);
                    attach.Post();
                }

                //If the file path is invalid and the attachment never uploads, ALM throws an error but posts the attachment anyways
                //returning no attachment object. So the only way to detect this is to look for 0 sized attachments and delete them
                attach.Refresh();

                if (attach.FileSize == 0 && attach.Type == (int)TDAPI_ATTACH_TYPE.TDATT_FILE)
                {
                    attachFact.RemoveItem(attach.ID);
                    success = false;
                }
                else
                {
                    success = true;
                }
            }
            catch (COMException ce)
            {  
                rr.AddErrorLine(HandleException(ce));
            }
            finally { Disconnect(); }


            return success;
        }

        string TestStatus(TestStatus status)
        {
            string returnValue = default(string);

            switch (status)
            {
                case ALMEntity.TestStatus.Blocked:
                case ALMEntity.TestStatus.Failed:
                case ALMEntity.TestStatus.Passed:
                    returnValue = status.ToString();
                    break;
                case ALMEntity.TestStatus.NA:
                    returnValue = "N/A";
                    break;
                case ALMEntity.TestStatus.Not_Completed:
                    returnValue = "Not Completed";
                    break;
                case ALMEntity.TestStatus.No_Run:
                    returnValue = "No Run";
                    break;
            }

            return returnValue;
        }

        public int Create(
            int TestSetId,
            int TestConfigId,
            TestStatus Status,
            string RunName,
            bool DraftRun,
            string[] Additional = default(string[]))
        {
            int runId = 0;

            try
            {
                if (!Connect(ServerUrl, Username, Password, Domain, Project))
                {
                    return 0;
                }

                if (!CanPerformAction("ac_run_manual_test"))
                {
                    rr.AddErrorLine("Error: The user does not have permission to execute tests");
                    return 0;
                }

                string runStatus = TestStatus(Status);

                TestSetFactory tsFact = tdc.TestSetFactory;
                TestSet targetTestSet = tsFact[TestSetId];
                TSTestFactory tsTestFactory = targetTestSet.TSTestFactory;

                TDFilter filter = tsTestFactory.Filter;
                filter["TC_TEST_CONFIG_ID"] = TestConfigId.ToString();

                List testInstanceList = filter.NewList();

                if (testInstanceList.Count == 1)
                {
                    TSTest testInstance = testInstanceList[1];
                    RunFactory runFact = testInstance.RunFactory;

                    //Best practice is to provide a null value, but an ALM bug keeps the test status of the test instance unchanged unless a name is provided
                    Run testRun = runFact.AddItem(RunName + "_" + DateTime.Now);

                    testRun.Status = runStatus;

                    if (DraftRun)
                    {
                        testRun["RN_DRAFT"] = "Y";
                    }

                    //Set additional field values
                    if (Additional != default(string[]))
                    {
                        foreach (string fieldPair in Additional)
                        {
                            string[] tempFieldArray = fieldPair.Split(new[] { ";;" }, StringSplitOptions.None);
                            testRun[tempFieldArray[0]] = tempFieldArray[1];
                        }
                    }

                    testRun.Post();
                    runId = testRun.ID;
                    //Console.Out.WriteLine(runId);
                }
                else if (testInstanceList.Count == 0)
                {
                    rr.AddErrorLine("Error: The test configuration ID does not exist in the test set.");
                }
                else
                {
                    //More than one instace of the test configuration exists in the test set
                    //The integration cannot support duplicates
                    rr.AddErrorLine("Error: multiple instances of the test configuration exist in this test set.");
                }
            }
            catch (COMException ce)
            {
                rr.AddErrorLine(HandleException(ce));
            }
            finally { Disconnect(); }

            return runId;
        }

    }


}
