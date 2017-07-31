# Download selenium module (into Lib, or add it to python path) from here: https://pypi.python.org/packages/source/s/selenium/selenium-2.44.0.tar.gz
# Use the following for file encoding:
# -*- coding: utf-8 -*-

import unittest
from selenium import webdriver
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

class SampleCode(unittest.TestCase):
    driver = None   
    command_executor = None
            
    def setUp(self):
        global command_executor
         
        user = 'MyUser'
        password = 'MyPassword'
        host = 'MyCloud.perfectomobile.com'
        command_executor = 'https://' + user + ':' + password + '@' + host + '/nexperience/wd/hub'
 
        platform_name = 'ANDROID'
        platform_version = '4.4'
        capabilities = {'platformName': platform_name, 'platformVersion': platform_version, 'browserName': 'perfectoMobile', 'javascriptEnabled': True}
        self.driver = self.create_driver(capabilities)
 		
#		Alternatively, you can specify a device using the device id:
#       deviceId = 'C8C4E7A345EF2ACBA0C9E00718820012FD8DE55E'
#       capabilities = {'deviceName': deviceId, 'browserName': 'mobileDefault', 'javascriptEnabled': True}
          
    def create_driver(self, capabilities):
        return webdriver.Remote(command_executor = command_executor, desired_capabilities = capabilities)

#		Example 1:
#		Navigate to images.google.com and search for text
#		This example uses XPath to identify the elements on the webpage
    def test_search_images_google_for_selenium_logo(self):
        global driver
        
        driver = self.driver
        
        # Step 1: Go to images.google.com
        url = 'https://images.google.com/'
        driver.get(url)
        
        # Step 2: validate the page has loaded by waiting for the images.google search edit field to load
        wait = WebDriverWait(driver, 15)
        query_name = 'q'
        element = wait.until(EC.element_to_be_clickable((By.NAME, query_name)))
		# Validate the text 'images' appears in the URL
        self.assertIn('images', driver.current_url)
         
        # Step 3: Enter the text 'Selenium Logo'
        element = driver.find_element_by_name(query_name)
        text = 'Selenium Logo'
        type_text = [text]
        element.send_keys(type_text)
         
        # Step 4: Validate entered text and click on search icon
        current_text = element.text
        assert current_text in text
        search_icon_css = '#tsbb'
        element = driver.find_element_by_css_selector(search_icon_css)
        element.click()
		
        time.sleep(1)
        
#		Example 2:
#		Navigate to images.google.com and search for text
#		This example uses XPath to identify the elements on the webpage

    def test_search_google_for_perfecto_rwd(self):
        global driver
        
        driver = self.driver

        # Step 1: Go to www.google.com
        url = 'http://www.google.com'
        driver.get(url)
         
        # Step 2: validate the page has loaded by waiting for the google search edit field to load
        wait = WebDriverWait(driver, 15)
        query_id = 'lst-ib'
        element = wait.until(EC.element_to_be_clickable((By.ID, query_id)))
		# Validate the the title is 'Google'
        self.assertIn('Google', driver.title)
         
        # Step 3: Enter the text 'Perfecto Mobile' and then the text 'Python example for RemoteWebDriver'
        element = driver.find_element_by_id(query_id)
        first_text = 'Perfecto Mobile'
        second_text = 'Python example for RemoteWebDriver'
        type_text = [first_text, second_text]
        element.send_keys(type_text)
         
        # Step 4: Validate entered text and submit search
        current_text = element.text
        assert current_text in second_text      
        element.submit()
        
        time.sleep(1)


	def tearDown(self):
       if driver is not None:
           driver.close()
           # Download Perfecto Mobile report
           self.download_rwd_report('', 'xml')
           self.download_rwd_report('c:/rwdReports/', 'pdf')
           # Download Perfecto Mobile report attachments
		   self.download_rwd_report_attachments('', 'image', 'png', 1)
           self.download_rwd_report_attachments('', 'video', 'flv')
           driver.quit()
        

    def save_to_file(self, source, file_full_path):
        file = None
        try:
            file = base64.b64decode(source.encode('ascii'))
            with open(file_full_path, 'wb') as f:
                f.write(file)
            print 'File was downloaded to ' + file_full_path
        except (NameError, IOError, AttributeError) as e:
            print 'Failed to download file to ' + file_full_path
            print 'Error details: ' + str(e)
        finally:
            del file

#	Specify location where to download the report and the report format
#	The report name will be rwd_report
    def download_rwd_report(self, location, report_format):
        command = 'mobile:report:download'
        
        params = {}
        params['type'] = report_format
        
        report = self.driver.execute_script(command, params)
        
        self.save_to_file(report, location + 'rwd_report.' + report_format)
            
#   Possible attachment types: image, actual-image, expected-image, video, vital, network.
#   By default the method downloads the first file that matches the requested type. 
#   If there are several files (e.g. the report contains several images), then it is possible to specify an index.
#   Index starts from zero (default).
    def download_rwd_report_attachments(self, location, attachment_type, attachment_format, index = 0):
        command = 'mobile:report:attachment'
       
        params = {}
        params['type'] = attachment_type
        params['index'] = index
        
        attachment = self.driver.execute_script(command, params)
        
        self.save_to_file(attachment, location + attachment_type + '.' + attachment_format)

        
if __name__ == '__main__':
    unittest.main()
