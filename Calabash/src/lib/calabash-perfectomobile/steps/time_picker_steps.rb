# Modified by Perfecto Mobile Ltd.

Given /^I set the time to "(\d\d:\d\d)" on TimePicker with index "([^\"]*)"$/ do |time, index|
  PM_unsupported()
end

Given /^I set the "([^\"]*)" time to "(\d\d:\d\d)"$/ do |content_description, time|
  PM_unsupported()
end

## iOS step
# time_str can be in any format that Time can parse
Then(/^I change the date picker time to "([^"]*)"$/) do |time_str|
  PM_unsupported()
end

## iOS step
# date_str can be in any format that Date can parse
Then(/^I change the date picker date to "([^"]*)"$/) do |date_str|
  PM_unsupported()
end

## iOS step
# date_str can be in any format that Date can parse
Then(/^I change the date picker date to "([^"]*)" at "([^"]*)"$/) do |date_str, time_str|
  PM_unsupported()
end

