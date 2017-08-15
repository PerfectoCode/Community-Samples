# HPE ALM Reporting Bridge #

## Overview ##
The HPE ALM Reporting Bridge is a C# wrapper implementation of the ALM Open Test Architecture (OTA) API. It has been written to support the specific needs of running Selenium and Appium tests written in a variety of languages while still using HPE ALM as a reporting repository. 

The reporting bridge is compiled into a single Windows executable that is run from a command prompt. It can be used in batch jobs, or embedded inside of any test execution framework. When executed, the application will return an XML string with the status of the operation, entity IDs, and any errors. 

The full code of the project is provided here along with a full suite of unit tests. The command line unit tests are especially helpful to understand how individual commands are assembled. 

## Requirements ##
- HPE ALM/Quality Center 11+
- Microsoft .NET Framework 4.5.2 or higher
- Machine executing the command line must have either the HPE ALM Client Components registered (by following **Tools** > **HP ALM Client Registration** > **Register HP ALM** links from the spash screen) or by installing the HP ALM Connectivity Tool (by following **Tools** > **HP ALM  Connectivity** > **Download HP ALM Connectivity** from the splash screen)
- Must have permission to perform the operation in the ALM project

## Using the Reporting Bridge ##
To use the reporting bridge in a project, embed the contents of the *ALMReportingBridge/bin/x86/Release/* directory into a folder in your project. Note that while only *ALMReportingBridge.exe* and *CLAP.dll* are required to run, including the additional configuration files can be helpful when resolving library dependency issues that are common in Windows machines. 

The reporting bridge works on a simple format: `filename.exe command /argument=value`

Example: `ALMReportingBridge.exe test /serverurl=http://myalmserver:8080/qcbin /username=johndoe /password=mypassword /domain=ALMREPORTINGBRIDGE /project=ConnectionTests`

Running the executable with no arguments or by using the ***/h*** argument will display the help. The help displays all of the possible commands, arguments, and data types for each command. When a command is executed, the results of the command are returned in XML. 

Any argument value that contains a space must be encapsulated by quotations. For example: `/password="my password contains spaces"`.

Note: When running the executable (with or without arguments), it first checks if the ALM OTA API components are registered on the machine. If they are not, you will receive the following error: 
<pre>Error: HPE ALM OTA client is not registered on this machine.</pre>
Should you receive this error, refer to **Requirements** section above for information in registering the ALM Client Components. 

## Commands ##
| Command     		 | Description	 | 
|--------	 	     | ---			 | 
| copytestset        | Copies an ALM test set to the destination folder using a test set ID. Useful when copying a template test set for a test suite that runs frequently. The test set copy only copies the test instances -- no run data is copied. Therefore all tests have a "No Run" initial status. |
| updaterunfield     | Updates a field associated to the Run entity to the specified value based on the Run ID. The *fieldname* must be the database identifier of the field, not the logical ALM name. |
| recordrunresult    | Records a run result to a test instance. This command creates a new run for the test instance with the status provided. | 
| createtestset      | Creates a new empty test set in the specified folder | 
| updatetestsetfield | Updates the value of a test set field by its database name. | 
| addtesttotestset   | Adds a test configuration to a test set. Note that this function is not safe for parallelization across threads when adding test configurations of the same parent test. See the detailed note below. | 
| attachtorun        | Uploads attachment to a run. The attachment can be a file or a URL. | 
| attachtotestset    | Uploads attachment to a test set. The attachment can be a file or a URL. | 
| test               | Tests the connection to the ALM server | 

## Example Command Return Values ##
### copytestset ###
<pre>
&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;CopyTestSet&lt;/operation&gt;
  &lt;status&gt;Success&lt;/status&gt;
  &lt;entityid&gt;2721&lt;/entityid&gt;
  &lt;message&gt;Error: a test set with the same name as the template already exists in the test set folder
The new test set was created, but will have the suffix "_copy_ appended to the name&lt;/message&gt;
&lt;/body&gt;
</pre>

<pre>
&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;CopyTestSet&lt;/operation&gt;
  &lt;status&gt;Failed&lt;/status&gt;
  &lt;message&gt;Error: Node not found.
Unhandled: -2147220481
56628227&lt;/message&gt;
&lt;/body&gt;
</pre>

