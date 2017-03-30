using Microsoft.VisualStudio.TestTools.UnitTesting;
using ALMReportingBridge;
using System.Configuration;
using System;

namespace Tests
{
    [TestClass]
    public class ConnectionTests : BaseTest
    {

        [TestMethod]
        public void Success()
        {
            Assert.IsTrue(ReportingConnector.Test(ServerUrl, AdminUser, AdminPassword, AlmDomain, ConnectionTestProject));        
        }

        [TestMethod]
        public void BadHost()
        {
            Assert.IsFalse(ReportingConnector.Test(InvalidHostUrl, AdminUser, AdminPassword, AlmDomain, ConnectionTestProject));
        }

        [TestMethod]
        public void InvalidPath()
        {
            Assert.IsFalse(ReportingConnector.Test(InvalidPathUrl, AdminUser, AdminPassword, AlmDomain, ConnectionTestProject));
        }

        [TestMethod]
        public void BadUsername()
        {
            Assert.IsFalse(ReportingConnector.Test(ServerUrl, "badname", AdminPassword, AlmDomain, ConnectionTestProject));
        }

        [TestMethod]
        public void BadPassword()
        {
            Assert.IsFalse(ReportingConnector.Test(ServerUrl, AdminUser, "badpassword", AlmDomain, ConnectionTestProject));
        }

        [TestMethod]
        public void NoProjectAccess()
        {
            Assert.IsFalse(ReportingConnector.Test(ServerUrl, NoAccessUser, NoAccessPassword, AlmDomain, ConnectionTestProject));
        }

        [TestMethod]
        public void BadDomain()
        {
            Assert.IsFalse(ReportingConnector.Test(ServerUrl, AdminUser, AdminPassword, "baddomain", ConnectionTestProject));
        }

        [TestMethod]
        public void BadProject()
        {
            Assert.IsFalse(ReportingConnector.Test(ServerUrl, AdminUser, AdminPassword, AlmDomain, "badproject"));
        }
    }
}
