import os
import base64
import urllib
import urllib2
import json
from EclipseConnector import EclipseConnector

class RemoteWebDriverUtils():

    #   Specify location where to download the report and the report format
    # 	 * type - pdf, html, csv, xml
    # 	 * Example: download_report(driver, '/test/report.pdf');
    @classmethod
    def download_report(cls, driver, full_file_path):
        try: 
            report = driver.execute_script('mobile:report:download',
                                            {'type':full_file_path.split(".")[-1]})
            
            cls.save_to_file(report, full_file_path)
            
        except Exception as e: 
            print('Report download failed with error:')
            print(e)

    #   Possible attachment types: image, actual-image, expected-image, video, vital, network.
    #   By default the method downloads the first file that matches the requested type. 
    #   If there are several files (e.g. the report contains several images), then it is possible to specify an index.
    #   Index starts from zero (default).
    # 	 * Examples:
    # 	 * download_attachment(driver, "video", '/test/report/video.flv")
    # 	 * download_attachment(driver, "image", '/test/report/video.jpeg")
    @classmethod
    def download_attachment(cls, driver, file_type, full_file_path, index=0):
        try:
            file_name, file_extension = full_file_path.split(".")
            
            done = False
            while not done:
                attachment = driver.execute_script('mobile:report:attachment',
                                                    {'type':file_type,
                                                     'index':index})
                if attachment == None:
                    done = True
                else:    
                    index = index + 1
                    cls.save_to_file(attachment, ('{0}_{1}.{2}').format(file_name, index, file_extension))
            
        except Exception as e: 
            print('Attachment download failed with error:')
            print(e)
    
    # 	 * Uploads a file to the media repository.
    # 	 * Example:
    # 	 * uploadMedia('/test/ApiDemos.apk', "demo.perfectomobile.com", "john@perfectomobile.com", "123456", 'PRIVATE:apps/ApiDemos.apk');
    @classmethod
    def upload_media_from_file(cls, file_path, host, user, password, repository_key, overwrite=True):
        print ("Uploading item " + file_path)
        new_file = open(file_path, 'rb')
        content = new_file.read()
        new_file.close()
        url = cls.get_request_url(host, user, password, repository_key, "upload", overwrite)
        cls.send_request(url, content)
    
    # Get list of repository items
    @classmethod
    def get_repository_list(cls, file_path, host, user, password, repository_key):
        print ("Getting list of repository items under root " + repository_key)
        url = cls.get_request_url(host, user, password, repository_key, "list")
        items = cls.send_request_with_json_response(url, "")
        return items['items']
    
    # Download item from repository
    @classmethod
    def download_media(cls, file_path, host, user, password, repository_key):
        print ("Downloading item " + repository_key)
        url = cls.get_request_url(host, user, password, repository_key, "download")
        response = cls.send_request(url, "")
        cls.save_to_file(response.read(), file_path)
    
    # Delete item from the repository
    @classmethod
    def delete_media(cls, file_path, host, user, password, repository_key):
        print ("Deleting item " + repository_key)
        url = cls.get_request_url(host, user, password, repository_key, "delete")
        json = cls.send_request_with_json_response(url, "")
        status = json['status']
        if (status != "Success") :
            raise RuntimeError("Repository item " + repository_key + " was not deleted")
        print ("item " + repository_key + " deleted")
    
    @classmethod
    def send_request(cls, url, content):
        print("Submitting", url)
        if (content == "") :
            response = urllib2.urlopen(url)
        else :
            response = urllib2.urlopen(url, content)
        if response.getcode() == 200:
            return response
        else :
            print('Request to ' + url + ' failed with code: ' + str(response.getcode()))
    
    @classmethod
    def send_request_with_json_response(cls, url, content):
        response = cls.send_request(url, content)
        text = response.read().decode("utf-8")
        return json.loads(text)
    
    @classmethod
    def get_request_url(cls, host, user, password, repository_key, operation, overwrite=False):
        url = 'https://' + host + '/services/repositories/media/'
        if (repository_key != ""):
            url += "/" + repository_key
        params = {"operation": operation,"user": user, "password": password }
        if overwrite: params['overwrite'] = True
        query = urllib.urlencode(params)
        url += "?" + query
        return url
    
    @classmethod
    def save_to_file(self, source, file_full_path, encoding='ascii'):
        try:
            file_dir = os.path.dirname(os.path.abspath(file_full_path))
            new_file = base64.b64decode(source.encode(encoding))
            if not os.path.exists(file_dir):
                os.makedirs(file_dir)
            with open(file_full_path, 'wb') as f:
                f.write(new_file)
            print('File was downloaded to ' + file_full_path)
        except (NameError, IOError, AttributeError) as e:
            print('Failed to download file to ' + file_full_path)
            print('Error details: ' + str(e))
        finally:
            del new_file
    
    # Adds MobileCloud plugin execution id to capabilities
    # Allows script to be executed against device in recording window
    @classmethod
    def set_execution_id_capability(cls, capabilities) :
        connector = EclipseConnector();
        executionId = connector.getExecutionId();
        capabilities[EclipseConnector.ECLIPSE_EXECUTION_ID] = executionId
        
    # Returns webdriver URL string with credentials
    # Credentials pulled from Eclipse are specified here:
    # Window>>Preferences>>MobileCloud Login
    @classmethod
    def get_MobileCloud_url_with_login_creds(cls, user=None, password=None, host=None) :
        connector = EclipseConnector()
        if user == None: user = connector.getUser()
        if password == None: password = connector.getPassword()
        if host == None: host = connector.getHost()
        return 'https://' + user + ':' + password + '@' + host + '/nexperience/wd/hub'
