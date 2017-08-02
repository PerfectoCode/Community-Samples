Action()
{

// ******************************************* Execute Perfecto Script  **************************************/    
	lr_vuser_status_message("Initializing...");
	web_reg_save_param("ExecutionID", "LB=<executionId>", "RB=</","ORD=1", "Notfound=warning", LAST);
	web_reg_save_param("ReportID", "LB=<reportKey>", "RB=</","ORD=1", "Notfound=warning", LAST);
	web_url("web_url",
			"URL=https://{PerfectoCloud}/services/executions?operation=execute&scriptkey={ScriptName}.xml&user={UserName}&password={Password}&responseformat=xml&param.DUT={DeviceID}",    
            "TargetFrame=",
            "Resource=1",
            "Referer=",
            "Mode=HTML",
            LAST); 

	if( strlen(lr_eval_string("{ExecutionID}")) > 0)	
		reportCloudErrorStatus("Unable to Access Cloud Resources, Please check your Parameter Data");
 

	
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
		return scriptError("Script execution error");


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
		return scriptError("No Transaction Found, please check the execution status of your Automation Script");
	
    return 0;
}
