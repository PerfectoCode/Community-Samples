# Modified by Perfecto Mobile Ltd.

Feature: Calabash-perfectomobile Testing - sample file
 
 Scenario: log in
    When I enter "myUser" into input field number 1
	   Then I press the "Login" button
	   Then I wait
	   Then I should see text containing "INVALID"

# Scenario: Swiping left-right 
#    When I swipe left
# 	 	Then I wait   
# 	 	Then I swipe right
 	