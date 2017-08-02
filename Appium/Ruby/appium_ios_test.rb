require 'rubygems'
require 'test/unit'
require 'appium_lib'

class AppiumIOSTest < Test::Unit::TestCase

    def setup
        desired_caps = {
            caps:       {
                platformName: 'iOS',
                deviceName: '', # enter here your device Id
                bundleId: 'com.apple.mobileslideshow',
                user: 'user@company.com', # enter here your Perfecto user name
                password: 'password', # enter here your Perfecto password
                takesScreenshot: false,
            },
            appium_lib: {
                server_url: 'https://%s/nexperience/perfectomobile/wd/hub' % 'your_host', # enter here your Perfecto host name
            }
        }
        @driver = Appium::Driver.new(desired_caps)
        @driver.start_driver.manage.timeouts.implicit_wait = 10
    end

    def teardown
        @driver.driver_quit
    end

    def test_gestures
        @driver.find_element(:name, 'Moments')
        img_elements = @driver.find_elements(:class_name, 'UIACollectionCell')
        img_elements[0].click

        size = @driver.window_size
        start_x = size.width * 0.8
        end_x = size.width * 0.2
        y = size.height / 2

        params_zoom = {
            'start'     => '50%,35%',
            'end'       => '50%,40%',
            'duration'  => '1',
            'operation' => 'zoom',
        }
        params_pinch = {
            'start'     => '50%,65%',
            'end'       => '50%,60%',
            'duration'  => '1',
            'operation' => 'pinch',
        }

        img_elements.each do
            sleep 0.5
            @driver.execute_script 'mobile:touch:gesture', params_zoom
            @driver.execute_script 'mobile:touch:gesture', params_pinch
            @driver.swipe start_x: start_x, start_y: y, end_x: end_x, end_y: y, duration: 500
        end

        @driver.find_element(:name, 'Back').click
    end
end
