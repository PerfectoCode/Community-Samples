using ALMReportingBridge;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static ALMReportingBridge.ALMEntity;

namespace Tests
{
    public class EntityCommand
    {
        protected string ServerUrl;
        protected string Username;
        protected string Password;
        protected string Domain;
        protected string Project;
        protected verb Command;
        protected int EntityId;
        protected AttachmentType AttachType;
        protected string Path;
        protected string Description;
        protected string AttachmentName;
        protected string FieldName;
        protected string FieldValue;
        protected string[] Additional;
        private String commandString;

        public string CommandString
        {
            get
            {
                return commandString;
            }

            set
            {
                commandString = value;
            }
        }

        public enum verb
        {
            copytestset, updaterunfield, recordrunresult, createtestset, updatetestsetfield, addtesttotestset, attachtorun, attachtotestset, test
        }

        protected EntityCommand(verb Command, string ServerUrl, string Username, string Password, string Domain, string Project)
        {
            this.Command = Command;
            this.ServerUrl = ServerUrl;
            this.Username = Username;
            this.Password = Password;
            this.Domain = Domain;
            this.Project = Project;

            CommandString = " " + Command.ToString() + " /serverurl=" + ServerUrl + " /username=\"" + Username + "\" /password=\"" + Password +
                "\" /project=" + Project + " /domain=" + Domain;
        }

        public void AddAttachmentPath(string Path)
        {
            CommandString = CommandString + " /path=\"" + Path + "\"";
        }

        public void AddAttachmentType(ALMEntity.AttachmentType Type)
        {
            CommandString = CommandString + " /type=" + Type.ToString();
        }

        public void AddAttachmentDescription(string Description)
        {
            CommandString = CommandString + " /description=\"" + Description + "\"";
        }

        public void AddAttachmentName(string Name)
        {
            CommandString = CommandString + " /name=\"" + Name + "\"";
        }

        public void AddFieldName(string FieldName)
        {
            CommandString = CommandString + " /fieldname=\"" + FieldName + "\"";
        }

        public void AddFieldValue(string Value)
        {
            CommandString = CommandString + " /value=\"" + Value + "\"";
        }
    }
}
