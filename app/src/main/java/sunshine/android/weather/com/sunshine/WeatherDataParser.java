package sunshine.android.weather.com.sunshine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/**
 * Created by mpudota on 12/5/16.
 */
public class WeatherDataParser {

    public double getMaxTemp(String JsonString, int dayIndex) throws JSONException {

        JSONObject weather = new JSONObject(JsonString);
        JSONArray days = weather.getJSONArray("list");
        JSONObject dayInfo = days.getJSONObject(dayIndex);
        JSONObject temparatureInfo = dayInfo.getJSONObject("temp");
        return temparatureInfo.getDouble("max");

    }

    public double getMinTemp(String JsonString, int dayIndex) throws JSONException {

        JSONObject weather = new JSONObject(JsonString);
        JSONArray days = weather.getJSONArray("list");
        JSONObject dayInfo = days.getJSONObject(dayIndex);
        JSONObject temparatureInfo = dayInfo.getJSONObject("temp");
        return temparatureInfo.getDouble("min");

    }

    public String getMain(String JsonString, int dayIndex) throws JSONException {

        JSONObject weather = new JSONObject(JsonString);
        JSONArray days = weather.getJSONArray("list");
        JSONObject dayInfo = days.getJSONObject(dayIndex);
        JSONArray temparatureInfo = dayInfo.getJSONArray("weather");
        JSONObject temp = temparatureInfo.getJSONObject(0);
        return temp.getString("main");

    }
}
