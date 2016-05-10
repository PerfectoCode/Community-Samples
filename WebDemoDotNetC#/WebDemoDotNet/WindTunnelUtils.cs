using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Text;
using System.Web.Script.Serialization;
using OpenQA.Selenium.Remote;

namespace WebDemoDotNet
{
    public static class WindTunnelUtils
    {
        #region Constants
        public const string SINGLE_TEST_REPORT_URL_CAPABILITY = "singleTestReportUrl";

        public const string WIND_TUNNEL_PERSONA_CAPABILITY = "windTunnelPersona";
        public const string WIND_TUNNEL_LOCATION_CAPABILITY = "windTunnelLocation";
        public const string WIND_TUNNEL_LOCATION_ADDRESS_CAPABILITY = "windTunnelLocationAddress";
        public const string WIND_TUNNEL_ORIENTATION_CAPABILITY = "windTunnelOrientation";
        public const string WIND_TUNNEL_VNETWORK_CAPABILITY = "windTunnelVNetwork";
        public const string WIND_TUNNEL_BACKGROUND_RUNNING_APPS_CAPABILITY = "windTunnelBackgroundRunningApps";
        //WIND_TUNNEL_REPORT_URL_CAPABILITY is deprecated, use SINGLE_TEST_REPORT_URL_CAPABILITY instead
        public const string WIND_TUNNEL_REPORT_URL_CAPABILITY = "windTunnelReportUrl";
        public const string WIND_TUNNEL_PERSONA_KEY_CAPABILITY = "windTunnelPersonaKey";

        public const string DEVICE_NAME_CAPABILITY = "deviceName";
        public const string DEVICE_PLATFORM_NAME_CAPABILITY = "platformName";
        public const string DEVICE_PLATFORM_VERSION_CAPABILITY = "platformVersion";
        public const string DEVICE_MODEL_CAPABILITY = "model";
        public const string DEVICE_MANUFACTURER_CAPABILITY = "manufacturer";
        public const string DEVICE_NETWORK_CAPABILITY = "network";
        public const string DEVICE_LOCATION_CAPABILITY = "location";
        public const string DEVICE_RESOLUTION_CAPABILITY = "resolution";
        public const string DEVICE_DESCRIPTION_CAPABILITY = "description";

        public const string IMAGE = "image";
        public const string DESCRIPTION = "description";
        public const string NAME = "name";
        public const string PROPERTIES = "properties";
        public const string SETTINGS = "settings";
        public const string DEVICE = "device";

        public const string POINT_OF_INTEREST_DESCRIPTION = "description";
        public const string POINT_OF_INTEREST_STATUS = "status";

        public const string REPORT_TIMER_NAME = "name";
        public const string REPORT_TIMER_RESULT = "result";
        public const string REPORT_TIMER_THRESHOLD = "threshold";
        public const string REPORT_TIMER_DESCRIPTION = "description";

        public const string MOBILE_STATUS_EVENT_COMMAND = "mobile:status:event";
        public const string MOBILE_STATUS_TIMER_COMMAND = "mobile:status:timer";

        public const string GEORGIA = "Georgia";
        public const string ROSS = "Ross";
        public const string PETER = "Peter";
        public const string SAM = "Sam";
        public const string SARA = "Sara";
        public const string EMPTY = "Empty";
        #endregion

