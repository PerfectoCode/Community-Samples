
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class iOSImageInjectionTestSample {

    RemoteWebDriver driver = null;
    Map<String, Object> params;

    private static final String CLOUD = "PUT YOUR CLOUD URL HERE";
    private static final String TOKEN = "PUT YOUR TOKEN HERE";

    private static final String ALLOW_CAMERA_ACCESS_OK = "//*[@label='OK']";
    private static final By VIDEO_BUTTON = By.xpath("//*[@label='Video']");

    //PREREQUISITE: upload application to MCM repository
    private static final String APP_REPO_LOCATION = "PRIVATE:CameraTestApp.ipa";
    private static final String APP_BUNDLE_ID = "Perfecto.CameraTestApp";
    //PREREQUISITE: upload PNG to MCM repository
    private static final String IMAGE_NAME = "Testing.png";

    @BeforeClass(alwaysRun = true)
    public void beforeClass() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("securityToken", TOKEN);

        capabilities.setCapability("app", APP_REPO_LOCATION);
        capabilities.setCapability("bundleId", APP_BUNDLE_ID);
        capabilities.setCapability("platformName", "ios");
        capabilities.setCapability("sensorInstrument", true);

        driver = new IOSDriver<>(new URL("https://" + CLOUD + "/nexperience/perfectomobile/wd/hub"), capabilities);

        //to allow access to camera - press OK on permission pop-up
        try {
            WebElement popUpBtn = driver.findElementByXPath(ALLOW_CAMERA_ACCESS_OK);
            if (popUpBtn != null) popUpBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test()
    public void imageInjectionTestIOS() {

        //start image injection
        params = new HashMap<>();
        params.put("repositoryFile", "PRIVATE:" + IMAGE_NAME);
        params.put("identifier", APP_BUNDLE_ID);
        driver.executeScript("mobile:image.injection:start", params);

        //click on Video button
        driver.findElement(VIDEO_BUTTON).click();

        //verify that injected image displayed on the app screen
        params = new HashMap<>();
        params.put("content", IMAGE_NAME.split("\\.")[0]);
        params.put("timeout", "10");
        params.put("threshold", "20");
        Object result = driver.executeScript("mobile:text:find", params);

        Assert.assertTrue(Boolean.valueOf(result.toString()), "Injected image not found on device screen");

        //stop image injection
        params = new HashMap<>();
        driver.executeScript("mobile:image.injection:stop", params);

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

}