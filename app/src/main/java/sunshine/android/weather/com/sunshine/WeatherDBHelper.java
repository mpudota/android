package sunshine.android.weather.com.sunshine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import sunshine.android.weather.com.sunshine.WeatherDBcontract.LocationEntry;
import sunshine.android.weather.com.sunshine.WeatherDBcontract.WeatherEntry;
/**
 * Created by mpudota on 2/1/17.
 */

public class WeatherDBHelper extends SQLiteOpenHelper {

    public static final String databasename = "weather.db";
    public static final int Database_version = 2;
    public WeatherDBHelper(Context context) {
        super(context, databasename, null, Database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_WEATHER_TABLE = "CREATE TABLE" + WeatherEntry.TABLE_NAME + "(" +
                WeatherEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + WeatherEntry.COLUMN_LOC_KEY + "INTEGER NOT NULL,"
                + WeatherEntry.COLUMN_DATE + "INTEGER NOT NULL,"
                + WeatherEntry.COLUMN_SHORT_DESC + "TEXT NOT NULL,"
                + WeatherEntry.COLUMN_WEATHER_ID + "INTEGER NOT NULL,"
                + WeatherEntry.COLUMN_MIN_TEMP + "REAL NOT NULL,"
                + WeatherEntry.COLUMN_MAX_TEMP + "REAL NOT NULL,"
                + WeatherEntry.COLUMN_HUMIDITY + "REAL NOT NULL,"
                + WeatherEntry.COLUMN_WIND_SPEED + "REAL NOT NULL,"
                + WeatherEntry.COLUMN_PRESSURE + "REAL NOT NULL,"
                + WeatherEntry.COLUMN_DEGRESS + "REAL NOT NULL,"
                + " FOREIGN KEY (" + WeatherEntry.COLUMN_LOC_KEY + ") REFERENCES " +
                LocationEntry.TABLE_NAME + " (" + LocationEntry._ID + "), " +

                // To assure the application have just one weather entry per day
                // per location, it's created a UNIQUE constraint with REPLACE strategy
                " UNIQUE (" + WeatherEntry.COLUMN_DATE + ", " +
                WeatherEntry.COLUMN_LOC_KEY + ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_LOCATION_TABLE = "CREATE TABLE" + LocationEntry.TABLE_NAME + "("
                + LocationEntry._ID + "INTEGER PRIMARY KEY "
                + LocationEntry.COLUMN_LOCATION_SETTING + "TEXT UNIQUE NOT NULL"
                + LocationEntry.COLUMN_CITY_NAME + "TEXT NOT NULL"
                + LocationEntry.COLUMN_COORD_LAT + "REAL NOT NULL"
                + LocationEntry.COLUMN_COORD_LONG + "REAL NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_LOCATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP IF TABLE EXISTS " + WeatherEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP IF TABLE EXISTS " + LocationEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
