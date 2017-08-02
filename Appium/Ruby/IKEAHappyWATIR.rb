
# This script launches the IKEA website, logs in, navigates to the Ireland country site and home store then iterates across all
# the special offers for that store to ensure that the links take you to the correct landing page.
#
# At the end the script logs out of the site and then launches a local web browser to take you to the Single Test Report for that script run.
#
# This script is written in Ruby and uses the WATIR framework


require 'selenium-webdriver'
require 'watir-webdriver'
require 'uri'
require 'set'
require 'win32/registry'
require 'launchy'


puts "Testing Watir Webdriver with Perfecto"


# Input capabilities

  Win32::Registry::HKEY_CURRENT_USER.open('ENVIRONMENT') do |reg|
    @user_id = reg['PM_USERID']
    @password = reg['PM_PASSWORD']
    @host = reg['PM_CLOUD']
  end

deviceid = "your-device-id"
puts "deviceid = " + deviceid


    caps = Selenium::WebDriver::Remote::Capabilities.new
    caps["browserName"] = "MobileOS"
    caps["host"] = @host
    caps["user"] = @user_id
    caps["password"] = @password
    caps["deviceName"] = deviceid
    caps["takesScreenshot"] = true



$driver = Selenium::WebDriver.for(:remote,
:url => "http://demo.perfectomobile.com/nexperience/perfectomobile/wd/hub",
:desired_capabilities => caps)


puts "capabilities = " + caps.to_s


#set test parameters
@siteURL = "www.ikea.com"
@region = "Europe"
@country = "Ireland"
@store = "Dublin"
@ik_user = "andyw@perfectomobile.com"
@ik_pass = "Mypassw0rd!"
@loggedIn = false


def teardown()
  if !($driver.nil?)
    puts "\n ***** Teardown *****\n"
       if !($browser.nil?)
          if @loggedIn
            logOut
          end
          $driver.close
          getReport
          $driver.quit()
       end
  end
end


def getReport
  repCaps = $driver.capabilities
  @WTReportURL = repCaps["windTunnelReportUrl"]
  puts "Report URL: " + @WTReportURL.to_s
  Launchy.open(@WTReportURL)
end


def logOut
  #Click the IKEA Menu
  $browser.element(:xpath, '//i[contains(@class, "ikea_menu")]').click

  #Click Your Profile
  $browser.element(:xpath, '/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/ul[2]/li[2]/a[1]/span[2]').click

  #Click Logout

  Watir::Wait.until { $browser.element(:xpath,
'/html[1]/body[1]/div[3]/div[3]/section[1]/div[1]/div[2]/div[1]/div[1]/form[1]/a[1]').exists?
 }
  $browser.element(:xpath, '/html[1]/body[1]/div[3]/div[3]/section[1]/div[1]/div[2]/div[1]/div[1]/form[1]/a[1]').click

  #Press the Home Button on the test device
  params = {}
  $driver.execute_script('mobile:handset:ready', params)

  @loggedIn = false
end


begin


  # creates the $browser object applying the remote webdriver profile to an instance of Watir webdriver
  $browser = Watir::Browser.new $driver


  # goes to IKEA front page
  $browser.goto("www.ikea.com")

  #select Region
  Watir::Wait.until { $browser.element(:xpath, '//A[contains(text(),"' + @region + '")]').exists? }
  Watir::Wait.until { $browser.element(:xpath, '//A[contains(text(),"' + @region + '")]').visible? }
  $browser.element(:xpath => '//A[contains(text(),"' + @region + '")]').click


  #select Country
  #scroll to country if not visible
  params = {
      :content => @country,
      :scrolling => 'scroll',
      :threshold => 100,
      :maxscroll => 20,
      :next => "SWIPE_UP",
      :report => "none"
  }
  $driver.execute_script('mobile:checkpoint:text', params)

  #now click the required country
  $browser.element(:xpath => '//A[contains(text(),"' + @country + '")]').click


  #Log In
  #Click the IKEA Menu
  Watir::Wait.until { $browser.element(:xpath, '//i[contains(@class, "ikea_menu")]').exists? }
  Watir::Wait.until { $browser.element(:xpath, '//i[contains(@class, "ikea_menu")]').visible? }
  $browser.element(:xpath, '//i[contains(@class, "ikea_menu")]').click

  #Click Login
  $browser.element(:xpath, '/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/ul[2]/li[1]/a[1]').click

  #set Userid
  Watir::Wait.until { $browser.element(:xpath, '(//input[@id="login-email"])[1]').exists? }
  Watir::Wait.until { $browser.element(:xpath, '(//input[@id="login-email"])[1]').visible? }
  $browser.element(:xpath, '(//input[@id="login-email"])[1]').send_keys(@ik_user)


  #set Password
  $browser.element(:xpath, '(//input[@id="login-password"])[1]').send_keys(@ik_pass)


  #Click the Login Button
  $browser.element(:xpath, '/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/p[1]/a[1]').click
  @loggedIn = true

  #Set Store
  #Click the IKEA Menu
  Watir::Wait.until { $browser.element(:xpath, '//i[contains(@class, "ikea_menu")]').exists? }
  Watir::Wait.until { $browser.element(:xpath, '//i[contains(@class, "ikea_menu")]').visible? }
  $browser.element(:xpath, '//i[contains(@class, "ikea_menu")]').click


  #Click the required Store
  $browser.element(:xpath, '(//a[@id="IKEA-Module-Header-Default-_EntranceLinks-store-link"])[1]').click

  #Get Offers

  Watir::Wait.until { $browser.element(:xpath,
'/html[1]/body[1]/div[3]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[2]/a[1]').exists?
 }
  Watir::Wait.until { $browser.element(:xpath,
'/html[1]/body[1]/div[3]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[2]/a[1]').visible?
 }
  $browser.element(:xpath, '/html[1]/body[1]/div[3]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[2]/a[1]').click

  articles = '//article/div/a'
  Watir::Wait.until { $browser.element(:xpath, articles).exists? }


  itemDescs = '//article/div/a/p/span'
  descXpathTgt = "//SPAN[contains(@class,'pie-description')][1]"
  elements = $browser.elements(:xpath, articles)
  elements2 = $browser.elements(:xpath, itemDescs )
  elementsCount = elements2.size

  myIterator = 2
  remainingOffers = true
     begin
    ind = myIterator.to_s
    clickXpath = "/html[1]/body[1]/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/article[" + ind + "]/div[1]/a[1]"

    descXpath  =
"/html[1]/body[1]/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/article["
 + ind + "]/div[1]/a[1]/p[1]/span[1]"

    element = $browser.element(:xpath, descXpath)
    itemDesc1 = element.text
    puts deviceid + "->Desc1: " + itemDesc1


    element = $browser.element(:xpath, clickXpath)
    element.click

    Watir::Wait.until { $browser.element(:xpath, descXpathTgt).exists? }
    element = $browser.element(:xpath, descXpathTgt)
    itemDesc2 = element.text
    puts deviceid + "->Desc2: " + itemDesc2

    if itemDesc1 == itemDesc2
      puts deviceid + "->PASS: Descriptions Match"
    else
      puts deviceid + "->FAIL: Descriptions DO NOT Match"
    end

    itemDesc1 = ""
    itemDesc2 = ""

    if myIterator <= elementsCount
      myIterator = myIterator + 1
    else
       remainingOffers = false
      puts "*********** no more offers ********************"
    end

    $browser.back

 end while remainingOffers


teardown()


  puts "End of IKEA Happy Flow"


rescue
  teardown()
  raise
end
