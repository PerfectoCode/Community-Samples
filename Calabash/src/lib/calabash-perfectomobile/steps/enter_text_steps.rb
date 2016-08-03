# Modified by Perfecto Mobile Ltd.

Then /^I enter "([^\"]*)" as "([^\"]*)"$/ do |text, target|
  PM_enter_text_to_field_by_name(text,target)
end


Then /^I enter "([^\"]*)" into (?:input|text) field number (\d+)$/ do |text, index|
#Then /^I enter "([^\"]*)" into input field number (\d+)$/ do |text, number|
  PM_enter_text_to_field_by_num(text,index)
end

Then /^I enter "([^\"]*)" into "([^\"]*)"$/ do |text, name|
  PM_enter_text_to_field_by_name(text,name)
end

Then /^I enter text "([^\"]*)" into field with id "([^\"]*)"$/ do |text, view_id|
  PM_enter_text_to_field_by_id(text,view_id)
end

## iOS step
Then /^I enter "([^\"]*)" into the "([^\"]*)" field$/ do |text, field_name|
  PM_enter_text_to_field_by_name(text,field_name)
end

## iOS step
Then /^I enter "([^\"]*)" into the "([^\"]*)" (?:text|input) field$/ do |text, field_name|
  PM_enter_text_to_field_by_name(text,field_name)
end

## iOS step
Then /^I fill in "([^\"]*)" with "([^\"]*)"$/ do |text_field, text_to_type|
  PM_unsupported()
end

## iOS step
Then /^I use the native keyboard to enter "([^\"]*)" into the "([^\"]*)" (?:text|input) field$/ do |text_to_type, field_name|
  PM_unsupported()
end

## iOS step
Then /^I fill in text fields as follows:$/ do |table|
  PM_unsupported()
end

## iOS step
Then /^I use the native keyboard to enter "([^\"]*)" into (?:input|text) field number (\d+)$/ do |text_to_type, index|
  PM_unsupported()
end

## iOS step
Then /^I (?:press|touch) (?:input|text) field number (\d+)$/ do |index|
  # PM gives error when trying to "click" a text field
  PM_unsupported()
end

## iOS step
Then /^I (?:press|touch) the "([^\"]*)" (?:input|text) field$/ do |name|
  # PM gives error when trying to "click" a text field
  PM_unsupported()
end


Then /^I clear "([^\"]*)"$/ do |name|
  PM_enter_text_to_field_by_name("",name)
end

Then /^I clear (?:input|text) field number (\d+)$/ do |index|
  PM_enter_text_to_field_by_num("",index)
end

Then /^I clear input field with id "([^\"]*)"$/ do |view_id|
  PM_enter_text_to_field_by_id("",view_id)
end

## iOS step
When /^I clear "([^\"]*)"$/ do |name|
  PM_unsupported()
end
