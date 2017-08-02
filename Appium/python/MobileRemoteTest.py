from selenium import webdriver
from RemoteWebDriverUtils import RemoteWebDriverUtils

class MobileRemoteTest():
            
    def main(self):
        
        # Create a desired capabilities object as a starting point.
        capabilities = {}
        capabilities['version'] = ''
        capabilities['browserName'] = 'MobileOS'
        capabilities['platform'] = 'ANY'
        host = 'myHost.perfectomobile.com'
        capabilities['user'] = 'myUser'
        capabilities['password'] = 'myPassword'
        
        # Use the automationName capability to define the required framework
        # capabilities['automationName'] = 'PerfectoMobile'

        # Call this method if you want the script to share the devices with the recording plugin.
        #self.set_execution_id_capability(capabilities)
        
        driver = webdriver.Remote("https://" + host + "/nexperience/perfectomobile/wd/hub", capabilities)
        
        try :
            # write your code here...
            print('Driver created')
            
        except Exception as e:
            print(e)
        finally:
            # disconnect from the Remote server
            if driver is not None:
                driver.close()
                
                # In case you want to down the report or the report attachments, do it here.
                RemoteWebDriverUtils.download_report(driver, '/test/report.pdf')
                RemoteWebDriverUtils.download_attachment(driver, "video", '/test/video.flv')
                RemoteWebDriverUtils.download_attachment(driver, "image", '/test/image.jpg')
                
                driver.quit()
            
        print('Run ended')

if __name__ == "__main__":
    print('Run started')
    MobileRemoteTest().main()
    
