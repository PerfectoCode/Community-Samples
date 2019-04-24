import { browser } from 'protractor';

class ApplicationUtilities {

    /**
     * Brings the application identified by its name to its initial installation
     * state. <p> The clean application command cleans the data (including cache) from any application installed on the device and brings the application back to its initial state.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param appName The name of the application which needs to be cleaned
     * @return String - "OK" or "Failed"
     *
     * @see <b> Examples </b> for calling function
     * @see 1. cleanApplicationByName("Selendroid")
     * @see 2. cleanApplicationByName("Messages")
     *
     * @see <b>For more information refer</b> <a href=http://developers.perfectomobile.com/display/PD/Clean+application>cleanApplication1</a>,
     * <a> href=https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877456>cleanApplication2</a>
     */
    async cleanApplicationByName(appName: string) {
        var params = { 'name': appName };
        return await browser.driver.executeScript('mobile:application:clean', params);
    };

    /**
     * Brings the application identified by its identifier to its initial
     * installation state. <p> The clean application command cleans the data (including cache) from any application installed on the device and brings the application back to its initial state.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param identifier
     *            : The identifier of the application which needs to be cleaned
     * @return String - "OK" or "Failed"
     *
     * @see <b> Examples </b> for calling function
     * @see 1. cleanApplicationByIdentifier("io.selendroid.testapp");
     * @see 2. cleanApplicationByIdentifier("com.perfecto.io.example");
     * @see :For more information refer <a href=
     *      http://developers.perfectomobile.com/display/PD/Clean+application>cleanApplication1</a>,
     * <a href=
     *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877456>cleanApplication2</a>
     *
     */
    async cleanApplicationByIdentifier(appIdentifier: string) {
        var params = { 'identifier': appIdentifier };
        return await browser.driver.executeScript('mobile:application:clean', params);
    };

    /**
     * Closes an application identified by its name on the device.
     * <p> Closes an application on the device. Application process is killed, not minimized. If you reopen the application, the application will launch from the start page. (If you minimize the application, e.g. using the Home button, the application will launch from the last page previously open.)
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param appName
     *            : The name of the application which needs to be cleaned
     * @return String - "OK" or "Failed"
     *
     * @see <b> Examples </b> for calling function
     * @see 1. closeApplicationByName("Selendroid");
     * @see 2. closeApplicationByName("Messages");
     *
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Close+application>closeApplication1</a>,
     * <a href=
     *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877446>closeApplication2</a>
     */
    async closeApplicationByName(appName: string) {
        var params = { 'name': appName };
        return await browser.driver.executeScript('mobile:application:close', params);
    };

    /**
     * Closes an application identified by its identifier on the device.
     * <p> Closes an application on the device. Application process is killed, not minimized. If you reopen the application, the application will launch from the start page. (If you minimize the application, e.g. using the Home button, the application will launch from the last page previously open.)
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param identifier
     *            : The identifier of the application which needs to be closed
     * @return String - "OK" or "Failed"
     *
     * @see <b> Examples </b> for calling function
     * @see 1. closeApplicationByIdentifier("io.selendroid.testapp");
     * @see 2. closeApplicationByIdentifier("com.perfecto.io.example");
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Close+application>closeApplication1</a>,
     * <a href=
     *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877446>closeApplication2</a>
     */
    async closeApplicationByIdentifier(appIdentifier: string) {
        var params = { 'identifier': appIdentifier };
        return await browser.driver.executeScript('mobile:application:close', params);
    };


    /**
     * Installs a single application on the device. The application should be
     * present at repositoryPath.
     * <p>To use, the application must first be uploaded to the Lab repository. PerfectoLabUtils class provides the uploadMedia method to upload a file to the Repository.
     * <p>The Install application function performs the same operation as the Install widget in Interactive
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param repositoryPath
     *            : The full repository path, including directory and file name,
     *            where to locate the application.
     * @return null
     *
     * @see <b> Examples </b> for calling function
     * @see 1. installApplication("PUBLIC:FingerPrint/Selendroid.apk");
     * @see 2. installApplication("PUBLIC:FingerPrint/testapp.ipa");
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Install+application>installApplication1</a>,
     * <a href=
     *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877452>installApplication2</a>
     */
    async installApplication(repositoryPath: string) {
        var params = { 'file': repositoryPath };
        return await browser.driver.executeScript('mobile:application:install', params);
    };

