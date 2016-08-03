# Modified by Perfecto Mobile Ltd.

# -*- coding: utf-8 -*-
Given /^My app is running$/ do
#  rotate_phone(0)
end

Given /^my app is running$/ do
#  rotate_phone(0)
end

## iOS step
Given /^(my|the) app is running$/ do |_|
  #no-op exists for backwards compatibility
end

