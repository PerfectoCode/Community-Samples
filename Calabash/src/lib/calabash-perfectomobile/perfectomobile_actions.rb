# Modified by Perfecto Mobile Ltd.
require 'net/http'
require 'cgi'

##############################################################################################
# Screen navigation / Phone Buttons - called from navigation_steps.rb
##############################################################################################

def PM_press_phone_back_button()
  debug( "PM_press_phone_back_button")
  param = "&param.keySequence=BACK"
  PM_runCommand("presskey","",param,true)  
end

def PM_press_phone_menu_button()
  debug(  "PM_press_phone_back_button")
  param = "&param.keySequence=MENU"
  PM_runCommand("presskey","",param,true)  
end

def PM_press_phone_enter_button()
  debug(  "PM_press_phone_back_button")
  param = "&param.keySequence=ENTER"
  PM_runCommand("presskey","",param,true)  
end

def PM_swipe_right() 
  debug(  "PM_swipe_right")
  startPoint = "25%,50%"
  endPoint = "75%,50%"  
  PM_swipe(startPoint,endPoint,1)
end

def PM_swipe_left() 
  debug(  "PM_swipe_left")
  startPoint = "75%,50%"
  endPoint = "25%,50%"  
  PM_swipe(startPoint,endPoint,1)
end

def PM_scroll_down() 
  debug(  "PM_scroll_down")
  startPoint = "50%,75%"
  endPoint = "50%,25%"  
  PM_swipe(startPoint,endPoint,1)
end

def PM_scroll_up() 
  debug(  "PM_scroll_up")
  endPoint = "50%,75%"
  startPoint = "50%,25%"
  PM_swipe(startPoint,endPoint,1)
end

def PM_drag(fromX,toX,fromY,toY,seconds)
  startPoint = "#{fromX},#{fromY}"
  endPoint = "#{toX},#{toY}"
  PM_swipe(startPoint,endPoint,seconds)
end

def PM_swipe(startPoint,endPoint,duration) 
  encodedStartPoint = CGI.escape(startPoint)
  encodedEndPoint = CGI.escape(endPoint)
  param = "&param.start=#{encodedStartPoint}&param.end=#{encodedEndPoint}&param.duration=#{duration}"
  PM_runCommand("touch","swipe",param,true)    
end


##############################################################################################
# Rotate
##############################################################################################
def PM_rotate(mode)
  # mode = /landscape/portrait
  debug(  "PM_rotate:"+mode)
  param = "&param.state="+mode
  PM_runCommand("device","rotate",param,true)  
end


###################################################################33
#  screenShot
##################################################################33
def PM_screenshot()
  param = ""
  PM_runCommand("screen","image",param,true)  
end
##############################################################################################
# Wait
##############################################################################################

def PM_wait(seconds) 
  debug(  "PM_ACTIONS:PM_wait: seconds:"+seconds.to_s)
  sleep(seconds.to_i)
end

#def PM_wait_for_text(text) 
#  puts "PM_ACTIONS: PM_wait_for_text : "+text
#  val = URI::encode(text)
#  param = "&param.value=#{val}&param.by=linkText"
#  outcome = PM_runCommand("application.element","find",param,false) 
#
#  
#end

##############################################################################################
# Checkbox
##############################################################################################
def PM_toggle_checkbox(checkboxNumber)
  debug(  "PM_ACTIONS:PM_toggle_checkbox")
  xpath = "//checkbox["+checkboxNumber+"]"
  encodedXpath = URI::encode(xpath)
  param = "&param.value=#{encodedXpath}&param.by=xpath"
  PM_runCommand("application.element","click",param,true)  

end


##############################################################################################
# RadioButtons
##############################################################################################
def PM_radiobutton_click_by_text(text)
     debug(  "PM_radiobutton_click_by_text (by xpath):"+text)
     # Example : //radiobutton[text()='Africa']
	 xpath1 = ".//radiobutton[text()='"
     xpath2 = "']"
     encodedXpath = getEncodedXpath(xpath1, text, xpath2)
     param = "&param.value=#{encodedXpath}&param.by=xpath"
     PM_runCommand("application.element","click",param,true)  
     
end 