    /**
     * Installs a single application on the device. The application should be
     * present at repositoryPath.
     * <p>To use, the application must first be uploaded to the Lab repository. PerfectoLabUtils class provides the uploadMedia method to upload a file to the Repository.
     * <p>The Install application function performs the same operation as the Install widget in Interactive
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param repositoryPath
     *            : The full repository path, including directory and file name,
     *            where to locate the application.
     * @return null
     *
     * @see <b> Examples </b> for calling function
     * @see 1. installApplicationWithAutoInstrument("PUBLIC:FingerPrint/Selendroid.apk");
     * @see 2. installApplicationWithAutoInstrument("PUBLIC:FingerPrint/testapp.ipa");
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Install+application>installApplication1</a>,
     * <a href=
     *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877452>installApplication2</a>
     */
    async installApplicationWithAutoInstrument(repositoryPath: string) {
        var params = { 'file': repositoryPath, 'instrument': 'instrument' };
        return await browser.driver.executeScript('mobile:application:install', params);
    };

    /**
     * Installs a single application on the device. The application should be
     * present at repositoryPath.
     * <p>To use, the application must first be uploaded to the Lab repository. PerfectoLabUtils class provides the uploadMedia method to upload a file to the Repository.
     * <p>The Install application function performs the same operation as the Install widget in Interactive
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param repositoryPath
     *            : The full repository path, including directory and file name,
     *            where to locate the application.
     * @return null
     *
     * @see <b> Examples </b> for calling function
     * @see 1. installApplicationWithSensorInstrument("PUBLIC:FingerPrint/Selendroid.apk");
     * @see 2. installApplicationWithSensorInstrument("PUBLIC:FingerPrint/testapp.ipa");
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Install+application>installApplication1</a>,
     * <a href=
     *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877452>installApplication2</a>
     */
    async installApplicationWithSensorInstrument(repositoryPath: string) {
        var params = { 'file': repositoryPath, 'sensorInstrument': 'sensor' };
        return await browser.driver.executeScript('mobile:application:install', params);
    };

    /**
     * Installs a single application on the device. The application should be
     * present at repositoryPath.
     * <p>To use, the application must first be uploaded to the Lab repository. PerfectoLabUtils class provides the uploadMedia method to upload a file to the Repository.
     * <p>The Install application function performs the same operation as the Install widget in Interactive
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param repositoryPath
     *            : The full repository path, including directory and file name,
     *            where to locate the application.
     * @return null
     *
     * @see <b> Examples </b> for calling function
     * @see 1. installApplicationWithAutoAndSensorInstrument("PUBLIC:FingerPrint/Selendroid.apk");
     * @see 2. installApplicationWithAutoAndSensorInstrument("PUBLIC:FingerPrint/testapp.ipa");
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Install+application>installApplication1</a>,
     * <a href=
     *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877452>installApplication2</a>
     */
    async installApplicationWithAutoAndSensorInstrument(repositoryPath: string) {
        var params = {
            'file': repositoryPath, 'instrument': 'instrument',
            'sensorInstrument': 'sensor'
        };
        return await browser.driver.executeScript('mobile:application:install', params);
    };

