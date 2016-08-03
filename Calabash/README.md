Perfecto Integration with Calabash
===========================

[Calabash](http://calaba.sh/) is an automated testing technology for Android and iOS native applications; it is a free open source project.

Calabash integration with Perfecto Mobile enables testing on real devices from anywhere in your organization, within the [Perfecto Continuous Quality Lab](http://www.perfectomobile.com/). This includes a large scale of devices located in various geographies, and capabilities such as device logs, device vitals, video, detailed reports and more.

:information_source: **Notice**: this Perfecto integration with Calabash is not an official Perfecto version. This is a sample beta version demonstrating how to work with Perfecto and Calabash.

### Documentation
The documentation is split into the following sections:

- [Installation](https://github.com/PerfectoCode/Community-Samples/blob/master/Calabash/src/documents/installation.md)
- [Predefined Steps](https://github.com/PerfectoCode/Community-Samples/blob/master/Calabash/src/documents/canned_steps.md)
- [Environment variables](https://github.com/PerfectoCode/Community-Samples/blob/master/Calabash/src/ENVIRONMENT_VARIABLES.md)

### Supported mobile platforms
 - Android 4.2 and higher<br/>
 - iOS 7 and higher

### Supported Platforms
- Windows 7
- Macintosh OS X v10.9.5


Running your first test
--------------
After the installation is done you can run your first test. To do that use the provided test, that installs an application on a device in the Lab, enters data and validates the result. 

1. Copy the `features-skeleton` directory located in `<RUBY_HOME directory>\lib\ruby\gems\<rubyVersion>\gems\calabash-perfectomobile-<version>` (also available [here](https://github.com/PerfectoCode/Community-Samples/blob/master/Calabash/src/features-skeleton)), into any folder, and rename it to `features`. 
2. open the Lab in your browser, select the device you want to work on (Android device for this sample) and copy it's ID into the 'myDeviceId' parameter below.
3. Open command line in the directory that contains your features directory, and run </br> 

		calabash-perfectomobile -i -a 'Perfecto Mobile OSE' -c 'mobilecloud.perfectomobile.com' -u 'myUsername' 
		-p 'myPassword' -d 'myDeviceId' 'TestApp.apk' -r features\Reports

4. Open the dashboard (more/device dashboard) in the browser to view the test executed in real time.
5. Open the PDF report in the reports folder and view the report. 
	


Create your Cucumber skeleton
--------------

Copy the `features-skeleton` directory located in `<RUBY_HOME directory>\lib\ruby\gems\<rubyVersion>\gems\calabash-perfectomobile-<version>` (also available [here](https://github.com/PerfectoCode/Community-Samples/blob/master/Calabash/src/features-skeleton)), into any folder, and rename it to `features`. It is recommended to save your app in this directory, this will enable you to specify only the app name (without the full path) in your `calabash-perfectomobile` test execution.

The Cucumber skeleton looks like this:

    features
    |_support
    | |_app_installation_hooks.rb
    | |_app_life_cycle_hooks.rb
    | |_env.rb
    | |_hooks.rb
    |_step_definitions
    | |_calabash_steps.rb
    |_my_first.feature

In this skeleton you find a reference to all the predefined steps that come with `calabash-perfectomobile`, and the `my_first.feature` file.

Write a test
--------------

The Cucumber features go into the `features` library and should have the ".feature" extension.

You can start out by looking at `features/my_first.feature`. You can extend this feature or write your own using the [predefined steps](https://github.com/PerfectoCode/Community-Samples/blob/master/Calabash/src/documents/canned_steps.md) that come with calabash-perfectomobile.

Configure environment parameters
------------
There are 3 ways to configure the `calabash-perfectomobile` parameters:

1. Command line arguments
	- To use, run <br/>
	`calabash-perfectomobile [options] apk-file/ipa-file` <br/>
	For example: `calabash-perfectomobile -i -a 'myAppName' -c 'mobilecloud.perfectomobile.com' -u 'myUsername' -p 'myPassword' -d 'myDeviceId' 'myApp.ipa' -r features\Reports`
	- For more command options, run <br/>
	`calabash-perfectomobile -h`
2. Environment variables
	- Add to your environment variables: <br/>
	click [here](https://github.com/PerfectoCode/Community-Samples/blob/master/Calabash/src/ENVIRONMENT_VARIABLES.md) for more details on the environment variables. <br/>
	`CALABASH_PERFECTO_MOBILE_APP_FILE` <br/>
	`CALABASH_PERFECTO_MOBILE_APP_NAME` <br/>
	`CALABASH_PERFECTO_MOBILE_INSTALL` <br/>
	`CALABASH_PERFECTO_MOBILE_MOBILECLOUD` <br/>
	`CALABASH_PERFECTO_MOBILE_USER` <br/>
	`CALABASH_PERFECTO_MOBILE_PASSWORD` <br/>
	`CALABASH_PERFECTO_MOBILE_DEVICE` <br/>
	`CALABASH_PERFECTO_MOBILE_REPORT_DIR` <br/>
	`CALABASH_PERFECTO_MOBILE_UPLOAD_LOCATION` <br/>
3. env.rb
	- Update the `env.rb` file, located in `features\support`

Run test
------------
To run your test, cd to the current working directory that contains your features directory, and run the command:

    calabash-perfectomobile [options] apk-file/ipa-file

For more command options, run `calabash-perfectomobile -h`

Note: calabash-perfectomobile will install the application as non-instrumented.

### Report location
Perfecto Mobile reports are placed in the current working directory by default. The location can be changed with the command line argument `-r`, or by setting the `SCREENSHOT_PATH` environment variable, or by modifying the `$PMReportDir` in the env.rb file.

Predefined steps
-----------------

The predefined steps are located in the `features/step_definitions` directory. A compiled list of predefined steps with comments is available in the [canned_steps](https://github.com/PerfectoCode/Community-Samples/blob/master/Calabash/src/documents/canned_steps.md) file.

Custom steps
-----------------

You can use proprietary Perfecto commands using the `PM_runCommand(command, subcommand, param, raiseError)`.

For additional examples click [here](https://github.com/PerfectoCode/Community-Samples/blob/master/Calabash/src/lib/calabash-perfectomobile/perfectomobile_actions.rb)

For a complete list of Perfecto commands click [here](https://community.perfectomobile.com/?communities=&media_types=&types%5B%5D=series&categories%5B%5D=Perfecto%20CQ%20Lab&tags%5B%5D=function%20reference&authors_ids=&feed=recent)


```Ruby
def gotoUrl(url)
    command = "browwser"
	subcommand = "goto"
	param = "&param.url=#{url}&param.automation=native"
    PM_runCommand(command,subcommand,param)  
end
```