def PM_radiobutton_verify_is_clicked(text,clicked)
    debug(  "PM_radiobutton_verify_is_clicked "+text)
     # Example : //radiobutton[text()='Africa']
     xpath1 = ".//radiobutton[text()='"
     xpath2 = "']"
     encodedXpath = getEncodedXpath(xpath1, text, xpath2)
     param = "&param.value=#{encodedXpath}&param.by=xpath&param.property=checked"
     result = PM_runCommand("application.element","info",param,true)  
     isChecked =  result['returnValue']
     if ("#{isChecked}" != "#{clicked}") 
       raise "Radio button checked - "+isChecked
     end  

end 

##############################################################################################
# iOS switch
##############################################################################################

def PM_toggle_switch_by_name(name)
  debug(  "PM_toggle_switch_by_name "+name)

  xpath1 = ".//switch[@name='"
  xpath2 = "']"
  encodedXpath = getEncodedXpath(xpath1, name, xpath2)
  param = "&param.value=#{encodedXpath}&param.by=xpath"
  PM_runCommand("application.element","click",param,true)  
  
end

def PM_toggle_switch_by_num(number)
  debug(  "PM_toggle_switch_by_num : #{number}")
  xpath = ".//switch[#{number}]"
  encodedXpath = URI::encode(xpath)
  param = "&param.value=#{encodedXpath}&param.by=xpath"
  PM_runCommand("application.element","click",param,true)  
  
end

##############################################################################################
# Buttons
##############################################################################################
      
#def PM_button_click_by_text_no_xpath(text)
#    # Don't want to use this because there might be multiple elements with this text
#    # use PM_button_click_by_text with xpath
#     puts "PM_ACTIONS:PM_button_click_by_text"
#     val = URI::encode(text)
#     param = "&param.value=#{val}&param.by=linkText"
#     PM_runCommand("application.element","click",param,true)  
#end 

def PM_obj_click_by_text(text)
  # Fails if text is not the full text of the object
  debug(  "PM_ACTIONS:PM_obj_click_by_text")
  val = URI::encode(text)
  param = "&param.value=#{val}&param.by=partialLinkText"
  PM_runCommand("application.element","click",param,true) 
end

def PM_button_find_by_text(text,appear)
  pm_rc = PM_button_do_by_text(text,"find",false) 
  outcome = pm_rc['reason']

  debug ("GOT HERE after validation : outcome: #{outcome}") 
  if "#{outcome}" != "Success" and appear == true
    # Button not found although we are validating that it should appear
    raise "\""+text+"\" button not found"
  end 
  if "#{outcome}" == "Success" and appear == false
    # Button found although we are validating that it should not appear
    raise "\""+text+"\" button found"
  end 
end

def PM_button_click_by_text(text)
  PM_button_do_by_text(text,"click",true)
end

def PM_button_do_by_text(text,action,raise)
    debug(  "PM_ACTIONS:PM_button_click_by_text (by xpath)")
     ## for this object find use XPATH with //button and text
     #xpath = ".//button[text()='#{text}']"
     # Do not encode the text - if it has a space it will be escaped later with CGI
     xpath1 = ".//button[text()='"
     xpath2 = "']"
     encodedXpath = getEncodedXpath(xpath1, text, xpath2)
     #puts " >>>>>  #{encodedXpath}"
     param = "&param.value=#{encodedXpath}&param.by=xpath"
  begin
    return PM_runCommand("application.element",action,param,raise)  
  rescue
    # iPhone - some cases cell object is used as button
    if (@deviceOS == 'iOS')
      debug("PM_ACTIONS:PM_button_click_by_text : trying cell on iPhone")
      xpath1 = ".//cell[@name='"
      xpath2 = "']"
	  encodedXpath = getEncodedXpath(xpath1, text, xpath2)
      #puts " >>>>>  #{encodedXpath}"
      param = "&param.value=#{encodedXpath}&param.by=xpath"
      return PM_runCommand("application.element",action,param,raise)
    else
      raise $!
    end
 
  end
      
end 

def PM_button_click_by_number(number)
    debug(  "PM_ACTIONS:PM_button_click_by_number")
    xpath = "(//button)["+number+"]"  
    encodedXpath = URI::encode(xpath)
    param = "&param.value=#{encodedXpath}&param.by=xpath"
    PM_runCommand("application.element","click",param,true)  
end 

def PM_image_button_click_by_number(number)
  debug(  "PM_ACTIONS:PM_image_button_click_by_number")
    
    # XPATH example : (//*[contains(@class,'ImageButton')])[1] 
    
  xpath1 = "(//*[contains(@class,'ImageButton')])["
  xpath2 = "]"
  encodedXpath = getEncodedXpath(xpath1, number, xpath2)
  #puts "*******encodedXpath2:"+encodedXpath
 
  param = "&param.value=#{encodedXpath}&param.by=xpath"
  PM_runCommand("application.element","click",param,true)  