    /**
     * The command identifies the active application automatically and then
     * moves it to the background for the timeout defined and then returns it to
     * the foreground.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param timeout
     *            : The time, in seconds, to run the current application as a
     *            background application.
     * @return String - "OK" or "Failed"
     *
     * @see <b> Examples </b> for calling function
     * @see 1. runAppInBackground('20');
     * @see 2. runAppInBackground('30');
     *
     * @see :For more information refer <a href=
     *       https://developers.perfectomobile.com/display/PD/Run+app+in+background>runAppInBackground1</a>,
     * <a href=
     *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877998>runAppInBackground2</a>
     */
    async runAppInBackground(timeout: number) {
        var params = { 'timeout': timeout };
        return await browser.driver.executeScript('mobile:application:back', params);
    };


    /**
     * Command injects the success authentication reply for a fingerprint check
     * performed by the application identified by its name.
     * <p>Command injects the authentication reply for a fingerprint check performed by the application. The command should be activated by the automation script at the point that the target application presents a "popup" indicating that the application is trying to retrieve information from the fingerprint reader.
     * <p><b>Note: </b>The application must have been "sensor instrumented" at time of installation for this function
     * to work.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param appName
     *            : The name of the application which checks for fingerprint
     *
     * @return null
     *
     * @see <b> Examples </b> for calling function
     * @see 1. setFingerPrintByAppNameForSuccess("FingerPrintDemo");
     * @see 2. setFingerPrintByAppNameForSuccess("TouchIDDemo");
     *
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Set+fingerprint>setFingerPrint</a>
     *
     */
    async setFingerPrintByAppNameForSuccess(appName: string) {
        var params = { 'name': appName, 'resultAuth': 'success' };
        return await browser.driver.executeScript('mobile:fingerprint:set', params);
    };

    /**
     * Command injects the success authentication reply for a fingerprint check
     * performed by the application identified by its identifier.
     * <p>Command injects the authentication reply for a fingerprint check performed by the application. The command should be activated by the automation script at the point that the target application presents a "popup" indicating that the application is trying to retrieve information from the fingerprint reader.
     * <p><b>Note: </b>The application must have been "sensor instrumented" at time of installation for this function
     * to work.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param identifier
     *            : The identifier of the application which checks for
     *            fingerprint
     *
     * @return null
     *
     * @see <b> Examples </b> for calling function
     * @see 1. setFingerPrintByAppIdentifierForSuccess(
     *      "com.perfectomobile.fingerprintdemo");
     * @see 2. setFingerPrintByAppIdentifierForSuccess(
     *      "io.test.TouchIDDemo");
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Set+fingerprint>setFingerPrint</a>
     *
     */
    async setFingerPrintByAppIdentifierForSuccess(identifier: string) {
        var params = { 'identifier': identifier, 'resultAuth': 'success' };
        return await browser.driver.executeScript('mobile:fingerprint:set', params);
    };

    /**
     * Command injects the failure authentication reply for a fingerprint check
     * performed by the application identified by its name.
     * <p>Command injects the authentication reply for a fingerprint check performed by the application. The command should be activated by the automation script at the point that the target application presents a "popup" indicating that the application is trying to retrieve information from the fingerprint reader.
     * <p><b>Note: </b>The application must have been "sensor instrumented" at time of installation for this function
     * to work.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param appName
     *            : The name of the application which checks for fingerprint
     * @param errorType
     *            : The type of error for failure (Valid values are: authFailed,
     *            userCancel, userFallback, systemCancel, lockOut)
     *
     * @return null
     *
     * @see <b> Examples </b> for calling function
     * @see 1. setFingerPrintByAppNameForFailure( "FingerPrintDemo",
     *      "authFailed");
     * @see 2. setFingerPrintByAppNameForFailure( "FingerPrintDemo",
     *      "systemCancel");
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Set+fingerprint>setFingerPrint</a>
     *
     */
    async setFingerPrintByAppNameForFailure(appName: string, errorType: string) {
        var params = { 'name': appName, 'resultAuth': 'fail', 'errorType': errorType };
        return await browser.driver.executeScript('mobile:fingerprint:set', params);
    };


