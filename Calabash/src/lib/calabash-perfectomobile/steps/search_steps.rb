# Modified by Perfecto Mobile Ltd.

Then /^I enter "([^\"]*)" into search field$/ do |text|
  PM_enter_search_field_by_num(text,1)
end

Then /^I enter "([^\"]*)" into search field number (\d+)$/ do |text, number|
  PM_enter_search_field_by_num(text,number)
end
