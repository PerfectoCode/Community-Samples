
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

/**
 * This class is the utils class for exporting Reportium data
 */
public class ReportiumExportUtils {

    // The Perfecto Continuous Quality Lab you work with
    private static final String CQL_NAME = "demo"; // TODO put your Continuous Quality Lab name here

    // See http://developers.perfectomobile.com/display/PD/DigitalZoom+Reporting+Public+API on how to obtain a Security Token
    private static final String PERFECTO_SECURITY_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJzbFV4OFFBdjdVellIajd4YWstR0tTbE43UjFNSllDbC1TRVJiTlU1RFlFIn0.eyJqdGkiOiJlMWZjNzg2OC1hNTIwLTRiOGItYWQ2MS01Y2RlNzZkZTg0NGMiLCJleHAiOjAsIm5iZiI6MCwiaWF0IjoxNTQxNjc2MTU1LCJpc3MiOiJodHRwczovL2F1dGgucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL2RlbW8tcGVyZmVjdG9tb2JpbGUtY29tIiwiYXVkIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJzdWIiOiI5ZjMyOGNjZi1jODcxLTQ3MjYtOTJhMi03NmU0NGVkZTIyZGUiLCJ0eXAiOiJPZmZsaW5lIiwiYXpwIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJub25jZSI6ImIxNWQ4NmYxLTM2ZWQtNGYzOS04MTNjLTJkZjQyMTE2OTVjOCIsImF1dGhfdGltZSI6MCwic2Vzc2lvbl9zdGF0ZSI6ImRjYmUwMDNlLWJkYmEtNGU4YS1hZGY5LTNkZmJmMDJkZWQ1ZCIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fX0.CFPUqhYhUhAKG_g3zIygB6K7AGJkUkvXtYRA1FbACXylwnKc5CNCaQBjesYgakTZHT94G93zKQlp14FN_VNfTeymTzwHz1IZ0Y_olJ9ez_WeE1jKFyBSMDVOVwK498qW0le1h9ot01YVl9zTE1L5jJHzHD6juFhwEgYvskUq9WdG3PDiHIjefsTBTYRgUTQWaZUtBDBTA-3-0JpLTEZ7EDpG-YH7wiA9s0n60SvlRfPeTrw1tnVWBsJKw1JNmZ4__I9pAFXy8B2jmx-2Crqv5l4knaS62RPQhQWWqFi41-4TJoKitMSZt3jkYMNZ0WskfeHnqc7FYD47WOMLuBL-Hg"; // TODO put your security token here


    private static final int TIMEOUT_MILLIS = 60000;
    public static final int PDF_DOWNLOAD_ATTEMPTS = 12;
    private static final String REPORTING_SERVER_URL = "https://" + CQL_NAME + ".app.perfectomobile.com";
    private static final String SECURITY_TOKEN = System.getProperty("security-token", PERFECTO_SECURITY_TOKEN);
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static HttpClient httpClient = HttpClientBuilder.create()
            .setRetryHandler(new DefaultHttpRequestRetryHandler(3, true))
            .setDefaultRequestConfig(RequestConfig.custom()
                    .setSocketTimeout(TIMEOUT_MILLIS)
                    .setConnectTimeout(TIMEOUT_MILLIS)
                    .setConnectionRequestTimeout(TIMEOUT_MILLIS)
                    .build())
            .build();

    /**
     * Returns a JSON instance containing commands of a single test
     *
     * @param testId the ID of the test
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static JsonObject retrieveTestCommands(String testId) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(REPORTING_SERVER_URL + "/export/api/v1/test-executions/" + testId + "/commands");

        HttpGet getCommands = new HttpGet(uriBuilder.build());
        JsonObject commandsJson = getJson(getCommands);
        System.out.println("\nList of commands response:\n" + gson.toJson(commandsJson));

        return commandsJson;
    }

    /**
     * Returns a JSON instance containing information regarding the execution: tests, artifacts.
     * For more info
     *
     * @param executionId
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static JsonObject retrieveTestExecutions(String executionId) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(REPORTING_SERVER_URL + "/export/api/v1/test-executions");
        uriBuilder.addParameter("externalId[0]", executionId);

        HttpGet getTestExecutions = new HttpGet(uriBuilder.build());
        JsonObject testExecutionsJson = getJson(getTestExecutions);
        System.out.println("\nList of test executions response:\n" + gson.toJson(testExecutionsJson));

        return testExecutionsJson;
    }

    /**
     * Downloads the driver execution summary PDF report
     *
     * @param summaryPdfPath    local path that the downloaded report will be saved to
     * @param driverExecutionId the driver execution ID of the report
     * @throws URISyntaxException
     * @throws IOException
     */
    public static void downloadExecutionSummaryReport(Path summaryPdfPath, String driverExecutionId) throws URISyntaxException, IOException {
        System.out.println("Downloading PDF for driver execution ID: " + driverExecutionId);
        URIBuilder uriBuilder = new URIBuilder(REPORTING_SERVER_URL + "/export/api/v1/test-executions/pdf");
        uriBuilder.addParameter("externalId[0]", driverExecutionId);

        downloadPdfFileToFS(summaryPdfPath, uriBuilder.build());
    }

