import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public final class WindTunnelUtils {

    public static final String PROPERTIES = "properties";
    public static final String SETTINGS = "settings";
    public static final String DEVICE = "device";

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";

    public static final String SINGLE_TEST_REPORT_URL_CAPABILITY = "singleTestReportUrl";

    public static final String WIND_TUNNEL_PERSONA_CAPABILITY = "windTunnelPersona";
    public static final String WIND_TUNNEL_LOCATION_CAPABILITY = "windTunnelLocation";
    public static final String WIND_TUNNEL_LOCATION_ADDRESS_CAPABILITY = "windTunnelLocationAddress";
    public static final String WIND_TUNNEL_ORIENTATION_CAPABILITY = "windTunnelOrientation";
    public static final String WIND_TUNNEL_VNETWORK_CAPABILITY = "windTunnelVNetwork";
    public static final String WIND_TUNNEL_BACKGROUND_RUNNING_APPS_CAPABILITY = "windTunnelBackgroundRunningApps";
    //WIND_TUNNEL_REPORT_URL_CAPABILITY is deprecated, use SINGLE_TEST_REPORT_URL_CAPABILITY instead
    public static final String WIND_TUNNEL_REPORT_URL_CAPABILITY = "windTunnelReportUrl";
    public static final String WIND_TUNNEL_PERSONA_KEY_CAPABILITY = "windTunnelPersonaKey";

    public static final String DEVICE_NAME_CAPABILITY = "deviceName";
    public static final String DEVICE_PLATFORM_NAME_CAPABILITY = "platformName";
    public static final String DEVICE_PLATFORM_VERSION_CAPABILITY = "platformVersion";
    public static final String DEVICE_MODEL_CAPABILITY = "model";
    public static final String DEVICE_MANUFACTURER_CAPABILITY = "manufacturer";
    public static final String DEVICE_NETWORK_CAPABILITY = "network";
    public static final String DEVICE_LOCATION_CAPABILITY = "location";
    public static final String DEVICE_RESOLUTION_CAPABILITY = "resolution";
    public static final String DEVICE_DESCRIPTION_CAPABILITY = "description";

    public static final String MOBILE_STATUS_EVENT_COMMAND = "mobile:status:event";
    public static final String MOBILE_STATUS_TIMER_COMMAND = "mobile:status:timer";

    public static final String POI_DESCRIPTION = "description";
    public static final String POI_STATUS = "status";

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    public static final String REPORT_TIMER_RESULT = "result";
    public static final String REPORT_TIMER_THRESHOLD = "threshold";
    public static final String REPORT_TIMER_DESCRIPTION = "description";
    public static final String REPORT_TIMER_NAME = "name";

    public static final String GEORGIA = "Georgia";
    public static final String ROSS = "Ross";
    public static final String PETER = "Peter";
    public static final String SAM = "Sam";
    public static final String SARA = "Sara";
    public static final String EMPTY = "Empty";

    /**
     * Adds a point of interest to the Wind Tunnel report.
     * Example:
     * pointOfInterest(driver, "Login Successful", WindTunnelUtils.SUCCESS);
     */
    public static String pointOfInterest(RemoteWebDriver driver, String name, String status) {
        Map<String, Object> params = new HashMap<String, Object>(4);
        params.put(POI_DESCRIPTION, name);
        params.put(POI_STATUS, status);
        String responseStatus = (String) driver.executeScript(MOBILE_STATUS_EVENT_COMMAND, params);
        return responseStatus;
    }

    /**
     * Adds a timer report to the Wind Tunnel report.
     * Example:
     * reportTimer(driver, loginScreenTimer, 5000, "Timer for login screen", "");
     */
    public static String reportTimer(RemoteWebDriver driver, long result, long threshold,
                                     String description, String name) {
        Map<String, Object> params = new HashMap<String, Object>(7);
        params.put(REPORT_TIMER_RESULT, result);
        params.put(REPORT_TIMER_THRESHOLD, threshold);
        params.put(REPORT_TIMER_DESCRIPTION, description);
        params.put(REPORT_TIMER_NAME, name);
        String status = (String) driver.executeScript(MOBILE_STATUS_TIMER_COMMAND, params);
        return status;
    }

    /**
     * Example:
     * PersonaProperties properties = new PersonaProperties("Pedro", "This is Pedro's profile", "PUBLIC:personas/Perdo.jpg");
     * PersonaDevice device = new PersonaDevice();
     * device.setModel("iPhone-5S");
     * PersonaSettings settings = new PersonaSettings(null, "Boston", "landscape", "4G LTE Advanced Good", "Waze,YouTube");
     * String persona = createWindTunnelPersona(properties, device, settings);
     */
    public static String createWindTunnelPersona(PersonaProperties properties, PersonaDevice device, PersonaSettings settings) throws JsonProcessingException {
        Map<String, Object> persona = new HashMap<>();
        Map<String, Object> propertiesMap = createPropertiesMap(properties);
        persona.put(PROPERTIES, propertiesMap);
        Map<String, Object> deviceMap = createDevice(device);
        persona.put(DEVICE, deviceMap);
        Map<String, Object> settingsMap = createSettings(settings);
        persona.put(SETTINGS, settingsMap);
        String json = convertToJson(persona);
        return json;
    }

    /**
     * Example:
     * PersonaProperties properties = new PersonaProperties("Pedro", "This is Pedro's profile", "PUBLIC:personas/Perdo.jpg");
     * PersonaDevice device = new PersonaDevice();
     * device.setModel("iPhone-5S");
     * PersonaSettings settings = new PersonaSettings(null, "Boston", "landscape", "4G LTE Advanced Good", "Waze,YouTube");
     * String repositoryKey = uploadWindTunnelPersona(myHost, myUser, myPassword, "PRIVATE:Personas", properties, device, settings);
     * capabilities.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_KEY_CAPABILITY, repositoryKey);
     */
    public static String uploadWindTunnelPersona(String host, String user, String password, String repositoryFolder,
                                                 PersonaProperties properties, PersonaDevice device, PersonaSettings settings) throws UnsupportedEncodingException, MalformedURLException, IOException {
        if (repositoryFolder == null) {
            throw new RuntimeException("Can't upload persona without repository folder");
        }
        String persona = createWindTunnelPersona(properties, device, settings);
        String repositoryKey = repositoryFolder + "/" + properties.getName() + ".json";
        PerfectoLabUtils.uploadMedia(host, user, password, persona.getBytes(), repositoryKey);
        return repositoryKey;
    }

    public static class PersonaProperties {
        private String _name;
        private String _description;
        private String _image;

        public PersonaProperties(String name, String description, String image) {
            _name = name;
            _description = description;
            _image = image;
        }

        public String getName() {
            return _name;
        }

        public String getDescription() {
            return _description;
        }

        public String getImage() {
            return _image;
        }
    }

    public static class PersonaSettings {
        private String _location;
        private String _locationAddress;
        private String _orientation;
        private String _vnetworkProfile;
        private String _applications;

        public PersonaSettings(String location, String locationAddress, String orientation, String vnetworkProfile, String applications) {
            _location = location;
            _locationAddress = locationAddress;
            _orientation = orientation;
            _vnetworkProfile = vnetworkProfile;
            _applications = applications;
        }

        public String getLocation() {
            return _location;
        }

        public String getLocationAddress() {
            return _locationAddress;
        }

        public String getOrientation() {
            return _orientation;
        }

        public String getVnetworkProfile() {
            return _vnetworkProfile;
        }

        public String getApplications() {
            return _applications;
        }
    }

    public static class PersonaDevice {
        private String _deviceName;
        private String _platformName;
        private String _platformVersion;
        private String _manufacturer;
        private String _model;
        private String _resolution;
        private String _network;
        private String _location;
        private String _description;

        public PersonaDevice() {
        }

        public PersonaDevice(String deviceName, String platformName, String platformVersion, String manufacturer, String model,
                             String resolution, String network, String location, String description) {
            _deviceName = deviceName;
            _platformName = platformName;
            _platformVersion = platformVersion;
            _manufacturer = manufacturer;
            _model = model;
            _resolution = resolution;
            _network = network;
            _location = location;
            _description = description;
        }

        public String getDeviceName() {
            return _deviceName;
        }

        public void setDeviceName(String deviceName) {
            _deviceName = deviceName;
        }

        public String getPlatformName() {
            return _platformName;
        }

        public void setPlatformName(String platformName) {
            _platformName = platformName;
        }

        public String getPlatformVersion() {
            return _platformVersion;
        }

        public void setPlatformVersion(String platformVersion) {
            _platformVersion = platformVersion;
        }

        public String getManufacturer() {
            return _manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            _manufacturer = manufacturer;
        }

        public String getModel() {
            return _model;
        }

        public void setModel(String model) {
            _model = model;
        }

        public String getResolution() {
            return _resolution;
        }

        public void setResolution(String resolution) {
            _resolution = resolution;
        }

        public String getNetwork() {
            return _network;
        }


        public void setNetwork(String network) {
            _network = network;
        }

        public String getLocation() {
            return _location;
        }

        public void setLocation(String location) {
            _location = location;
        }

        public String getDescription() {
            return _description;
        }


        public void setDescription(String description) {
            _description = description;
        }
    }

    private static String convertToJson(Map<String, Object> content) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(content);
        return json;
    }

    private static Map<String, Object> createDevice(PersonaDevice device) {
        Map<String, Object> deviceMap = new HashMap<>();
        if (device != null) {
            if (device.getDeviceName() != null) {
                deviceMap.put(DEVICE_NAME_CAPABILITY, device.getDeviceName());
            }
            if (device.getPlatformName() != null) {
                deviceMap.put(DEVICE_PLATFORM_NAME_CAPABILITY, device.getPlatformName());
            }
            if (device.getPlatformVersion() != null) {
                deviceMap.put(DEVICE_PLATFORM_VERSION_CAPABILITY, device.getPlatformVersion());
            }
            if (device.getModel() != null) {
                deviceMap.put(DEVICE_MODEL_CAPABILITY, device.getModel());
            }
            if (device.getManufacturer() != null) {
                deviceMap.put(DEVICE_MANUFACTURER_CAPABILITY, device.getManufacturer());
            }
            if (device.getNetwork() != null) {
                deviceMap.put(DEVICE_NETWORK_CAPABILITY, device.getNetwork());
            }
            if (device.getLocation() != null) {
                deviceMap.put(DEVICE_LOCATION_CAPABILITY, device.getLocation());
            }
            if (device.getResolution() != null) {
                deviceMap.put(DEVICE_RESOLUTION_CAPABILITY, device.getResolution());
            }
            if (device.getDescription() != null) {
                deviceMap.put(DEVICE_DESCRIPTION_CAPABILITY, device.getDescription());
            }

        }
        return deviceMap;
    }

    private static Map<String, Object> createSettings(PersonaSettings settings) {
        Map<String, Object> settingsMap = new HashMap<>();
        if (settings != null) {
            if (settings.getLocation() != null) {
                settingsMap.put(WIND_TUNNEL_LOCATION_CAPABILITY, settings.getLocation());
            }
            if (settings.getLocationAddress() != null) {
                settingsMap.put(WIND_TUNNEL_LOCATION_ADDRESS_CAPABILITY, settings.getLocationAddress());
            }
            if (settings.getOrientation() != null) {
                settingsMap.put(WIND_TUNNEL_ORIENTATION_CAPABILITY, settings.getOrientation());
            }
            if (settings.getVnetworkProfile() != null) {
                settingsMap.put(WIND_TUNNEL_VNETWORK_CAPABILITY, settings.getVnetworkProfile());
            }
            if (settings.getApplications() != null) {
                settingsMap.put(WIND_TUNNEL_BACKGROUND_RUNNING_APPS_CAPABILITY, settings.getApplications());
            }
        }
        return settingsMap;
    }

    private static Map<String, Object> createPropertiesMap(PersonaProperties properties) {
        if (properties == null) {
            throw new RuntimeException("Can't create persona without properties");
        }
        if (properties.getName() == null) {
            throw new RuntimeException("Can't create persona without name");
        }

        Map<String, Object> propertiesMap = new HashMap<>();
        if (properties.getName() != null) {
            propertiesMap.put(NAME, properties.getName());
        }
        if (properties.getDescription() != null) {
            propertiesMap.put(DESCRIPTION, properties.getDescription());
        }
        if (properties.getImage() != null) {
            propertiesMap.put(IMAGE, properties.getImage());
        }
        return propertiesMap;
    }

    private WindTunnelUtils() {
    }
}
