import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class AndroidImageInjectionTestSample {

    private static final String cloud = "PUT YOUR CLOUD URL HERE";
    private static final String user = "PUT YOUR USER NAME HERE";
    private static final String token = "PUT YOUR TOKEN HERE";

    RemoteWebDriver driver = null;

    //PREREQUISITE: upload application to MCM repository
    private static final String appRepoLocation = "PRIVATE:CameraApp4.apk";
    private static final String appPackage = "com.perfectomobile.cameraapp";
    //PREREQUISITE: upload PNG to MCM repository
    private static final String imageName = "square.png";

    final static By allowPopUp = By.xpath("//*[@resource-id=\"com.android.packageinstaller:id/permission_allow_button\" or @resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]");

    @BeforeClass(alwaysRun = true)
    public void beforeClass() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("securityToken", token);
        capabilities.setCapability("user", user);

        capabilities.setCapability("app", appRepoLocation);
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("autoInstrument", true);
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("sensorInstrument", true);

        driver = new AndroidDriver(new URL("https://" + cloud + "/nexperience/perfectomobile/wd/hub"), capabilities);
    }

    @Test()
    public void imageInjectionTestAndroid() {

        try {
            WebElement popUpBtn = driver.findElement(allowPopUp);
            if (popUpBtn != null) popUpBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            WebElement popUpBtn = driver.findElement(allowPopUp);
            if (popUpBtn != null) popUpBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> imageInjectionStartParams = new HashMap<>();
        imageInjectionStartParams.put("repositoryFile", "PRIVATE:" + imageName);
        imageInjectionStartParams.put("identifier", appPackage);
        driver.executeScript("mobile:image.injection:start", imageInjectionStartParams);

        Map<String, Object> textFindParams = new HashMap<>();
        textFindParams.put("content", imageName.split("\\.")[0]);
        textFindParams.put("timeout", "60");
        textFindParams.put("threshold", "20");
        Object findResult = driver.executeScript("mobile:text:find", textFindParams);

        Assert.assertTrue(Boolean.valueOf(findResult.toString()), "Injected image not found on device screen");

        Map<String, Object> imageInjectionStopParams = new HashMap<>();
        driver.executeScript("mobile:image.injection:stop", imageInjectionStopParams);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }


}
