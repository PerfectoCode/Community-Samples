import { browser, protractor, ElementFinder } from 'protractor';

let expCondn = protractor.ExpectedConditions;

class CommonUtilities {

    getURL(URL: string) {
        browser.getCapabilities().then(function (response) {
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

    getAllDeviceProperties() {
        return browser.getCapabilities().then(function (response) {
            console.log('********* All device capabilities =', response);
        });
    };

    getDeviceProperty(deviceProperty: string) {
        return browser.getCapabilities().then(function (response) {
            console.log('********* deviceProperty: ' + deviceProperty + ' = ', response.get(deviceProperty) + ' *********');
            return response.get(deviceProperty);
        });
    };

    waitForVisible(element: ElementFinder) {
        browser.wait(expCondn.visibilityOf(element), 20000).then(function () {
            browser.sleep(500);
        });
    };

    clearText(element: ElementFinder) {
        this.waitForVisible(element);
        element.clear();
    };

    setText(element: ElementFinder, value: string) {
        this.waitForVisible(element);
        element.sendKeys(value);
    };

    click(element: ElementFinder) {
        this.waitForVisible(element);
        element.click();
    };

    clickIfAvailable(element: ElementFinder, locator: string, timeout: number) {
        return browser.wait(expCondn.visibilityOf(element), timeout).then(function () {
            try {
                console.log("********* Found " + locator + " element. So performing click action");
                element.click();
            }
            catch (e) {
                console.log('********* In catch block, Not able to click on element: ' + locator, e);
                throw ("********* Not able to click on element" + locator);
            }
        }, function (err) {
            console.info('********* In error block: Element ' + locator + ' not found', err);
        });
    };

    getText(element: ElementFinder) {
        this.waitForVisible(element);
        return element.getText().then(function (text) {
            return text;
        }, function (err) {
            console.info('********* In error block: Not able to click element ' + element, err);
        });
    };

    getAttribute(element: ElementFinder, attribute: string) {
        this.waitForVisible(element);
        return element.getAttribute(attribute);
    };

    scrollToElement = function scrollToElement(element: ElementFinder, locator: string, timeout: number, start: string, end: string) {
        return browser.wait(expCondn.visibilityOf(element), timeout).then(function () {
            console.log("******* Found " + locator + " element. So quitting the loop without trying to scroll and search");
        }, function (err) {
            var params = { 'start': start, 'end': end, 'duration': '2' };
            return browser.driver.executeScript("mobile:touch:swipe", params).then(scrollToElement(element, locator, timeout, start, end));
        });
    };

    scrollToElementAndClick = function scrollToElementAndClick(element: ElementFinder, locator: string, timeout: number, start: string, end: string) {
        return browser.wait(expCondn.visibilityOf(element), timeout).then(function () {
            console.log("******* Found " + locator + " element. So quitting the loop without trying to scroll and search");
            console.log("******* Clicking on the element");
            element.click();
        }, function (err) {
            console.log("********* element not found so scrolling and searching: ")
            var params = { 'start': start, 'end': end, 'duration': '2' };
            return browser.driver.executeScript("mobile:touch:swipe", params).then(scrollToElementAndClick(element, locator, timeout, start, end));
        });
    };

    switchAppContext(context: string) {
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

    acceptAlertIfExists() {
        browser.wait(expCondn.alertIsPresent(), 3000).catch(function () { console.log("alert not found") });
        browser.driver.switchTo().alert().accept().catch(function () { console.log("alert found") });
    };

    /**
     * Sets the picker wheel to the value specified.
     *
     * @param pickerElement
     *            - WebElement that holds the XCUIElementTypePicker
     * @param direction
     *            - Direction to spin the spinner, either next or previous defaults
     *            to next
     * @param value
     *            - value to compare this must be exact
     */
    setPickerWheel(pickerElement: ElementFinder, direction: string, value: string, timeout: number) {
        return browser.wait(expCondn.visibilityOf(pickerElement), timeout).then(function () {
            pickerElement.getAttribute("value").then(function (attribute) {
                var flag = false;
                var compare = function () {
                    if (!(attribute == value)) {
                        pickerElement.getId().then(function (id) {
                            var params = {
                                'order': direction,
                                'offset': '0.15',
                                'element': id
                            };
                            browser.driver.executeScript('mobile: selectPickerWheelValue', params).then(function () {
                                pickerElement.getAttribute("value").then(function (response) {
                                    attribute = response;
                                    compare();
                                });
                            });
                        });
                    } else {
                        flag = true;
                        return attribute;
                    }
                }
                if (flag == false) {
                    compare();
                }
            });
        });
    };

};

export {
    CommonUtilities
};