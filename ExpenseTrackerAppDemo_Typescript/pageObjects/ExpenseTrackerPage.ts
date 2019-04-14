import { protractor, browser, element, by, ElementFinder } from 'protractor';
import { Reader } from 'properties-reader';
import {applicationUtilities} from '../utilities/ApplicationUtilities';
import {uiObjectsUtilities} from '../utilities/uiObjectsUtilities';
import {deviceUtilities} from '../utilities/deviceUtilities';
import {otherUtilities} from '../utilities/otherUtilities';
import {commonUtilities} from '../utilities/commonUtilities';

let ApplicationUtilities = new applicationUtilities();
let UiObjectsUtilities = new uiObjectsUtilities();
let DeviceUtilities = new deviceUtilities();
let OtherUtilities = new otherUtilities();
let CommonUtilities = new commonUtilities();

let expCondn = protractor.ExpectedConditions;
let properties: Reader;

let usernameElement : ElementFinder;
let passwordElement : ElementFinder;
let loginElement : ElementFinder;
let enableTouchIdElement : ElementFinder;
let OkButton : ElementFinder;
let addButtonweb : ElementFinder;
let attachReceiptButton : ElementFinder;
let addButtonmobile : ElementFinder;
let headDropdown : ElementFinder;
let headFlightDropdown : ElementFinder;
let amountTextbox : ElementFinder;
let currencyDropdown : ElementFinder;
let currencyInrDropdown : ElementFinder;
let categoryDropdown : ElementFinder;
let categoryBusinessDropdown : ElementFinder;
let attachmentLink : ElementFinder;
let addAttachmentLink : ElementFinder;
let cameraBtn : ElementFinder;
let okButton : ElementFinder;
let addAttachmentAllowBtn : ElementFinder;
let attachmentViewLnk : ElementFinder;
let detailsTextbox : ElementFinder;
let saveButton : ElementFinder;
let popUpOKButton : ElementFinder;
let addLabel : ElementFinder;
let headTextbox : ElementFinder;
let currencyTextbox : ElementFinder;
let categoryTextbox : ElementFinder;
let dateTextbox : ElementFinder;
let pickerWheel1 : ElementFinder;
let takePhotoBtn : ElementFinder;
let usePhotoBtn : ElementFinder;
let logoutButtonWElement : ElementFinder;
let logoutButtonElement : ElementFinder;
let hamburgerMenuElement : ElementFinder;

export function initProperties (properties1: Reader) {
    properties = properties1;
}

interface Login {
    username: string,
    password: string,
}

