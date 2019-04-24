# Protractor + Jasmine + Typescript
The project demonstrates automating perfecto's demo app "Expense Tracker" on [Protractor](http://www.protractortest.org/#/) tests written with [Jasmine](http://jasmine.github.io/) using [Typescript] (https://www.protractortest.org/#/typescript) 

:information_source: Click [here](http://developers.perfectomobile.com/display/PD/Simple+Browsing+Protractor+Code+Sample) for a guide to get started with Protractor.

## Getting started
Install NodeJS dependencies with this command:

> npm install

## project structure

1. Update your Perfecto credentials (either securityToken or Username and Password) and deviceName in [config.ts](config.ts). The apps specified in the capabilities are available on demo cloud under same repository structure.
2. App locators are found in properties file for each platform under locators folder under project's root folder
3. The functions to test the app are written in (ExpenseTrackerPage.ts) under pageObjects folder
4. Jasmine spec file (spec_expenseTracker.ts) calls the necessary function in ExpenseTrackerPage.ts to run the test
5. For parallel execution, add necessary capabilities under multiCapabilities field in (config.ts) file. This file already has the capabilities added in it to execute tests on Android, iOS and VMs for native/hybrid and web versions of this app. Comment/Uncomment the necessary capabilities before running it.
6. The utilities folder contains all Perfecto features as Typescript functions in ts files. 

## Running the test

You can run [spec_expenseTracker.ts](spec_expenseTracker.ts) with this command:

> npm test

## Scenario execution:

Native/Hybrid/Web App
1. Login to application with valid credentials
2. Initiate TouchID/FingerPrint authentication incase of Native/Hybrid
4. Click on Add expense
5. Enter details like, (head, amount, currency, category etc.)
6. Attach receipt using Perfecto Image injection incase of Native/Hybrid and using customizationScript and customizationScriptArgs capabilities for web
7. Save Expense
8. Logout from application

## 💡 important note
Jasmine framework does not wait for anything asynchronous that may have been started in a reporter on `specDone` or `suiteDone`.
To report testEnd from this methods please add this following code snippet in the end of any `describe` block.

```
afterAll(function(done){
    process.nextTick(done); // let all current waiting events to complete
});
```
For more details see: // https://github.com/angular/protractor/issues/1938