    /**
     * Command injects the failure authentication reply for a fingerprint check
     * performed by the application identified by its identifier.
     * <p>Command injects the authentication reply for a fingerprint check performed by the application. The command should be activated by the automation script at the point that the target application presents a "popup" indicating that the application is trying to retrieve information from the fingerprint reader.
     * <p><b>Note: </b>The application must have been "sensor instrumented" at time of installation for this function
     * to work.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param identifier
     *            : The identifier of the application which checks for
     *            fingerprint
     * @param errorType
     *            : The type of error for failure (Valid values are: authFailed,
     *            userCancel, userFallback, systemCancel, lockOut)
     *
     * @return null
     *
     * @see <b> Examples </b> for calling function
     * @see 1. setFingerPrintByAppIdentifierForFailure(
     *      "com.perfectomobile.fingerprintdemo", "userCancel");
     * @see 2. setFingerPrintByAppIdentifierForFailure(
     *      "io.test.TouchIDDemo", "systemCancel");
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Set+fingerprint>setFingerPrint</a>
     *
     */
    async setFingerPrintByAppIdentifierForFailure(identifier: string, errorType: string) {
        var params = { 'identifier': identifier, 'resultAuth': 'fail', 'errorType': errorType };
        return await browser.driver.executeScript('mobile:fingerprint:set', params);
    };

    /**
     * Command injects the success authentication reply for a sensor
     * authentication check performed by the application identified by name.
     * <p>Command injects the authentication reply for a sensor authentication check performed by the application (e.g. fingerprint or faceId, on supported devices).
     * <p>The command should be activated by the automation script at the point that the target application presents a "popup" indicating that the application is trying to retrieve information from the sensor reader.
     * <p><b>Note: </b>The application must have been "sensor instrumented" at time of installation for this function
     * to work.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param appName
     *            : The name of the application which checks for fingerprint
     *
     * @return null
     *
     * @see <b> Examples </b> for calling function
     * @see 1. setFaceIdByAppNameForSuccess( "FingerPrintDemo");
     * @see 2. setFaceIdByAppNameForSuccess( "TouchIDDemo");
     *
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Set+Sensor+Authentication>setSensorAuthentication</a>
     *
     */
    async setFaceIdByAppNameForSuccess(appName: string) {
        var params = { 'name': appName, 'resultAuth': 'success' };
        return await browser.driver.executeScript('mobile:sensorAuthentication:set', params);
    };

    /**
     * Command injects the success authentication reply for a sensor
     * authentication check performed by the application identified by
     * identifier.
     * <p>Command injects the authentication reply for a sensor authentication check performed by the application (e.g. fingerprint or faceId, on supported devices).
     * <p>The command should be activated by the automation script at the point that the target application presents a "popup" indicating that the application is trying to retrieve information from the sensor reader.
     * <p><b>Note: </b>The application must have been "sensor instrumented" at time of installation for this function
     * to work.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param identifier
     *            : The name of the application which checks for fingerprint
     *
     * @return null
     *
     * @see <b> Examples </b> for calling function
     * @see 1. setFaceIdByAppIdentifierForSuccess(
     *      "com.perfectomobile.FingerprintTest");
     * @see 2. setFaceIdByAppIdentifierForSuccess(
     *      "io.test.TouchIDDemo");
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Set+Sensor+Authentication>setSensorAuthentication</a>
     *
     */
    async setFaceIdByAppIdentifierForSuccess(identifier: string) {
        var params = { 'identifier': identifier, 'resultAuth': 'success' };
        return await browser.driver.executeScript('mobile:sensorAuthentication:set', params);
    };

