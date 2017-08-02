import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.dom4j.tree.BackedList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.handler.GetCurrentWindowHandle;
public class TestExe {
    public static void main(String[] args) {
        //get the lib
        SalesforceManager salesforce = new SalesforceManager();
        DesiredCapabilities capabilities = salesforce.getCapabilities("PerfectoUser","PerfectoPassword","deviceID");
        AppiumDriver driver = null;
        try {
            driver = new AppiumDriver(new URL("http://demo.perfectomobile.com/nexperience/perfectomobile/wd/hub") , capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //connect the driver to the lib 
            salesforce.setDriver(driver);
            //use the generic actions in the lib
            salesforce.login("uzi@test.com","Perfecto1");
            salesforce.openAccountOpportunities("GenePoint");
            salesforce.backToMain();
            System.out.println("****                     "+salesforce.getAccountOwner("GenePoint"));
            salesforce.backToMain();
            salesforce.addAccount("Try ACC 12");
            salesforce.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            driver.quit();
       }
   }
}
