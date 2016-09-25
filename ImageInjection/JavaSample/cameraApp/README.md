## Sample Overview

The attached sample script demonstrates the use of the Perfecto Image Injection functionality to provide a prepared image file as *camera input* to a simple application that "reads" input from the camera.

The sample script performs the following steps:
+ Activates the RealTimeFilter application - displays the input from the camera.
+ [Starts Image Injection](https://community.perfectomobile.com/posts/1235463) on the application, identifying the image file to use as camera input.
+ Performs a visual checkpoint on the device to verify that the image is displayed.
+ Deactivates the Image Injection.

The following should be noted when using image injection:
+ The target application must be installed with a special *Camera Instrumentation*. This is performed by Appium if the **_cameraInstrument_** capability is set to **true** or if the **_cameraInsrumentation_** parameter of the Install Application command is set to **true**.
+ [Start Image Injection](https://community.perfectomobile.com/posts/1235463) should be activated while the application is executing.
+ The provided image will be supplied to the application until either the [Stop Image Inject](https://community.perfectomobile.com/posts/1235602) command is called or the application stops.

