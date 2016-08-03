## Calabash Perfecto Mobile Environment Variables

Calabash Perfecto Mobile references environment variables to control its runtime behavior.
The environment variables include: <br/>

1. `CALABASH_PERFECTO_MOBILE_APP_FILE` <br/>
2. `CALABASH_PERFECTO_MOBILE_APP_NAME` <br/>
3. `CALABASH_PERFECTO_MOBILE_INSTALL` <br/>
4. `CALABASH_PERFECTO_MOBILE_MOBILECLOUD` <br/>
5. `CALABASH_PERFECTO_MOBILE_USER` <br/>
6. `CALABASH_PERFECTO_MOBILE_PASSWORD` <br/>
7. `CALABASH_PERFECTO_MOBILE_DEVICE` <br/>
8. `CALABASH_PERFECTO_MOBILE_REPORT_DIR` <br/>
9. `CALABASH_PERFECTO_MOBILE_UPLOAD_LOCATION`

## Conventions

Paths or values with spaces need double or single quotes.

#### Example: Quoting values with spaces.

	CALABASH_PERFECTO_MOBILE_APP_FILE="~/path with/a spaces/in it"   # Correct!
	CALABASH_PERFECTO_MOBILE_APP_FILE=~/path with/a spaces/in it     # Incorrect.

## Environment Variables

### 1. `CALABASH_PERFECTO_MOBILE_APP_FILE`
Application file for installing the app on the device.

##### Example

	CALABASH_PERFECTO_MOBILE_APP_FILE=Sudoku.apk			# app located in the current working directory
	CALABASH_PERFECTO_MOBILE_APP_FILE=C:/myApps/Sudoku.apk	# app with path to its directory

### 2. `CALABASH_PERFECTO_MOBILE_APP_NAME`

Application name for starting the app, as it is displayed on the device screen or the application manager. You can also find the application name in the
[device application list]("http://help.perfectomobile.com/article/AA-00466/0#deviceApplicationList").

##### Example

	CALABASH_PERFECTO_MOBILE_APP_NAME=Sudoku

### 3. `CALABASH_PERFECTO_MOBILE_INSTALL`

Option to re-install the app before running the test/s (feature file/s). The default is false.

##### Example

	CALABASH_PERFECTO_MOBILE_INSTALL="true"

### 4. `CALABASH_PERFECTO_MOBILE_MOBILECLOUD`

The Perfecto Mobile MobileCloud to connect to.

##### Example

	CALABASH_PERFECTO_MOBILE_MOBILECLOUD="mobilecloud.perfectomobile.com"

### 5. `CALABASH_PERFECTO_MOBILE_USER`

The MobileCloud user-name to connect with.

### 6. `CALABASH_PERFECTO_MOBILE_PASSWORD `

The MobileCloud password to connect with.

### 7. `CALABASH_PERFECTO_MOBILE_DEVICE`

The device id of the device in the MobileCloud to connect to.

##### Example

	CALABASH_PERFECTO_MOBILE_DEVICE="FA76LS9040823"

### 8. `CALABASH_PERFECTO_MOBILE_REPORT_DIR`
Directory where to download the Perfecto Mobile reports. The default is the current working directory.
 
### 9. `CALABASH_PERFECTO_MOBILE_UPLOAD_LOCATION`
Lab repository directory where to upload the apk/ipa file. The default is the PRIVATE repository.

Click [here](https://community.perfectomobile.com/posts/941431) for more information on the Lab repository.

##### Example

	CALABASH_PERFECTO_MOBILE_UPLOAD_LOCATION="PRIVATE:Calabash/AndroidApps"

 
