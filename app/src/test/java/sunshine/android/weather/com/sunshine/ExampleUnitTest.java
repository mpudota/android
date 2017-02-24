package sunshine.android.weather.com.sunshine;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest  {
    Context mContext ;

    public static final String LOG_TAG = ExampleUnitTest.class.getSimpleName();

    // Since we want each test to start with a clean slate
    void deleteTheDatabase() {

        mContext.deleteDatabase(WeatherDBHelper.databasename);
    }

    public void setUp (){
        deleteTheDatabase();
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void subtraction_is_correct () {
        assertEquals(2, 4-2);
    }

    @Test
    public void testCreateDb() throws Throwable {
        // build a HashSet of all of the table names we wish to look for
        // Note that there will be another table in the DB that stores the
        // Android metadata (db version information)
        final HashSet<String> tableNameHashSet = new HashSet<String>();
        tableNameHashSet.add(WeatherDBcontract.LocationEntry.TABLE_NAME);
        tableNameHashSet.add(WeatherDBcontract.WeatherEntry.TABLE_NAME);

//        mContext.deleteDatabase(WeatherDBHelper.databasename);
        SQLiteDatabase db = new WeatherDBHelper.(this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());

        // have we created the tables we want?
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        assertTrue("Error: This means that the database has not been created correctly",
                c.moveToFirst());

        // verify that the tables have been created
        do {
            tableNameHashSet.remove(c.getString(0));
        } while( c.moveToNext() );

        // if this fails, it means that your database doesn't contain both the location entry
        // and weather entry tables
        assertTrue("Error: Your database was created without both the location entry and weather entry tables",
                tableNameHashSet.isEmpty());

        // now, do our tables contain the correct columns?
        c = db.rawQuery("PRAGMA table_info(" + WeatherDBcontract.LocationEntry.TABLE_NAME + ")",
                null);

        assertTrue("Error: This means that we were unable to query the database for table information.",
                c.moveToFirst());

        // Build a HashSet of all of the column names we want to look for
        final HashSet<String> locationColumnHashSet = new HashSet<String>();
        locationColumnHashSet.add(WeatherDBcontract.LocationEntry._ID);
        locationColumnHashSet.add(WeatherDBcontract.LocationEntry.COLUMN_CITY_NAME);
        locationColumnHashSet.add(WeatherDBcontract.LocationEntry.COLUMN_COORD_LAT);
        locationColumnHashSet.add(WeatherDBcontract.LocationEntry.COLUMN_COORD_LONG);
        locationColumnHashSet.add(WeatherDBcontract.LocationEntry.COLUMN_LOCATION_SETTING);

        int columnNameIndex = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndex);
            locationColumnHashSet.remove(columnName);
        } while(c.moveToNext());

        // if this fails, it means that your database doesn't contain all of the required location
        // entry columns
        assertTrue("Error: The database doesn't contain all of the required location entry columns",
                locationColumnHashSet.isEmpty());
        db.close();
    }

    /*
        Students:  Here is where you will build code to test that we can insert and query the
        location database.  We've done a lot of work for you.  You'll want to look in TestUtilities
        where you can uncomment out the "createNorthPoleLocationValues" function.  You can
        also make use of the ValidateCurrentRecord function from within TestUtilities.
    */
        public void testLocationTable() {
            // First step: Get reference to writable database

            // Create ContentValues of what you want to insert
            // (you can use the createNorthPoleLocationValues if you wish)

            // Insert ContentValues into database and get a row ID back

            // Query the database and receive a Cursor back

            // Move the cursor to a valid database row

            // Validate data in resulting Cursor with the original ContentValues
            // (you can use the validateCurrentRecord function in TestUtilities to validate the
            // query if you like)

            // Finally, close the cursor and database

        }

    /*
        Students:  Here is where you will build code to test that we can insert and query the
        database.  We've done a lot of work for you.  You'll want to look in TestUtilities
        where you can use the "createWeatherValues" function.  You can
        also make use of the validateCurrentRecord function from within TestUtilities.
     */
    public void testWeatherTable() {
        // First insert the location, and then use the locationRowId to insert
        // the weather. Make sure to cover as many failure cases as you can.

        // Instead of rewriting all of the code we've already written in testLocationTable
        // we can move this code to insertLocation and then call insertLocation from both
        // tests. Why move it? We need the code to return the ID of the inserted location
        // and our testLocationTable can only return void because it's a test.

        // First step: Get reference to writable database

        // Create ContentValues of what you want to insert
        // (you can use the createWeatherValues TestUtilities function if you wish)

        // Insert ContentValues into database and get a row ID back

        // Query the database and receive a Cursor back

        // Move the cursor to a valid database row

        // Validate data in resulting Cursor with the original ContentValues
        // (you can use the validateCurrentRecord function in TestUtilities to validate the
        // query if you like)

        // Finally, close the cursor and database
    }


    /*
        Students: This is a helper method for the testWeatherTable quiz. You can move your
        code from testLocationTable to here so that you can call this code from both
        testWeatherTable and testLocationTable.
     */
    public long insertLocation() {
        return -1L;
    }


