fdescribe('expense tracker', function () {
    var commonUtilities;
    var applicationUtilities;
    var PropertiesReader;
    var properties;
    var expenseTrackerPage;

    beforeAll(function () {
        commonUtilities = require('../utilities/CommonUtilities.js').commonUtilities;
        commonUtilities = new commonUtilities();
        applicationUtilities = require('../utilities/ApplicationUtilities.js').applicationUtilities;
        applicationUtilities = new applicationUtilities();
        PropertiesReader = require('properties-reader');
        commonUtilities.getDeviceProperty('appType').then(function (appType) {
            if (!(appType == null || appType == '')) {
                appType = appType.toUpperCase();
            }
            commonUtilities.getDeviceProperty('platformName').then(function (platformName) {
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
                expenseTrackerPage = require('../pageObjects/ExpenseTrackerPage.js');
                expenseTrackerPage = new expenseTrackerPage(commonUtilities,properties);
                browser.ignoreSynchronization = true;
            });
        });
    });

    beforeEach(function () {

    });

    it('loginAddExpenseLogout', async function () {
        await expenseTrackerPage.loginToApplication('test@perfecto.com','test123').then(async function () {
           await expenseTrackerPage.addAnExpense('Flight','3000','USD - $','Personal','C:/temp/example.jpg','Client visit bill').then(async function () {
               await expenseTrackerPage.logout().then(function () {
                    browser.sleep(5000);
               });
           });
        });
    });

    afterAll(function(done){
        process.nextTick(done); // let all current waiting events to complete
    });

});