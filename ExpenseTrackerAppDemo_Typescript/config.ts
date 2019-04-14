var reportingClient;
var resourceArgs = { 'timeout': 120, 'fileslist': 'example.jpg' };

import { browser, Config } from 'protractor';

export let config: Config = {
seleniumAddress: 'https://demo.perfectomobile.com/nexperience/perfectomobile/wd/hub',
    multiCapabilities: [
        // {
        //     securityToken: '',
        //     bundleId: 'io.perfecto.expense.tracker',
        //     platformName: 'iOS',
        //     deviceName: '9C71D808D1C001F1B3DE289247CC4080B36C880D', // demo cloud device
        //     app: 'PUBLIC:ExpenseTracker/Native/InvoiceApp1.0.ipa',
        //     appType: 'Native',
        //     sensorInstrument : 'true',
        //     browserName: '',
        //     scriptName: 'iOS Native',
        // },
        // {
        //     securityToken: '',
        //     appPackage: 'io.perfecto.expense.tracker',
        //     platformName: 'Android',
        //     deviceName: '988633475656463938', // demo cloud device
        //     app: 'PUBLIC:ExpenseTracker/Native/ExpenseAppVer1.0.apk',
        //     appType: 'Native',
        //     sensorInstrument : 'true',
        //     browserName: '',
        //     scriptName: 'Android Native',
        // },
        {
            securityToken: '',
            appPackage: 'io.perfecto.expense.tracker.hybrid',
            platformName: 'Android',
            deviceName: '988667424653364E48', // demo cloud device
            app: 'PUBLIC:ExpenseTracker/Hybrid/ExpenseHybridAppVer1.0.apk',
            appType: 'Hybrid',
            sensorInstrument : 'true',
            autoInstrument : 'true',
            browserName: '',
            scriptName: 'Android Hybrid',
        },
        // {
        //     securityToken: '',
        //     bundleId: 'io.perfecto.expense.tracker',
        //     platformName: 'iOS',
        //     deviceName: '8AF8F6AFBC0F6CF46617EE6EFA70D140DA8D8965', // demo cloud device
        //     app: 'PUBLIC:ExpenseTracker/Hybrid/InvoiceAppHybrid.ipa',
        //     appType: 'Hybrid',
        //     sensorInstrument : 'true',
        //     autoInstrument : 'true',
        //     browserName: '',
        //     scriptName: 'iOS Hybrid',
        // },
        // {
        //     securityToken: '',
        //     platformName: 'Windows',
        //     platformVersion: '10',
        //     browserName: 'Chrome',
        //     browserVersion: 'latest',
        //     resolution: '1600x1200',
        //     location: 'EU Frankfurt',
        //     customizationScript: 'pm-upload-files.yml',
        //     customizationScriptArgs: resourceArgs,
        //     appType: 'Web',
        //     scriptName: 'Chrome browser VM',
        // },
        // {
        //     securityToken: '',
        //     platformName: 'Windows',
        //     platformVersion: '10',
        //     browserName: 'Firefox',
        //     browserVersion: 'latest',
        //     resolution: '1600x1200',
        //     location: 'US East',
        //     customizationScript: 'pm-upload-files.yml',
        //     customizationScriptArgs: resourceArgs,
        //     appType: 'Web',
        //     scriptName: 'Firefox browser VM',
        // },
        // {
        //     securityToken: '',
        //     platformName: 'Windows',
        //     platformVersion: '10',
        //     browserName: 'Internet Explorer',
        //     browserVersion: '11',
        //     resolution: '1600x1200',
        //     location: 'US East',
        //     customizationScript: 'pm-upload-files.yml',
        //     customizationScriptArgs: resourceArgs,
        //     appType: 'Web',
        //     scriptName: 'IE browser VM',
        // },
        // {
        //     securityToken: '',
        //     platformName: 'Windows',
        //     platformVersion: '10',
        //     browserName: 'Edge',
        //     browserVersion: '17',
        //     resolution: '1600x1200',
        //     location: 'US East',
        //     customizationScript: 'pm-upload-files.yml',
        //     customizationScriptArgs: resourceArgs,
        //     appType: 'Web',
        //     scriptName: 'Edge browser VM',
        // },
    ],
framework: 'jasmine',
specs: ['./specs/spec_expenseTracker.js'],
    jasmineNodeOpts: {
        showColors: true,
        defaultTimeoutInterval: 400000
    },

    onComplete: function () {
        return reportingClient.getReportUrl().then(function (url) {
            console.log("Report url = '" + url + "'");
        });
    },

    onPrepare: function () {
        const Reporting = require('perfecto-reporting');
        browser.manage().timeouts().implicitlyWait(30000);
        reportingClient = new Reporting.Perfecto.PerfectoReportingClient(new Reporting.Perfecto.PerfectoExecutionContext({
            webdriver: browser.driver,
            tags: ['expenseTracker', 'expenseTrackerJasmine']
        }));
        browser.reportingClient = reportingClient;

        var myReporter = {
            specStarted: function (result) {
                reportingClient.testStart(result.fullName);
            },
            specDone: function (result) {
                if (result.status === 'failed') {
                    const failure = result.failedExpectations[result.failedExpectations.length - 1];
                    reportingClient.testStop({
                        status: Reporting.Constants.results.failed,
                        message: `${failure.message} ${failure.stack}`
                    });
                } else {
                    reportingClient.testStop({
                        status: Reporting.Constants.results.passed
                    });
                }
            }
        }
        jasmine.getEnv().addReporter(myReporter);
    }
}