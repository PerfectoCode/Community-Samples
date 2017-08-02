var webdriver = require('selenium-webdriver');
var fs = require('fs');
var driver;             

setupDriver(); 
test_search_images_google_for_selenium_logo();
test_search_google_for_perfecto_rwd();
tearDown();

function setupDriver() {

    console.log("Setup driver");

    var user = 'myUser';
    var password = 'myPassword';
    var host = 'myLab.perfectomobile.com';

    var url = "https://" + host + "/nexperience/perfectomobile/wd/hub";

    var deviceId = '041547DdF42A0E3030h1';

    // Set capabilities
    var capabilities = {
            'browserName' : 'mobileOS', 
            'scriptName'  : 'testingGoogleImages',
            'deviceName'  : deviceId, 
            'user'        : user,
            'password'    : password,
            'platformName': 'Android',
            'windTunnelPersona': 'Georgia'
            
    }

    driver = new webdriver.Builder().
        usingServer(url).
        withCapabilities(capabilities).
        build();
    console.log("Run started");
}

​
//Test 1, search Google images for Selenium logo
function test_search_images_google_for_selenium_logo() {
    console.log("Start test: test_search_images_google_for_selenium_logo");

    // Step 1: Go to images.google.com
    var url = 'https://images.google.com/';
    driver.get(url);


    // Step 2: validate the page has loaded by waiting for the images.google search edit field to load
    var queryName = 'q';
    text = 'Selenium Logo';

    driver.wait(function() {
        return driver.findElement(webdriver.By.name(queryName)).isDisplayed();
    }, 1500);

    // Validate the text 'images' appears in the URL
    var currentUrl = driver.getCurrentUrl();
    console.log(currentUrl);

    // Step 3: Enter the text 'Selenium Logo'
    var element = driver.findElement(webdriver.By.name(queryName)).sendKeys(text);

    // Step 4: Validate entered text and click on search icon
    var currentText = element.text;

    searchIconCss = '#tsbb';
    element = driver.findElement(webdriver.By.css(searchIconCss)).click();
}         

//Test 2, search Google for Perfecto RemoteWebDriver
function test_search_google_for_perfecto_rwd() {
    console.log("Start test: test_search_google_for_perfecto_rwd");

    // Step 1: Go to www.google.com
    var url = 'http://www.google.com';
    driver.get(url);

    // Step 2: validate the page has loaded by waiting for the google search edit field to load
    //wait = Selenium::WebDriver::Wait.new(:timeout => 15) # seconds

    var queryId = 'lst-ib';
    driver.wait(function() {
        return driver.findElement(webdriver.By.id(queryId));
    }, 1500);

    driver.wait(function() {
        return driver.getTitle().then(function(title) {
            return 'Google' === title;
        });
    }, 10000);

    // Validate that the title is 'Google'

    // Step 3: Enter the text 'Perfecto Mobile' and then the text 'JS example for RemoteWebDriver'
    var element = driver.findElement(webdriver.By.id(queryId));
    var typeText = 'JavaScript example for RemoteWebDriver, Perfecto Mobile';
    element.sendKeys(typeText);

    // Step 4: Validate entered text and submit search
    var currentText = element.text;
    element.submit();
}

function tearDown() {
    if (driver != null) {
        driver.close();
        target = "";
        downloadPdfReport(target);
        driver.quit();
    }
    console.log("Run ended");
}  

function downloadPdfReport (target) {
    command = 'mobile:report:download';
    params = {'type' : 'pdf'};
    driver.executeScript(command, params).then(function(report) {
        console.log("Saving Perfecto Mobile report as PDF");
        var buffer = new Buffer(report, 'base64');
        var filename = 'C:\\test\\report.pdf';
        fs.writeFileSync(filename, buffer);
    });
}​