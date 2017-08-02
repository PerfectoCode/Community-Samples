from RemoteWebDriverUtils import RemoteWebDriverUtils
import json

class WindTunnelUtils(object):
    WIND_TUNNEL_LOCATION_CAPABILITY = "windTunnelLocation"
    WIND_TUNNEL_LOCATION_ADDRESS_CAPABILITY = "windTunnelLocationAddress"
    WIND_TUNNEL_ORIENTATION_CAPABILITY = "windTunnelOrientation"
    WIND_TUNNEL_VNETWORK_CAPABILITY = "windTunnelVNetwork"
    WIND_TUNNEL_BACKGROUND_RUNNING_APPS_CAPABILITY = "windTunnelBackgroundRunningApps"
    IMAGE = "image"
    DESCRIPTION = "description"
    NAME = "name"
    PROPERTIES = "properties"
    SETTINGS = "settings"

    # 
    # 	 * Example:
    # 	 * String repositoryKey = uploadWindTunnelPersona(host, user, password, "Pedro", "This is Pedro's profile", "PUBLIC:personas\\Perdo.jpg", null, "Boston", 
    # 	 * "landscape", "4G LTE Advanced Good", "Waze,YouTube", "PRIVATE:Personas");
    # 	 * @param host
    # 	 * @param user
    # 	 * @param password 
    # 	 * @param name persona name
    # 	 * @param description persona description
    # 	 * @param image persona image as repository item
    # 	 * @param location location as coordinates with format latitude,longitude
    # 	 * @param locationAddress location as address
    # 	 * @param orientation device orientation (landscape or portrait)
    # 	 * @param vnetworkProfile virtual network profile
    # 	 * @param applications list of application names
    # 	 * @return persona repository key
    @classmethod
    def uploadWindTunnelPersona(cls, host, user, password, name, description, image, location, locationAddress, orientation, vnetworkProfile, applications, repositoryFolder):
        if repositoryFolder == None:
            raise RuntimeError("Can't upload persona without repository folder")
        persona = cls.createWindTunnelPersona(name, description, image, location, locationAddress, orientation, vnetworkProfile, applications)
        repository_key = repositoryFolder + "/" + name + ".json"
        url = RemoteWebDriverUtils.get_request_url(host, user, password, repository_key, "upload", True)
        RemoteWebDriverUtils.send_request(url, persona.encode('utf-8'))
        return repository_key

    # 
    # 	 * Example:
    # 	 * String persona = createWindTunnelPersona("Pedro", "This is Pedro's profile", "PUBLIC:personas\\Perdo.jpg", null, "Boston", 
    # 	 * "landscape", "4G LTE Advanced Good", "Waze,YouTube");
    # 	 * @param name persona name
    # 	 * @param description persona description
    # 	 * @param image persona image as repository item
    # 	 * @param location location as coordinates with format latitude,longitude
    # 	 * @param locationAddress location as address
    # 	 * @param orientation device orientation (landscape or portrait)
    # 	 * @param vnetworkProfile virtual network profile
    # 	 * @param applications list of application names
    # 	 * @return persona in json format
    @classmethod
    def createWindTunnelPersona(cls, name, description, image, location, locationAddress, orientation, vnetworkProfile, applications):
        if name == None:
            raise RuntimeError("Can't create persona without name")
        persona = {}
        properties = cls.createProperties(name, description, image)
        persona.__setitem__(cls.PROPERTIES, properties)
        settings = cls.createSettings(location, locationAddress, orientation, vnetworkProfile, applications)
        persona.__setitem__(cls.SETTINGS, settings)
        json = cls.convertToJson(persona)
        return json

    # 	 * Example: String settings = createWindTunnelSettings(null, "Boston", "landscape", "4G LTE Advanced Good", "Waze,YouTube");
    # 	 * @param location location as coordinates with format latitude,longitude
    # 	 * @param locationAddress location as address
    # 	 * @param orientation device orientation (landscape or portrait)
    # 	 * @param vnetworkProfile virtual network profile
    # 	 * @param applications list of application names
    # 	 * @return settings in json format
    # 	 * @throws JsonProcessingException
    @classmethod
    def createWindTunnelSettings(cls, location, locationAddress, orientation, vnetworkProfile, applications):
        settings = cls.createSettings(location, locationAddress, orientation, vnetworkProfile, applications)
        json = cls.convertToJson(settings)
        return json

    @classmethod
    def createProperties(cls, name, description, image):
        properties = {}
        if name != None:
            properties.__setitem__(cls.NAME, name)
        if description != None:
            properties.__setitem__(cls.DESCRIPTION, description)
        if image != None:
            properties.__setitem__(cls.IMAGE, image)
        return properties

    @classmethod
    def createSettings(cls, location, locationAddress, orientation, vnetworkProfile, applications):
        settings = {}
        if location != None:
            settings.__setitem__(cls.WIND_TUNNEL_LOCATION_CAPABILITY, location)
        if locationAddress != None:
            settings.__setitem__(cls.WIND_TUNNEL_LOCATION_ADDRESS_CAPABILITY, locationAddress)
        if orientation != None:
            settings.__setitem__(cls.WIND_TUNNEL_ORIENTATION_CAPABILITY, orientation)
        if vnetworkProfile != None:
            settings.__setitem__(cls.WIND_TUNNEL_VNETWORK_CAPABILITY, vnetworkProfile)
        if applications != None:
            settings.__setitem__(cls.WIND_TUNNEL_BACKGROUND_RUNNING_APPS_CAPABILITY, applications)
        return settings

    @classmethod
    def convertToJson(cls, content):
        return json.dumps(content)

