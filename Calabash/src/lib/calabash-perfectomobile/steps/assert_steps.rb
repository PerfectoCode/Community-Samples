# Modified by Perfecto Mobile Ltd.

Then /^I see the text "([^\"]*)"$/ do |text|
  PM_validate_by_text_obj(text,true,0)
end

Then /^I see "([^\"]*)"$/ do |text|
  PM_validate_by_text_obj(text,true,0)
end

Then /^I should see "([^\"]*)"$/ do |text|
  PM_validate_by_text_obj(text,true,0) 
end

Then /^I (?:should)?(?: )?see text containing "([^\"]*)"$/ do |text|
#Then /^I  see text containing "([^\"]*)"$/ do |text|
#Then /^I see text containing "([^\"]*)"$/ do |text|
#Then /^I should see text containing "([^\"]*)"$/ do |text|
  PM_validate_by_text_contain(text,true)
end

## iOS step
Then /^I don't see the "([^\"]*)"$/ do |text|
  macro %Q|I should not see "#{text}"|
end

## iOS step
Then /^I see the "([^\"]*)"$/ do |text|
  macro %Q|I should see "#{text}"|
end

## iOS step
Then /^I (?:should)?(?: )?see text starting with "([^\"]*)"$/ do |text|
  PM_validate_by_text_start(text,true)
end

## iOS step
Then /^I (?:should)?(?: )?see text ending with "([^\"]*)"$/ do |text|
  PM_validate_by_text_end(text,true)
end

Then /^I should not see "([^\"]*)"$/ do |text|
  PM_validate_by_text_obj(text,false,0)
end


Then /^I don't see the text "([^\"]*)"$/ do |text|  
  PM_validate_by_text_obj(text,false,0)
end

Then /^I don't see "([^\"]*)"$/ do |text|  
  PM_validate_by_text_obj(text,false,0)
end

## iOS step
Then /^I should see a "([^\"]*)" button$/ do |text|
  PM_button_find_by_text(text,true)
end

## iOS step
Then /^I should not see a "([^\"]*)" button$/ do |text|
  PM_button_find_by_text(text,false)
end

## iOS step
Then /^I see (\d+) (?:input|text) field(?:s)?$/ do |count|
  PM_unsupported()
end

## iOS step
Then /^I should see a "([^\"]*)" (?:input|text) field$/ do |name|
  PM_validate_textfield_by_name(name,true)
end

## iOS step
Then /^I should not see a "([^\"]*)" (?:input|text) field$/ do |text|
  PM_validate_textfield_by_name(text,false)
end
   
# This step is more of an example or macro to be used within your own custom steps
# Generally, assert_view_property takes 3 args, but for if 'property'='compoundDrawables', the next arg should be 'left'/'right'/'top'/'bottom', followed by the expected drawable ID.
# @param view_id - the name of the view, eg: R.my_view_id
# @param property - eg: 'visibility' (visible/invisible/gone), 'drawable' (expected drawable ID) 
Then /^the view with id "([^\"]*)" should have property "([^\"]*)" = "([^\"]*)"$/ do | view_id, property, value |
  # get_view_property is also available: performAction( 'get_view_property', 'my_view', 'visibility') 
  PM_unsupported()
  #performAction( 'assert_view_property', view_id, property, value )
end

Then /^the "([^\"]*)" activity should be open$/ do | expected_activity |
  PM_unsupported()
  #actual_activity = performAction('get_activity_name')['message']
  #raise "The current activity is #{actual_activity}" unless( actual_activity == expected_activity || actual_activity == expected_activity + 'Activity' )
end

## iOS step
Then /^I should see a map$/ do
  PM_unsupported()
end

## iOS step
Then /^I should see (?:the)? user location$/ do
  PM_unsupported()
end
