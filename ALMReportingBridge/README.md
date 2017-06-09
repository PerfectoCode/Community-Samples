# HPE ALM Reporting Bridge #

## Overview ##
The HPE ALM Reporting Bridge is a C# wrapper implementation of the ALM Open Test Architecture (OTA) API. It has been written to support the specific needs of running Selenium and Appium tests written in a variety of languages while still using HPE ALM as a reporting repository. 

The reporting bridge is compiled into a single Windows executable that is run from a command prompt. It can be used in batch jobs, or embedded inside of any test execution framework. When executed, the application will return an XML string with the status of the operation, entity IDs, and any errors. 

The full code of the project is provided here along with a full suite of unit tests. The unit command line unit tests are especially helpful to understand how individual commands are assembled. 

## Requirements ##
- HPE ALM/Quality Center 11+
- Machine executing the command line must have either the HPE ALM Client Components registered (by following **Tools** > **HP ALM Client Registration** > **Register HP ALM** links from the spash screen) or by installing the HP ALM Connectivity Tool (by following **Tools** > **HP ALM  Connectivity** > **Download HP ALM Connectivity** from the splash screen)
- Must have permission to perform the operation in the ALM project

## Using the Reporting Bridge ##
Two files must be either embedded in your testing project or accessible to the execution engine of your code: *ALMReportingBridge.exe* and *CLAP.dll*. Both must be in the same directory. Binary versions of these files can be found in the */bin* folder of the GitHub project. 

The reporting bridge works on a simple format: `filename.exe command /argument=value`

Example: `ALMReportingBridge.exe test /serverurl=http://myalmserver:8080/qcbin /username=johndoe /password=mypassword /domain=ALMREPORTINGBRIDGE /project=ConnectionTests`

Running the executable with no arguments or by using the ***/h*** argument will display the help. The help displays all of the possible commands, arguments, and data types for each command. When a command is executed, the results of the command are returned in XML. 

## Commands ##
Command     | Description | Return XML
-------- | --- |
copytestset | Copies an ALM test set to the destination folder using a test set ID. Useful when copying a template test set for a test suite that runs frequently. The test set copy only copies the test instances -- no run data is copied. Therefore all tests have a "No Run" initial status. | test
updaterunfield    | Updates a field associated to the Run entity to the specified value based on the Run ID. The *fieldname* must be the database identifier of the field, not the logical ALM name. | test
recordrunresult     | Records a run result to a test instance. This command creates a new run for the test instance with the status provided. | test
createtestset | Creates a new empty test set in the specified folder | test
updatetestsetfield | Updates the value of a test set field by its database name. | test
addtesttotestset | Adds a test configuration to a test set. | test
attachtorun | Uploads attachment to a run | test
attachtotestset | Uploads attachment to a test set | test
test | Tests the connection to the ALM server | test



## Understanding Tests vs. Test Configurations ##
ALM projects use test entities to represent a test that is to be run. Digital testing creates a new problem in that the same test may need to be executed on many different browers or device combinations. Creating unique test entities for each combination, while possible, replicates data and tests and generally makes the project harder to manage. 

To solve this problem, HP introduced the concept of a Test Configuration. Test configurations allow the parameterization of tests so that they can be run many times without having to duplicate the test entities. A Test Configuration represents a unique combination of a test and a data scenario. In this case, the data scenario is the device or browser under test. As an example, you may have a test that verifies an account balance. That test may have 10 Test Configurations -- one for each mobile device in the coverage model. 

This project uses the Test Configuration model when interacting with test cases. 
