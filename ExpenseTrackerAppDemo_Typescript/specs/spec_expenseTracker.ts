import { browser } from 'protractor';
import { initProperties, loginToApplication, addAnExpense, logout } from '../pageObjects/ExpenseTrackerPage';
import { Reader } from 'properties-reader';
import { commonUtilities } from '../utilities/commonUtilities';
import { applicationUtilities } from '../utilities/ApplicationUtilities';

fdescribe('expense tracker', function () {
    let CommonUtilities = new commonUtilities();
    let ApplicationUtilities = new applicationUtilities();
    let PropertiesReader: any;
    let properties: Reader;

    beforeAll(function () {
        PropertiesReader = require('properties-reader');
        CommonUtilities.getDeviceProperty('appType').then(function (appType) {
            if (!(appType == null || appType == '')) {
                appType = appType.toUpperCase();
            }
            CommonUtilities.getDeviceProperty('platformName').then(function (platformName) {
                if (!(platformName == null || platformName == '')) {
                    platformName = platformName.toUpperCase();
                }
                if (appType == 'NATIVE' && platformName == 'IOS') {
                    console.log("********* Getting locators from 'expenseTrackerIOS.properties' file");
                    properties = PropertiesReader('./locators/iOS/expenseTrackerIOS.properties');
                } else if (appType == 'NATIVE' && platformName == 'ANDROID') {
                    console.log("********* Getting locators from 'expenseTrackerAndroid.properties' file");
                    properties = PropertiesReader('./locators/android/expenseTrackerAndroid.properties');
                } else {
                    console.log("********* Getting locators from 'expenseTracker.properties' file");
                    properties = PropertiesReader('./locators/web/expenseTracker.properties');
                }
                browser.ignoreSynchronization = true;
                initProperties(properties);
            });
        });
    });

    beforeEach(function () {

    });

    it('loginAddExpenseLogout', async function () {
        let loginDetails = {
            username: 'test@perfecto.com',
            password: 'test123',
        }

        let expenseDetails = {
            Head: 'Flight',
            Amount: '3000',
            Currency: 'USD - $',
            Category: 'Personal',
            ReceiptFile: 'C:/temp/example.jpg',
            Details: 'Client visit bill'
        }
        await loginToApplication(loginDetails).then(async function () {
            await addAnExpense(expenseDetails).then(async function () {
                await logout().then(function () {
                    browser.sleep(5000);
                });
            });
        });
    });

    afterAll(function (done) {
        process.nextTick(done); // let all current waiting events to complete
    });

});