//    @Test
//    public void dbcreate(SQLiteDatabase sqLiteDatabase) {
//        final String SQL_CREATE_WEATHER_TABLE = "CREATE TABLE" + WeatherDBcontract.WeatherEntry.TABLE_NAME + "(" +
//                WeatherDBcontract.WeatherEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + WeatherDBcontract.WeatherEntry.COLUMN_LOC_KEY + "INTEGER NOT NULL,"
//                + WeatherDBcontract.WeatherEntry.COLUMN_DATE + "INTEGER NOT NULL,"
//                + WeatherDBcontract.WeatherEntry.COLUMN_SHORT_DESC + "TEXT NOT NULL,"
//                + WeatherDBcontract.WeatherEntry.COLUMN_WEATHER_ID + "INTEGER NOT NULL,"
//                + WeatherDBcontract.WeatherEntry.COLUMN_MIN_TEMP + "REAL NOT NULL,"
//                + WeatherDBcontract.WeatherEntry.COLUMN_MAX_TEMP + "REAL NOT NULL,"
//                + WeatherDBcontract.WeatherEntry.COLUMN_HUMIDITY + "REAL NOT NULL,"
//                + WeatherDBcontract.WeatherEntry.COLUMN_WIND_SPEED + "REAL NOT NULL,"
//                + WeatherDBcontract.WeatherEntry.COLUMN_PRESSURE + "REAL NOT NULL,"
//                + WeatherDBcontract.WeatherEntry.COLUMN_DEGRESS + "REAL NOT NULL,"
//                + " FOREIGN KEY (" + WeatherDBcontract.WeatherEntry.COLUMN_LOC_KEY + ") REFERENCES " +
//                WeatherDBcontract.LocationEntry.TABLE_NAME + " (" + WeatherDBcontract.LocationEntry._ID + "), " +
//
//                // To assure the application have just one weather entry per day
//                // per location, it's created a UNIQUE constraint with REPLACE strategy
//                " UNIQUE (" + WeatherDBcontract.WeatherEntry.COLUMN_DATE + ", " +
//                WeatherDBcontract.WeatherEntry.COLUMN_LOC_KEY + ") ON CONFLICT REPLACE);";
//
//        final String SQL_CREATE_LOCATION_TABLE = "CREATE TABLE" + WeatherDBcontract.LocationEntry.TABLE_NAME + "("
//                + WeatherDBcontract.LocationEntry._ID + "INTEGER PRIMARY KEY "
//                + WeatherDBcontract.LocationEntry.COLUMN_LOCATION_SETTING + "TEXT UNIQUE NOT NULL"
//                + WeatherDBcontract.LocationEntry.COLUMN_CITY_NAME + "TEXT NOT NULL"
//                + WeatherDBcontract.LocationEntry.COLUMN_COORD_LAT + "REAL NOT NULL"
//                + WeatherDBcontract.LocationEntry.COLUMN_COORD_LONG + "REAL NOT NULL);";
//
//        sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);
//        sqLiteDatabase.execSQL(SQL_CREATE_LOCATION_TABLE);
//    }


}