    /**
     * Command injects the failure authentication reply for a faceID check
     * performed by the application identified by its name.
     * <p>Command injects the authentication reply for a sensor authentication check performed by the application (e.g. fingerprint or faceId, on supported devices).
     * <p>The command should be activated by the automation script at the point that the target application presents a "popup" indicating that the application is trying to retrieve information from the sensor reader.
     * <p><b>Note: </b>The application must have been "sensor instrumented" at time of installation for this function
     * to work.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param appName
     *            : The name of the application which checks for fingerprint
     * @param errorType
     *            : The type of error for failure (Valid values are: authFailed,
     *            userCancel, userFallback, systemCancel, lockOut)
     *
     * @return null
     *
     * @see <b> Examples </b> for calling function
     * @see 1. setFaceIdByAppNameForFailure( "FingerPrintDemo",
     *      "authFailed");
     * @see 2. setFaceIdByAppNameForFailure( "FingerPrintDemo",
     *      "systemCancel");
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Set+Sensor+Authentication>setSensorAuthentication</a>
     *
     */
    async setFaceIdByAppNameForFailure(appName: string, errorType: string) {
        var params = { 'name': appName, 'resultAuth': 'fail', 'errorType': errorType };
        return await browser.driver.executeScript('mobile:sensorAuthentication:set', params);
    };

    /**
     * Command injects the failure authentication reply for a faceID check
     * performed by the application identified by its identifier.
     * <p>Command injects the authentication reply for a sensor authentication check performed by the application (e.g. fingerprint or faceId, on supported devices).
     * <p>The command should be activated by the automation script at the point that the target application presents a "popup" indicating that the application is trying to retrieve information from the sensor reader.
     * <p><b>Note: </b>The application must have been "sensor instrumented" at time of installation for this function
     * to work.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param identifier
     *            : The identifier of the application which checks for
     *            fingerprint
     * @param errorType
     *            : The type of error for failure (Valid values are: authFailed,
     *            userCancel, userFallback, systemCancel, lockOut)
     *
     * @return null
     *
     * @see <b> Examples </b> for calling function
     * @see 1. setFaceIdByAppIdentifierForFailure(
     *      "com.perfectomobile.fingerprintdemo", "userCancel");
     * @see 2. setFaceIdByAppIdentifierForFailure( "io.test.TouchIDDemo",
     *      "systemCancel");
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Set+Sensor+Authentication>setSensorAuthentication</a>
     *
     */
    async setFaceIdByAppIdentifierForFailure(identifier: string, errorType: string) {
        var params = { 'identifier': identifier, 'resultAuth': 'fail', 'errorType': errorType };
        return await browser.driver.executeScript('mobile:sensorAuthentication:set', params);
    };


    /**
     * Starts an Android activity on the device.
     * <p>An activity is an application component that provides a screen with which users can interact, such as dial the phone, take a photo, send an email, or view a map.
     * <p>Applications can be large and complex, written by many development teams. The start activity and sync activity commands enable a tester to start and sync a specific activity on the device, and decreases the time it takes to write a test and allowing more coverage in a dynamic environment where time to develop tests is short.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param appPackage
     *            : The package name.
     * @param activity
     *            : The activity name.
     *
     * @return String - "OK" or "Failed"
     *
     * @see <b> Examples </b> for calling function
     * @see 1. startActivity( "com.sec.android.app.camera", ".Camera");
     * @see 2. startActivity( "com.sec.android.app.photo", ".photo");
     *
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Start+activity>startActivity1</a>
     *       <a href=
     *       https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877495>startActivity2</a>
     */
    async startActivity(appPackage: string, activity: string) {
        var params = { 'package': appPackage, 'activity': activity };
        return await browser.driver.executeScript('mobile:activity:open', params);
    };

    /**
     * Starts an application identified by its name on the device.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param appName
     *            : The name of the application which needs to be launched
     *
     *
     * @see <b> Examples </b> for calling function
     * @see 1. startApplicationByName("Selendroid");
     * @see 2. startApplicationByName("Messages");
     *
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Start+application>startApplication1</a>
     *       <a href=
     *       https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877449>startApplication2</a>
     */
    async startApplicationByName(appName: string) {
        var params = { 'name': appName };
        return await browser.driver.executeScript('mobile:application:open', params);
    };

