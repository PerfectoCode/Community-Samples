var reportingClient;
var resourceArgs = { 'timeout': 120, 'fileslist': 'example.jpg' };

import { browser, Config } from 'protractor';

export let config: Config = {
    seleniumAddress: 'https://HOST.perfectomobile.com/nexperience/perfectomobile/wd/hub',
    multiCapabilities: [
        {
            securityToken: 'SECURITY TOKEN',
            bundleId: 'io.perfecto.expense.tracker',
            platformName: 'iOS',
            deviceName: 'DEVICE NAME',
            app: 'PUBLIC:ExpenseTracker/Native/InvoiceApp1.0.ipa',
            appType: 'Native',
            sensorInstrument: 'true',
            browserName: '',
            scriptName: 'iOS Native',
        },
        {
            securityToken: 'SECURITY TOKEN',
            appPackage: 'io.perfecto.expense.tracker',
            platformName: 'Android',
            deviceName: 'DEVICE NAME',
            app: 'PUBLIC:ExpenseTracker/Native/ExpenseAppVer1.0.apk',
            appType: 'Native',
            sensorInstrument: 'true',
            browserName: '',
            scriptName: 'Android Native',
        },
        {
            securityToken: 'SECURITY TOKEN',
            appPackage: 'io.perfecto.expense.tracker.hybrid',
            platformName: 'DEVICE NAME',
            deviceName: '988667424653364E48',
            app: 'PUBLIC:ExpenseTracker/Hybrid/ExpenseHybridAppVer1.0.apk',
            appType: 'Hybrid',
            sensorInstrument: 'true',
            autoInstrument: 'true',
            browserName: '',
            scriptName: 'Android Hybrid',
        },
        {
            securityToken: 'SECURITY TOKEN',
            bundleId: 'io.perfecto.expense.tracker.hybrid',
            platformName: 'iOS',
            deviceName: 'DEVICE NAME',
            app: 'PUBLIC:ExpenseTracker/Hybrid/InvoiceHybridApp1.0.ipa',
            appType: 'Hybrid',
            sensorInstrument: 'true',
            autoInstrument: 'true',
            browserName: '',
            scriptName: 'iOS Hybrid',
        },
        {
            securityToken: 'SECURITY TOKEN',
            platformName: 'Windows',
            platformVersion: '10',
            browserName: 'Chrome',
            browserVersion: 'latest',
            resolution: '1600x1200',
            location: 'EU Frankfurt',
            customizationScript: 'pm-upload-files.yml',
            customizationScriptArgs: resourceArgs,
            appType: 'Web',
            scriptName: 'Chrome browser VM',
        },
        {
            securityToken: 'SECURITY TOKEN',
            platformName: 'Windows',
            platformVersion: '10',
            browserName: 'Firefox',
            browserVersion: 'latest',
            resolution: '1600x1200',
            location: 'US East',
            customizationScript: 'pm-upload-files.yml',
            customizationScriptArgs: resourceArgs,
            appType: 'Web',
            scriptName: 'Firefox browser VM',
        },
        // {
        //     securityToken: 'SECURITY TOKEN',
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
        // {
        //     securityToken: 'SECURITY TOKEN',
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
            tags: ['expenseTrackerTS', 'expenseTrackerJasmineTS']
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