end 

def PM_element_click_by_id(id)
  debug( "PM_ACTIONS:PM_element_click_by_id:"+id)
  
  xpath1 = "//*[contains(@resourceid,'"
  xpath2 = "')]"
  encodedXpath = getEncodedXpath(xpath1, id, xpath2)
 
  param = "&param.value=#{encodedXpath}&param.by=xpath"
  PM_runCommand("application.element","click",param,true)  

end

def PM_click_on_screen(x,y) 
  debug( "PM_ACTIONS:PM_click_on_screen x:"+x.to_s+",y:")
  coordinates = "#{x},#{y}"
  encodedCoordinates = CGI.escape(coordinates)
  param = "&param.location=#{encodedCoordinates}&param.duration=1"
  PM_runCommand("touch","tap",param,true)    
end

##############################################################################################
#  Text Field
##############################################################################################

def PM_enter_text_to_field_by_num(text,number)
  debug(  "PM_ACTIONS:PM_enter_text_to_field_by_num")
  xpath = ".//textfield[#{number}]"  
  encodedXpath = URI::encode(xpath)
  encodedText = URI::encode(text)
  param = "&param.text=#{encodedText}&param.value=#{encodedXpath}&param.by=xpath"

  begin
    PM_runCommand("application.element","set",param,true)  
  rescue
    if (@deviceOS == 'iOS')
      debug(  "PM_enter_text_to_field_by_num - trying secure field in ios")
      # looking for first as there is no way to know order of secure fields within fields 
      # assuming no more than 1 password 
      xpath = ".//secure[1]"  
      encodedXpath = URI::encode(xpath)
      encodedText = URI::encode(text)
      param = "&param.text=#{encodedText}&param.value=#{encodedXpath}&param.by=xpath"
      PM_runCommand("application.element","set",param,true)
    else
      raise $!
    end
  end  
end

def PM_validate_textfield_by_name(name,appear)
  xpath1 = ".//textfield[@name='"
  xpath2 = "']"
  encodedXpath = getEncodedXpath(xpath1, name, xpath2)
  param = "&param.value=#{encodedXpath}&param.by=xpath"
  pm_rc = PM_runCommand("application.element","find",param,false) 
  outcome = pm_rc['reason']

  debug ("GOT HERE after validation : outcome: #{outcome}") 
  if "#{outcome}" != "Success" and appear == true
    # Textfield not found although we are validating that it should appear
    raise "\""+name+"\" textfield not found"
  end 
  if "#{outcome}" == "Success" and appear == false
    # Textfield found although we are validating that it should not appear
    raise "\""+name+"\" textfield found"
  end 
   
end

def PM_enter_text_to_field_by_name(text,name)
  debug(  "PM_ACTIONS:PM_enter_text_to_field_by_name")
  xpath1 = ".//textfield[@name='"
  xpath2 = "']"
  encodedXpath = getEncodedXpath(xpath1, name, xpath2)
  encodedText = URI::encode(text)
  param = "&param.text=#{encodedText}&param.value=#{encodedXpath}&param.by=xpath"
  PM_runCommand("application.element","set",param,true)  
  
end

def PM_enter_text_to_field_by_id(text,id)
  debug(  "PM_enter_text_to_field_by_id:"+text+", id:"+id)
  
  xpath1 = "//*[contains(@resourceid,'"
  xpath2 = "')]"
  encodedXpath = getEncodedXpath(xpath1, id, xpath2)
  encodedText = URI::encode(text)
 
  param = "&param.text=#{encodedText}&param.value=#{encodedXpath}&param.by=xpath"
  PM_runCommand("application.element","set",param,true)  

end

def PM_enter_search_field_by_num(text,number)
  debug(  "PM_ACTIONS:PM_enter_search_field_by_num")
  xpath = ".//search[#{number}]"  
  encodedXpath = URI::encode(xpath)
  encodedText = URI::encode(text)
  param = "&param.text=#{encodedText}&param.value=#{encodedXpath}&param.by=xpath"
  PM_runCommand("application.element","set",param,true)  
end

##############################################################################################
#  Validation
##############################################################################################