    /**
     * Starts an application identified by its identifier on the device.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param identifier
     *            : The identifier of the application which needs to be launched
     *
     *
     * @see <b> Examples </b> for calling function
     * @see 1. startApplicationByIdentifier("io.selendroid.testapp");
     * @see 2. startApplicationByIdentifier("com.perfecto.io.example");
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Start+application>startApplication1</a>
     *       <a href=
     *       https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877449>startApplication2</a>
     */
    async startApplicationByIdentifier(appIdentifier: string) {
        var params = { 'identifier': appIdentifier };
        return await browser.driver.executeScript('mobile:application:open', params);
    };


    /**
     * Launches a Progressive Web application on the device.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param displayName
     *            : The PWA icon display name as it is displayed on the device
     *            screen.
     *
     * @see <b> Examples </b> for calling function
     * @see 1. startPWA("Selendroid");
     * @see 2. startPWA("Messages");
     *
     * @see :For more information refer <a href=
     *       https://developers.perfectomobile.com/display/PD/Start+PWA>startPWA1</a>
     *       <a href=
     *       https://developers.perfectomobile.com/pages/viewpage.action?pageId=31097554>startPWA2</a>
     */
    async startPWA(displayName: string) {
        var params = { 'displayName': displayName };
        return await browser.driver.executeScript('mobile:pwa:start', params);
    };

    /**
     * Closes the PWA currently running in the foreground.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @see <b> Examples </b> for calling function
     * @see 1. stopPWA(driver);
     * @see 2. stopPWA(driver);
     *
     * @see :For more information refer <a href=
     *       https://developers.perfectomobile.com/display/PD/Stop+PWA>stopPWA1</a>
     *       <a href=
     *       https://developers.perfectomobile.com/pages/viewpage.action?pageId=31097556>stopPWA2</a>
     */
    async stopPWA() {
        var params = {};
        return await browser.driver.executeScript('mobile:pwa:stop', params);
    };

    /**
     * Verifies the Android package or activity has started on the device.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param appPackage
     *            : The package name.
     * @param activity
     *            : The activity name.
     * @return String - "OK" or "Failed"
     *
     * @see <b> Examples </b> for calling function
     * @see 1. syncActivity( "com.sec.android.app.camera", ".Camera");
     * @see 2. syncActivity( "com.sec.android.app.photo", ".photo");
     *
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Sync+activity>syncActivity1</a>
     *       <a href=
     *       https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877497>syncActivity2</a>
     */
    async syncActivity(appPackage: string, activity: string) {
        var params = { 'package': appPackage, 'activity': activity };
        return await browser.driver.executeScript('mobile:activity:sync', params);
    };


    /**
     * Removes an application identified by its name on the device.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param appName
     *            : The name of the application which needs to be removed
     * @return String - "OK" or "Failed"
     *
     * @see <b> Examples </b> for calling function
     * @see 1. uninstallApplicationByName("Selendroid");
     * @see 2. uninstallApplicationByName("Messages");
     *
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Uninstall+application>uninstallApplication1</a>
     *       <a href=
     *       https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877459>uninstallApplication2</a>
     */
    async uninstallApplicationByName(appName: string) {
        var params = { 'name': appName };
        return await browser.driver.executeScript('mobile:application:uninstall', params);
    };


    /**
     * Removes an application identified by its identifier on the device.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param identifier
     *            : The identifier of the application which needs to be removed
     * @return String - "OK" or "Failed"
     *
     * @see <b> Examples </b>  for calling function
     * @see 1. uninstallApplicationByIdentifier("io.selendroid.testapp");
     * @see 2. uninstallApplicationByIdentifier("com.perfecto.io.example");
     * @see :For more information refer <a href=
     *       http://developers.perfectomobile.com/display/PD/Uninstall+application>uninstallApplication1</a>
     *       <a href=
     *       https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877459>uninstallApplication2</a>
     */
    async uninstallApplicationByIdentifierunction(identifier: string) {
        var params = { 'name': identifier };
        return await browser.driver.executeScript('mobile:application:uninstall', params);
    };

};

export {
    ApplicationUtilities
}