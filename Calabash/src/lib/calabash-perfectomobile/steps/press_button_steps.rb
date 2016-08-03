# Modified by Perfecto Mobile Ltd.

Then /^I (?:press|touch) the "([^\"]*)" button$/ do |name|
#Then /^I press the "([^\"]*)" button$/ do |buttonText|
  PM_button_click_by_text(name)
end

Then /^I (?:press|touch) button number (\d+)$/ do |index|
#Then /^I press button number (\d+)$/ do |buttonNumber|
  PM_button_click_by_number(index)
end

Then /^I press image button number (\d+)$/ do |button_number|
  PM_image_button_click_by_number(button_number)
end

Then /^I press view with id "([^\"]*)"$/ do |view_id|
  PM_unsupported()
end

Then /^I (?:press|touch) "([^\"]*)"$/ do |name|
#Then /^I press "([^\"]*)"$/ do |identifier|
  PM_element_click_by_id(name)
end

Then /^I click on screen (\d+)% from the left and (\d+)% from the top$/ do |x, y|
  PM_click_on_screen(x,y)
end

## iOS step
Then /^I (?:press|touch) on screen (\d+) from the left and (\d+) from the top$/ do |x, y|
  PM_click_on_screen(x,y)
end

## iOS step
Then /^I (?:press|touch) (\d+)% right and (\d+)% down from "([^\"]*)" $/ do |x,y,name|
  raise "This step is not yet implemented on iOS"
end

Then /^I touch the "([^\"]*)" text$/ do |text|
  PM_obj_click_by_text(text)
end

## iOS step
Then /^I (?:touch|press) (?:done|search)$/ do
  PM_unsupported()
end

Then /^I (?:press|touch) list item number (\d+)$/ do |index|
#Then /^I press list item number (\d+)$/ do |line_index|
  PM_unsupported()
end

## iOS step
Then /^I (?:press|touch) list item "([^\"]*)"$/ do |cell_name|
  PM_unsupported()
end

Then /^I long press list item number (\d+)$/ do |line_index|
  PM_unsupported()
end

Then(/^I select "(.*?)" radio button$/) do |text|
   PM_radiobutton_click_by_text(text)
end

Then(/^"(.*?)" radio button should be selected$/) do |text|
  PM_radiobutton_verify_is_clicked(text,true)
end

Then(/^"(.*?)" radio button should not be selected$/) do |text|
  PM_radiobutton_verify_is_clicked(text,false)
end

## iOS step
Then /^I swipe (left|right|up|down) on number (\d+)$/ do |dir, index|
  PM_unsupported()
end

## iOS step
Then /^I swipe (left|right|up|down) on number (\d+) at x (\d+) and y (\d+)$/ do |dir, index, x, y|
  PM_unsupported()
end

## iOS step
Then /^I swipe (left|right|up|down) on "([^\"]*)"$/ do |dir, mark|
  PM_unsupported()
end

## iOS step
Then /^I swipe on cell number (\d+)$/ do |index|
  PM_unsupported()
end
