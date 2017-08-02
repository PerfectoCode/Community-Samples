import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
public class SalesforceManager {
    AppiumDriver _driver;
    public SalesforceManager() {
    }
    public void setDriver(AppiumDriver driver)
    {
        _driver = driver;    
        _driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        _driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    public DesiredCapabilities getCapabilities(String cloudUser,String cloudPassword,String deviceID)
    {
        DesiredCapabilities capabilities = new DesiredCapabilities("", "", Platform.ANY);
        capabilities.setCapability("user", cloudUser);
        capabilities.setCapability("password", cloudPassword);
        capabilities.setCapability("deviceName",  deviceID);
        capabilities.setCapability("app-activity","Salesforce1");
        capabilities.setCapability("bundleId", "Salesforce1");
        capabilities.setCapability("takesScreenShot", false);
        capabilities.setCapability("automationName", "PerfectoMobile");
                return capabilities;
    }
    public void login(String user, String password)
    {
        WebElement user1 =   _driver.findElement(By.xpath("//*[@name='Username'][3]"));
        //user1.clear();
        user1.sendKeys(user);
        WebElement pass =_driver.findElement(By.xpath("//*[@name='Password'][3]"));
        //pass.clear();
        pass.sendKeys(password);
        _driver.findElement(By.xpath("//button[@label='Log in to Salesforce']")).click();
        _driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        util.sleep(2000);
        _driver.findElement(By.xpath("//button[contains(@label,'Allow')]")).click();
        _driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        _driver.findElement(By.xpath("//*[@name='stageLeftButton']"));  
    }
    public void search(String value)
    {
        openMenu();
        util.sleep(500);
        _driver.findElement(By.xpath("//search")).sendKeys(value);
        util.pressKey("ENTER", _driver);
    }
    public void logout()
    {
        openMenu();
        util.sleep(500);
        util.swipe("30%,90%", "30%,20%", _driver);
        util.sleep(500);
        _driver.findElement(By.xpath("//text[@label='Log Out']")).click();
        util.sleep(500);
        _driver.findElement(By.xpath("//button[@label='Log Out']")).click();
    }
    public void openMenu()
    {
        _driver.findElement(By.xpath("//*[@name='stageLeftButton']")).click();  
        util.sleep(500);
        _driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try {
            _driver.findElement(By.xpath("//text[@label='Dashboards']")) ;
        } catch (Exception e) {
            _driver.findElement(By.xpath("//*[@name='stageLeftButton']")).click();  
        }
        _driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    public void openAccountContacts(String account)
    {
        openAccountRelate(account);
        _driver.findElement(By.xpath("//text[@label='Contacts']")).click();
    }
    public void addAccount(String accountName )
    {
        openMenu();
        _driver.findElement(By.xpath("//text[@label='Accounts']")).click();
        _driver.findElement(By.xpath("//button[@label='New']")).click();
        _driver.findElement(By.xpath("//textfield[@name='Account Name']")).sendKeys(accountName);
        _driver.findElement(By.xpath("//button[@label='Save']")).click();
    }
    public void openAccountCases(String account)
    {
        openAccountRelate(account);
        _driver.findElement(By.xpath("//text[@label='Cases']")).click();
    }
    public void openAccountOpportunities (String account)
    {
        openAccountRelate(account);
        _driver.findElement(By.xpath("//text[@label='Opportunities']")).click();
    }
    public String getAccountOwner (String account)
    {
        openAccountDetails(account);
        WebElement el = _driver.findElement(By.xpath("//text[@label='Account Owner']/following::*//text[1]"));
        return el.getText();
    }
    public void backToMain()
    {
        try {
            _driver.findElement(By.xpath("//button[@label='Back']")).click();    
            util.sleep(500);
            _driver.findElement(By.xpath("//button[@label='Back']")).click();    
            util.sleep(500);
        } catch (Exception e) {
            // do nothing no back button 
        }
    }
    private void openAccountRelate(String account)
    {
        openMenu();
        _driver.findElement(By.xpath("//text[@label='Accounts']")).click();
        _driver.findElement(By.xpath("//search")).sendKeys(account);
        _driver.findElement(By.xpath("//text[text()='"+account+"']")).click();
        _driver.findElement(By.xpath("//button[text()='RELATED']")).click();
    }
    private void openAccountDetails(String account)
    {
        openMenu();
        _driver.findElement(By.xpath("//text[@label='Accounts']")).click();
        _driver.findElement(By.xpath("//search")).sendKeys(account);
        _driver.findElement(By.xpath("//text[text()='"+account+"']")).click();
        _driver.findElement(By.xpath("//button[text()='DETAILS']")).click();
    }
}
