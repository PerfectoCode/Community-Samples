using ALMReportingBridge.Entities;
using CLAP;
using CLAP.Validation;
using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using TDAPIOLELib;

namespace ALMReportingBridge
{
    public class ReportingConnector
    {

        static TDConnection tdc = new TDConnection();

        // Consume them
        public static void Main(string[] args)
        {

            try
            {
                Parser.Run<ReportingConnector>(args);
            }
            catch (MissingRequiredArgumentException e)
            {
                Console.Out.WriteLine(e.Message);
            }
            catch (VerbNotFoundException e)
            {
                Console.Out.WriteLine(e.Message);
            }
            catch (Exception e)
            {
                Console.Out.WriteLine(e.Message);
            }

            //Environment.Exit(0);
        }


        [Verb(Description = "Creates a a copy of a test set")]
        public static int CopyTestSet(
            [Required Description("The HPE ALM server URL")] string ServerUrl,
            [Required Description("ALM username")] string Username,
            [Required Description("ALM password")] string Password,
            [Required Description("ALM project domain name")] string Domain,
            [Required Description("ALM project name")] string Project,
            [Required Description("Destination folder where the new test set will be copied")] string DestinationPath,
            [Required Description("Name of the newly copied test set")] string TestSetName,

            [MoreThan(0)]
            [Required Description("The ID of the test set to be copied")] int TemplateTestSetId)
        {
            ALMTestSet TestSet = new ALMTestSet(ServerUrl, Username, Password, Domain, Project);
            int result = TestSet.Copy(DestinationPath, TestSetName, TemplateTestSetId);
            TestSet.WriteResult(ReturnResult.Operation.CopyTestSet, Convert.ToBoolean(result), result);
            return result;     
        }


        [Verb(Description = "Update a run result field by its database name")]
        public static bool UpdateRunField(
            [Required Description("The HPE ALM server URL")] string ServerUrl,
            [Required Description("ALM username")] string Username,
            [Required Description("ALM password")] string Password,
            [Required Description("ALM project domain name")] string Domain,
            [Required Description("ALM project name")] string Project,

            [MoreThan(0)]
            [Required Description("The run ID to update")] int RunId,
            [Required Description("The database field name to update")] string FieldName,
            [Required Description("The value to update")] string Value)
        {
            ALMRun Run = new ALMRun(ServerUrl, Username, Password, Domain, Project);
            bool result = Run.UpdateField(RunId, FieldName, Value);
            Run.WriteResult(ReturnResult.Operation.UpdateRunField, result);
            return result;
        }

        [Verb(Description = "Records the result of a test run")]
        public static int RecordRunResult(
            [Required Description("The HPE ALM server URL")] string ServerUrl,
            [Required Description("ALM username")] string Username,
            [Required Description("ALM password")] string Password,
            [Required Description("ALM project domain name")] string Domain,
            [Required Description("ALM project name")] string Project,

            [MoreThan(0)]
            [Required Description("The ID of the test set containing the test to record results in")] int TestSetId,

            [MoreThan(0)]
            [Required Description("The configuration ID of the test to record results")] int TestConfigId,
            [Required Description("The status of the ALM test")] ALMEntity.TestStatus Status,
            [Required Description("A prefix for the run name in ALM")] string RunName,

            [DefaultValue (false)]
            [Description("Create a draft run") ] bool DraftRun,
            [Description("An array of additional fields and values to update in the following format: fieldName;;value,fieldName;;value")] string[] Additional = default(string[])
            )
        {
            ALMRun Run = new ALMRun(ServerUrl, Username, Password, Domain, Project);
            int result = Run.Create(TestSetId, TestConfigId, Status, RunName, DraftRun, Additional);
            Run.WriteResult(ReturnResult.Operation.RecordRunResult, Convert.ToBoolean(result), result);
            return result;
        }


        [Verb(Description = "Creates a new empty test set in the destination folder")]
        public static int CreateTestSet(
            [Required Description("The HPE ALM server URL")] string ServerUrl,
            [Required Description("ALM username")] string Username,
            [Required Description("ALM password")] string Password,
            [Required Description("ALM project domain name")] string Domain,
            [Required Description("ALM project name")] string Project,
            [Required Description("Destination folder where the new test set will be created")] string DestinationPath,
            [Required Description("Name of the new test set")] string TestSetName,
            [Description("An array of additional fields and values to update in the following format: fieldName;;value,fieldName;;value")] string[] Additional = default(string[]))
        {
            ALMTestSet TestSet = new ALMTestSet(ServerUrl, Username, Password, Domain, Project);
            int result = TestSet.Create(DestinationPath, TestSetName, Additional);
            TestSet.WriteResult(ReturnResult.Operation.CreateTestSet, Convert.ToBoolean(result), result);
            return result;
        }

