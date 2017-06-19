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
Two files must be either embedded in your testing project or accessible to the execution engine of your code: *ALMReportingBridge.exe* and *CLAP.dll*. Both must be in the same directory. Binary versions of these files can be found in the */bin* folder of the GitHub project. 

The reporting bridge works on a simple format: `filename.exe command /argument=value`

Example: `ALMReportingBridge.exe test /serverurl=http://myalmserver:8080/qcbin /username=johndoe /password=mypassword /domain=ALMREPORTINGBRIDGE /project=ConnectionTests`

Running the executable with no arguments or by using the ***/h*** argument will display the help. The help displays all of the possible commands, arguments, and data types for each command. When a command is executed, the results of the command are returned in XML. 

Any argument value that contains a space must be encapsulated by quotations. For example: `/password="my password contains spaces"`.

## Commands ##
| Command     		 | Description	 | 
|--------	 	     | ---			 | 
| copytestset        | Copies an ALM test set to the destination folder using a test set ID. Useful when copying a template test set for a test suite that runs frequently. The test set copy only copies the test instances -- no run data is copied. Therefore all tests have a "No Run" initial status. |
| updaterunfield     | Updates a field associated to the Run entity to the specified value based on the Run ID. The *fieldname* must be the database identifier of the field, not the logical ALM name. |
| recordrunresult    | Records a run result to a test instance. This command creates a new run for the test instance with the status provided. | 
| createtestset      | Creates a new empty test set in the specified folder | 
| updatetestsetfield | Updates the value of a test set field by its database name. | 
| addtesttotestset   | Adds a test configuration to a test set. | 
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

## Understanding Tests vs. Test Configurations ##
ALM projects use test entities to represent a test that is to be run. Digital testing creates a new problem in that the same test may need to be executed on many different browsers or device combinations. Creating unique test entities for each combination, while possible, replicates data and tests and generally makes the project harder to manage. 

To solve this problem, HP introduced the concept of a Test Configuration. Test configurations allow the parameterization of tests so that they can be run many times without having to duplicate the test entities. A Test Configuration represents a unique combination of a test and a data scenario. In this case, the data scenario is the device or browser under test. As an example, you may have a test that verifies an account balance. That test may have 10 Test Configurations -- one for each mobile device in the coverage model. 

This project uses the Test Configuration model when interacting with test cases. 

## Useful Tips ##
- Do not add a test instance to a test set more than once if you plan on modifying the test instance via this tool. When there are multiple test instances with the same configuration ID, ALM makes it very difficult to distinguish between them.
- When updating an entity field, make sure the data that is being entered conforms to the field's ALM configuration. The ALM OTA API enforces data types and field transition rules. However, it does not enforce string masks. 
- When specifying test set paths in the Test Lab module, use the following format: `Root\My Folder\My Sub Folder`. All paths start with Root, use backslashes to denote folders, and lack a trailing slash.
