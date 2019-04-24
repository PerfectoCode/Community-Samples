var expCondn = protractor.ExpectedConditions;

var commonUtilities = exports.commonUtilities = function () {

    this.getURL = function (URL) {
        browser.getCapabilities()
        .then(function (response) {
            var deviceProperty = response.get('platformName');
            if (!(deviceProperty == null || deviceProperty == '')) {
                deviceProperty = deviceProperty.toLowerCase();
            }
            console.log('********* getURL ' + 'platformName' + ' = ', deviceProperty + ' *********');
            if (!(deviceProperty == 'android' || deviceProperty == 'ios')) {
                browser.manage().window().maximize();
            }
            browser.get(URL);
        });
    };

    this.getAllDeviceProperties = function () {
        return browser.getCapabilities()
            .then(function (response) {
                console.log('********* All device capabilities =', response);
            });
    };

    this.getDeviceProperty = function (deviceProperty) {
        return browser.getCapabilities()
            .then(function (response) {
                console.log('********* deviceProperty: ' + deviceProperty + ' = ', response.get(deviceProperty) + ' *********');
                return response.get(deviceProperty);
            });
    };

    this.waitForVisible = function (element) {
        browser.wait(expCondn.visibilityOf(element), 20000).then(function () {
            browser.sleep(500);
        });
    };

    this.clearText = function (element) {
        this.waitForVisible(element);
        element.clear();
    };

    this.setText = function (element, value) {
        this.waitForVisible(element);
        element.sendKeys(value);
    };

    this.click = function (element) {
        this.waitForVisible(element);
        element.click();
    };

    this.clickIfAvailable = function (element, locator, timeout) {
        return browser.wait(expCondn.visibilityOf(element), timeout).then(function () {
            try {
                console.log("********* Found " + locator + " element. So performing click action");
                element.click();
            }
            catch (e) {
                console.log('********* In catch block, Not able to click on element: ' + locator, e);
                throw("********* Not able to click on element" + locator);
            }
        }, function (err) {
            console.info('********* In error block: Element ' + locator + ' not found', err);
        });
    };

    this.getText = function (element) {
        this.waitForVisible(element);
        return element.getText().then(function (text) {
            return text;
        }, function (err) {
            console.info('********* In error block: Not able to click element ' + element , err);
        });
    };

    this.getAttribute = function (element, attribute) {
        this.waitForVisible(element);
        return element.getAttribute(attribute);
    };

    this.scrollToElement = function scrollToElement(element, locator, timeout, start, end) {
        return browser.wait(expCondn.visibilityOf(element), timeout).then(function () {
            console.log("******* Found " + locator + " element. So quitting the loop without trying to scroll and search");
        }, function (err) {
            var params = {'start': start, 'end': end, 'duration': '2'};
            return browser.driver.executeScript("mobile:touch:swipe", params).then(scrollToElement(element, locator, timeout, start, end));
        });
    };

    this.scrollToElementAndClick = function scrollToElementAndClick(element, locator, timeout, start, end) {
        return browser.wait(expCondn.visibilityOf(element), timeout).then(function () {
            console.log("******* Found " + locator + " element. So quitting the loop without trying to scroll and search");
            console.log("******* Clicking on the element");
            element.click();
        }, function (err) {
            console.log("********* element not found so scrolling and searching: ")
            var params = {'start': start, 'end': end, 'duration': '2'};
            return browser.driver.executeScript("mobile:touch:swipe", params).then(scrollToElementAndClick(element, locator, timeout, start, end));
        });
    };

    this.switchAppContext = function (context) {
        return browser.driver.getCurrentContext().then(function (currentContext) {
            console.log("******* Current context is: " + currentContext);
            browser.driver.selectContext(context).then(function () {
                browser.driver.getCurrentContext().then(function (newContext) {
                    console.log("******* Switched context to: " + newContext);
                    return newContext;
                });
            });
        });
    };

    this.acceptAlertIfExists = function() {
        browser.wait(expCondn.alertIsPresent(), 3000).catch(function(){console.log("alert not found")});
        browser.driver.switchTo().alert().accept().catch(function(){console.log("alert found")});
    };

    /**
     * Sets the picker wheel to the value specified.
     *
     * @param picker
     *            - WebElement that holds the XCUIElementTypePicker
     * @param direction
     *            - Direction to spin the spinner, either next or previous defaults
     *            to next
     * @param value
     *            - value to compare this must be exact
     */
     this.setPickerWheel = function(pickerElement,direction,value) {
         return browser.wait(expCondn.visibilityOf(pickerElement), 30000).then(function () {
//            console.log("******* value before: ",value);
            value = value.replace("/[^\\x00-\\x7F]/g", "");
//            console.log("******* value after: ",value);
            pickerElement.getAttribute("value").then(function (attribute) {
//                console.log("******* attribute before: ",attribute);
                attribute = attribute.replace("/[^\\x00-\\x7F]/g", "");
//                console.log("******* attribute after: ",attribute);
                var flag = false;
                this.compare = function (){
//                    console.log("******* inside compare function ");
                    if(! (attribute == value)){
//                        console.log("******* attribute inside compare function: ",attribute);
                        pickerElement.getId().then(function (id) {
                            var params = {
                                'order': direction,
                                'offset': '0.15',
                                'element': id
                            };
//                            console.log("******* params value: ",params);
                            browser.driver.executeScript('mobile: selectPickerWheelValue', params).then(function () {
                                pickerElement.getAttribute("value").then(function (response) {
                                    attribute = response;
                                    attribute = attribute.replace("/[^\\x00-\\x7F]/g", "");
//                                    console.log("******* new attribute value: ",attribute);
                                    compare();
                                });
                            });
                        });
                    } else {
                        flag = true;
//                        console.log("******* picker wheel set to: ",attribute);
                        return attribute;
                    }
                }
                if (flag == false){
                    this.compare();
                }
            });
         });
     };

};