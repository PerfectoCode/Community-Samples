Canned steps
============
Perfecto implementation of Calabash includes the following set of predefined steps.
You can add your own steps or change the ones you see here.

Assertion steps
---------------

To assert that specified text can be found use any of the following steps.

    Then /^I see the text "([^\"]*)"$/ do |text|
    Then /^I see "([^\"]*)"$/ do |text|
	Then /^I see the "([^\"]*)"$/ do |text|
    Then /^I should see "([^\"]*)"$/ do |text|
	Then /^I should see text containing "([^\"]*)"$/ do |text|
	Then /^I (?:should)?(?: )?see text starting with "([^\"]*)"$/ do |text|
	Then /^I (?:should)?(?: )?see text ending with "([^\"]*)"$/ do |text|


To assert that specified text cannot be found use any of the following steps.

    Then /^I should not see "([^\"]*)"$/
    Then /^I don't see the text "([^\"]*)"$/
    Then /^I don't see "([^\"]*)"$/
	Then /^I don't see the "([^\"]*)"$/ do |text|  


To assert that specified button can be found.

	Then /^I should see a "([^\"]*)" button$/ do |expected_mark|
	Then /^I should not see a "([^\"]*)" button$/ do |expected_mark|


To assert that the specified input field or text field exists 

	Then /^I should see a "([^\"]*)" (?:input|text) field$/ do |name|
	Then /^I should not see a "([^\"]*)" (?:input|text) field$/ do |text|


To assert that the specified radio button is selected. (Android only)

	Then(/^"(.*?)" radio button should be selected$/) do |text|
	Then(/^"(.*?)" radio button should not be selected$/) do |text|


Input steps
-----------
Toggles the checkout with the specified number. (Android only)

    Then /^I toggle checkbox number (\d+)$/ do |checkboxNumber|

Toggles the first switch. (iOS only)

    Then /^I toggle the switch$/ do

Toggles the iOS switch with the specified name. (iOS only)

	Then /^I toggle the "([^\"]*)" switch$/ do |name|

Selects the radio button with the specified text. (Android only)

	Then(/^I select "(.*?)" radio button$/) do |text|

Enters the specified text into the input field with the specified index.

	Then /^I enter "([^\"]*)" into (?:input|text) field number (\d+)$/ do |text, index|

Enters the specified text into the input field with the specified name.

	Then /^I enter "([^\"]*)" into the "([^\"]*)" field$/ do |text, field_name|
	Then /^I enter "([^\"]*)" into the "([^\"]*)" (?:text|input) field$/ do |text, field_name|

Enters the specified text into the input field that has the specified content description (name or target).

	Then /^I enter "([^\"]*)" into "([^\"]*)"$/ do |text, name|
	Then /^I enter "([^\"]*)" as "([^\"]*)"$/ do |text, target|

Enters the specified text into the input field with the specified id. (Android only)

	Then /^I enter text "([^\"]*)" into field with id "([^\"]*)"$/ do |text, view_id|

Clears the text of the input field with the specified index. (Android only)

    Then /^I clear (?:input|text) field number (\d+)$/ do |index|

Clears the text of input fields marked by name. (Android only)

    Then /^I clear "([^\"]*)"$/ do |name|

Clears the text of the input field with the specified id. (Android only)

    Then /^I clear input field with id "([^\"]*)"$/ do |view_id|

Enters the specified text into the first search field.

	Then /^I enter "([^\"]*)" into search field$/ do |text|

Enters the specified text into the search field with the specified index.

	Then /^I enter "([^\"]*)" into search field number (\d+)$/ do |text, number|

Buttons
-------
Simulates that the user pressed the back button. (Android only)

    Then /^I go back$/

Simulates that the user pressed the menu button. (Android only)

    Then /^I press the menu key$/

Simulates that the user pressed the enter button on the keyboard. (Android only)

    Then /^I press the enter button$/

Gestures
--------
Swipes left.

	Then /^I swipe left$/

Swipes right.

    Then /^I swipe right$/

Scrolls down.
 
    Then /^I scroll down$/

Scrolls up.
    
    Then /^I scroll up$/


Drags from one point on the screen to another.<br/>
**Note: x:y co-ordinates are expressed as percentages of the screen width:height**
    
	Then /^I drag from (\d+):(\d+) to (\d+):(\d+) moving with (\d+) steps$/ do |from_x, from_y, to_x, to_y, steps|
    
Touching
--------
Taps the button containing the specified text.

	Then /^I (?:press|touch) the "([^\"]*)" button$/ do |name|

Taps the button with the specified index.

    Then /^I (?:press|touch) button number (\d+)$/ do |index|

Taps the image button with the specified index.

    Then /^I press image button number (\d+)$/ do |buttonNumber|

Taps the specified text.

    Then /^I touch the "([^\"]*)" text$/ do |text|

Taps the view marked by the specified identifier.

	Then /^I (?:press|touch) "([^\"]*)"$/ do |name|
	Then /^I press "([^\"]*)"$/ do |identifier|

Taps the screen at the specified location.

    Then /^I click on screen (\d+)% from the left and (\d+)% from the top$/ do |x, y|
	Then /^I (?:press|touch) on screen (\d+) from the left and (\d+) from the top$/ do |x, y|     

Device orientation
------------------
Rotate the device to landscape or portrait mode.

    Then /^I rotate the device to landscape$/ do
	Then /^I rotate the device to portrait$/ do

(This performs an actual physical rotation of the device.)

Waiting
-------
Waits for the specified text to appear.

    Then /^I wait for "([^\"]*)" to appear$/ do |text|
    Then /^I wait to see "([^\"]*)"$/ do |text|

Waits for the specified text to not appear.

	Then /^I wait until I don't see "([^\"]*)"$/ do |text|
	Then /^I wait to not see "([^\"]*)"$/ do |text|

Waits for the specified text to appear, with a custom timeout.

    Then /^I wait up to (\d+) seconds for "([^\"]*)" to appear$/ do |timeout, text|
    Then /^I wait up to (\d+) seconds to see "([^\"]*)"$/ do |timeout, †ext|

Waits for a button marked by the specified text to appear.

	Then /^I wait for the "([^\"]*)" button to appear$/ do |text|

Waits for one second.

    Then /^I wait for 1 second$/ do
	Then /^I wait for a second$/ do

Waits for two seconds (default value).

    Then /^I wait$/ do

Waits for the $defaultTimeout twice ($defaultTimeout is defined in env.rb file).

	Then /^I wait and wait$/ do 

Waits for the $defaultTimeout five times.

	Then /^I wait and wait and wait...$/ do

Waits for a specified number of seconds.

	Then /^I wait for ([\d\.]+) second(?:s)?$/ do |num_seconds|


Screenshots
-----------
To take a screenshot of the device while running the test use any of these steps.

    Then /^take picture$/
    Then /^I take a picture$/
    Then /^I take a screenshot$/