### updaterunfield ###
<pre>
&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;UpdateRunField&lt;/operation&gt;
  &lt;status&gt;Success&lt;/status&gt;
&lt;/body&gt;
</pre>

<pre>
&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;UpdateRunField&lt;/operation&gt;
  &lt;status&gt;Failed&lt;/status&gt;
  &lt;message&gt;Error: Invalid field name &lt; RN_USER_99 &gt;.
Refer to the HPE ALM Project Database Reference for valid field names&lt;/message&gt;
&lt;/body&gt;
</pre>


### recordreunresult ###
<pre>
&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;RecordRunResult&lt;/operation&gt;
  &lt;status&gt;Success&lt;/status&gt;
  &lt;entityid&gt;510&lt;/entityid&gt;
&lt;/body&gt;
</pre>

<pre>
&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;RecordRunResult&lt;/operation&gt;
  &lt;status&gt;Failed&lt;/status&gt;
  &lt;message&gt;Error: Entity with key '3' does not exist in table 'CYCLE'
Test set does not exist.&lt;/message&gt;
&lt;/body&gt;
</pre>


### createtestset ###
<pre>
&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;CreateTestSet&lt;/operation&gt;
  &lt;status&gt;Success&lt;/status&gt;
  &lt;entityid&gt;2719&lt;/entityid&gt;
&lt;/body&gt;
</pre>

<pre>&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;CreateTestSet&lt;/operation&gt;
  &lt;status&gt;Failed&lt;/status&gt;
  &lt;message&gt;Error: Node not found.
Unhandled: -2147220481
32972388&lt;/message&gt;
&lt;/body&gt;
</pre>


### updatetestsetfield ###
<pre>
&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;UpdateTestSetField&lt;/operation&gt;
  &lt;status&gt;Success&lt;/status&gt;
&lt;/body&gt;
</pre>

<pre>
&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;UpdateTestSetField&lt;/operation&gt;
  &lt;status&gt;Failed&lt;/status&gt;
  &lt;message&gt;Error: Exception from HRESULT: 0x8004051A
Refer to the HPE ALM project customization for valid transitions for this field.&lt;/message&gt;
&lt;/body&gt;
</pre>


### addtesttotestset ###
<pre>
&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;AddTestToTestSet&lt;/operation&gt;
  &lt;status&gt;Success&lt;/status&gt;
&lt;/body&gt;
</pre>

<pre>
&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;AddTestToTestSet&lt;/operation&gt;
  &lt;status&gt;Failed&lt;/status&gt;
  &lt;message&gt;Error: '5d676' is out of range for an integer value.
Refer to the HPE ALM Project Database Reference or the project customization for the correct data type for the field&lt;/message&gt;
&lt;/body&gt;
</pre>


### attachtorun ###
<pre>
&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;AttachToRun&lt;/operation&gt;
  &lt;status&gt;Success&lt;/status&gt;
&lt;/body&gt;
</pre>

<pre>
&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;AttachToRun&lt;/operation&gt;
  &lt;status&gt;Failed&lt;/status&gt;
&lt;/body&gt;
</pre>


### attachtotestset ###
<pre>
&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;AttachToTestSet&lt;/operation&gt;
  &lt;status&gt;Success&lt;/status&gt;
&lt;/body&gt;
</pre>

<pre>
&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;AttachToTestSet&lt;/operation&gt;
  &lt;status&gt;Failed&lt;/status&gt;
  &lt;message&gt;Unhandled Error
Error: You do not have the required permissions to execute this action.
-2147219913&lt;/message&gt;
&lt;/body&gt;
</pre>

### test ###
<pre>
&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;Test&lt;/operation&gt;
  &lt;status&gt;Success&lt;/status&gt;
&lt;/body&gt;
</pre>

<pre>
&lt;?xml version="1.0" encoding="utf-16" standalone="yes"?&gt;
&lt;body&gt;
  &lt;operation&gt;Test&lt;/operation&gt;
  &lt;status&gt;Failed&lt;/status&gt;
  &lt;message&gt;Error: Server is not available
Check the server URL. Server URLs must be in the following format: 
http://hostname:port/qcbin
Example: http://alm.mydomain.com:8080/qcbin&lt;/message&gt;
&lt;/body&gt;
</pre>