        public static void PerfectoCloseConnectionGetReports(this RemoteWebDriverExtended driver)
        {
            // Retrieve the URL of the Single Test Report, can be saved to your execution summary and used to download the report at a later point
            string reportUrl = (string)(driver.Capabilities.GetCapability(SINGLE_TEST_REPORT_URL_CAPABILITY));
            
            driver.Close();
            var parameters = new Dictionary<String, Object>();
            driver.ExecuteScript("mobile:execution:close", parameters);

            // In case you want to download the report or the report attachments, do it here.
            try
            {
                var reportDir = new DirectoryInfo(AppDomain.CurrentDomain.BaseDirectory).Parent.Parent.FullName
                    + "/reports/" + driver.Capabilities.GetCapability("scriptName");
                Directory.CreateDirectory(reportDir);
                Trace.WriteLine("Reports dir here: " + reportDir);

                driver.DownloadReport(DownloadReportTypes.pdf, reportDir + "/report");
                //driver.DownloadAttachment(DownloadAttachmentTypes.video, reportDir + "/video", "flv");
                //driver.DownloadAttachment(DownloadAttachmentTypes.image, reportDir + "/images", "jpg");
            }
            catch (Exception ex)
            {
                Trace.WriteLine(string.Format("Error getting test logs: {0}", ex.Message));
            }

            driver.Quit();

            Debug.WriteLine("WindTunnel Report Link:\n\t" + reportUrl);
            Process.Start(reportUrl);
        }

        #region Public static methods
        /// <summary>
        /// The Wind Tunnel Point of interest (POI) allows mobile teams to pin point real user specific issues around performance, 
        /// functionality and more. By using POI users can get a detailed dashboard with view into the actual error for a specific transaction.
        /// This functions adds a Point of interest to the Wind Tunnel report.
        /// Example: string pointOfInterest = PointOfInterest(driver, "Page load", PointOfInterestStatus.SUCCESS);
        /// </summary>
        /// <param name="driver">RemoteWebDriver entity</param>
        /// <param name="name">Name of point of interest</param>
        /// <param name="status">Defined point of interest status</param>
        /// <returns>The command response contains basic details about the completion status and relevant timers. It may also include additional data that is specific for the command.</returns>
        public static string PointOfInterest(RemoteWebDriver driver, string name, PointOfInterestStatus status)
        {
            var parameters = new Dictionary<string, object>();
            parameters.Add(POINT_OF_INTEREST_DESCRIPTION, name);
            parameters.Add(POINT_OF_INTEREST_STATUS, status.ToString().ToLower());

            return driver.ExecuteScript(MOBILE_STATUS_EVENT_COMMAND, parameters).ToString();
        }

        /// <summary>
        /// Adds a timer report to the test. The measured timer result is reported from the test into the generated Wind Tunnel report.
        /// </summary>
        /// <param name="driver">RemoteWebDriver entity</param>
        /// <param name="result">The calculated timer result duration in milliseconds.Minimum value is 0.</param>
        /// <param name="threshold">The maximum threshold duration allowed for this timer in milliseconds.If a threshold is defined, the success/fail state of the command will be based on whether the reported timer value exceeds the threshold or not. Minimum value is 0.</param>
        /// <param name="description">The description text to display as part of the test and in the Wind Tunnel report.</param>
        /// <param name="name">The timer report name to display as part of the test and in the Wind Tunnel report.</param>
        /// <returns>The command response contains basic details about the completion status and relevant timers. It may also include additional data that is specific for the command.</returns>
        public static string ReportTimer(RemoteWebDriver driver, long result, long threshold, string description, string name)
        {
            var parameters = new Dictionary<string, object>();

            parameters.Add(REPORT_TIMER_RESULT, result);
            parameters.Add(REPORT_TIMER_THRESHOLD, threshold);
            parameters.Add(REPORT_TIMER_DESCRIPTION, description);
            parameters.Add(REPORT_TIMER_NAME, name);

            return driver.ExecuteScript(MOBILE_STATUS_TIMER_COMMAND, parameters).ToString();
        }

