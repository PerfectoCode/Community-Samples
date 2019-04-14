var deviceUtilities = exports.deviceUtilities = function () {

/**
	 * Retrieves the Perfecto Lab system log relevant to the device. Device log
	 * returned as String This number of lines will be retrieved from the end of
	 * the log
	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @param log_lines
	 *            : This number of lines will be retrieved from the end of the
	 *            log
	 *
	 * @return String
	 * @see <b> Examples </b> for calling method
	 * @see 1. getDeviceLogs(9);
	 * @see : For more information refer <a href=
	 *      http://developers.perfectomobile.com/pages/viewpage.action?pageId=13893786>getDeviceLogs</a>
	 *
	 */

    this.getDeviceLogs = function (log_lines) {
        var params = {
            'tail':log_lines
        };
        return browser.driver.executeScript('mobile:device:log', params);
    };

    
    /**
    	 * Notifies the system to start collecting the log information.
         * @author  Praveen H
         * @version  1.0
          *
          * @see <b> Examples </b> for calling method
          * @see 1. startDeviceLogs();
         * @see : For more information refer <a href =http://developers.perfectomobile.com/display/PD/Start+debug+logs>startDeviceLogs</a>
         *
          */
    this.startDeviceLogs = function () {
        var params = {};
        return browser.driver.executeScript('mobile:logs:start', params);
    };
    
/**
	 * Notifies the system to stop collecting the log entries.
     * @author  Praveen H
     * @version  1.0
      *
      * @see <b> Examples </b> for calling method
      * @see 1. stopDeviceLogs();
     * @see  For more information refer <a href = https://developers.perfectomobile.com/display/PD/Stop+debug+logs>stopDeviceLogs</a>
     *
      */
    this.stopDeviceLogs = function () {
        var params = {

        };
        return browser.driver.executeScript('mobile:logs:stop', params);
    };

    /**
    	 * Retrieves the device's configured time zone. Return value is string that
    	 * complies to tz database format. Retrieves the device time zone. Supported
    	 * OS: Android
    	 *
    	 * @author Praveen H
    	 * @version 1.0
    	 * @return String
    	 * @see <b> Examples </b> for calling method
    	 * @see 1. getDeviceTimeZone();
    	 * @see: For more information refer <a href =
    	 *       https://developers.perfectomobile.com/display/PD/Get+device+time+zone>getDeviceTimeZone</a>
    	 *
    	 */
    this.getDeviceTimeZone = async function () {
        var params = { };
        return await browser.driver.executeScript('mobile:timezone:get', params);
    };

    /**
    	 * Resets the device's time zone to the default timezone configured in the device.
    	 *
    	 * Resets the device time zone.
    	 *
         * @author  Praveen H
         * @version  1.0
         *
         * @return  String
          * @see <b> Examples </b> for calling method
          * @see 1. resetDeviceTimeZone();
         * @see : For more information refer <a href = https://developers.perfectomobile.com/display/PD/Reset+device+time+zone>resetDeviceTimeZone</a>
         *
          */
    this.resetDeviceTimeZone = async function () {
        var params = { };
        return await browser.driver.executeScript('mobile:timezone:reset', params);
    };

    /**
    	 * Sets the device time zone.
    	 * Before changing the device time zone verify that the "Automatic time zone" setting is turned off on the device.
    	 * The time zone to be set. Format follows tz database convention, Example: Etc/GMT-8

    	 *
         * @author  Praveen H
         * @version  1.0
         *
          *
          * @param Timezone
          *           : The time zone to be set. Format follows tz database convention. Example: Etc/GMT-8
          *
         * @return  String
          * @see <b> Examples </b> for calling method
          * @see 1. setTimeZone(America/Los_Angeles);
         * @see : For more information refer <a href = https://developers.perfectomobile.com/display/PD/Set+device+time+zone>setTimeZone</a>
         *
          */
    
    this.setTimeZone = function (Timezone) {
        var params = {
            'timezone':Timezone
        };
        return browser.driver.executeScript('mobile:timezone:set', params);
    };
    
/**
	 * To collect application-specific vital measurements from mobile devices
	 * Vitals will be separately collected for all of the selected services.
	 *
     * @author  Praveen H
     * @version  1.0
     *

      * @param  ApplicationName
      *              : Application Name
      *  @param interval
      *             : The data collection frequency, in seconds.
      *
     * @return  String
      * @see <b> Examples </b> for calling method
      * @see 1. startDeviceVitalsByApplicationName("YouTube", 10);
     * @see : For more information refer <a href = https://developers.perfectomobile.com/pages/viewpage.action?pageId=19170124>startDeviceVitalsByApplicationName</a>
     *
      */
    this.startDeviceVitalsByApplicationName = function (ApplicationName,interval) {
        var params = {
            'sources':ApplicationName ,
            'interval':interval ,

        };
       // console.log(interval);
        return browser.driver.executeScript('mobile:monitor:start', params);
    };	
    
    /**
    	 * To collect application-specific vital measurements from mobile devices
    	 * Vitals will be separately collected for all of the selected services.
    	 *
         * @author  Praveen H
         * @version  1.0
         *

          * @param  ApplicationName
          *              : Application identifier
          *  @param interval
          *             : The data collection frequency, in seconds.
          *
         * @return  String
          * @see <b> Examples </b> for calling method
          * @see 1. startDeviceVitalsByApplicationName("com.google.Youtube", 10);
         * @see : For more information refer <a href = https://developers.perfectomobile.com/pages/viewpage.action?pageId=19170124>startDeviceVitalsByApplicationName</a>
         *
          */
    this.startDeviceVitalsByApplicationIdentifier = function (identifier,interval) {
        var params = {
            'sources':identifier ,
            'interval':interval ,
        };
        return browser.driver.executeScript('mobile:monitor:start', params);
    };  	

    /**
    	 * Begins collecting Vitals, until the Vitals stop function is invoked or at
    	 * script end. The selected performance data vitals are generated by the
    	 * device and collected in real time. This information helps to determine
    	 * how efficient an application is at using resources, as well as measuring
    	 * the general device performance.
    	 *
    	 * @author Praveen H
    	 * @version 1.0

    	 * @param interval
    	 *            : The data collection frequency, in seconds.
    	 *
    	 * @return String
    	 * @see <b> Examples </b> for calling method
    	 * @see 1. startDeviceVitals(10);
    	 *
    	 * @see : For more information refer <a href =
    	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=19170124>startDeviceVitals</a>
    	 *
    	 */
    this.startDeviceVitals = function (interval) {
        var params = {
            'sources':"Device" ,
            'interval':interval ,
        };
        return browser.driver.executeScript('mobile:monitor:start', params);
    };

    /**
    	 * Stops collecting Vitals, and saves them to the repository in a CSV file format.
    	 * If the script contains several start/stop pairs for the same device, there will be one CSV file for the device, as long as the device remains open.
    	 * If you open/close the device several times in the script, there will be several CSV files. In other words, for every closure of the device, there will be a CSV file.
    	 *
    	 *
    	 * @author Praveen H
    	 * @version 1.0
    	 *
    	 * @return None
    	 * @see <b> Examples </b> for calling method
    	 * @see 1. stopDeviceVitals();
    	 *
    	 * @see : For more information refer <a href=
    	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=19170127>stopDeviceVitals</a>
    	 *
    	 */
    
    this.stopDeviceVitals = function () {
        var params = {};
        return browser.driver.executeScript('mobile:monitor:stop', params);
    };


/**
	 * Copies a file from the repository to the device file system.
	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @param handsetfile
	 *            : The device file path, including root and file name, where to
	 *            upload the file. The root can be phone or card Example -
	 *            phone:/images/myImage.jpg
	 * @param repositoryfile
	 *            : The full repository path, including directory and file name,
	 *            where to save the file.
	 * @return None
	 * @see <b> Examples </b> for calling method
	 * @see 1. putFile(phone:/images/homeBtn.jpg,
	 *      PRIVATE:/images/homeBtn.jpg);
	 * @see : For more information refer <a href =
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14878114>putFile</a>
	 *
	 */
  this.putFile = function (handsetfile,repositoryfile) {
    var params = {
        'handsetFile':handsetfile ,
        'repositoryFile':repositoryfile ,
    };
    return browser.driver.executeScript('mobile:media:put', params);
};

/**
	 * Copies a file from the device into the repository.
	 * The device file path, including root and file name, where to upload the file.
	 * The root can be phone or card Example - phone:/images/myImage.jpg
	 * repository file- The full repository path, including directory and file
	 * name, where to save the file.
	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @param handsetfile
	 *            : The device file path, including root and file name, where the file is located.
	 *              The root can be phone or card.
	 *              Example - phone:/images/myImage.jpg

	 * @param repositoryfile
	 *            : The full repository path, including directory and file name, where to save the file.
	 *              Example - PRIVATE:dir1/dir2/name.jpg
	 * @return None
	 * @see <b> Examples </b> for calling method
	 * @see 1. getFile(phone:/images/homeBtn.jpg,PRIVATE:/images/homeBtn.jpg);
	 *
	 * @see : For more information refer <a href =
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14878112>getFile</a>
	 *
	 */

this.getFile = function (handsetfile,repositoryfile) {
    var params = {
        'handsetFile':handsetfile ,
        'repositoryFile':repositoryfile ,
    };
    return browser.driver.executeScript('mobile:media:get', params);
};
/**
	 * Deletes single or multiple files from the device handsetFile - The device
	 * file path, including root and file name, where to upload the file. The root can be phone or card

	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @param handsetfile
	 *            : The device file path, including root and file name, where the file is located.
	 *              The root can be phone or card.
	 *              Example - phone:/images/myImage.jpg
	 * @return None
	 * @see <b> Examples </b> for calling method
	 * @see 1. deleteFile(phone:/images/homeBtn.jpg);
	 *
	 * @see : For more information refer <a href=
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14878108>deleteFile</a>
	 *
	 */

this.deleteFile = function (handsetfile) {
    var params = {
        'handsetFile':handsetfile
    };
    return browser.driver.executeScript('mobile:media:delete', params);
};



/**
	 * Retrieves the internal IP address of the device. Verify that device is
	 * connected to WiFi.
	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @return Returns the internal IP address of device in Lab WiFi.
	 * @see <b> Examples </b> for calling method
	 * @see 1. getWifiAddress();
	 * @see : For more information refer <a href =
	 *      https://developers.perfectomobile.com/display/PD/Get+WiFi+IP+address>getWifiAddress</a>
	 *
	 */
this.getWifiAddress = async function () {
    var params = { };
    return await browser.driver.executeScript('mobile:ipAddress:get', params);
};



	/**
	 * Locks the device screen for a set number of seconds.
	 * check instantly how your app behaves when the screen is locked.
	 *
	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @param duration
	 *            : Time, in seconds, to lock device (default: 10)
	 *
	 * @return String OK - if successful
	 * @see <b> Examples </b> for calling method
	 * @see 1. lockScreen(10);
	 * @see : For more information refer <a href =
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=13893790>lockScreen</a>
	 *
	 */
this.lockScreen = function (duration) {
    var params = {
        'timeout':duration
    };
    return browser.driver.executeScript('mobile:screen:lock', params);
};


/**
	 * Reboots the device and returns it to the unlocked state. Performed by
	 * software reset for Android and iOS devices.
	 *
	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @return None
	 *
	 * @see <b> Examples </b> for calling method
	 * @see 1. rebootDevice();
	 * @see : For more information refer <a href =
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=13893780>rebootDevice</a>
	 *
	 */

this.rebootDevice = function () {
    var params = {};
    return browser.driver.executeScript('mobile:handset:reboot', params);
};

  /**
	 * Recovers a connected device that is unresponsive.
	 * For example, a device with an interrupted video, black screen or touch failure.
	 * The device is  disconnected and reconnected to the Lab, and returned unlocked, it is not rebooted
	 * Recovery will take a few minutes to complete.
	 *
	 *
	 * @author Praveen H
	 * @version 1.0
	 * @return None
	 *
	 * @see <b> Examples </b> for calling method
	 * @see 1. recoverDevice();
	 * @see : For more information refer <a href =
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=13893782>recoverDevice</a>
	 *
	 */
this.recoverDevice = function () {
    var params = { };
    return browser.driver.executeScript('mobile:handset:recover', params);
};

/**
	 * Retrieves the specified device property and inserts its value into a defined variable. Use the Property parameter to specify the device property to retrieve.
	 * Device property to retrieve. Only one property is retrieved in a single call.
	 * possible values- manufacturer | model | phoneNumber | deviceId | resolution |
      * resolutionWidth | resolutionHeight | os | osVersion | firmware | location | network | distributer | language | imsi | nativeImei | wifiMacAddress |
     * cradleId | status | inUse | description | position | method | rotation | locked | roles |
     *  currentActivity |currentPackage | all | hasAudio | automationInfrastructure
     *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @param deviceProperty
	 *            : Device property to retrieve. Only one property is retrieved in a single call.
	 *
	 * @return String value of the retrieved property
	 *
	 * @see <b> Examples </b> for calling method
	 * @see 1. deviceInfo("osversion");
	 * @see : For more information refer <a href =
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=13893798>deviceInfo</a>
	 *
	 */
this.deviceInfo = async function (deviceProperty) {
    var params = {
        'property':deviceProperty
    };
    return await browser.driver.executeScript('mobile:device:info', params);
};

  /**
     * Rotates the device to either landscape or portrait.
     * To rotate the device orientation, set method to Device. This will affect the screen orientation as well.
     * To rotate the device view, without manipulating the device screen, set method to be View.
     *
	 * @author Praveen H
	 * @version 1.0
	 *
	 *
	 * @param deviceView
	 *            : Rotate the device orientation (landscape/portrait)
	 *
	 * @return None
	 *
	 * @see <b> Examples </b> for calling method
	 * @see 1. deviceRotate("landscape");
	 * @see : For more information refer <a href =
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=13893788>deviceRotate</a>
	 *
	 */
this.deviceRotate = function (deviceView) {
    var params = {
        'state':deviceView
    };
    return browser.driver.executeScript('mobile:device:rotate', params);
};


  /**
     * For iOS and Android devices, the device is unlocked and returned to its default rotate orientation
      *For example, iPhone devices are returned to portrait mode and iPad devices to landscape mode.
      *Use this function at the beginning of a script, to ensure a known starting point for the user.
      *
	 * @author Praveen H
	 * @version 1.0
	 *
	 *
	 * @return None
	 *
	 * @see <b> Examples </b> for calling method
	 * @see 1. deviceReady();
	 * @see : For more information refer <a href =
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=8323119>deviceReady</a>
	 *
	 */
this.deviceReady = function () {
    var params = {};
    return browser.driver.executeScript('mobile:handset:ready', params);
};

  /**
    *
    * Opens the notifications shade.
    * Test for scenarios which use notifications from internal and external apps - how does your app behave when a weather notification comes in?
    *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @return None
	 *
	 * @see <b> Examples </b> for calling method
	 * @see 1. openNotification();
	 * @see : For more information refer <a href =
	 *      http://developers.perfectomobile.com/pages/viewpage.action?pageId=14877463>openNotification</a>
	 *
	 */


this.openNotification = function () {
    var params = { };
    return browser.driver.executeScript('mobile:notifications:open', params);
};

/**
	 * Releases one or more devices from the current script execution. Releases
	 * the device previously allocated to the current session.
	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 *
	 * @return None
	 *
	 * @see <b> Examples </b> for calling method
	 * @see 1. closeDevice();
	 * @see : For more information refer <a href =
	 *      https://developers.perfectomobile.com/display/PD/Close+device>closeDevice</a>
	 *
	 */

this.closeDevice = function () {
   var params = {};
return browser.driver.executeScript('mobile:device:close', params);
};

	/**
	 * Command retrieves an image from the repository.
	 * Perfecto system supplies this image to the specified application when application tries to retrieve a camera image.
	 * The application must have been "camera instrumented" at time of installation on device.
	 * Image injection continues for this application until activating the Stop Image Injection function.
	 *
     * @author  Praveen H
     * @version  1.0
     *
      * @param  application_identifier
      *              : Identifier (BundleID for iOS or PackageName for Android) of the application.
      * @param imagePath
      *            : The full repository path, including directory and file name, where to locate the image.
      *            : Example - PUBLIC:dir1/dir2/name.png
      *
      *
      *
      * @return  None
      * @see <b> Examples </b> for calling method
      * @see 1. startImageInjectionByIdentifier("Victor.RealTimeFilter", "PUBLIC:dir1/dir2/name.png");
     * @see : For more information refer <a href = https://developers.perfectomobile.com/pages/viewpage.action?pageId=13893792>ImageInjection</a>
     *
      */
this.startImageInjectionByIdentifier = function (application_identifier,imagePath) {
    var params = {
        'repositoryFile':imagePath ,
        'identifier':application_identifier 
    };
    return browser.driver.executeScript('mobile:image.injection:start', params);
};

/**
	 * Command retrieves an image from the repository.
	 * Perfecto system supplies this image to the specified application when application tries to retrieve a camera image.
	 * The application must have been "camera instrumented" at time of installation on device.
	 * Image injection continues for this application until activating the Stop Image Injection function.
	 *
     * @author  Praveen H
     * @version  1.0
     *
      * @param  application_Name
      *              : Display name of the application
      * @param imagePath
      *            : The full repository path, including directory and file name, where to locate the image.
      *            : Example - PUBLIC:dir1/dir2/name.png
      *
      *
      *
      * @return  None
      * @see <b> Examples </b> for calling method
      * @see 1. startImageInjectionByIdentifier("Victor", "PUBLIC:dir1/dir2/name.png");
     * @see : For more information refer <a href = https://developers.perfectomobile.com/pages/viewpage.action?pageId=13893792>ImageInjection</a>
     *
      */
this.startImageInjectionByAppName = function (application_Name,imagePath) {
    var params = {
        'repositoryFile':imagePath ,
        'name':application_Name ,
    };
    return browser.driver.executeScript('mobile:image.injection:start', params);
};


/**
	 *
	 *Command stops Perfecto system from supplying image to application when application tries to retrieve a camera image.
	 *Images are supplied by the device camera after invoking the command.
	 *Used in conjunction with Start Image Injection.
	 *
     * @author  Praveen H
     * @version  1.0
     *
      *
      *
      * @return  None
      * @see <b> Examples </b> for calling method
      * @see 1. stopImageInjection();
     * @see : For more information refer <a href = https://developers.perfectomobile.com/pages/viewpage.action?pageId=13893794>stopImageInjection</a>
     *
      */
this.stopImageInjection = function () {
    var params = { };
    return browser.driver.executeScript('mobile:image.injection:stop', params);
};

	/**
	 * Sets the device location. This enables testing a location-aware app that
	 * uses Location Services, without moving the device from place to place to
	 * generate location data. The location is supplied as either: Address - may
	 * be a full street address - street, city, state, country or a partial
	 * address. Latitude, Longitude pair
	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @param address
	 *            : The address location to set. Format: Google Geocoding.
	 *            Example: 1600 Amphitheatre Parkway, Mountain View, CA
	 *
	 * @return None
	 * @see <b> Examples </b> for calling method
	 * @see 1.
	 *      setDeviceLocationByAddress("4145 Bushnell, University Heights, OH"
	 *      );
	 * @see : For more information refer <a href =
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=19169309>setDeviceLocation</a>
	 *
	 */
this.setDeviceLocationByAddress = function (address) {
    var params = {
        'address':address
    };
    return browser.driver.executeScript('mobile:location:set', params);
};

/**
	 * Sets the device location. This enables testing a location-aware app that
	 * uses Location Services, without moving the device from place to place to
	 * generate location data. The location is supplied as either: Address - may
	 * be a full street address - street, city, state, country or a partial
	 * address. Latitude, Longitude pair
	 *
	 * @author Praveen H
	 * @version 1.0
	 * @param coordinates
	 *            : The latitude and longitude coordinate of the device location to set.
	 *              Example: 43.642659,-79.387050
	 *
	 * @return None
	 * @see <b> Examples </b> for calling method
	 * @see 1.
	 *      setDeviceLocationByCordinates("43.642659,-79.387050"
	 *      );
	 * @see : For more information refer <a href =
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=19169309>setDeviceLocation</a>
	 *
	 */

this.setDeviceLocationByCordinates = function (coordinates) {
    var params = {
        'address':coordinates
    };
    return browser.driver.executeScript('mobile:location:set', params);
};

/**
	 * Retrieves the current location of the device, The location is returned as
	 * a Latitude, Longitude pair.
	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @return Object with the Latitude and Longitude values. Cast this as a
	 *         Location object.
	 * @see <b> Examples </b> for calling method
	 * @see 1. getDeviceLocation();
	 * @see : For more information refer <a href =
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=19169303>getDeviceLocation</a>
	 *
	 */

this.getDeviceLocation = async function () {
    var params = {};
    return await browser.driver.executeScript('mobile:location:get', params);
};

/**
	 * Retrieves the network settings and can insert its value into a defined
	 * variable. Use the Property parameter to specify the network setting to
	 * retrieve.(WiFi,Data,Airplane mode)
	 *
	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @param property
	 *            : The network setting property to retrieve: WiFi Data Airplane
	 *            mode
	 *
	 * @return String : Indicates if the requested network is "true" (on) or "false"
	 *         (off)
	 *
	 * @see <b> Examples </b> for calling method
	 * @see 1. getNetworkSettings("wifi");
	 * @see : For more information refer <a href=
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14876783>getNetworkSettings</a>
	 *
	 */
this.getNetworkSettings = async function (property) {
    var params = {
        'property':property 
    };
    return await browser.driver.executeScript('mobile:network.settings:get', params);
};
/**
	 * Sets the network settings of the device.
	 * Note: Setting the Airplane mode is supported for Android devices running OS earlier than 7.0
	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @param Name
	 *            : The network setting property to retrieve: WiFi Data Airplane
	 *            mode
	 *
	 * @param value
	 *             :enabled | disabled
	 *
	 * @return None
	 *
	 * @see <b> Examples </b> for calling method
	 * @see 1. setNetworkSettings("wifi", "enabled");
	 * @see : For more information refer <a href=
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14876786>setNetworkSettings</a>
	 *
	 */
this.setNetworkSettings = function (Name,Value) {
    var params = {
        'name':Name,
        'value':Value,
    };
    return browser.driver.executeScript('mobile:network.settings:set', params);
};




};