    /**
     * Downloads a single test execution PDF report
     *
     * @param testPdfPath local path that the downloaded report will be saved to
     * @param testId      the test ID
     * @throws URISyntaxException
     * @throws IOException
     */
    public static void downloadTestReport(Path testPdfPath, String testId) throws URISyntaxException, IOException {
        System.out.println("Starting PDF generation for test ID: " + testId);
        URIBuilder taskUriBuilder = new URIBuilder(REPORTING_SERVER_URL + "/export/api/v2/test-executions/pdf/task");
        taskUriBuilder.addParameter("testExecutionId", testId);
        HttpPost httpPost = new HttpPost(taskUriBuilder.build());
        addDefaultRequestHeaders(httpPost);

        CreatePdfTask task = null;
        for (int attempt = 1; attempt <= PDF_DOWNLOAD_ATTEMPTS; attempt++) {

            HttpResponse response = httpClient.execute(httpPost);
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                if (HttpStatus.SC_OK == statusCode) {
                    task = gson.fromJson(EntityUtils.toString(response.getEntity()), CreatePdfTask.class);
                    break;
                } else if (HttpStatus.SC_NO_CONTENT == statusCode) {

                    // if the execution is being processed, the server will respond with empty response and status code 204
                    System.out.println("The server responded with 204 (no content). " +
                            "The execution is still being processed. Attempting again in 5 sec (" + attempt + "/" + PDF_DOWNLOAD_ATTEMPTS + ")");
                    Thread.sleep(5000);
                } else {
                    String errorMsg = IOUtils.toString(response.getEntity().getContent(), Charset.defaultCharset());
                    System.err.println("Error downloading file. Status: " + response.getStatusLine() + ".\nInfo: " + errorMsg);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }
        if (task == null) {
            throw new RuntimeException("Unable to create a CreatePdfTask");
        }

        downloadTestReport(testPdfPath, task, testId);
    }

    public static void writeJsonToFile(Path path, JsonObject jsonObject) throws IOException {
        Files.write(path, gson.toJson(jsonObject).getBytes());
    }

    private static JsonObject getJson(HttpGet httpGet) throws IOException {
        JsonObject result;
        addDefaultRequestHeaders(httpGet);
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpResponse getExecutionsResponse = httpClient.execute(httpGet);

        try (InputStreamReader inputStreamReader = new InputStreamReader(getExecutionsResponse.getEntity().getContent())) {
            String response = IOUtils.toString(inputStreamReader);
            try {
                result = gson.fromJson(response, JsonObject.class);
            } catch (JsonSyntaxException e) {
                throw new RuntimeException("Unable to parse response: " + response);
            }
        }
        return result;
    }

    private static void downloadTestReport(Path testPdfPath, CreatePdfTask task, String testId) throws URISyntaxException, IOException {
        System.out.println("Downloading PDF for test ID: " + testId);
        long startTime = System.currentTimeMillis();
        int maxWaitMin = 10;
        long maxGenerationTime = TimeUnit.MINUTES.toMillis(maxWaitMin);
        String taskId = task.getTaskId();

        CreatePdfTask updatedTask;
        do {
            updatedTask = getUpdatedTask(taskId);
            try {
                if (updatedTask.getStatus() != TaskStatus.COMPLETE) {
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        while (updatedTask.getStatus() != TaskStatus.COMPLETE && startTime + maxGenerationTime > System.currentTimeMillis());

        if (updatedTask.getStatus() == TaskStatus.COMPLETE) {
            downloadPdfFileToFS(testPdfPath, new URI(updatedTask.getUrl()));
        } else {
            throw new RuntimeException("The task is still in " + updatedTask.getStatus() + " status after waiting " + maxWaitMin + " min");
        }
    }

    private static CreatePdfTask getUpdatedTask(String taskId) throws URISyntaxException, IOException {
        CreatePdfTask task;
        URIBuilder taskUriBuilder = new URIBuilder(REPORTING_SERVER_URL + "/export/api/v2/test-executions/pdf/task/" + taskId);
        HttpGet httpGet = new HttpGet(taskUriBuilder.build());
        addDefaultRequestHeaders(httpGet);
        HttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        if (HttpStatus.SC_OK == statusCode) {
            task = gson.fromJson(EntityUtils.toString(response.getEntity()), CreatePdfTask.class);
        } else {
            throw new RuntimeException("Error while getting AsyncTask: " + response.getStatusLine().toString());
        }
        return task;
    }

    public static void downloadFileToFS(Path path, URI uri) throws IOException {
        HttpGet httpGet = new HttpGet(uri);
        HttpResponse response = httpClient.execute(httpGet);
        try (FileOutputStream fileOutputStream = new FileOutputStream(path.toFile())) {
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                IOUtils.copy(response.getEntity().getContent(), fileOutputStream);
            } else {
                String errorMsg = IOUtils.toString(response.getEntity().getContent(), Charset.defaultCharset());
                System.err.println("Error downloading file. Status: " + response.getStatusLine() + ".\nInfo: " + errorMsg);
            }
        } finally {
            EntityUtils.consumeQuietly(response.getEntity());
        }
    }

    private static void downloadPdfFileToFS(Path pdfPath, URI uri) throws IOException {
        boolean downloadComplete = false;
        HttpGet httpGet = new HttpGet(uri);
        ReportiumExportUtils.addDefaultRequestHeaders(httpGet);
        for (int attempt = 1; attempt <= PDF_DOWNLOAD_ATTEMPTS && !downloadComplete; attempt++) {

            HttpResponse response = httpClient.execute(httpGet);
            FileOutputStream fileOutputStream = null;

            try {
                int statusCode = response.getStatusLine().getStatusCode();
                if (HttpStatus.SC_OK == statusCode) {
                    fileOutputStream = new FileOutputStream(pdfPath.toFile());
                    IOUtils.copy(response.getEntity().getContent(), fileOutputStream);
                    System.out.println("Saved downloaded file to: " + pdfPath.toString());
                    downloadComplete = true;
                } else if (HttpStatus.SC_NO_CONTENT == statusCode) {

                    // if the execution is being processed, the server will respond with empty response and status code 204
                    System.out.println("The server responded with 204 (no content). " +
                            "The execution is still being processed. Attempting again in 5 sec (" + attempt + "/" + PDF_DOWNLOAD_ATTEMPTS + ")");
                    Thread.sleep(5000);
                } else {
                    String errorMsg = IOUtils.toString(response.getEntity().getContent(), Charset.defaultCharset());
                    System.err.println("Error downloading file. Status: " + response.getStatusLine() + ".\nInfo: " + errorMsg);
                    downloadComplete = true;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                EntityUtils.consumeQuietly(response.getEntity());
                IOUtils.closeQuietly(fileOutputStream);
            }
        }
        if (!downloadComplete) {
            System.err.println("The execution is still being processed. No more download attempts");
        }
    }

    private static void addDefaultRequestHeaders(HttpRequestBase request) {
        if (SECURITY_TOKEN == null || SECURITY_TOKEN.equals("MY_CONTINUOUS_QUALITY_LAB_SECURITY_TOKEN")) {
            throw new RuntimeException("Invalid security token '" + SECURITY_TOKEN + "'. Please set a security token");
        }
        request.addHeader("PERFECTO_AUTHORIZATION", SECURITY_TOKEN);
    }

    private enum TaskStatus {
        IN_PROGRESS, COMPLETE
    }

    private static class CreatePdfTask {
        private String taskId;
        private TaskStatus status;
        private String url;

        public CreatePdfTask() {
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public TaskStatus getStatus() {
            return status;
        }

        public void setStatus(TaskStatus status) {
            this.status = status;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}