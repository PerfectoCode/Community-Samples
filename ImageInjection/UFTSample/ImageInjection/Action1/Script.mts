'//** Required Parameters- please point to app and image location in repository
'//** Default Values can be kept
Dim appInRepository, checkImageInRepository

appInRepository 		= "private:ImageInjection/RealTimeFilter.ipa"
checkImageInRepository  = "private:ImageInjection/check.jpeg"

'//** Optional Pre-Step
'//** Below Uploads the App and Image automatically to specified repository location
Dim url, perfectoUser, perfectoPass, appLocalPath, checkImageLocalPath 
url 				 = "demo.perfectomobile.com"
perfectoUser		 = "philipps@perfectomobile.com"
perfectoPass		 = EnterPerfectoPassword
appLocalPath		 = "C:\tmp\RealTimeFilter.ipa"
checkImageLocalPath  = "C:\tmp\check.jpeg"

upload_repository_item "https://"&url&"/services/repositories/", _
					checkImageLocalPath, perfectoUser,_
					perfectoPass, _
                    "media", checkImageInRepository
upload_repository_item "https://"&url&"/services/repositories/", _
					appLocalPath, perfectoUser,_
					perfectoPass, _
                    "media", appInRepository

'//** Main Test
Device("DUT").Home
Device("DUT").Applications.Install appInRepository,"cameraInstrument=camera"
Device("DUT").Applications.Start "name=realtimefilter"

Device("DUT").StartImageInjection checkImageInRepository,"name=realtimefilter"
wait 5 'to give us a chance to see
Device("DUT").MObject("YOUR NAME").Find "timeout=20"
Device("DUT").StopImageInjection

Device("DUT").Applications.Uninstall "name=realtimefilter"

'//** Helper Function to upload Media files to Repository. https://community.perfectomobile.com/posts/932639-qtp-uft-upload-files-to-mcm-repository	
Function upload_repository_item(url, file_to_upload, user_id, password, rep_type, rep_path)
    mcm_url = url
    src_file = file_to_upload
    strUserID = user_id
    strPassword = password
    repository_type = rep_type
    repository_path = rep_path
    strURL = mcm_url & repository_type & "/" & repository_path & _
             "?operation=upload&user=" & strUserID & "&password=" & strPassword
    Set HTTP = CreateObject ("Microsoft.XMLHTTP")
    Set objStream = CreateObject("ADODB.Stream")
    objStream.Type = 1
    objStream.Open 
    objStream.LoadFromFile src_file
    HTTP.open "PUT", strURL, False
    file_stream = objStream.Read
    HTTP.send file_stream
    Set HTTP = Nothing
End Function
