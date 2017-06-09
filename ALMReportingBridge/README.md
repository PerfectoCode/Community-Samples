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

Running the executable with no arguments or by using the ***/h*** argument will display the help. The help displays all of the possible commands, arguments, and data types for each command. 

## Commands ##
Command     | Description
-------- | ---
copytestset | $1600
updaterunfield    | $12
recordrunresult     | $1
