# Modified by Perfecto Mobile Ltd.

Then /^I rotate the device to landscape$/ do
  PM_rotate("landscape")
end

Then /^I rotate the device to portrait$/ do
  PM_rotate("portrait")
end

## iOS step
Then /^I rotate device (left|right)$/ do |dir|
  PM_unsupported()
end

## iOS step
Then /^I send app to background for (\d+) seconds$/ do |secs|
  PM_unsupported()
end

