package sunshine.android.weather.com.sunshine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.preferred_location) {


            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(geolocation());
            if (intent.resolveActivity(getPackageManager()) != null ) {
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public Uri geolocation () {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String zipcode = sharedPreferences.getString( "location", getString(R.string.location_def_value));
        Uri geolocale = Uri.parse("geo:0,0?").buildUpon().appendQueryParameter("q", zipcode).build();
        return geolocale;
    }


}
