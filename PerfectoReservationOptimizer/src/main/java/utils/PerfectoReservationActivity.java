package utils;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.xml.sax.SAXException;

public class PerfectoReservationActivity {
	private static String cloudName;
	private static String startTime;
	private static String endTime;
	private static String securityToken;
	private static int minutesReservedFor = 31;
	static Set<String> resourceId = new HashSet();

	public PerfectoReservationActivity(String cloudName, String securityToken) {
		PerfectoReservationActivity.cloudName = cloudName;
		PerfectoReservationActivity.securityToken = securityToken;
	}

	public void setTimings(int minutesReservedFor, String startTime, String endTime) {
		PerfectoReservationActivity.minutesReservedFor = minutesReservedFor;
		PerfectoReservationActivity.startTime = startTime;
		PerfectoReservationActivity.endTime = endTime;
	}

	public void setTimings(int minutesReservedFor) {
		PerfectoReservationActivity.minutesReservedFor = minutesReservedFor;
	}

	public void setTimings(String startTime, String endTime) {
		PerfectoReservationActivity.startTime = startTime;
		PerfectoReservationActivity.endTime = endTime;
	}

	public boolean deleteUnusedReservations()
			throws ClientProtocolException, IOException, ParserConfigurationException, SAXException, ParseException {
		boolean result = false;
		Map reservedForWhile = this.getReservationList();
		Map reservedInfoNotUsed = this.getNotUsedList(reservedForWhile);

		try {
			Map arg5 = this.deleteAllReservations(reservedInfoNotUsed);
			if (arg5.isEmpty() || arg5 == null) {
				result = true;
			}

			System.out.println("below reservations for below device Ids are deleted: ");
			System.out.println(arg5);
		} catch (Exception arg4) {
			System.out.println("no reservation is deleted");
		}

		return result;
	}

	private Map<String, String> deleteAllReservations(Map<String, String> reservedInfoNotUsed)
			throws ClientProtocolException, IOException {
		Iterator arg2 = reservedInfoNotUsed.entrySet().iterator();

		while (arg2.hasNext()) {
			Entry res = (Entry) arg2.next();
			String URI = "https://" + cloudName + "/services/reservations/" + ((String) res.getKey()).toString()
					+ "?operation=delete&securityToken=" + securityToken + "&admin=true";
			new URL(URI);
			DefaultHttpClient client = new DefaultHttpClient();
			HttpDelete delReq = new HttpDelete(URI);
			CloseableHttpResponse response = client.execute(delReq);
			if (response.getStatusLine().getStatusCode() != 200) {
				reservedInfoNotUsed.remove(res.getKey());
			}
		}

		return reservedInfoNotUsed;
	}

	private Map<String, String> getNotUsedList(Map<String, String> reservedForWhile)
			throws IOException, ParseException {
		HashMap notUsedForWhile = new HashMap();
		Iterator arg3 = reservedForWhile.entrySet().iterator();

		while (arg3.hasNext()) {
			Entry res = (Entry) arg3.next();
			System.out.println("reservation and device Id: " + res);
			System.out.println("current millis: " + System.currentTimeMillis());
			System.out.println("device unused millis: " + this
					.lastUsedMillisForCradle(this.getCradleIdForDeviceInString(((String) res.getValue()).toString())));
			if (Long.compare(
					System.currentTimeMillis() - this.lastUsedMillisForCradle(
							this.getCradleIdForDeviceInString(((String) res.getValue()).toString())),
					(long) (minutesReservedFor * 60 * 1000)) > 0) {
				notUsedForWhile.put((String) res.getKey(), (String) res.getValue());
			}
		}

		System.out.println("Active reservatation reserved before " + minutesReservedFor + " minutes but not used: "
				+ notUsedForWhile);
		return notUsedForWhile;
	}

	public long lastUsedMillisForCradle(String cradleId) throws ClientProtocolException, IOException {
		long millis = 0L;
		String URI = "https://" + cloudName + "/services/cradles/" + cradleId + "?operation=info&securityToken="
				+ securityToken;
		URL url = new URL(URI);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		if (conn.getResponseCode() == 200) {
			String reponseLine = "";

			Scanner scanner;
			for (scanner = new Scanner(url.openStream()); scanner
					.hasNextLine(); reponseLine = reponseLine + scanner.nextLine()) {
				;
			}

			cradleId = reponseLine.substring(reponseLine.indexOf("<changeTime>") + 12,
					reponseLine.indexOf("</changeTime>"));
			millis = Long
					.parseLong(cradleId.substring(cradleId.indexOf("<millis>") + 8, cradleId.indexOf("</millis>")));
			scanner.close();
		}

		return millis;
	}

	public String getCradleIdForDeviceInString(String DeviceId) throws IOException {
		String URI = "https://" + cloudName + "/services/handsets/" + DeviceId + "?operation=info&securityToken="
				+ securityToken;
		URL url = new URL(URI);
		String cradleId = "";
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		if (conn.getResponseCode() == 200) {
			String reponseLine = "";

			Scanner scanner;
			for (scanner = new Scanner(url.openStream()); scanner
					.hasNextLine(); reponseLine = reponseLine + scanner.nextLine()) {
				;
			}

			cradleId = reponseLine.substring(reponseLine.indexOf("<cradleId>") + 10,
					reponseLine.indexOf("</cradleId>"));
			scanner.close();
		}

		System.out.println("Device id: " + DeviceId + " Cradle id: " + cradleId);
		return cradleId;
	}

