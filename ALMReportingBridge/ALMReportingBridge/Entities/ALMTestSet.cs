using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using TDAPIOLELib;
using static ALMReportingBridge.Entities.ReturnResult;

namespace ALMReportingBridge.Entities
{
    class ALMTestSet : ALMEntity
    {

        public ALMTestSet(string ServerUrl, string Username, string Password, string Domain, string Project)
            : base(ServerUrl, Username, Password, Domain, Project)
        { }


        public int Create(
            string DestinationPath,
            string TestSetName,
            string[] Additional = default(string[]))
        {
            int returnValue = 0;

            try
            {
                if (Connect(ServerUrl, Username, Password, Domain, Project))
                {
                    TestSetTreeManager tree = tdc.TestSetTreeManager;
                    TestSetFolder folder;


                    folder = tree.NodeByPath[DestinationPath];
                    TestSetFactory TSFact = folder.TestSetFactory;
                    TestSet NewTestSet;

                    NewTestSet = TSFact.AddItem(DBNull.Value);
                    NewTestSet.Name = TestSetName;

                    //Set additional field values
                    if (Additional != default(string[]))
                    {
                        foreach (string fieldPair in Additional)
                        {
                            string[] tempFieldArray = fieldPair.Split(new[] { ";;" }, StringSplitOptions.None);
                            NewTestSet[tempFieldArray[0]] = tempFieldArray[1];
                        }
                    }
                    NewTestSet.Post();
                    //Console.Out.WriteLine(NewTestSet.ID);
                    returnValue = NewTestSet.ID;
                }


            }
            catch (COMException ce)
            {
                rr.AddErrorLine(HandleException(ce));
            }
            finally { Disconnect();  }

            return returnValue;
        }

        public int Copy(
                string DestinationPath,
                string TestSetName,
                int TemplateTestSetId)
        {
            int returnValue = 0;

            try
            {
                if(!Connect(ServerUrl, Username, Password, Domain, Project))
                {
                    Disconnect();
                    return 0;
                }
               
                if (!CanPerformAction("ac_copy_cycle"))
                {
                    rr.AddErrorLine("Error: user has no permission to copy a test set");
                    Disconnect();
                    return 0;
                }

                ISupportCopyPaste4 iscp;
                string clipboard;
                TestSetFactory testSetFact;

                TestSetTreeManager treeMan = tdc.TestSetTreeManager;
                TestSetFolder folder = null;

                folder = treeMan.NodeByPath[DestinationPath];

                testSetFact = tdc.TestSetFactory;
                iscp = (ISupportCopyPaste4)testSetFact;

                clipboard = iscp.CopyToClipBoard(TemplateTestSetId.ToString(), 0, "");

                try
                {
                    iscp.PasteFromClipBoard(clipboard, folder.NodeID.ToString(), 0, 1);
                }
                catch (COMException ce)
                {
                    rr.AddErrorLine(HandleException(ce));
                    //An exception happens when the name is already taken. The TS will copy successfully. 
                }

                TDFilter filter;
                TestSetFactory tsFact = folder.TestSetFactory;
                filter = tsFact.Filter;

                string tsName = tsFact[TemplateTestSetId].Name;
                tsName = tsName.Trim(' ');
                string filterString = "\"" + "*" + tsName + "*" + "\"";
                filter["CY_CYCLE"] = filterString;
                filter.OrderDirection["CY_CYCLE_ID"] = (short)TDAPI_FILTERORDER.TDOLE_DESCENDING;

                List testSetList = filter.NewList();

                //Because of an ALM API bug, the OrderDirection doesn't always work, so we have to iterate manually to find the newly created TS
                int newestId = 0;
                TestSet newCopy;
                foreach (TestSet newTestSet in testSetList)
                {
                    if (newTestSet.ID > newestId)
                    {
                        newestId = newTestSet.ID;
                    }
                }

                newCopy = tsFact[newestId];

                newCopy.Name = TestSetName;

                try {newCopy.Post(); }
                catch(COMException ce)
                {
                    rr.AddErrorLine(HandleException(ce));
                }
                
                returnValue = newCopy.ID;
                //Console.Out.WriteLine(returnValue);


            } catch (COMException ce)
            {
                rr.AddErrorLine(HandleException(ce));
            }
            finally
            {
                Disconnect();
            }     

            return returnValue;
        }

        public bool UpdateField(
            int TestSetId,
            string FieldName,
            string Value)
        {
            bool returnValue = false;

            try
            {
                if (!Connect(ServerUrl, Username, Password, Domain, Project))
                {
                    Disconnect();
                    return false;
                }

                TestSetFactory tsFact = tdc.TestSetFactory;

                TestSet tset = tsFact[TestSetId];

                tset[FieldName] = Value;
                tset.Post();
                returnValue = true;
            }
            catch (COMException ce) { rr.AddErrorLine(HandleException(ce)); }
            finally
            {
                Disconnect();
            }

            return returnValue;
        }

