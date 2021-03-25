package com.testDemos;

import com.google.gson.*;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;
import com.sun.codemodel.JCodeModel;

public class ReportExport_CrashFiles{

    // See http://developers.perfectomobile.com/display/PD/DigitalZoom+Reporting+Public+API on how to obtain a Security Token
    public static final String PERFECTO_SECURITY_TOKEN = "<securityToken>"; // TODO put your security token here
    private static final String SECURITY_TOKEN = System.getProperty("security-token", PERFECTO_SECURITY_TOKEN);

    // The Perfecto Continuous Quality Lab you work with
    private static final String CQL_NAME = System.getProperty("CQL_NAME", "<cloudName>");//e.g : "demo", "partners"); // TODO put your Continuous Quality Lab name here
    private static final String REPORTING_SERVER_URL = "https://" + CQL_NAME + ".app.perfectomobile.com";
        
    public static void main(String[] args) throws Exception {
        // Retrieve a list of the test executions in your lab (as a json)
        JsonObject executions = retrieveTestExecutions();
        String TodaysDate = (new SimpleDateFormat("DDMMYYYY").format(new Date()));
        
        File fileObj = new File(System.getProperty("user.dir")+File.separator+TodaysDate);
        if (!fileObj.exists()) {
            if (fileObj.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
      
        //Write JSON file
        try (FileWriter Jsonfile = new FileWriter(System.getProperty("user.dir")+File.separator+TodaysDate+File.separator+"TestExecutionsReport.json")) {
 
        	Jsonfile.write(executions.toString());
        	Jsonfile.flush();
        	System.out.println("Data has been Sucessfully Writeen to JSON file"+ Jsonfile);
            //System.out.println(Jsonfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JSONObject output;
        
        JsonArray resources = executions.getAsJsonArray("resources");
        if (resources.size() == 0) {
            System.out.println("there are no test executions for that period of time");
        } else {
        	
            for (JsonElement resourcesElement : resources) {
	            JsonObject testExecution = resourcesElement.getAsJsonObject();
	
	            // Retrieves a list of commands of a single test (as a json)
	            retrieveTestCommands(testExecution);
	
	            // Download video
	            //downloadVideo(testExecution);
	
	            // Download attachments such as device logs, vitals or network files (relevant for Mobile tests only)
	            //downloadAttachments(testExecution);
	            
	            // Download attachments such as crash files (relevant for Mobile tests only)
	            downloadCrashFiles(testExecution);
	            System.out.println("\nDownload of Crash files operation : done\n");
	            }
        }
    }
    
    public void convert2JSON(URL inputJson, File outputPojoDirectory, String packageName, String className) throws IOException{
		JCodeModel codeModel = new JCodeModel();

		URL source = inputJson;

		GenerationConfig config = new DefaultGenerationConfig() {
		@Override
		public boolean isGenerateBuilders() { // set config option by overriding method
		return true;
		}
		public SourceType getSourceType(){
            return SourceType.JSON;
        }
		};
		SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
		mapper.generate(codeModel, className, packageName, source);

		codeModel.build(outputPojoDirectory);
	}

    private static JsonObject retrieveTestExecutions() throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(REPORTING_SERVER_URL + "/export/api/v1/test-executions");

        /*
         * 
         * Here as user have to collect failureReason from URL of digital zoom report are got selected failed & crashed reports after applying filters.
         * 
         * https://<yourCloud>.app.perfectomobile.com/export/api/v1/test-executions?startExecutionTime[0]=1616092200000
         * &endExecutionTime[0]=1616178599999&status[0]=FAILED&failureReason[0]=5c0e0b6b2aa3a8ed5fab87ac&_search=&_searchViewAll=false
         * 
         * 
         */
        
        
        Scanner inputObj = new Scanner(System.in);
        
        int days = 1;
        String status = "FAILED";
        String failureReason = "5c0e0afa2aa3a8ed5fa6f064"; // crash failure reason which is different on all cloud
        
        
        // Optional: Filter by range. In this example: retrieve test executions of the past month (result may contain tests of multiple driver executions)
        uriBuilder.addParameter("startExecutionTime[0]", Long.toString(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(days)));
        //uriBuilder.addParameter("startExecutionTime[1]","custom"); // as this not supported by API
        uriBuilder.addParameter("endExecutionTime[0]", Long.toString(System.currentTimeMillis()));
        uriBuilder.addParameter("status[0]", status);
        uriBuilder.addParameter("failureReason[0]", failureReason);
        uriBuilder.addParameter("_search","");
        uriBuilder.addParameter("_searchViewAll","false");
        //uriBuilder.addParameter("tag[0]", Long.toString(tag)); 
        //uriBuilder.addParameter("_page", Long.toString(page));
        //add other parameters if you want it
        
        // Optional: Filter by a specific driver execution ID that you can obtain at script execution
        // uriBuilder.addParameter("externalId[0]", "SOME_DRIVER_EXECUTION_ID");

        HttpGet getExecutions = new HttpGet(uriBuilder.build());
        addDefaultRequestHeaders(getExecutions);
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpResponse getExecutionsResponse = httpClient.execute(getExecutions);
        JsonObject executions;
        try (InputStreamReader inputStreamReader = new InputStreamReader(getExecutionsResponse.getEntity().getContent())) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String response = IOUtils.toString(inputStreamReader);
            try {
                executions = gson.fromJson(response, JsonObject.class);
            } catch (JsonSyntaxException e) {
                throw new RuntimeException("Unable to parse response: " + response);
            }
            System.out.println("\nList of test executions response:\n" + gson.toJson(executions));
        }
        return executions;
    }

    private static void retrieveTestCommands(JsonObject testExecution) throws URISyntaxException, IOException {
        String testId = testExecution.get("id").getAsString();
        HttpGet getCommands = new HttpGet(new URI(REPORTING_SERVER_URL + "/export/api/v1/test-executions/" + testId + "/commands"));
        addDefaultRequestHeaders(getCommands);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse getCommandsResponse = httpClient.execute(getCommands);
        try (InputStreamReader inputStreamReader = new InputStreamReader(getCommandsResponse.getEntity().getContent())) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject commands = gson.fromJson(IOUtils.toString(inputStreamReader), JsonObject.class);
            System.out.println("\nList of commands response:\n" + gson.toJson(commands));
        }
    }

