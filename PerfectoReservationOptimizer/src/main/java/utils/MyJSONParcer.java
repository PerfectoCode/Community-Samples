package utils;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MyJSONParcer {
	private JSONParser parser = new JSONParser();
	private JSONObject jObj;

	public MyJSONParcer(String reponseLine) throws ParseException {
		try {
			this.jObj = (JSONObject) this.parser.parse(reponseLine);
		} catch (org.json.simple.parser.ParseException arg2) {
			arg2.printStackTrace();
		}

	}

	public JSONObject getJSONRootObj() {
		return this.jObj;
	}

	public JSONArray getChildArray(JSONObject obj, String node) {
		return (JSONArray) this.jObj.get(node);
	}

	public Map<String, String> getMapOfChildAttributeFromArray(JSONArray jArray, String key, String value) {
		HashMap map = new HashMap();

		for (int i = 0; i < jArray.size(); ++i) {
			JSONObject obj = (JSONObject) jArray.get(i);
			map.put(obj.get(key).toString(), obj.get(value).toString());
		}

		return map;
	}
}