        [Verb(Description = "Updates the value of a test set field by its database name")]
        public static bool UpdateTestSetField(
            [Required Description("The HPE ALM server URL")] string ServerUrl,
            [Required Description("ALM username")] string Username,
            [Required Description("ALM password")] string Password,
            [Required Description("ALM project domain name")] string Domain,
            [Required Description("ALM project name")] string Project,

            [MoreThan(0)]
            [Required Description("The ID of the test set to update")] int TestSetId,
            [Required Description("The database field name to update")] string FieldName,
            [Required Description("The value to update")] string Value)
        {
            ALMTestSet TestSet = new ALMTestSet(ServerUrl, Username, Password, Domain, Project);
            bool result = TestSet.UpdateField(TestSetId, FieldName, Value);
            TestSet.WriteResult(ReturnResult.Operation.UpdateTestSetField, result);
            return result;
        }

        [Verb(Description = "Adds a test configuration to a test set")]
        public static int AddTestToTestSet(
            [Required Description("The HPE ALM server URL")] string ServerUrl,
            [Required Description("ALM username")] string Username,
            [Required Description("ALM password")] string Password,
            [Required Description("ALM project domain name")] string Domain,
            [Required Description("ALM project name")] string Project,

            [MoreThan(0)]
            [Required Description("Test set ID to add tests to")] int TestSetId,

            [MoreThan(0)]
            [Required Description("Test configuration ID to add to test set")] int TestConfigId,
            [Description("An array of additional fields and values to update in the following format: fieldName;;value,fieldName;;value")] string[] Additional = default(string[]),
            
            [DefaultValue(true)]
            [Description("Remove the newly added test if a field update fails while adding")] bool RemoveTestOnUpdateFail = true)
        {
            ALMTestSet TestSet = new ALMTestSet(ServerUrl, Username, Password, Domain, Project);
            int result = TestSet.AddTest(TestSetId, TestConfigId, Additional, RemoveTestOnUpdateFail);
            TestSet.WriteResult(ReturnResult.Operation.AddTestToTestSet, Convert.ToBoolean(result));
            return result;
        }

        [Verb(Description = "Upload attachment to run")]
        public static bool AttachToRun(
            [Required Description("The HPE ALM server URL")] string ServerUrl,
            [Required Description("ALM username")] string Username,
            [Required Description("ALM password")] string Password,
            [Required Description("ALM project domain name")] string Domain,
            [Required Description("ALM project name")] string Project,

            [MoreThan(0)]
            [Required Description("Run ID to add attachment to")] int RunId,
            [Required Description("Type of attachment: file or url")] ALMEntity.AttachmentType Type,
            [Required Description("File path or URL address")] string Path,
            [Description("Attachment description")] string Description = default(string),
            [Description("Attachment name. Be sure to include the file type in the name. Ex: myfile.txt")] string Name = default(string)
            )
        {
            ALMRun Run = new ALMRun(ServerUrl, Username, Password, Domain, Project);
            bool result = Run.Attach(RunId, Type, Path, Description, Name);
            Run.WriteResult(ReturnResult.Operation.AttachToRun, result);
            return result;
        }

        [Verb(Description = "Upload attachment to a test set")]
        public static bool AttachToTestSet(
            [Required Description("The HPE ALM server URL")] string ServerUrl,
            [Required Description("ALM username")] string Username,
            [Required Description("ALM password")] string Password,
            [Required Description("ALM project domain name")] string Domain,
            [Required Description("ALM project name")] string Project,

            [MoreThan(0)]
            [Required Description("Run ID to add attachment to")] int TestSetId,
            [Required Description("Type of attachment: file or url")] ALMEntity.AttachmentType Type,
            [Required Description("File path or URL address")] string Path,
            [Description("Attachment description")] string Description = default(string),
            [Description("Attachment name. Be sure to include the file type in the name. Ex: myfile.txt")] string Name = default(string))
        {
            ALMTestSet TestSet = new ALMTestSet(ServerUrl, Username, Password, Domain, Project);
            bool result = TestSet.Attach(TestSetId, Type, Path, Description, Name);
            TestSet.WriteResult(ReturnResult.Operation.AttachToTestSet, result);
            return result;
        }



        [Verb(IsDefault = true), Help, Empty]
        static void ShowHelp(string help)
        {
            Console.WriteLine(help);
        }

        [Verb(Description = "Tests connection to the ALM server")]
        public static bool Test(
            [Required Description("The HPE ALM server URL")] string ServerUrl,
            [Required Description("ALM username")] string Username,
            [Required Description("ALM password")] string Password,
            [Required Description("ALM project domain name")] string Domain,
            [Required Description("ALM project name")] string Project)
        {
            ALMEntity connection = new ALMEntity(ServerUrl, Username, Password, Domain, Project);
            bool result = connection.Test();

            connection.WriteResult(ReturnResult.Operation.Test, result);
            return result;
        }



    }

}
