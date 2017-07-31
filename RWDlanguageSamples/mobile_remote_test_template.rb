require 'selenium-webdriver'
require 'net/http'
require 'uri'
require 'test/unit'




class SampleCode < Test::Unit::TestCase


  $driver = nil
  
  def initialize(name = nil)
      @test_name = name
      super(name) unless name.nil?
      puts "\nStart test: " + @test_name
  end
  
  def setup()
      
    protocol = 'https'
    user = 'MyUser'
    password = 'MyPassword'
    host = 'MyCloud.perfectomobile.com'
    # get the port of https protocol:
    port = URI(protocol + '://google.com').port
    path = '/nexperience/wd/hub'
    url = URI::Generic.new(protocol, user + ':' + password, host, port, nil, path, nil, nil, nil, nil) 
    
    deviceId = 'HT1BET1345GVF2VJ619'
    
    caps = {
    :deviceName            => deviceId,
    :browser_name          => 'chrome',
    :javascript_enabled    => true,
    :takes_screenshot      => true,
    }
    
    $driver = Selenium::WebDriver.for(:remote, :url => url, :desired_capabilities => caps)


  end


  def test_search_images_google_for_selenium_logo
    # Step 1: Go to images.google.com
    url = 'https://images.google.com/'
    $driver.get(url)
    
    # Step 2: validate the page has loaded by waiting for the images.google search edit field to load
    wait = Selenium::WebDriver::Wait.new(:timeout => 15) # seconds
    query_name = 'q'
    wait.until { $driver.find_element(:name => query_name) }
    # Validate the text 'images' appears in the URL
    assert($driver.current_url.include?('images'))
    
    # Step 3: Enter the text 'Selenium Logo'
    element = $driver.find_element(:name => query_name)
    text = 'Selenium Logo'
    type_text = [text]
    element.send_keys(type_text)
    
    # Step 4: Validate entered text and click on search icon
    current_text = element.text
    assert(current_text.include?(text))
    search_icon_css = '#tsbb'
    element = $driver.find_element(:css => search_icon_css)
    element.click()
    sleep(5)
  end
      
  #   Example 2:
  #   Navigate to google and search for text
  #   This example uses ID to identify the elements on the webpage
  def test_search_google_for_perfecto_rwd
    # Step 1: Go to www.google.com
    url = 'http://www.google.com'
    $driver.get(url)
    
    # Step 2: validate the page has loaded by waiting for the google search edit field to load
    wait = Selenium::WebDriver::Wait.new(:timeout => 15) # seconds
    
    query_id = 'lst-ib'
    wait.until { $driver.find_element(:id => query_id) }
    # Validate that the title is 'Google'
    assert($driver.title.include?('Google'))
     
    # Step 3: Enter the text 'Ruby example for RemoteWebDriver, Perfecto Mobile'
    element = $driver.find_element(:id => query_id)
    type_text = 'Ruby example for RemoteWebDriver, Perfecto Mobile'
    element.send_keys(type_text)
     
    # Step 4: Validate entered text and submit search
    current_text = element.text
    assert(type_text.include?(current_text))       
    element.submit()
    sleep(5)
  end
  
  def teardown()
    if !($driver.nil?) 
      $driver.close
      download_rwd_report($driver, @test_name)
      download_rwd_report_attachments($driver, @test_name, 'video', 'flv')
      download_rwd_report_attachments($driver, @test_name, 'image', 'png', 1)
      $driver.quit()
    end
  end


end # test class SampleCode




 def download_rwd_report(driver, test_name)
  command = 'mobile:report:download';
  
  params = {}
  params['type'] = 'pdf'
  report = driver.execute_script(command, params)
  
  File.open('C:/test/report_' + test_name + '.pdf', 'wb') { |f| f << report.unpack("m")[0] }
end


def download_rwd_report_attachments(driver, test_name, attachment_type, attachment_format, index = 0)
  command = 'mobile:report:attachment'
         
  params = {}
  params['type'] = attachment_type
  params['index'] = index
  
  attachment = driver.execute_script(command, params)
    
  File.open('C:/test/report_' + test_name + '_' + attachment_type + '_' + index.to_s + '.' + attachment_format, 'wb') { |f| f << attachment.unpack("m")[0] }
end