        /// <summary>
        /// This method will create a new persona and upload it to the repository.
        /// Example:
        ///  string repositoryKey = uploadWindTunnelPersona(host, user, password, "Pedro", "This is Pedro's profile", "PUBLIC:personas/Perdo.jpg", null,
        ///  "Boston", "landscape", "4G LTE Advanced Good", "Waze,YouTube", "PRIVATE:Personas", personaDevice);
        /// capabilities.SetCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_KEY_CAPABILITY, repositoryKey);
        /// </summary>
        /// <param name="host">The cloud host name</param>
        /// <param name="user">The cloud username</param>
        /// <param name="password">The cloud password</param>
        /// <param name="name">Persona name</param>
        /// <param name="description">Persona description</param>
        /// <param name="image">Persona image as repository item</param>
        /// <param name="location">Location as coordinates with format latitude,longitude</param>
        /// <param name="locationAddress">Location as address</param>
        /// <param name="orientation">Device orientation (landscape or portrait)</param>
        /// <param name="vnetworkProfile">Virtual network profile</param>
        /// <param name="applications">List of application names</param>
        /// <param name="repositoryFolder">Repository key</param>
        /// <param name="device">Persona's device, object created by "CreateDevice()" function</param>
        /// <returns>Repository key for the newly created persona</returns>
        public static string UploadWindTunnelPersona(string host, string user, string password, string name, string description, string image,
                string location, string locationAddress, string orientation, string vnetworkProfile, string applications, string repositoryFolder,
                object device)
        {
            if (repositoryFolder == null)
                throw new Exception("Can't upload persona without repository folder");

            var persona = CreateWindTunnelPersona(name, description, image, location, locationAddress, orientation, vnetworkProfile, applications, device);
            var repositoryKey = string.Format(@"{0}/{1}.json", repositoryFolder, name);
            PerfectoLabUtils.UploadMedia(host, user, password, Encoding.ASCII.GetBytes(persona), repositoryKey);

            return repositoryKey;
        }


        /// <summary>
        /// This method will create a new persona in order to add it to the repository.
        /// Example:
        /// string persona = createWindTunnelPersona("Pedro", "This is Pedro's profile", "PUBLIC:personas/Perdo.jpg", null, "Boston",
        /// "landscape", "4G LTE Advanced Good", "Waze,YouTube", personaDevice);
        /// </summary>
        /// <param name="name">Persona name</param>
        /// <param name="description">Persona description</param>
        /// <param name="image">Persona image as repository item</param>
        /// <param name="location">Location as coordinates with format latitude,longitude</param>
        /// <param name="locationAddress">Location as address</param>
        /// <param name="orientation">Device orientation (landscape or portrait)</param>
        /// <param name="vnetworkProfile">Virtual network profile</param>
        /// <param name="applications">List of application names</param>
        /// <param name="device">Persona's device, object created by "CreateDevice()" function</param>
        /// <returns>Persona in json format</returns>
        public static string CreateWindTunnelPersona(string name, string description, string image, string location, string locationAddress, string orientation,
            string vnetworkProfile, string applications, object device)
        {
            if (name == null)
                throw new Exception("Can't create persona without name");

            //Adding the properties and setting
            var persona = new Dictionary<string, object>();
            var properties = CreateProperties(name, description, image);
            persona.Add(PROPERTIES, properties);
            var settings = CreateSettings(location, locationAddress, orientation, vnetworkProfile, applications);
            persona.Add(SETTINGS, settings);
            persona.Add(DEVICE, device);

            return ConvertToJson(persona);
        }

        /// <summary>
        /// This method will create a json with Wind Tunnel settings
        /// Example: string settings = createWindTunnelSettings(null, "Boston", "landscape", "4G LTE Advanced Good", "Waze,YouTube");
        /// </summary>
        /// <param name="location">Location as coordinates with format latitude,longitude</param>
        /// <param name="locationAddress">Location as address</param>
        /// <param name="orientation">Device orientation (landscape or portrait)</param>
        /// <param name="vnetworkProfile">Virtual network profile</param>
        /// <param name="applications">List of application names</param>
        /// <returns>Settings in json format</returns>
        public static string CreateWindTunnelSettings(string location, string locationAddress, string orientation, string vnetworkProfile,
            string applications)
        {
            var settings = CreateSettings(location, locationAddress, orientation, vnetworkProfile, applications);

            return ConvertToJson(settings);
        }

