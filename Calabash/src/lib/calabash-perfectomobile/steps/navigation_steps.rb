# Modified by Perfecto Mobile Ltd.

Then /^I go back$/ do
  PM_press_phone_back_button()
end

Then /^I press the menu key$/ do
  PM_press_phone_menu_button()
end

Then /^I press the enter button$/ do
  PM_press_phone_enter_button()
end

Then /^I swipe left$/ do
  PM_swipe_left()
end

Then /^I swipe right$/ do  
  PM_swipe_right()
end

Then /^I select "([^\"]*)" from the menu$/ do |item|  
  PM_unsupported()
end

Then /^I select tab number (\d+)$/ do | tab |
  PM_unsupported()
end

# @param - the "tag" associated with the tab, or the text within the tab label
Then /^I select the "([^\"]*)" tab$/ do | tab |
  PM_unsupported()
end

Then /^I scroll down$/ do
  PM_scroll_down()
end

Then /^I scroll up$/ do
  PM_scroll_up()
end

Then /^I swipe down$/ do
  PM_scroll_down()
end

Then /^I swipe up$/ do
  PM_scroll_up()
end

Then /^I drag from (\d+):(\d+) to (\d+):(\d+) moving with (\d+) steps$/ do |fromX, fromY, toX, toY, steps|
  PM_drag(fromX,toX,fromY,toY,steps)
end
   
## iOS step
Then /^I pinch to zoom (in|out)$/ do |in_out|
  PM_unsupported()
end

## iOS step
Then /^I pinch to zoom (in|out) on "([^\"]*)"$/ do |in_out, name|
  PM_unsupported()
end

## iOS step
Then /^I scroll (left|right|up|down) on "([^\"]*)"$/ do |dir,name|
  PM_unsupported()
end
