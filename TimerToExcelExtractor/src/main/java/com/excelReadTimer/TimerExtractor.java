package com.excelReadTimer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.tree.DefaultElement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.perfectomobile.httpclient.Credentials;
import com.perfectomobile.httpclient.HttpClient;
import com.perfectomobile.httpclient.HttpClientException;
import com.perfectomobile.httpclient.InputStreamResponse;
import com.perfectomobile.httpclient.MediaType;
import com.perfectomobile.httpclient.execution.ExecutionsHttpClient;

public class TimerExtractor 
{
	static String scriptKey;
	static String user;
	static String sCloudUrl = "";
	static String passwordSalt = "Perfect";
	static String excelpath;
	//static Credentials credentials;
	
    public static void main( String[] args )
    {  	
    	System.out.println("started");
    	sCloudUrl = args[0];
    	user = args[1];
    	String password = StringEncrypt.decryptXOR(args[2], passwordSalt);
    	scriptKey = args[3];  
    	String excelPath = args[4];
    	Integer anchorTime = -1;
    	Integer offset = 500000;
    	String sTimeZone = args[5]; //"GMT+2";//"GMT-4"; //also possible GMT+2

    	System.out.println("number of arguments: " +args.length);
    	if(args.length > 6)
        {    		
    		if (args[6].equals("LastSingleExecution")){
    			System.out.println("Getting Last Run result");
    			///  To add single last Execution to Excel file
    	    	AddLastExecutionToExcel(user, password, scriptKey, sTimeZone, excelPath );
    		}
    		
    		else {
    	    	///  To add a number of executions
    			System.out.println("Getting all available Run results");
    	    	AddExecutionsFromTimeframe(user, password, scriptKey, anchorTime, offset, sTimeZone, excelPath);
    	 	    
    		}
        }
    	
    	
    	

	    System.out.print("FINISHED");
    }
    /**
    * Adds all Timers of all specified Executions to Excel
    * Maximum of ~150 executions retrieved  
    * Time zone for Excel output- specify eg. GMT+2:00 , CET, EST
    * !!!anchorTime currently not working and is ignored!!!
     * @param excelPath 
    */
    private static void AddExecutionsFromTimeframe(String user, String password, String scriptKey,Integer anchorTime, Integer offset,String sTimeZone, String excelPath) {
		// TODO Auto-generated method stub
       
    	List <String> lReportKeys =getReportIDs(user,password,scriptKey,anchorTime, offset);
    	System.out.println("Reports: "+lReportKeys.size());
    	downloadReport(user, password, lReportKeys.get(0), MediaType.XML, "PerfectoReport");
    	
    	Iterator<String> reportIterator = lReportKeys.iterator();
    	String sReportKey;
		while (reportIterator.hasNext()) {
			sReportKey = reportIterator.next();
			System.out.println("reportkey: "+sReportKey);
			Document document = downloadReportAsDom(user, password, sReportKey);
			LinkedHashMap<String, String> mTimerResults;	    
	    	mTimerResults = (LinkedHashMap<String, String>) getTimers(document,sTimeZone);
	    	mTimerResults.put("Report Name", sReportKey);
	    	
		    WriteResultsToExcel(excelPath, mTimerResults, sReportKey);
		}    	    	
    	
	}
    
	/** Retrieves last execution using REST, parses it and adds timers to excel. 
	 * If Excel does not exist it is created.
	 * If new Timers are added, they're added to Excel dynamically (using ExcelDriver) 
	 * @param excelPath 
	 */
    @SuppressWarnings("unused")
	private static void AddLastExecutionToExcel(String user, String password, String scriptKey, String sTimeZone, String excelPath) {
		// TODO Auto-generated method stub
       	String sReportKey =getLastReportID(user,password,scriptKey);
    	System.out.println("ReportKey is: " + sReportKey);    	
    	
    	String sFilepath = downloadReport(user, password, sReportKey, MediaType.XML,"PerfectoReport");
    	Document document = downloadReportAsDom(user, password, sReportKey);
    	//Get Alternative Dom4J Document 
	    LinkedHashMap<String, String> mTimerResults;	    
    	mTimerResults = (LinkedHashMap<String, String>) getTimers(document, sTimeZone);
    	mTimerResults.put("Report Name", sReportKey);
    	
	    WriteResultsToExcel(excelPath, mTimerResults, sReportKey);
	}