        public int AddTest(
            int TestSetId,
            int TestConfigId,
            string[] Additional = default(string[]),
            bool RemoveTestOnUpdateFail = default(bool))
        {
            int result = 0;

            try
            {
                if(!Connect(ServerUrl, Username, Password, Domain, Project))
                {
                    return 0;
                }

                //Get the test ID from the config factory
                TestConfigFactory TestConfigFact = tdc.TestConfigFactory;

                TestConfig TestConfig = TestConfigFact[TestConfigId];

                int TestId = TestConfig.TestId;

                //API provides no way to add a specific test configuration to the test set
                //Instead we will add the test to the test set then remove the unneeded instances

                TestSetFactory TSFact = tdc.TestSetFactory;
                TestSet TestSet = TSFact[TestSetId];

                TSTestFactory TSTestFact = TestSet.TSTestFactory;

                //Capture the starting list of tests in the test set
                List StartingTestList = TSTestFact.NewList("");
                System.Collections.Generic.List<int> StartingTestInstanceList = new List<int>();

                foreach (TSTest testInstance in StartingTestList)
                {
                    StartingTestInstanceList.Add(int.Parse(testInstance.ID));
                }

                //Add the test to the test set
                TSTestFact.AddItem(TestId);

                //Capture the new list of tests in the test set
                List EndingTestList = TSTestFact.NewList("");
                System.Collections.Generic.List<int> EndingTestInstanceList = new List<int>();

                foreach (TSTest testInstance in EndingTestList)
                {
                    EndingTestInstanceList.Add(int.Parse(testInstance.ID));
                }


                //Remove added tests that we don't want
                TSTest tempInstance, addedTestInstance = null;
                TestConfig tempConfig;

                foreach (int testInstanceId in EndingTestInstanceList)
                {
                    if (!StartingTestInstanceList.Contains(testInstanceId))
                    {
                        tempInstance = TSTestFact[testInstanceId];
                        tempConfig = tempInstance.TestConfiguration;
                        if (!TestConfigId.Equals(tempConfig.ID))
                        {
                            TSTestFact.RemoveItem(tempInstance.ID);
                        }
                        else { addedTestInstance = tempInstance; }
                    }
                }

                result = int.Parse(addedTestInstance.ID);

                //Set additional field values
                if (Additional != default(string[]))
                {
                    foreach (string fieldPair in Additional)
                    {
                        string[] tempFieldArray = fieldPair.Split(new[] { ";;" }, StringSplitOptions.None);
                        addedTestInstance[tempFieldArray[0]] = tempFieldArray[1];
                    }

                    addedTestInstance.Post();
                }

            }
            catch (COMException ce)
            {
                rr.AddErrorLine(HandleException(ce));
                if (RemoveTestOnUpdateFail && result > 0)
                {
                    TSTestFactory fact = tdc.TSTestFactory;
                    fact.RemoveItem(result);
                    result = 0;
                }

            }

            Disconnect();
            return result;
        }




        public bool Attach(
            int TestSetId,
            AttachmentType Type,
            string Path,
            string Description = default(string),
            string Name = default(string))
        {
            bool success = false;

            try
            {
                if(!Connect(ServerUrl, Username, Password, Domain, Project)){
                    return false;
                }

                TestSetFactory tsFact = tdc.TestSetFactory;
                TestSet tSet = tsFact[TestSetId];
                string tSetName = tSet.Name; //Strange ALM bug allows you to select a non existant tet set. Calling name ensures it exists

                AttachmentFactory attachFact = tSet.Attachments;
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
                    case AttachmentType.Url:
                        attachType = TDAPI_ATTACH_TYPE.TDATT_INTERNET;
                        break;
                }

                attach.Type = (int)attachType;

                if (Description != default(string))
                {
                    attach.Description = Description;
                }

                //Post will fail if attachment path is bad
                attach.Post();

                if ((Name != default(string)) && (attachType == TDAPI_ATTACH_TYPE.TDATT_FILE))
                {
                    attach.Rename(Name);
                    attach.Post();
                }
                success = true;

            }
            catch (COMException ce)
            {
                //If we get an error, delete the attachment
                //If the file path is invalid and the attachment never uploads, ALM throws an error but posts the attachment anyways
                //returning no attachment object. So the only way to detect this is to look for 0 sized attachments and delete them
                TestSetFactory tsFact = tdc.TestSetFactory;
                TestSet tSet = tsFact[TestSetId];
                AttachmentFactory attachFact = tSet.Attachments;
                List attachList = attachFact.NewList("");
                foreach (Attachment a in attachList)
                {
                    if (a.FileSizeEx == 0)
                    {
                        attachFact.RemoveItem(a.ID);
                    }
                }
                rr.AddErrorLine(HandleException(ce));
            }
            finally { Disconnect();}

            return success;

        }
    }
}