## Formatting Multifield Inputs on Creation ##
During the entity creation process (specifically commands *recordrunresults*, *createtestset*, and *addtesttotestset*), your ALM project customization may require the population of one or many required fields before the entity can be successfully created. These commands include the */additional* argument which provide a means of supplying data for required fields that may not be otherwise accounted for. The format for this data is an array formatted as follows:
<pre><code>fieldName;;value,fieldName;;value</code></pre>

*fieldName* must correspond to the ALM Project database name of the field (*CY\_USER\_01**, for example). 

For example: 
<pre><code>/additional="CY_USER_01;;Regression Test,CY_USER_02;;SIT_2,CY_USER_03;;Ronald Swanson"</code></pre>

Note that if mulitple fields are required to be updated after an entity is created, simply run the corresponding update command multiple times, one for each field to be updated. 

## Understanding Tests vs. Test Configurations ##
ALM projects use test entities to represent a test that is to be run. Digital testing creates a new problem in that the same test may need to be executed on many different browsers or device combinations. Creating unique test entities for each combination, while possible, replicates data and tests and generally makes the project harder to manage. 

To solve this problem, HP introduced the concept of a Test Configuration. Test configurations allow the parameterization of tests so that they can be run many times without having to duplicate the test entities. A Test Configuration represents a unique combination of a test and a data scenario. In this case, the data scenario is the device or browser under test. As an example, you may have a test that verifies an account balance. That test may have 10 Test Configurations -- one for each mobile device in the coverage model. 

This project uses the Test Configuration model when interacting with test cases. 

## ALM Project Customization ##
The reporting bridge itself does not need any special HP ALM customization. However, the reliance on test configurations for the test mapping requires these configurations be built. Since the configurations are typically going to be the same or similar across an ALM project, the process of creating them can be expedited by using a small piece of workflow code in the ALM project. The code is triggered from a custom button that is added into the Test Plan module. When clicked, it creates configurations with names listed in a custom Project List, then removes the default configuration. This code dramatically speeds up the process of creating test configurations for your test cases.

The ***ALM Project Code*** folder contains two files of workflow code that can be included in your ALM project. Test Plan code contained in *testplan.tds* can be copied and pasted into the Test Plan module workflow customization editor unchanged. Code in the *common.tds* file should be copied into the Common module workflow customization editor, then modified for your ALM project. Line 1 of this file contains the following code:
<code>Const TESTING_COMBINATIONS = "Testing Combinations"  </code>

This variable corresponds to a Project List that must be created in the ALM project (in this example, *"Testing Combinations"*. This project list should only have items listed at the Root level and should contain names of configurations you want created in your ALM project. These typically are devices or browsers that you intend to test on. For example:


- Safari - iPhone 6 - iOS 9
- Safari - iPhone 7 - iOS 10
- Safari - iPhone 6s - iOS 10
- Safari - iPad Pro 9.7 - iOS 11
- Chrome - Galaxy S7
- Firefox 54 - Windows 10 - 1280x1024
- Chrome 55 - MacOS Sierra - 1440x900

The custom button should be created in the HP ALM project customization and set to call the *CreateTestConfigs* method in the Test Plan module customization. 

## Useful Tips ##
- Do not add a test instance to a test set more than once if you plan on modifying the test instance via this tool. When there are multiple test instances with the same configuration ID, ALM makes it very difficult to distinguish between them.
- When updating an entity field, make sure the data that is being entered conforms to the field's ALM configuration. The ALM OTA API enforces data types and field transition rules. However, it does not enforce string masks. 
- When specifying test set paths in the Test Lab module, use the following format: `Root\My Folder\My Sub Folder`. All paths start with Root, use backslashes to denote folders, and lack a trailing slash.

## Using addtesttotestset ##
Because of a limitation in the HP ALM OTA API, using the **addtesttotestset** command in parallel threads can yield unpredictable results. Unfortunately, there is no method for adding a single test configuration to a test set. Instead, the parent test of a configuration must be added with all of its child test configurations. The Reporting Bridge logic then determines the test configurations that were not intended to be added and removes them. Running this command in parallel in multiple threads will result in an unpredictable results when adding test configurations with the same parent. 

The recommended way to consume this function is at the beginning of the test suite execution. Simply iterate through the tests to be run and add them serially to the test set. 
