@amazon
Feature: Amazon Search Feature 
#Sample Test Scenario Description

@amazonSearch
Scenario: Amazon Search Scenario
	Given I open browser to webpage "amazon.com"
	Then I perform a Splunk transaction using the name "1 Amazon Opened?" and description of "Opening Amazon Web Site" with an SLA of "60000" - I'm also utilizing an OCR checkpoint for the word "Departments" with a timeout of "60" and threshold of "100" 
	Then I search for "books"
	Then I perform a Splunk transaction using the name "2 searched item" and description of "Searching for an item" with an SLA of "60000" - I'm also utilizing an OCR checkpoint for the word "Best Books of the Month" with a timeout of "60" and threshold of "100"
	#Then I perform a Splunk transaction using the name "2 Audio Played?" and description of "Verifying audio loaded" with an SLA of "60000" - I'm also utilizing an Image checkpoint for the image "PUBLIC:VZTones/Stop_Button_GS4.png" with a timeout of "60" and threshold of "50"