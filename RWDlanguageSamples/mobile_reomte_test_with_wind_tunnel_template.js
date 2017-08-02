var webdriver = require('selenium-webdriver');
var fs = require('fs');
var driver;

setupDriver();
test_wind_tunnel_script();
tearDown();

function setupDriver() {
var args = process.argv.slice(2);
    console.log("Setup driver");

    var user = "USER";
    var password = "PASSWORD";
    var host = "HOST.perfectomobile.com";
    var protocol= "https://";
    var url = protocol + host + "/nexperience/perfectomobile/wd/hub";

    // Set capabilities
    var capabilities = {
            'browserName' : 'mobileOS',
            'user'        : user,
            'password'    : password,
            'windTunnelPersona': 'Sam'
    }

    driver = new webdriver.Builder().
        usingServer(url).
        withCapabilities(capabilities).
        build();
    console.log("Run started");
}

function test_wind_tunnel_script() {
    console.log("Wind tunnel script example");

    // start collecting device logs
    driver.executeScript("mobile:logs:start");

    pointOfInterest("Open YouTube App", "success");

    params = {'name' : 'youtube'};
    driver.executeScript('mobile:application:open', params);

    startVitalsPerApp("youtube", "all");

    waitForText("Home", 30);

    params={};
    launchTime = driver.executeScript('mobile:timer:info', params)
    timerReport("App Launch", "Application Launch Time", 5000, launchTime);
}

function pointOfInterest(description, status) {
    params = {  'description' : description,
                'status' : status
    };
    driver.executeScript('mobile:status:event', params);
}

function timerReport(name, description, threshold, result) {
    params = {'name' : name,
            'description' : description,
            'threshold' : threshold,
            'result' : result
    };

    driver.executeScript('mobile:status:timer', params);
}

function startVitalsPerApp(appName, vitals) {
    params = {  'vitals' : vitals,
        'sources' : appName
    };
    driver.executeScript('mobile:monitor:start', params);
}

function waitForText (text, timeout) {
    params = {  'content' : text,
        'source' : 'camera',
        'measurement' : 'accurate',
        'analysis' : 'automatic',
        'timeout' : timeout
    };
    driver.executeScript('mobile:checkpoint:text', params);
}

function tearDown() {
    if (driver != null) {
        driver.close();
        driver.quit();
    }
