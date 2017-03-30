using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Configuration;

namespace Tests
{
    [TestClass]
    public class BaseTest
    {
        protected TestContext testContextInstance;
        protected string ServerUrl = ConfigurationManager.AppSettings["ALMServer"];
        protected string InvalidHostUrl = ConfigurationManager.AppSettings["InvalidHost"];
        protected string InvalidPathUrl = ConfigurationManager.AppSettings["InvalidPath"];
        protected string AdminUser = ConfigurationManager.AppSettings["AdminUser"];
        protected string AdminPassword = ConfigurationManager.AppSettings["AdminPassword"];
        protected string AlmDomain = ConfigurationManager.AppSettings["ALMDomain"];
        protected string ConnectionTestProject = ConfigurationManager.AppSettings["ConnectionTestProject"];
        protected string ViewOnlyUser = ConfigurationManager.AppSettings["ViewOnlyUser"];
        protected string ViewOnlyPassword = ConfigurationManager.AppSettings["ViewOnlyPassword"];
        protected string StandardProject = ConfigurationManager.AppSettings["StandardProject"];
        protected string TransitionUser = ConfigurationManager.AppSettings["TransitionUser"];
        protected string TransitionUserPass = ConfigurationManager.AppSettings["TransitionUserPass"];
        protected string AttachmentPath = ConfigurationManager.AppSettings["AttachmentPath"];
        protected string NoAccessUser = ConfigurationManager.AppSettings["NoAccessUser"];
        protected string NoAccessPassword = ConfigurationManager.AppSettings["NoAccessPassword"];
        protected string RequiredFieldsProject = ConfigurationManager.AppSettings["RequiredFieldsProject"];

        public TestContext TestContext
        {
            get { return testContextInstance; }
            set { testContextInstance = value; }
        }
    }
}
