using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tests.Entities
{
    public class TestSetCommand : EntityCommand
    {

        public TestSetCommand(verb Command, string ServerUrl, string Username, string Password, string Domain, string Project)
            : base(Command, ServerUrl, Username, Password, Domain, Project)
        { }

        public void AddTemplateTestSetId(int TemplateTestSetId)
        {
            CommandString = CommandString + " /templatetestsetid=" + TemplateTestSetId;
        }

        public void AddTestSetName(string TestSetName)
        {
            CommandString = CommandString + " /testsetname=\"" + TestSetName + "\"";
        }

        public void AddDestinationPath(string DestinationPath)
        {
            CommandString = CommandString + " /destinationpath=\"" + DestinationPath + "\"";
        }

        public void AddTestConfigId(int TestConfigId)
        {
            CommandString = CommandString + " /testconfigid=" + TestConfigId;
        }

        public void AddAdditionalFields(string Additional)
        {
            CommandString = CommandString + " /additional=\"" + Additional + "\"";
        }

        public void AddTestSetId(int TestSetId)
        {
            CommandString = CommandString + " /testsetid=" + TestSetId;
        }

    }
}