	/** Writes Map of timers from a single execution to Excel sheet
	 */
	private static void WriteResultsToExcel(String excelPath, Map<String, String> mTimerResults, String sReportKey) {
		// TODO Auto-generated method stub
    	System.out.println(excelPath);
		try {
			ExcelDriver exceldriver = new ExcelDriver(excelPath, "Timer-Results", true);	
			int[] a = exceldriver.findCellByValue(sReportKey);
						
			if (a != null){
				System.out.println("Report duplicate, not adding "+sReportKey +"..."+a[0]+"."+a[1]);
			}
			else {
				exceldriver.addColumnsFromMap((LinkedHashMap<String, String>) mTimerResults);
				exceldriver.addResultsToDetailedSheet((HashMap<String, String>) mTimerResults);
				//excelwriter.addReportKey(sReportKey);
			//excelwriter.VerifyLastEntry();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** Returns a Dom4J Document that can consequently be traversed using DOM actions */
	private static Document downloadReportAsDom(String user, String password, String sReportKey) {
		
    	com.perfectomobile.httpclient.execution.ExecutionsHttpClient executionsClient = new ExecutionsHttpClient(sCloudUrl, new Credentials(user, password));
    	
    	Document document = null;
    	try {
			document = executionsClient.downloadReportDocument(sReportKey);
		} catch (HttpClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}

	/** returns map of timer/value pairs extracted from DOM document
	 * for Single report (pass Dom4J document eg. obtained with DownloadReportAsDom
	 * Timezone eg. GMT+2:00 , CET, EST
	 */
	public static Map<String, String> getTimers(Document document, String sTimeZone)
    {
    	Map<String, String> timerResults = new LinkedHashMap<String, String>();
    	
    	// Getting ReportID
    	Node reportIDNode = document.selectSingleNode("execution/info/dataItems/dataItem[@label=\"report\"]/key");
    	String reportID = reportIDNode.getStringValue();
    	String privatepublic = reportID.replace(":", "/"+user+"/");
    	
    	//ToAdd: Link to report
    	//
    	String reportLink = "https://salesforce.perfectomobile.com/services/reports/"+
    	reportID+
    	"?operation=download&format=html&user="+
    	user +
    	"&password=";
    	timerResults.put("ReportLink",reportLink);
    	
    	//	+++++ Getting Video Link
    	Node videoNode = document.selectSingleNode("execution/input/handsets/handset/recordings/recording/dataItems/dataItem[@label=\"recordingDownload\"]/file");
    	String videoName="";
		try {
			videoName = videoNode.getStringValue();
			System.out.println("This is the video: "+videoName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	    	
    	//+++++ Getting Date/Time Info:
    	Node timeNode = document.selectSingleNode("execution/info/times/flowTimes/start/millis");
    	String Millis = timeNode.getStringValue();
    	Long epoch = Long.parseUnsignedLong(Millis);
    	//timerResults.put("DateTime", timeNode.getStringValue());
    	Date date = new Date(epoch);
    	DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    	format.setTimeZone(TimeZone.getTimeZone(sTimeZone));
    	String formatted = format.format(date);
        System.out.println(formatted);        
    	timerResults.put("StartTime", formatted);

    	//+++++ Getting Timer Info:
    	//Creating Node List with GetTimer-Actions

    	String xpath = "//name[@displayName=\"Get timer\"]/parent::info/parent::action";
    	List<?> list = document.selectNodes(xpath);  
    	
    	 for (Iterator<?> iter = list.iterator(); iter.hasNext(); ) {
    		 DefaultElement timerElement = (DefaultElement) iter.next();
    		
    		 System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
    		 
    		//+++++ Accessing timer value in s. Depends on order of dataitem!!!  
    		Node valueNode = timerElement.selectSingleNode(".//info/dataItems/dataItem[@label=\"actual\"]/value");
     		System.out.println("valueNode: " +valueNode.getStringValue());
     		//+++++ Accessing timer name. Depends on order of parameters!!!
     		Node nameNode = timerElement.selectSingleNode(".//parameters/parameter[1]/value");
     		System.out.println("nameNode: " +nameNode.getStringValue());
     		timerResults.put(nameNode.getStringValue(), valueNode.getStringValue());
         }
    	 timerResults.put("VideoLink", videoName);
    	 return timerResults;
    }

	/** Returns all List of ReportIDs satisfying parameters. 
	 * !!!Anchor does not currently work and is ignored!!!
	 */
public static List<String> getReportIDs (String user, String password, String scriptKey, Integer epochTimeAnker, Integer offsetSeconds ){
			
    	List <String> lReportKeys = new ArrayList<String>();
    	
	   	URL url;
    	InputStreamResponse response;
		try {
			url = new URL("https://"+sCloudUrl +"/services/executions/?operation=list" +
					"&user="+user+
					"&password="+password+ 
					//"&owner="+user+
					"&scriptKey="+scriptKey  +
					"&time.offset="+offsetSeconds.toString() +
					//"&time.anchor="+epochTimeAnker.toString() +
					"&time.type=completed");
			
			System.out.println(url.toString());
			response = HttpClient.sendRequest(url);
			InputStream instr = response.getResponseStream();
			String message = org.apache.commons.io.IOUtils.toString(instr);
			System.out.println(message);			 
			
			JSONObject json = new JSONObject(message);
			JSONArray jsarray = json.getJSONArray("executions");
			
			System.out.println("JSONArray Length: "+jsarray.length());
			for (int i = 0; i < jsarray.length(); i++) {
				JSONObject jsExecution = jsarray.getJSONObject(i);
				lReportKeys.add(jsExecution.getString("reportKey"));
				}
						 
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.err.println("Exception: Likely no Execution in timeframe");			
			e.printStackTrace();
			
		}
    	
    	return lReportKeys;
    	
    }
	
/** Returns only very last ReportID from the list of executions satisfying parameters*/
    public static String getLastReportID (String user, String password, String scriptKey){
		
    	//earlier executions (in minutes) will be ignored
		Integer offset=5000;
		String sReportKey="";
    	URL url;
    	InputStreamResponse response;
		try {
			url = new URL("https://"+sCloudUrl +"/services/executions/?operation=list" +
					"&user="+user+
					"&password="+password+ 
					//"&owner="+user+
					"&scriptKey="+scriptKey  +
					"&time.offset="+offset.toString() +
					"&time.type=completed");
						
			response = HttpClient.sendRequest(url);
			InputStream instr = response.getResponseStream();
			String message = org.apache.commons.io.IOUtils.toString(instr);
			//System.out.println(message);			 
			
			JSONObject json = new JSONObject(message);
			JSONArray jsarray = json.getJSONArray("executions");
			 //String result = json.getJSONObject("executions").getString("executionId");
			//Get Object of of last Execution
			 JSONObject jsLastExecution = jsarray.getJSONObject(jsarray.length()-1);
			sReportKey = jsLastExecution.getString("reportKey");
			 
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.err.println("Exception: Likely no Execution in timeframe");			
			e.printStackTrace();
			
		}
    	
    	return sReportKey;
    	
    }

    /** Physically downloads report to provided path */
    public static String downloadReport(String user, String password, String sReportKey, MediaType format, String reportPath){
    	
    	System.out.println("Downloading " + sReportKey);
    	
    	String file_end="";
    	switch (format){
    		case XML : file_end=".xml";break;
    		case HTML : file_end=".html";break;
    		case PDF : file_end=".pdf";break;
    		case CSV: file_end=".csv";break;    		
    	}
    	
    	reportPath = reportPath + file_end;
    	InputStream instr;
    	com.perfectomobile.httpclient.execution.ExecutionsHttpClient executionsClient = new ExecutionsHttpClient(sCloudUrl, new Credentials(user, password));
    	try {    		
    		instr = executionsClient.downloadReport(sReportKey, format);
		
    	//IOUtils.copy(instr, System.out);
    	FileOutputStream fsout = new FileOutputStream(new File(reportPath));
    	IOUtils.copy(instr,fsout);
    	fsout.close();
    	
    	} catch (HttpClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return reportPath;
    }
}
