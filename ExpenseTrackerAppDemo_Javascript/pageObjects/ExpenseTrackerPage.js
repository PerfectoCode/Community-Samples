class expenseTrackerPage {
    constructor(commonUtilities, properties) {
        var expCondn = protractor.ExpectedConditions;
        var deviceUtilities = require('../utilities/DeviceUtilities.js').deviceUtilities;
        var uiObjectsUtilities = require('../utilities/UiObjectsUtilities.js').uiObjectsUtilities;
        var otherUtilities = require('../utilities/OtherUtilities.js').otherUtilities;
        var applicationUtilities = require('../utilities/ApplicationUtilities.js').applicationUtilities;
        deviceUtilities = new deviceUtilities();
        uiObjectsUtilities = new uiObjectsUtilities();
        otherUtilities = new otherUtilities();
        applicationUtilities = new applicationUtilities();

        let usernameElement;
        let passwordElement;
        let loginElement;
        let enableTouchIdElement;
        let OkButton;
        let addButtonweb;
        let attachReceiptButton;
        let addButtonmobile;
        let headDropdown;
        let headFlightDropdown;
        let amountTextbox;
        let currencyDropdown;
        let currencyInrDropdown;
        let categoryDropdown;
        let categoryBusinessDropdown;
        let attachmentLink;
        let addAttachmentLink;
        let cameraBtn;
        let okButton;
        let addAttachmentAllowBtn;
        let attachmentViewLnk;
        let detailsTextbox;
        let saveButton;
        let popUpOKButton;
        let addLabel;
        let headTextbox;
        let currencyTextbox;
        let categoryTextbox;
        let dateTextbox;
        let pickerWheel1;
        let takePhotoBtn;
        let usePhotoBtn;
        let logoutButtonWElement;
        let logoutButtonElement;
        let hamburgerMenuElement;

        this.loginToApplication = async function (username, password) {
            try {
                usernameElement = await this.getLocatorForElement('username.text.box');
                passwordElement = await this.getLocatorForElement('password.text.box');
                loginElement = await this.getLocatorForElement('login.button');
                enableTouchIdElement = await this.getLocatorForElement('enable.TouchID.button');
                OkButton = await this.getLocatorForElement('Ok.button');
                return commonUtilities.getDeviceProperty('appType').then(function (appType) {
                    if (!(appType == null || appType == '')) {
                        appType = appType.toUpperCase();
                    }
                    commonUtilities.getDeviceProperty('platformName').then(function (platformName) {
                        if (!(platformName == null || platformName == '')) {
                            platformName = platformName.toUpperCase();
                        }
                        if (appType == 'HYBRID' || appType == 'NATIVE') {
                            if (appType == 'HYBRID') {
                                console.log('********* HYBRID app so switching to WEBVIEW context');
                                commonUtilities.switchAppContext("WEBVIEW").then(function () { });
                            }
                            console.log('********* HYBRID or NATIVE app so trying touchID login');
                            enableTouchIdElement.click();
                        }
                        else if (appType == 'WEB') {
                            console.log('********* WEB app so launching url');
                            commonUtilities.getURL('http://expensetracker.perfectomobile.com/');
                        }
                        commonUtilities.getDeviceProperty('browserName').then(function (browserName) {
                            if (browserName == 'Firefox') {
                                usernameElement.sendKeys(username).then(function () {
                                    passwordElement.sendKeys(password).then(function () {
                                        loginElement.click();
                                    });
                                });
                            } else {
                                usernameElement.click().then(function () {
                                    browser.actions().sendKeys(username).perform().then(function () {
                                        passwordElement.click().then(function () {
                                            browser.actions().sendKeys(password).perform().then(function () {
                                                if (platformName == 'IOS' || platformName == 'ANDROID') {
                                                    if (platformName == 'IOS') {
                                                        loginElement.click().then(function () {
                                                            commonUtilities.getDeviceProperty('bundleId').then(async function (bundleId) {
                                                                if (appType == 'NATIVE') {
                                                                    await applicationUtilities.setFingerPrintByAppIdentifierForFailure(bundleId, "userCancel").then(async function (result) {
                                                                        await browser.sleep(6000).then(async function () {
                                                                            OkButton.click().then(function () {
                                                                                loginElement.click().then(async function () { });
                                                                            });
                                                                        });
                                                                    });
                                                                }
                                                                await applicationUtilities.setFingerPrintByAppIdentifierForSuccess(bundleId).then(function (result) {
                                                                    browser.sleep(6000);
                                                                });
                                                            });
                                                        });
                                                    } else {
                                                        loginElement.click().then(function () {
                                                            commonUtilities.getDeviceProperty('appPackage').then(function (appPackage) {
                                                                applicationUtilities.setFingerPrintByAppIdentifierForSuccess(appPackage).then(function () {
                                                                    browser.sleep(6000);
                                                                });
                                                            });
                                                        });
                                                    }
                                                } else {
                                                    loginElement.click();
                                                }
                                            });
                                        });
                                    });
                                });
                            }
                        });
                    });
                });
            } catch (err) {
                console.log("********* Error in loginToApplication function: ")
                throw (err);
            }
        };

        this.addAnExpense = async function (Head, Amount, Currency, Category, ReceiptFile, Details) {
            try {
                addButtonweb = await this.getLocatorForElement('add.buttonweb');
                attachReceiptButton = await this.getLocatorForElement('attachReceipt.button');
                headDropdown = await this.getLocatorForElement('head.dropdown');
                headFlightDropdown = await this.getLocatorForElement('head.flight.dropdown');
                amountTextbox = await this.getLocatorForElement('amount.text.box');
                currencyDropdown = await this.getLocatorForElement('currency.dropdown');
                currencyInrDropdown = await this.getLocatorForElement('currency.inr.dropdown');
                categoryDropdown = await this.getLocatorForElement('category.dropdown');
                categoryBusinessDropdown = await this.getLocatorForElement('category.business.dropdown');
                attachmentLink = await this.getLocatorForElement('attachment.link');
                addAttachmentLink = await this.getLocatorForElement('add.attachment');
                cameraBtn = await this.getLocatorForElement('camera.btn');
                addAttachmentAllowBtn = await this.getLocatorForElement('addAttachmentAllow.btn');
                attachmentViewLnk = await this.getLocatorForElement('attachmentView.lnk');
                detailsTextbox = await this.getLocatorForElement('details.text.box');
                saveButton = await this.getLocatorForElement('save.button');
                popUpOKButton = await this.getLocatorForElement('pop-up.OK.btn');
                addLabel = await this.getLocatorForElement('add.label');
                headTextbox = await this.getLocatorForElement('head.text.box');
                currencyTextbox = await this.getLocatorForElement('currency.text.box');
                categoryTextbox = await this.getLocatorForElement('category.text.box');
                dateTextbox = await this.getLocatorForElement('date.text.box');
                pickerWheel1 = await this.getLocatorForElement('pickerWheel1');
                OkButton = await this.getLocatorForElement('Ok.button');
                takePhotoBtn = await this.getLocatorForElement('takePhoto.btn');
                usePhotoBtn = await this.getLocatorForElement('usePhoto.btn');

                return commonUtilities.getDeviceProperty('appType').then(function (appType) {
                    appType = appType.toUpperCase();
                    commonUtilities.getDeviceProperty('platformName').then(function (platformName) {
                        platformName = platformName.toUpperCase();
                        if (appType == 'HYBRID' || appType == 'WEB') {
                            addButtonweb.click().then(function () {
                                headDropdown.click().then(function () {
                                    headFlightDropdown.click().then(function () {
                                        popUpOKButton.click().then(function () {
                                            if (platformName == 'ANDROID' || platformName == 'IOS') {
                                                amountTextbox.click().then(function () {
                                                    browser.actions().sendKeys(Amount).perform().then(function () {
                                                    });
                                                });
                                            } else {
                                                amountTextbox.sendKeys(Amount).then(function () {
                                                });
                                            }
                                            currencyDropdown.click().then(function () {
                                                currencyInrDropdown.click().then(function () {
                                                    popUpOKButton.click().then(function () {
                                                        categoryDropdown.click().then(function () {
                                                            categoryBusinessDropdown.click().then(function () {
                                                                popUpOKButton.click().then(function () {
                                                                    if (platformName == 'ANDROID') {
                                                                        commonUtilities.getDeviceProperty('appPackage').then(function (appPackage) {
                                                                            addAttachmentLink.click().then(function () {
                                                                                deviceUtilities.startImageInjectionByIdentifier(appPackage, 'PUBLIC:ExpenseTracker/Images/CreditCard.jpg').then(function () {
                                                                                    cameraBtn.click().then(function () {
                                                                                        commonUtilities.switchAppContext("NATIVE_APP").then(function () {
                                                                                            addAttachmentAllowBtn.click().then(function () {
                                                                                                addAttachmentAllowBtn.click().then(function () {
                                                                                                    commonUtilities.switchAppContext("WEBVIEW");
                                                                                                });
                                                                                            });
                                                                                        });
                                                                                    });
                                                                                });
                                                                            });
                                                                        });
                                                                    } else if (platformName == 'IOS') {
                                                                        addAttachmentLink.click().then(function () {
                                                                            commonUtilities.getDeviceProperty('bundleId').then(function (bundleId) {
                                                                                deviceUtilities.startImageInjectionByIdentifier(bundleId, 'PUBLIC:ExpenseTracker/Images/CreditCard.jpg').then(function () {
                                                                                    cameraBtn.click().then(function () {
                                                                                        commonUtilities.switchAppContext("NATIVE_APP").then(function () {
                                                                                            OkButton.click().then(function () {
                                                                                                takePhotoBtn.click().then(function () {
                                                                                                    usePhotoBtn.click().then(function () {
                                                                                                        commonUtilities.switchAppContext("WEBVIEW")
                                                                                                    });
                                                                                                });
                                                                                            });
                                                                                        });
                                                                                    });
                                                                                });
                                                                            });
                                                                        });
                                                                    }
                                                                    commonUtilities.scrollToElementAndClick(saveButton, null, 6000, '40%,90%', '40%,10%').then(function () {
                                                                    });
                                                                });
                                                            });
                                                        });
                                                    });
                                                });
                                            });
                                        });
                                    });
                                });
                            });
                        } else {
                            addLabel.click().then(function () {
                                headTextbox.click();
                            });
                            if (platformName == 'ANDROID') {
                                element(by.xpath("//*[@text='" + Head + "']")).click();
                            } else {
                                pickerWheel1.sendKeys(Head);
                            }
                            amountTextbox.sendKeys(Amount).then(function () {
                                currencyTextbox.click();
                            });
                            if (platformName == 'ANDROID') {
                                Currency = Currency.replace(/\s/g, "");
                                element(by.xpath("//*[@text='" + Currency + "']")).click();
                            } else {
                                pickerWheel1.sendKeys(Currency);
                            }
                            categoryTextbox.click();
                            if (platformName == 'ANDROID') {
                                element(by.xpath("//*[@text='" + Category + "']")).click().then(function () {
                                    commonUtilities.getDeviceProperty('appPackage').then(function (appPackage) {
                                        attachmentLink.click().then(function () {
                                            deviceUtilities.startImageInjectionByIdentifier(appPackage, 'PUBLIC:ExpenseTracker/Images/CreditCard.jpg').then(function () {
                                                cameraBtn.click().then(function () {
                                                    OkButton.click().then(function () {
                                                        saveButton.click();
                                                    });
                                                });
                                            });
                                        });
                                    });
                                });
                            } else {
                                pickerWheel1.sendKeys(Category).then(function () {
                                    dateTextbox.click().then(function () {
                                        commonUtilities.scrollToElementAndClick(attachmentLink, null, 6000, '40%,50%', '40%,10%').then(function () {
                                            commonUtilities.getDeviceProperty('bundleId').then(function (bundleId) {
                                                deviceUtilities.startImageInjectionByIdentifier(bundleId, 'PUBLIC:ExpenseTracker/Images/CreditCard.jpg').then(function () {
                                                    cameraBtn.click().then(function () {
                                                        commonUtilities.clickIfAvailable(OkButton, null, 6000).then(function () {
                                                            takePhotoBtn.click().then(function () {
                                                                usePhotoBtn.click().then(function () {
                                                                    saveButton.click().then(function () {
                                                                        OkButton.click();
                                                                    });
                                                                });
                                                            });
                                                        });
                                                    });
                                                });
                                            });
                                        });
                                    });
                                });
                            };
                        };
                    });
                });
            } catch (err) {
                console.log("********* Error in addAnExpense function: ")
                throw (err);
            }
        };

        this.logout = async function () {
            try {
                logoutButtonWElement = await this.getLocatorForElement('logout.buttonW');
                logoutButtonElement = await this.getLocatorForElement('logout.button');
                hamburgerMenuElement = await this.getLocatorForElement('hamburger.menu.button');
                OkButton = await this.getLocatorForElement('Ok.button');
                return commonUtilities.getDeviceProperty('appType').then(function (appType) {
                    appType = appType.toUpperCase();
                    commonUtilities.getDeviceProperty('platformName').then(function (platformName) {
                        platformName = platformName.toUpperCase();
                        if (appType == 'WEB') {
                            logoutButtonWElement.click();
                        } else if (appType == 'HYBRID' || platformName == 'IOS' || platformName == 'ANDROID') {
                            if (appType == 'HYBRID' || (platformName == 'ANDROID' && platformName == 'IOS')) {
                                uiObjectsUtilities.imageButtonClick('PUBLIC:ExpenseTracker/Images/hamburger.png', 80, 'bounded').then(function () {
                                });
                            } else {
                                hamburgerMenuElement.click();
                            }
                            logoutButtonElement.click();
                        }
                        if (appType == 'NATIVE' && platformName == 'ANDROID') {
                            OkButton.click();
                        }
                    });
                });
            } catch (err) {
                console.log("********* Error in logout function: ")
                throw (err);
            }
        };

        this.getLocatorForElement = async function (elementName) {
            try {
                var returnElement;
                var fullLocator = this.getProperty(elementName);
                var appType = await commonUtilities.getDeviceProperty('appType');
                appType = appType.toUpperCase();
                if (!(fullLocator == '' || fullLocator == null)) {
                    var index = fullLocator.indexOf('=');
                    var locatorType = fullLocator.substring(0, index);
                    var locatorValue = fullLocator.substring(index + 1);
                    if (locatorType == 'xpath')
                        returnElement = element(by.xpath(locatorValue));
                    else if (locatorType == 'id') {
                        if (appType == 'WEB' || appType == 'HYBRID')
                            returnElement = element(by.id(locatorValue));
                        else
                            returnElement = element(by.xpath("//*[@resource-id='" + locatorValue + "']"));
                    }
                    else if (locatorType == 'name') {
                        if (appType == 'WEB' || appType == 'HYBRID')
                            returnElement = element(by.name(locatorValue));
                        else
                            returnElement = element(by.xpath("//*[@name='" + locatorValue + "']"));
                    }
                    else if (locatorType == 'class')
                        returnElement = element(by.className(locatorValue));
                    else if (locatorType == 'css')
                        returnElement = element(by.css(locatorValue));
                }
                else {
                    console.log('******** locator not found in properties file for element : ', elementName);
                }
                return returnElement;
            }
            catch (err) {
                console.log("********* Error in getLocatorForElement function: ");
                throw (err);
            }
        };
        this.getProperty = function (propertyKey) {
            return properties.get(propertyKey);
        };
    }
}

module.exports = expenseTrackerPage;