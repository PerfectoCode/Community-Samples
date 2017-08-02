int ParamCount = 0; i = 0;
	
int reportScriptError(char* failureDescription)
{
	lr_error_message("Script Error: %s", failureDescription);
	lr_exit(LR_EXIT_ACTION_AND_CONTINUE, LR_FAIL);
	return 0;	
}

int reportKPI(int ParamCount)
{
	char sDeviceName[24], sCounterName[24], sNewCounterName[24], sLB[24], sTempParam[24];
	int timer=0;
	long double txTime;

	// retieve Transaction Timer Values from Script Report based on Transaction Name
	for(i = 1; i <= ParamCount; i++){
		sprintf(sTempParam, "{transactions_%d}", i);
		strcpy(sDeviceName, lr_eval_string("{DeviceGroupName}"));
		strcpy(sCounterName, lr_eval_string(sTempParam));
		lr_output_message("%s", sCounterName);
		
		sprintf(sLB, "LB=%s is ",sCounterName);
		web_reg_save_param(sCounterName, sLB, "RB= milliseconds", "ORD=1", "Notfound=warning", LAST);

		web_url("web_url",
			"URL=https://{PerfectoCloud}/services/reports/{ReportID}?operation=download&user={UserName}&password={Password}&responseformat=xml",
			"TargetFrame=",
			"Resource=1",
			"Referer=",
			"Mode=HTML",
			LAST);

		//Transform Transaction Timer Name based on DeviceName field from Script Parameters
		//this will report KPI's in Groups when the DeviceName is the same 	
		sprintf(sTempParam, "{%s}", sCounterName);
		sprintf(sNewCounterName, "%s_%s", sDeviceName, sCounterName);
		timer = atoi(lr_eval_string(sTempParam));

		if (timer>0)
		{
			txTime = (long double)timer / 1000;
			lr_output_message("Transaction %s Set to: %f", sNewCounterName, txTime);			
			lr_set_transaction(sNewCounterName, txTime, LR_PASS);
		}else
		{
			reportSnapshotOnError(sNewCounterName);
			return -1;
		}
	}
	return 0;
}

int reportSnapshotOnError(char* transactionName)
{
	char str_[100]="Text= ";
	strcat(str_, transactionName);
	strcat(str_, " Failed");
	lr_start_transaction(transactionName);
	web_reg_find(str_, LAST);
	web_url("web_url",
			"URL=https://{PerfectoCloud}/services/reports/{ReportID}&operation=download&user={UserName}&password={Password}&format=html",
			"TargetFrame=",
			"Resource=0",
			"Referer=",
			"Mode=HTML",
			LAST);

	lr_error_message(" Script Error: %s", lr_eval_string("{reason}"));
	lr_fail_trans_with_error("Script failed %s", lr_eval_string("{reason}"));
	lr_end_transaction(transactionName, LR_FAIL);
	
	return 0;
}

int reportCloudErrorStatus(errCode) {

	char failureDescription[255];

	if (strlen(errCode) > 0) {
		if(strcmp(errCode,"ResourcesUnavailable")==0)
			reportScriptError("Resource Unavailable. Check the device is available for use.");
		if(strcmp(errCode,"Failed")==0)
			reportScriptError("Automation Script Failure. Check the Cloud Execution Status for your script.");
	}

	return 1;
}