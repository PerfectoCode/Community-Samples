# Modified by Perfecto Mobile Ltd.

# By default "get_list_item_text" returns an array of arrays of text for each entry in the first ListView
# The "get_list_item_text" action also supports: 
# (all items of 2nd list) <code>performAction( 'get_list_item_text', '2' )</code>
# (1st item of 2nd list) <code>performAction( 'get_list_item_text', '2' , '1' )</code>
Then /^I should see following list:$/ do | expected_table |
  PM_unsupported()
end