    private static void downloadVideo(JsonObject testExecution) throws IOException, URISyntaxException {
        JsonArray videos = testExecution.getAsJsonArray("videos");
        if (videos.size() > 0) {
            JsonObject video = videos.get(0).getAsJsonObject();
            String downloadVideoUrl = video.get("downloadUrl").getAsString();
            String format = "." + video.get("format").getAsString();
            String testId = testExecution.get("id").getAsString();
            downloadFile(testId, URI.create(downloadVideoUrl), format, "video");
        } else {
            System.out.println("\nNo videos found for test execution");
        }
    }

    private static void downloadAttachments(JsonObject testExecution) throws IOException, URISyntaxException {
        // Example for downloading device logs
        JsonArray artifacts = testExecution.getAsJsonArray("artifacts");
        for (JsonElement artifactElement : artifacts) {
            JsonObject artifact = artifactElement.getAsJsonObject();
            String artifactType = artifact.get("type").getAsString();
            if (artifactType.equals("DEVICE_LOGS")) {
                String testId = testExecution.get("id").getAsString();
                String path = artifact.get("path").getAsString();
                URIBuilder uriBuilder = new URIBuilder(path);
                downloadFile(testId, uriBuilder.build(), ".zip", "device logs");
            }
        }
    }
    
    private static void downloadCrashFiles(JsonObject testExecution) throws IOException, URISyntaxException {
        // Example for downloading device logs
        JsonArray artifacts = testExecution.getAsJsonArray("artifacts");
        for (JsonElement artifactElement : artifacts) {
            JsonObject artifact = artifactElement.getAsJsonObject();
            String artifactType = artifact.get("type").getAsString();
            if (artifactType.equals("APP_CRASH_REPORT")) {
                String testId = testExecution.get("externalId").getAsString();
                String ownerId = testExecution.get("owner").getAsString();
                String path = artifact.get("path").getAsString();
                String fileName = artifact.get("fileName").getAsString();
                URIBuilder uriBuilder = new URIBuilder(path);
                downloadFile(testId, uriBuilder.build(), ".zip", "Crash file logs");
            }
        }
    }


    private static void downloadFile(String fileName, URI uri, String suffix, String description) throws IOException {
        downloadFileToFS(new HttpGet(uri), fileName, suffix, description);
    }

    private static void downloadFileToFS(HttpGet httpGet, String fileName, String suffix, String description) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(httpGet);
        FileOutputStream fileOutputStream = null;
        try {
        	fileName = System.getProperty("user.dir")+File.separator+(new SimpleDateFormat("DDMMYYYY").format(new Date()))+File.separator+fileName+suffix;
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                Path file = (new File(fileName)).toPath();
                fileOutputStream = new FileOutputStream(file.toFile());
                IOUtils.copy(response.getEntity().getContent(), fileOutputStream);
                System.out.println("\nSaved " + description + " to: " + file.toFile().getAbsolutePath());
            } else {
                String errorMsg = IOUtils.toString(response.getEntity().getContent(), Charset.defaultCharset());
                System.err.println("Error downloading file. Status: " + response.getStatusLine() + ".\nInfo: " + errorMsg);
            }
        } finally {
            EntityUtils.consumeQuietly(response.getEntity());
            IOUtils.closeQuietly(fileOutputStream);
        }
    }

    private static void addDefaultRequestHeaders(HttpRequestBase request) {
        if (SECURITY_TOKEN == null || SECURITY_TOKEN.equals("MY_CONTINUOUS_QUALITY_LAB_SECURITY_TOKEN")) {
            throw new RuntimeException("Invalid security token '" + SECURITY_TOKEN + "'. Please set a security token");
        }
        request.addHeader("PERFECTO_AUTHORIZATION", SECURITY_TOKEN);
    }
    
}


