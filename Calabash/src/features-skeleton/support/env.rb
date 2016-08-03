# Modified by Perfecto Mobile Ltd.

require 'calabash-perfectomobile/cucumber'
  
## Perfecto Mobile Parameters
## To use, uncomment variables and enter the values 
# Note: these can be overridden by environment variables, and command line arguments

## Application file for installing the app on the device
## Example (app located in the current working directory): myApp.ipa
## Example (with path to its directory): C:/myApps/myApp.ipa 
# $PMAppFile = "<APK_OR_IPA_FILE>"

## Application name for starting the app, as it is displayed on the device screen or the application manager
## You can also find the application name in the device application list (in the MobileCloud Interactive view).
## Example: myAppName
# $PMAppName = "<YOUR_APP_NAME>"

## MobileCloud repository directory where to upload the apk/ipa file.
## Default is the PRIVATE repository.
## For example: PRIVATE:Calabash/AndroidApps
$PMUploadLocation = ""

## MobileCloud connection credentials
# $PMCloud = "<YOUR_PERFECTO_MOBILE_CLOUD_HERE>"
# $PMUser = "<YOUR_PERFECTO_MOBILE_CLOUD_USER_HERE>"
# $PMPassword= "<YOUR_PERFECTO_MOBILE_CLOUD_PASSWORD_HERE>"

## Device id of the device in the MobileCloud to connect to.
# $PMDevice = "<YOUR_PERFECTO_MOBILE_DEVICE_ID_HERE>"

## Re-install the app before running the test/s (feature file/s)?
## Default is false.
# $PMReinstallBeforeTests = false

## In case $PMReinstallBeforeTests is true (or -i was used in the command)
## Re-install the app before running each scenario?
## Default is false
# $PMReinstallBetweenScenarios = false

## Directory where to download the reports
## Default is the current working directory
$PMReportDir = ""

## Default timeout for wait commands
$defaultTimeout = 2
