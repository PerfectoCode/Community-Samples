# Modified by Perfecto Mobile Ltd.

Then /^I wait$/ do
  PM_wait($defaultTimeout)
end

Then /^I wait for ([\d\.]+) second(?:s)?$/ do |num_seconds|
#Then /^I wait for (\d+) seconds$/ do |seconds|
  PM_wait(num_seconds)
end

Then /^I wait for a second$/ do
  PM_wait(1)
end

## iOS step
Then /^I wait and wait$/ do
  PM_wait($defaultTimeout * 2)
end

## iOS step
Then /^I wait and wait and wait...$/ do
  PM_wait($defaultTimeout * 5)
end

Then /^I wait for progress$/ do
  PM_unsupported() 
end

Then /^I wait for dialog to close$/ do
  PM_unsupported()
end

Then /^I wait for "([^\"]*)" to appear$/ do |text|
  PM_validate_by_text_obj(text,true,$defaultTimeout)
end

Then /^I wait up to (\d+) seconds for "([^\"]*)" to appear$/ do |timeout, text|
  PM_validate_by_text_obj(text,true,timeout)
end

Then /^I wait to see "([^\"]*)"$/ do |text|
  PM_validate_by_text_obj(text,true,$defaultTimeout)
end

Then /^I wait up to (\d+) seconds to see "([^\"]*)"$/ do |timeout, text|
  PM_validate_by_text_obj(text,true,timeout)
end

## iOS step
Then /^I wait until I don't see "([^\"]*)"$/ do |text|
  PM_validate_by_text_obj(text,false,0)
end

## iOS step
Then /^I wait to not see "([^\"]*)"$/ do |text|
  PM_validate_by_text_obj(text,false,0)
end

Then /^I wait for the "([^\"]*)" button to appear$/ do |text|
  PM_button_find_by_text(text,true)
end

Then /^I wait for the view with id "([^\"]*)" to appear$/ do |text|
  PM_unsupported()
end

Then /^I wait for the "([^\"]*)" view to appear$/ do |text|
  PM_unsupported()
end

Then /^I wait for the "([^\"]*)" screen to appear$/ do |text|
  PM_unsupported()
end

Then /^I wait upto (\d+) seconds for the "([^\"]*)" screen to appear$/ do |timeout, text|
  PM_unsupported()
end

Then /^I wait up to (\d+) seconds for the "([^\"]*)" screen to appear$/ do |timeout, text|
  PM_unsupported()
end

# @param - the "tag" associated with the tab, or the text within the tab label
Then /^I wait for the "([^\"]*)" tab to appear$/ do | tab |
  PM_unsupported()
end

## iOS step
Then /^I wait to see a navigation bar titled "([^\"]*)"$/ do |expected_mark|
  PM_unsupported()
end

## iOS step
Then /^I wait for the "([^\"]*)" (?:input|text) field$/ do |placeholder_or_view_mark|
  PM_unsupported()
end

## iOS step
Then /^I wait for (\d+) (?:input|text) field(?:s)?$/ do |count|
  PM_unsupported()
end
