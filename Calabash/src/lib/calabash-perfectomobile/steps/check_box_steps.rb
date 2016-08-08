# Modified by Perfecto Mobile Ltd.

Then /^I toggle checkbox number (\d+)$/ do |checkboxNumber|
  PM_toggle_checkbox(checkboxNumber)
end

## iOS step
Then /^I toggle the switch$/ do
  PM_toggle_switch_by_num(1)
end

## iOS step
Then /^I toggle the "([^\"]*)" switch$/ do |name|
  PM_toggle_switch_by_name(name)
end
