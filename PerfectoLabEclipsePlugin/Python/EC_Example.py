from selenium import webdriver
# EclipseConnector.py file needed for following import
from EclipseConnector import EclipseConnector

class EclipseConnectorExample():
            
    def get_driver(self):
        
        # Create a desired capabilities object as a starting point.
        capabilities = {}
        
        # To run script against device in MobileCloud Recording window
        # Replace the X's with the opened device's ID below:
        capabilities['deviceName'] = 'XXXXXXXXXXXXXXXXXX'
        
        # Get Id of running MobileCloud plugin and add to capabilities
        self.set_execution_id_capability(capabilities)
        
        # Get webdriver URL string with credentials populated
        command_executor = self.get_MobileCloud_url_with_login_creds()
        
        # Create driver with call to RWD Server
        driver = webdriver.Remote(command_executor=command_executor, desired_capabilities=capabilities)
        
        # Set the implicit wait period before throwing a NoSuchElementException
        driver.implicitly_wait(20)
                
        return driver;

    def load_wikipedia_page(self):
        # write your code here
        print('Run started')
        
        driver = self.get_driver();
        print('Driver created')
        
        try :
            # write your code here
            # browse to the target page 
            driver.get("wikipedia.org")
            
            # ensure page is loaded
            driver.find_element_by_xpath("//img[@alt='WikipediA']")
            print('Loaded page')
            
        except Exception as e:
            print(e)
        
        finally:
            # disconnect from the RWD server
            self.quit(driver)
        
        print('Run ended')

    def quit(self, driver):
        if driver is not None:
            driver.close()
            driver.quit()
            print('Driver closed')

    # Returns webdriver URL string with credentials
    # Credentials pulled from Eclipse are specified here:
    # Window>>Preferences>>MobileCloud Login
    def get_MobileCloud_url_with_login_creds(self) :
        connector = EclipseConnector();
        user = connector.getUser()
        password = connector.getPassword()
        host = connector.getHost()
        return 'https://' + user + ':' + password + '@' + host + '/nexperience/wd/hub'
    
    # Adds MobileCloud plugin execution id to capabilities
    # Allows script to be executed against device in recording window
    def set_execution_id_capability(self, capabilities) :
        connector = EclipseConnector();
        executionId = connector.getExecutionId();
        capabilities[EclipseConnector.ECLIPSE_EXECUTION_ID] = executionId
    
if __name__ == "__main__":
    EclipseConnectorExample().load_wikipedia_page()
    
