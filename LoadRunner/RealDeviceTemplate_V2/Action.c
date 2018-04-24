Action()
{

// ******************************************* Execute Perfecto Script  **************************************/    
	char url[1000]="";
	               
	lr_save_string(lr_get_attrib_string( "PerfectoScriptName" ), "scriptName");
	lr_save_string(lr_get_attrib_string( "PerfectoCloud" ), "PerfectoCloud");
	lr_save_string(lr_get_attrib_string( "PerfectoUserID" ), "UserName");
	lr_save_string(lr_decrypt(lr_get_attrib_string( "PerfectoPassword" )), "Password");

	web_set_sockets_option("SSL_VERSION", "TLS");
	// URL-encode the url (turn space to %20 etc.)
	web_convert_param("scriptName", "SourceEncoding=PLAIN",
            "TargetEncoding=URL", LAST);
	               
// create base URL
	if(lr_get_attrib_string("PerfectoMoreParameters") == NULL) {
		sprintf(url, "URL=https://%s/services/executions?operation=execute&scriptkey=%s.xml&responseformat=xml&param.DUT=%s&user=%s&password=%s%s",
			lr_get_attrib_string( "PerfectoCloud" ),
		    lr_eval_string( "{scriptName}" ),
		    lr_get_attrib_string( "PerfectoDeviceDUT" ),
		    lr_get_attrib_string( "PerfectoUserID" ),
		    lr_decrypt(lr_get_attrib_string( "PerfectoPassword" )));
	} else {
		sprintf(url, "URL=https://%s/services/executions?operation=execute&scriptkey=%s.xml&responseformat=xml&param.DUT=%s&user=%s&password=%s%s",
			lr_get_attrib_string( "PerfectoCloud" ),
		    lr_eval_string( "{scriptName}" ),
		    lr_get_attrib_string( "PerfectoDeviceDUT" ),
		    lr_get_attrib_string( "PerfectoUserID" ),
		    lr_decrypt(lr_get_attrib_string( "PerfectoPassword" )),
		    lr_get_attrib_string( "PerfectoMoreParameters" ));
	}	

//	    lr_save_string(url, "url");
// URL-encode the url (turn space to %20 etc.)	
//	sprintf(url, "URL=%s", lr_eval_string("{url}"));
	
	executePerfectoScript(url);
	checkExecutionStatus();
	analyzePerfectoReportAndReportToLR();
	


	
    return 0;
}

int executePerfectoScript(char *URL)
{
	int i = 0;
	web_reg_save_param("DeviceInUse", "LB=device", "RB=in use","ORD=1", "Notfound=warning", LAST);
	web_reg_save_param("AuthFail", "LB=bad", "RB=credentials","ORD=1", "Notfound=warning", LAST);
	web_reg_save_param("ExecutionID", "LB=<executionId>", "RB=</","ORD=1", "Notfound=warning", LAST);
	web_reg_save_param("ReportID", "LB=<reportKey>", "RB=</","ORD=1", "Notfound=warning", LAST);

	do{
	web_url("web_url",
			URL, 
            "TargetFrame=",
            "Resource=1",
            "Referer=",
            "Mode=HTML",
            LAST); 
		if( strlen(lr_eval_string("{ExecutionID}")) >0)
// success
			return 1;
		else
// authentication failure
			if( strlen(lr_eval_string("{AuthFail}")) >0)
			{
	          lr_error_message ("Authentication Error");
	          lr_exit(LR_EXIT_VUSER, LR_FAIL);
			}
			else
// device not available				
				lr_think_time(30);
	} while(i++<3 && strlen(lr_eval_string("{ExecutionID}")) <1);
	if( strlen(lr_eval_string("{ExecutionID}")) >0)
		return 1;
	else
//device not found		
	{
          lr_error_message ("Unable to allocate device");
          lr_exit(LR_EXIT_VUSER, LR_FAIL);
	}
// dummy we will never get here
	return 0;

}
void checkExecutionStatus()
{
	// ******************************************* Check Perfecto Script Exeution status **************************************/    

	lr_output_message(" ***** execID: %s", lr_eval_string("{ExecutionID}"));
	lr_output_message(" ***** repID: %s", lr_eval_string("{ReportID}"));
	lr_save_string("0","progressPercentage");
	i=1;
	do {
		lr_output_message(" ***** Pause for Report Generation [%ix] %i%% Completed", i, atoi(lr_eval_string("{progressPercentage}")));
		lr_vuser_status_message("Running... %i%% Completed", atoi(lr_eval_string("{progressPercentage}")));
		lr_think_time(30);
		web_reg_save_param("status", "LB=status\":\"", "RB=\"", "ORD=1", LAST);
		web_reg_save_param("reason", "LB=reason\":\"", "RB=\"", "ORD=1", "Notfound=warning", LAST);
		web_reg_save_param("progressPercentage", "LB=progressPercentage\":\"", "RB=\"", "ORD=1", LAST);
		web_url("web_url",
			"URL=https://{PerfectoCloud}/services/executions/{ExecutionID}?operation=status&user={UserName}&password={Password}",
			"TargetFrame=",                              
			"Resource=1",
			"Referer=",
			"Mode=HTML",
			LAST);
		
		if( strlen(lr_eval_string("{reason}")) > 0)
			reportCloudErrorStatus(lr_eval_string("{reason}"));

		i++;
	}while ( strcmp("Completed", lr_eval_string("{status}")) != 0 && i <24 );
	

	if (24<i)
	{
          lr_error_message ("Perfecto Script failed to complete");
          lr_exit(LR_EXIT_VUSER, LR_FAIL);
	}

}
int analyzePerfectoReportAndReportToLR()
{
	// ******************************************* Retrieve Transaction Names from  Perfecto Script Report **************************************/

	lr_vuser_status_message("Uploading KPI Results");
	web_reg_save_param("transactions", "LB=ux timer ", "RB= is", "ORD=ALL", "Notfound=warning", LAST);
	web_url("web_url",
		"URL=https://{PerfectoCloud}/services/reports/{ReportID}?operation=download&user={UserName}&password={Password}&responseformat=xml",		        
		"TargetFrame=",
		"Resource=1",
		"Referer=",
		"Mode=HTML",
		LAST);

	//Find all Transaction Names of type UX from Script Report
	ParamCount = atoi(lr_eval_string("{transactions_count}"));
	lr_output_message("*** Total Parameters Found: %d", ParamCount);
	
	

// ******************************************* Analyze Perfecto Script Report **************************************/

	if(ParamCount > 0) 
		reportKPI(ParamCount);		
	else
		return reportScriptError("No Transaction Found, please check the execution status of your Automation Script");

}
