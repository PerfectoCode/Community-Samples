using ALMReportingBridge;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tests.Entities
{
    public class TestRunCommand : EntityCommand
    {

        public TestRunCommand(verb Command, string ServerUrl, string Username, string Password, string Domain, string Project)
            : base(Command, ServerUrl, Username, Password, Domain, Project)
        { }

        public void AddRunId(int RunId)
        {
            CommandString = CommandString + " /runid=" + RunId;
        }

        public void AddTestSetId(int TestSetId)
        {
            CommandString = CommandString + " /testsetid=" + TestSetId;
        }

        public void AddRunName(string RunName)
        {
            CommandString = CommandString + " /runname=\"" + RunName + "\"";
        }


        public void AddTestConfigId(int TestConfigId)
        {
            CommandString = CommandString + " /testconfigid=" + TestConfigId;
        }

        public void AddAdditionalFields(string Additional)
        {
            CommandString = CommandString + " /additional=\"" + Additional + "\"";
        }

        public void AddTestStatus(ALMEntity.TestStatus Status)
        {
            CommandString = CommandString + " /status=\"" + Status.ToString() + "\"";
        }

        public void AddDraftRun(bool DraftRun)
        {
            CommandString = CommandString + " /draftrun=\"" + DraftRun + "\"";
        }

    }
}
