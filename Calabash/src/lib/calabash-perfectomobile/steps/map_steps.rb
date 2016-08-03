# Modified by Perfecto Mobile Ltd.

When /^I centre the map at (-?\d+\.\d+), (-?\d+\.\d+)$/ do | lat, lon |
  PM_unsupported()
end

When /^I pan the map to (-?\d+\.\d+), (-?\d+\.\d+)$/ do | lat, lon |
  PM_unsupported()
end

When /^(?:I )?set the map zoom level to (\d+)$/ do | zoom |
  PM_unsupported()
end

When /^(?:I )?zoom (in|out) on the map$/ do | zoom |
  PM_unsupported()
end

Then /^the map zoom level should be (\d+)$/ do | zoom |
  PM_unsupported()
end

When /^I tap the map marker "([^\"]*)"$/ do | marker_title |
  PM_unsupported()
end

When /^I double tap the map marker "([^\"]*)"$/ do | marker_title |
  PM_unsupported()
end

When /^I tap away from the markers$/ do
  PM_unsupported()
end

Then /^I should see the following markers:$/ do | marker_table |
  PM_unsupported()
end

Then /^the map should be centred at (-?\d+\.\d+), (-?\d+\.\d+)$/ do | lat, lon |
  PM_unsupported()
end

Then /^the map marker "([^\"]*)" should be highlighted$/ do | marker_title |
  PM_unsupported()
end

## iOS step
Then /^I touch (?:the)? user location$/ do
  PM_unsupported()
end