        /// <summary>
        /// This method will create an object that describes a persona's device
        /// Example: Dictionary<string, object> device = new Dictionary<string, object>();
        ///          device = WindTunnelUtils.CreateDevice(null, "Android", "5.0.2", "Galaxy S6", "Samsung", "AT&T", null, "1440 x 2560", null); 
        /// </summary>
        /// <param name="name"> Device's ID</param>
        /// <param name="platformName">name of OS</param>
        /// <param name="platformVersion">version of OS</param>
        /// <param name="model">Model of device</param>
        /// <param name="manufacturer">Manufactorer of device</param>
        /// <param name="network">Operator</param>
        /// <param name="location">Device's physical location (lab)</param>
        /// <param name="resolution">Device's resolution</param>
        /// <param name="description">Device's description</param>
        /// <returns></returns>
        public static Dictionary<string, object> CreateDevice(string name, string platformName, string platformVersion, string model, string manufacturer,
            string network, string location, string resolution, string description)
        {
            var device = new Dictionary<string, object>();

            if (name != null)
                device.Add(DEVICE_NAME_CAPABILITY, name);
            if (platformName != null)
                device.Add(DEVICE_PLATFORM_NAME_CAPABILITY, platformName);
            if (platformVersion != null)
                device.Add(DEVICE_PLATFORM_VERSION_CAPABILITY, platformVersion);
            if (model != null)
                device.Add(DEVICE_MODEL_CAPABILITY, model);
            if (manufacturer != null)
                device.Add(DEVICE_MANUFACTURER_CAPABILITY, manufacturer);
            if (network != null)
                device.Add(DEVICE_NETWORK_CAPABILITY, network);
            if (location != null)
                device.Add(DEVICE_LOCATION_CAPABILITY, location);
            if (resolution != null)
                device.Add(DEVICE_RESOLUTION_CAPABILITY, resolution);
            if (description != null)
                device.Add(DEVICE_DESCRIPTION_CAPABILITY, description);

            return device;
        }

        #endregion

        #region Private methods
        /// <summary>
        /// Create persona properties
        /// </summary>
        /// <param name="name">Persona name</param>
        /// <param name="description">Persona description</param>
        /// <param name="image">Persona image as repository item</param>
        /// <returns>Persona properties</returns>
        private static Dictionary<string, object> CreateProperties(string name, string description, string image)
        {
            var properties = new Dictionary<string, object>();

            if (name != null)
                properties.Add(NAME, name);

            if (description != null)
                properties.Add(DESCRIPTION, description);

            if (image != null)
                properties.Add(IMAGE, image);

            return properties;
        }

        /// <summary>
        /// Create persona settings
        /// </summary>
        /// <param name="location">Location as coordinates with format latitude,longitude</param>
        /// <param name="locationAddress">Location as address</param>
        /// <param name="orientation">Device orientation (landscape or portrait)</param>
        /// <param name="vnetworkProfile">Virtual network profile</param>
        /// <param name="applications">List of application names</param>
        /// <returns>Persona settings</returns>
        private static Dictionary<string, object> CreateSettings(string location, string locationAddress, string orientation, string vnetworkProfile,
            string applications)
        {
            var settings = new Dictionary<string, object>();

            if (location != null)
                settings.Add(WIND_TUNNEL_LOCATION_CAPABILITY, location);
            if (locationAddress != null)
                settings.Add(WIND_TUNNEL_LOCATION_ADDRESS_CAPABILITY, locationAddress);
            if (orientation != null)
                settings.Add(WIND_TUNNEL_ORIENTATION_CAPABILITY, orientation);
            if (vnetworkProfile != null)
                settings.Add(WIND_TUNNEL_VNETWORK_CAPABILITY, vnetworkProfile);
            if (applications != null)
                settings.Add(WIND_TUNNEL_BACKGROUND_RUNNING_APPS_CAPABILITY, applications);

            return settings;
        }

        /// <summary>
        /// Serialize content to JSON
        /// </summary>
        /// <param name="content"></param>
        /// <returns>Content as JSON</returns>
        private static string ConvertToJson(Dictionary<string, object> content)
        {
            return new JavaScriptSerializer().Serialize(content);
        }
        #endregion
    }


    public enum PointOfInterestStatus
    {
        Success,
        Failure
    }
}