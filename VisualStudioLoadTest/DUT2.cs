using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using PerfectoLoadTest;
using System.Configuration;

namespace Perfecto_Load_Test
{
    [TestClass]
    public class DUT2 : RealDeviceBase
    {
        [TestMethod]
        public void RealDeviceTest_DUT2()
        {
            ExecuteTest(ConfigurationManager.AppSettings["DUT2"], ConfigurationManager.AppSettings["DUT2_DeviceName"]);
        }
    }
}