	public Map<String, String> getReservationList()
			throws ClientProtocolException, IOException, ParserConfigurationException, SAXException, ParseException {
		HashMap reservedForWhile = null;
		String URI = "https://" + cloudName + "/services/reservations?operation=list&securityToken=" + securityToken;
		if (!resourceId.isEmpty()) {
			String arg10 = "";

			String arg11;
			for (Iterator arg12 = resourceId.iterator(); arg12.hasNext(); arg10 = arg10 + arg11 + ",") {
				arg11 = (String) arg12.next();
			}

			if (arg10.substring(arg10.length() - 1).contains(",")) {
				arg10 = arg10.replace(arg10.substring(arg10.length() - 1), "");
			}

			URI = URI + "&resourceIds=" + arg10;
		}

		if (startTime != null) {
			URI = URI + "&startTime=" + startTime;
		}

		if (endTime != null) {
			URI = URI + "&endTime=";
		}

		URL arg101 = new URL(URI);
		HttpsURLConnection arg111 = (HttpsURLConnection) arg101.openConnection();
		arg111.setRequestMethod("GET");
		arg111.connect();
		if (arg111.getResponseCode() == 200) {
			String arg121 = "";

			Scanner scanner;
			for (scanner = new Scanner(arg101.openStream()); scanner
					.hasNextLine(); arg121 = arg121 + scanner.nextLine()) {
				;
			}

			MyJSONParcer parcer = new MyJSONParcer(arg121);
			JSONArray jArray = parcer.getChildArray(parcer.getJSONRootObj(), "reservations");
			reservedForWhile = new HashMap();

			for (int i = 0; i < jArray.size(); ++i) {
				JSONObject obj = (JSONObject) jArray.get(i);
				if (obj.get("status").toString().toUpperCase().contains("STARTED")
						&& System.currentTimeMillis() - Long.parseLong(
								(String) ((JSONObject) obj.get("startTime")).get("millis")) > (long) (minutesReservedFor
										* 60 * 1000)) {
					reservedForWhile.put(obj.get("id").toString(), obj.get("resourceId").toString());
				}
			}

			scanner.close();
		}

		System.out.println(
				"Active reservatation reserved before " + minutesReservedFor + " minutes: " + reservedForWhile);
		return reservedForWhile;
	}

	public List<String> getAvailableDevices(DesiredCapabilities capabilities) throws IOException {
		Object deviceList = new ArrayList();
		String URI = "https://" + cloudName + "/services/handsets/?operation=list&securityToken=" + securityToken;
		Map capMap = RESTCapabilities.getRESTCapabilities(capabilities);

		Entry url;
		for (Iterator url1 = capMap.entrySet().iterator(); url1
				.hasNext(); URI = URI + "&" + (String) url.getKey() + "=" + (String) url.getValue()) {
			url = (Entry) url1.next();
		}

		URL url11 = new URL(URI);
		HttpsURLConnection conn1 = (HttpsURLConnection) url11.openConnection();
		conn1.setRequestMethod("GET");
		conn1.connect();
		MyXMLParcer parcer = new MyXMLParcer();
		if (conn1.getResponseCode() == 200) {
			parcer.parceInToXML(url11.openStream());
			deviceList = parcer.runXpath("//handsets/handset");
		}

		return (List) deviceList;
	}

	public boolean reserveDeviceNow(String deviceId, int reservTimeInMin) {
		boolean result = false;
		long startTimeLong = System.currentTimeMillis() / 100000L + 1L;
		long endTimeLong = 0L;
		Date date = new Date();
		Date date1 = new Date();
		if (reservTimeInMin / 60 > 0) {
			date.setHours(date.getHours() + reservTimeInMin / 60);
		} else {
			date.setHours(date.getHours() + 1 + reservTimeInMin / 60);
		}

		date.setMinutes(0);
		date.setSeconds(0);
		endTimeLong = date.getTime();
		if (date.getTime() - date1.getTime() < 1800000L) {
			endTimeLong = date.getTime() + 1800000L;
		}

		String startTime = startTimeLong + "00000";
		String URI = "https://" + cloudName + "/services/reservations/?operation=create&securityToken=" + securityToken
				+ "&resourceIds=" + deviceId + "&startTime=" + startTime + "&endTime=" + endTimeLong;
		HttpsURLConnection conn = null;
		URL url = null;

		try {
			url = new URL(URI);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
		} catch (MalformedURLException arg15) {
			arg15.printStackTrace();
		} catch (ProtocolException arg16) {
			arg16.printStackTrace();
		} catch (IOException arg17) {
			arg17.printStackTrace();
		}

		try {
			if (conn.getResponseCode() == 200) {
				result = true;
			}
		} catch (IOException arg14) {
			arg14.printStackTrace();
		}

		return result;
	}
}