def PM_validate_by_text_obj(text, appear, timeout)
    # Fails if text is not the full text of the object
    debug(  "PM_validate_by_text_obj")
    val = URI::encode(text)
    param = "&param.value=#{val}&param.by=partialLinkText"
    if (timeout != nil and timeout.to_i > 0)
      param += "&param.timeout=#{timeout}"
    end
    pm_rc = PM_runCommand("application.element","find",param,false) 
    outcome = pm_rc['reason']

    debug ("GOT HERE after validation : outcome: #{outcome}") 
    if "#{outcome}" != "Success" and appear == true
      # String not found although we are validating that it should appear
      raise "\""+text+"\" not found"
    end 
    if "#{outcome}" == "Success" and appear == false
      # String found although we are validating that it should not appear
      raise "\""+text+"\" found"
    end 

end

def PM_validate_by_text_contain(text, appear)
  PM_validate_by_text_how(text, appear,"contains")
end
def PM_validate_by_text_start(text, appear)
  PM_validate_by_text_how(text, appear,"starts-with")
end
def PM_validate_by_text_end(text, appear)
  PM_validate_by_text_how(text, appear,"ends-with")
end

def PM_validate_by_text_how(text, appear, how)
    # Succeeds if text is not the full text of the object

    debug("PM_validate_by_text_contains:"+text)
    
    xpath1 = "//*[#{how}(text(),'"
    xpath2 = "')]"
	encodedXpath = getEncodedXpath(xpath1, text, xpath2)
    param = "&param.value=#{encodedXpath}&param.by=xpath"
    
    
    pm_rc = PM_runCommand("application.element","find",param,false) 
    outcome = pm_rc['reason']

    debug ("GOT HERE after validation : outcome: #{outcome}") 
    if "#{outcome}" != "Success" and appear == true
      # String not found although we are validating that it should appear
      raise "\""+text+"\" not found"
    end 
    if "#{outcome}" == "Success" and appear == false
      # String found although we are validating that it should not appear
      raise "\""+text+"\" found"
    end 

end

      
def validateText(text)
        debug(" PM VALIDATE TEXT")
        param = "&param.timeout=20&param.content=#{text}"
        PM_runCommand("checkpoint","text",param)  
    
end
      
def buttonclick(text)
        param = "&param.label=#{text}"
        PM_runCommand("button-text","click",param)  
end

def setEditBox(name, text)
        param = "&param.label=#{name}&param.text=#{text}"
        PM_runCommand("edit-text","set",param)  
end


##############################################################################################
# Handle unsupported features of calabash
##############################################################################################

def PM_unsupported()
    raise("Unsupported by calabash-perfectomobile")
end

def  PM_no_screenshot_needed()
  puts "No need to take screenshot manually, it will be included in PM report in case of failure or error"
end
##############################################################################################
# General helper functions
##############################################################################################

def debug(text)
  if ENV["PM_DEBUG"] != nil
    puts "DEBUG:"+text      
  end

end
        
def gotoUrl(url)
    param = "&param.url=#{url}&param.automation=native"
    PM_runCommand("browser","goto",param)  
end 

def getEncodedXpath(xpath1, value, xpath2)
    encodedXpath1 = URI::encode(xpath1)
    encodedXpath2 = URI::encode(xpath2)
    encodedXpath = "#{encodedXpath1}#{value}#{encodedXpath2}" 
    return CGI.escape(encodedXpath)
end

def getResponse(urlStr)
  uri = URI.parse("#{urlStr}")

  http = Net::HTTP.new(uri.host, uri.port)
  http.use_ssl = true
  http.verify_mode = OpenSSL::SSL::VERIFY_NONE
  request = Net::HTTP::Get.new uri

  request = Net::HTTP::Get.new(uri.request_uri)
  response = http.request(request)

  return response.body
end

    
## Execute command on PM device    
 
def PM_runCommand(command, subcommand, param, raiseError)
    
    urlStr = "https://#{$PMCloud}/services/executions/#{@runID}?user=#{$PMUser}&password=#{$PMPassword}&operation=command&command=#{command}&subcommand=#{subcommand}&param.handsetId=#{$PMDevice}#{param}"
    debug ("CALL PM : run CMD : #{urlStr}")
    val = getResponse(urlStr)
    pm_rc = JSON.parse(val)
    
    debug ("********pm_rc:#{pm_rc}")
    outcome = pm_rc['reason']
    reason = pm_rc['description']
 
    debug ("**RETURN VAL FROM PM:#{outcome}:#{reason}")
     
    if (raiseError)     
      if outcome != 'Success'  
        raise "#{reason}"
      end  
    end
      
    return  pm_rc
end



