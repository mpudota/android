package sunshine.android.weather.com.sunshine;

import android.provider.BaseColumns;

import java.sql.Time;

/**
 * Created by mpudota on 1/31/17.
 */

public class WeatherDBcontract {

    public long day(long startDay) {

        android.text.format.Time time = new android.text.format.Time();
        time.set(startDay);
        int julianday = android.text.format.Time.getJulianDay(startDay, time.gmtoff);
        return time.setJulianDay(julianday);

    }

    public static final class LocationEntry implements BaseColumns {

        public static final String TABLE_NAME = "location";
        public static final String COLUMN_LOCATION_SETTING = "location_setting";
        public static final String COLUMN_CITY_NAME = "city name";
        public static final String COLUMN_COORD_LAT = "coord_lat";
        public static final String COLUMN_COORD_LONG = "coord_long";
    }

    public static final class WeatherEntry implements BaseColumns {

        public static final String TABLE_NAME = "weather";
        public static final String COLUMN_LOC_KEY = "location_id";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_WEATHER_ID ="weather_id";
        public static final String COLUMN_SHORT_DESC = "short_desc";
        public static final String COLUMN_MIN_TEMP = "min";
        public static final String COLUMN_MAX_TEMP = "max";
        public static final String COLUMN_HUMIDITY = "humidity";
        public static final String COLUMN_PRESSURE = "pressure";
        public static final String COLUMN_WIND_SPEED = "wind";
        public static final String COLUMN_DEGRESS = "degrees";
    }
}
