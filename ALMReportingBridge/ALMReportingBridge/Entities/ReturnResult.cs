using System;
using System.IO;
using System.Text;
using System.Xml;
using System.Xml.Linq;

namespace ALMReportingBridge.Entities
{
    public class ReturnResult
    {

        Operation op;
        bool result;
        int entityId = default(int);
        string errorMessage = default(string);
        XDocument xml;

        public Operation Op
        {
            get
            {
                return op;
            }

            set
            {
                op = value;
            }
        }

        public bool Result
        {
            get
            {
                return result;
            }

            set
            {
                result = value;
            }
        }

        public int EntityId
        {
            get
            {
                return entityId;
            }

            set
            {
                entityId = value;
            }
        }

        public string ErrorMessage
        {
            get
            {
                return errorMessage;
            }

            set
            {
                errorMessage = value;
            }
        }

        public enum Operation
        {
            AddTestToTestSet, CopyTestSet, CreateTestSet, UpdateTestSetField, AttachToRun, AttachToTestSet, RecordRunResult, Test, UpdateRunField
        }


        public ReturnResult()
        {
            xml = new XDocument(new XDeclaration("1.0", "utf-8", "yes"), new XElement("body"));
        }

        public void BuildXml()
        {
            xml.Element("body").Add(new XElement("operation", Op.ToString()));
            if (result)
            {
                xml.Element("body").Add(new XElement("status", "Success"));
            }else
            {
                xml.Element("body").Add(new XElement("status", "Failed"));
            }

            if(EntityId != default(int) && EntityId != 0)
            {
                xml.Element("body").Add(new XElement("entityid", EntityId));
            }
            
            if(ErrorMessage != default(string))
            {
                errorMessage = errorMessage.TrimEnd('\r', '\n');
                xml.Element("body").Add(new XElement("message", ErrorMessage));
            }

        }

        public void WriteToConsole()
        {
            var wr = new StringWriter();
            xml.Save(wr);
            string s = (wr.GetStringBuilder().ToString());
            Console.Out.WriteLine(s.Replace("utf-16", "utf-8"));
        }

        public void AddErrorLine(string msg)
        {
            errorMessage = errorMessage + msg + "\n";
        }

        public class Utf8StringWriter : StringWriter
        {
            public override Encoding Encoding { get { return Encoding.UTF8; } }
        }
    }
}