export async function loginToApplication(loginDetails: Login) {
    try{
        usernameElement = await getLocatorForElement('username.text.box');
        passwordElement = await getLocatorForElement('password.text.box');
        loginElement = await getLocatorForElement('login.button');
        enableTouchIdElement = await getLocatorForElement('enable.TouchID.button');
        OkButton = await getLocatorForElement('Ok.button');
        return CommonUtilities.getDeviceProperty('appType').then(function (appType) {
            if (!(appType == null || appType == '')) {
                appType = appType.toUpperCase();
            }
            CommonUtilities.getDeviceProperty('platformName').then(function (platformName) {
                if (!(platformName == null || platformName == '')) {
                    platformName = platformName.toUpperCase();
                }
                if (appType == 'HYBRID' || appType == 'NATIVE') {
                    if (appType == 'HYBRID'){
                        console.log('********* HYBRID app so switching to WEBVIEW context');
                        CommonUtilities.switchAppContext("WEBVIEW").then(function () {});
                    } 
                    browser.wait(expCondn.visibilityOf(enableTouchIdElement), 60000).then(function () {
                        console.log('********* HYBRID or NATIVE app so trying touchID login');
                        enableTouchIdElement.click();
                    });
                }
                else if (appType == 'WEB'){
                    console.log('********* WEB app so launching url');
                    CommonUtilities.getURL('http://expensetracker.perfectomobile.com/');
                }
                CommonUtilities.getDeviceProperty('browserName').then(function (browserName) {
                    if (browserName == 'Firefox' ) {
                        browser.wait(expCondn.visibilityOf(usernameElement), 60000).then(function () {
                            usernameElement.sendKeys(loginDetails.username).then(function() {
                                passwordElement.sendKeys(loginDetails.password).then(function() {
                                    loginElement.click();
                                });
                            });
                        });
                    } else {
                        browser.wait(expCondn.visibilityOf(usernameElement), 60000).then(function () {
                            usernameElement.click().then(function() {
                                browser.actions().sendKeys(loginDetails.username).perform().then(function() {
                                    passwordElement.click().then(function() {
                                        browser.actions().sendKeys(loginDetails.password).perform().then(function() {
                                            if (platformName == 'IOS' || platformName == 'ANDROID') {
                                                if (platformName == 'IOS') {
                                                    loginElement.click().then(function () {
                                                        CommonUtilities.getDeviceProperty('bundleId').then(async function (bundleId) {
                                                            await ApplicationUtilities.setFingerPrintByAppIdentifierForFailure(bundleId,"userCancel").then(async function (result) {
                                                                await browser.sleep(6000).then(async function () {
                                                                    await browser.wait(expCondn.visibilityOf(OkButton), 60000).then(function () {
                                                                        OkButton.click().then(function() {
                                                                            loginElement.click().then(async function () {
                                                                                await ApplicationUtilities.setFingerPrintByAppIdentifierForSuccess(bundleId).then(function (result) {
                                                                                    browser.sleep(6000);
                                                                                });
                                                                            });
                                                                        });
                                                                    });
                                                                });
                                                            });
                                                        });
                                                    });
                                                } else {
                                                    loginElement.click().then(function () {
                                                        CommonUtilities.getDeviceProperty('appPackage').then(function (appPackage) {
                                                            ApplicationUtilities.setFingerPrintByAppIdentifierForSuccess(appPackage).then(function () {
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
                        });
                    }
                });
            });
        });
        } catch(err) {
            console.log("********* Error in loginToApplication function: ")
            throw(err);
        }
};

interface AddExpense {
    Head: string,
    Amount: string,
    Currency: string,
    Category: string,
    ReceiptFile?: string,
    Details?: string
}

export async function addAnExpense(expenseDetails: AddExpense) {
    try{
        addButtonweb = await getLocatorForElement('add.buttonweb');
        attachReceiptButton = await getLocatorForElement('attachReceipt.button');
        addButtonmobile = await getLocatorForElement('add.buttonmobile');
        headDropdown = await getLocatorForElement('head.dropdown');
        headFlightDropdown = await getLocatorForElement('head.flight.dropdown');
        amountTextbox = await getLocatorForElement('amount.text.box');
        currencyDropdown = await getLocatorForElement('currency.dropdown');
        currencyInrDropdown = await getLocatorForElement('currency.inr.dropdown');
        categoryDropdown = await getLocatorForElement('category.dropdown');
        categoryBusinessDropdown = await getLocatorForElement('category.business.dropdown');
        attachmentLink = await getLocatorForElement('attachment.link');
        addAttachmentLink = await getLocatorForElement('add.attachment');
        cameraBtn = await getLocatorForElement('camera.btn');
        addAttachmentAllowBtn = await getLocatorForElement('addAttachmentAllow.btn');
        attachmentViewLnk = await getLocatorForElement('attachmentView.lnk');
        detailsTextbox = await getLocatorForElement('details.text.box');
        saveButton = await getLocatorForElement('save.button');
        popUpOKButton = await getLocatorForElement('pop-up.OK.btn');
        addLabel = await getLocatorForElement('add.label');
        headTextbox = await getLocatorForElement('head.text.box');
        currencyTextbox = await getLocatorForElement('currency.text.box');
        categoryTextbox = await getLocatorForElement('category.text.box');
        dateTextbox = await getLocatorForElement('date.text.box');
        pickerWheel1 = await getLocatorForElement('pickerWheel1');
        OkButton = await getLocatorForElement('Ok.button');
        takePhotoBtn = await getLocatorForElement('takePhoto.btn');
        usePhotoBtn = await getLocatorForElement('usePhoto.btn');

        return CommonUtilities.getDeviceProperty('appType').then(function (appType) {
            appType = appType.toUpperCase();
            CommonUtilities.getDeviceProperty('platformName').then(function (platformName) {
                platformName = platformName.toUpperCase();
                if (appType == 'HYBRID' || appType == 'WEB') {
                    if(appType == 'WEB'){
                        browser.wait(expCondn.visibilityOf(addButtonweb), 60000).then(function () {
                            addButtonweb.click().then(function() {
                            });
                        });
                    } else{
                        browser.wait(expCondn.visibilityOf(addButtonmobile), 60000).then(function () {
                            addButtonmobile.click();
                        });
                    }
                    browser.wait(expCondn.visibilityOf(headDropdown), 60000).then(function () {
                        headDropdown.click().then(function() {
                            headFlightDropdown.click().then(function() {
                                popUpOKButton.click().then(function() {
                                    if (platformName == 'ANDROID'){
                                        amountTextbox.click().then(function() {
                                            browser.actions().sendKeys(expenseDetails.Amount).perform().then(function() {
                                            });
                                        });
                                    } else {
                                        amountTextbox.sendKeys(expenseDetails.Amount).then(function() {
                                        });
                                    }
                                    currencyDropdown.click().then(function() {
                                        currencyInrDropdown.click().then(function() {
                                            popUpOKButton.click().then(function() {
                                                categoryDropdown.click().then(function() {
                                                    categoryBusinessDropdown.click().then(function() {
                                                        popUpOKButton.click().then(function() {
                                                            if (platformName == 'ANDROID'){
                                                                CommonUtilities.getDeviceProperty('appPackage').then(function (appPackage) {
                                                                    browser.wait(expCondn.visibilityOf(addAttachmentLink), 60000).then(function () {
                                                                        addAttachmentLink.click().then(function () {
                                                                            DeviceUtilities.startImageInjectionByIdentifier(appPackage,'PUBLIC:ExpenseTracker/Images/CreditCard.jpg').then(function () {
                                                                                browser.wait(expCondn.visibilityOf(cameraBtn), 20000).then(function () {
                                                                                    cameraBtn.click().then(function () {
                                                                                        CommonUtilities.switchAppContext("NATIVE_APP").then(function () {
                                                                                            browser.wait(expCondn.visibilityOf(addAttachmentAllowBtn), 20000).then(function () {
                                                                                                addAttachmentAllowBtn.click().then(function () {
                                                                                                    addAttachmentAllowBtn.click().then(function () {
                                                                                                        CommonUtilities.switchAppContext("WEBVIEW")
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
                                                            } 
                                                            CommonUtilities.scrollToElementAndClick(saveButton,null,20000,'40%,90%','40%,10%').then(function() {
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
                    browser.wait(expCondn.visibilityOf(addLabel), 60000).then(function () {
                        addLabel.click().then(function() {
                            headTextbox.click();
                        });
                    });
                    if (platformName == 'ANDROID') {
                        browser.wait(expCondn.visibilityOf(element(by.xpath("//*[@text='" + expenseDetails.Head + "']"))), 60000).then(function () {
                            element(by.xpath("//*[@text='" + expenseDetails.Head + "']")).click();
                        });
                    } else {
                        CommonUtilities.setPickerWheel(pickerWheel1, "next", expenseDetails.Head);
                    }
                    browser.wait(expCondn.visibilityOf(amountTextbox), 60000).then(function () {
                        amountTextbox.sendKeys(expenseDetails.Amount).then(function() {
                            currencyTextbox.click();
                        });
                    });
                    if (platformName == 'ANDROID') {
                        expenseDetails.Currency = expenseDetails.Currency.replace(/\s/g, "");
                        browser.wait(expCondn.visibilityOf(element(by.xpath("//*[@text='" + expenseDetails.Currency + "']"))), 60000).then(function () {
                            element(by.xpath("//*[@text='" + expenseDetails.Currency + "']")).click();
                        });
                    } else {
                        CommonUtilities.setPickerWheel(pickerWheel1, "next", expenseDetails.Currency);
                    }
                    browser.wait(expCondn.visibilityOf(categoryTextbox), 60000).then(function () {
                        categoryTextbox.click();
                    });
                    if (platformName == 'ANDROID') {
                        browser.wait(expCondn.visibilityOf(element(by.xpath("//*[@text='" + expenseDetails.Category + "']"))), 60000).then(function () {
                            element(by.xpath("//*[@text='" + expenseDetails.Category + "']")).click().then(function () {
                                CommonUtilities.getDeviceProperty('appPackage').then(function (appPackage) {
                                    browser.wait(expCondn.visibilityOf(attachmentLink), 60000).then(function () {
                                        attachmentLink.click().then(function () {
                                            DeviceUtilities.startImageInjectionByIdentifier(appPackage,'PUBLIC:ExpenseTracker/Images/CreditCard.jpg').then(function () {
                                                browser.wait(expCondn.visibilityOf(cameraBtn), 20000).then(function () {
                                                    cameraBtn.click().then(function () {
                                                        browser.wait(expCondn.visibilityOf(OkButton), 20000).then(function () {
                                                            OkButton.click().then(function () {
                                                                browser.wait(expCondn.visibilityOf(saveButton), 20000).then(function () {
                                                                    saveButton.click();
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
                        CommonUtilities.setPickerWheel(pickerWheel1, "next", expenseDetails.Category);
                        browser.wait(expCondn.visibilityOf(dateTextbox), 60000).then(function () {
                            dateTextbox.click().then(function() {
                                attachmentLink.click().then(function () {
                                    CommonUtilities.getDeviceProperty('bundleId').then(function (bundleId) {
                                        DeviceUtilities.startImageInjectionByIdentifier(bundleId,'PUBLIC:ExpenseTracker/Images/CreditCard.jpg').then(function () {
                                            cameraBtn.click().then(function () {
                                                OkButton.click().then(function() {
                                                    takePhotoBtn.click().then(function() {
                                                        usePhotoBtn.click().then(function() {
                                                            saveButton.click().then(function() {
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
    } catch(err) {
        console.log("********* Error in addAnExpense function: ")
        throw(err);
    }
};

export async function logout() {
    try{
        logoutButtonWElement = await getLocatorForElement('logout.buttonW');
        logoutButtonElement = await getLocatorForElement('logout.button');
        hamburgerMenuElement = await getLocatorForElement('hamburger.menu.button');
        OkButton = await getLocatorForElement('Ok.button');
        return CommonUtilities.getDeviceProperty('appType').then(function (appType) {
            appType = appType.toUpperCase();
            CommonUtilities.getDeviceProperty('platformName').then(function (platformName) {
                platformName = platformName.toUpperCase();
                if (appType == 'WEB') {
                    browser.wait(expCondn.visibilityOf(logoutButtonWElement), 60000).then(function () {
                        logoutButtonWElement.click();
                    });
                } else if (appType == 'HYBRID' || platformName == 'IOS' || platformName == 'ANDROID'){
                    browser.wait(expCondn.visibilityOf(hamburgerMenuElement), 60000).then(function () {
                        if (appType == 'HYBRID' || platformName == 'ANDROID'){
                            UiObjectsUtilities.imageButtonClick('PUBLIC:ExpenseTracker/Images/hamburger.png',80,'bounded').then(function() {
                            });
                        } else {
                            hamburgerMenuElement.click();
                        }
                        logoutButtonElement.click();
                    });
                }
                if (appType == 'NATIVE' && platformName == 'ANDROID') {
                    OkButton.click();
                }
            });
        });
    } catch(err) {
        console.log("********* Error in logout function: ")
        throw(err);
    }
};

async function getLocatorForElement(elementName: string){
    try{
        let returnElement : ElementFinder;
        let fullLocator =  getProperty(elementName) as string;
        let appType = await CommonUtilities.getDeviceProperty('appType');
        appType = appType.toUpperCase();
        if (!(fullLocator == '' || fullLocator ==  null)){
            let index = fullLocator.indexOf('=');
            let locatorType = fullLocator.substring(0,index);
            let locatorValue = fullLocator.substring(index+1);
            if (locatorType == 'xpath')
                returnElement = element(by.xpath(locatorValue));
            else if (locatorType == 'id'){
                if (appType == 'WEB' || appType == 'HYBRID')
                    returnElement = element(by.id(locatorValue));
                else
                    returnElement = element(by.xpath("//*[@resource-id='"+locatorValue+"']"));
            } else if (locatorType == 'name'){
                if (appType == 'WEB' || appType == 'HYBRID')
                    returnElement = element(by.name(locatorValue));
                else
                    returnElement = element(by.xpath("//*[@name='"+locatorValue+"']"));
            } else if (locatorType == 'class')
                returnElement = element(by.className(locatorValue));
            else if (locatorType == 'css')
                returnElement = element(by.css(locatorValue));
        } else {
            console.log('******** locator not found in properties file for element : ',elementName);
        }
        return returnElement;
    } catch(err) {
        console.log("********* Error in getLocatorForElement function: ")
        throw(err);
    }
}

function getProperty(propertyKey: string){
    return properties.get(propertyKey);
}

