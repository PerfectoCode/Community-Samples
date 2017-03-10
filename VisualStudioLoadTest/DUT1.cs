using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using PerfectoLoadTest;
using System.Configuration;

namespace Perfecto_Load_Test
{
    [TestClass]
    public class DUT1 : RealDeviceBase
    {
        [TestMethod]
        public void RealDeviceTest_DUT1()
        {
            ExecuteTest(ConfigurationManager.AppSettings["DUT1"], ConfigurationManager.AppSettings["DUT1_DeviceName"]);
        }
    }
}
