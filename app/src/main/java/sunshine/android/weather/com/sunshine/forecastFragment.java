package sunshine.android.weather.com.sunshine;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mpudota on 11/13/16.
 */

public class forecastFragment extends Fragment {

    public forecastFragment(){
        }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {

//        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh)
        {
         FetchWeather fetchWeather = new FetchWeather();
           fetchWeather.execute("94043");
            return true;
        }
      return super.onOptionsItemSelected(item);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String [] forecastArray = {"Today-Sunny-88/53",
                "Tomorrow-cloud-77/65",
                "Wed-rainy-69/61",
                "Thu-Sunny-81/63",
                "Fri-Snow-85/55",
                "Sat-Rain-83/57",
                "Sun-Sunny-85/78"};

                    List<String> weatherArray = new ArrayList<String>(Arrays.asList(forecastArray));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast, R.id.list_item_forecast_textview, weatherArray);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

         ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
            listView.setAdapter(adapter);
            return rootView;
        }

    public class FetchWeather extends AsyncTask<String,Void,Void> {

        public static final String Open_Weather_Map_Api = "48c58a9aa540fc470bf186871e168d68";
        @Override
        protected Void doInBackground(String... strings) {

            if (strings.length == 0) {
                return null;
            }

            final String format = "json";
            final String units = "metric";
            int numdays = 7;

            final String QUERY_PARAM = "q";
            final String FORMAT_PARAM = "mode";
            final String UNIT_PARAM = "untits";
            final String COUNT_PARAM = "cnt";
            final String APPID_PARAM = "APPID";

            HttpURLConnection httpURLConnection = null ;
            BufferedReader bufferedReader = null;
            String forecastJson = null;


            try {
                String baseUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?";
//                String apikey = "&APPID="+Open_Weather_Map_Api;
//

                Uri builduri = Uri.parse(baseUrl).buildUpon()
                        .appendQueryParameter(QUERY_PARAM, strings[0])
                        .appendQueryParameter(FORMAT_PARAM, format)
                        .appendQueryParameter(UNIT_PARAM, units)
                        .appendQueryParameter(COUNT_PARAM, Integer.toString(numdays))
                        .appendQueryParameter(APPID_PARAM, Open_Weather_Map_Api).build();

                URL url = new URL(builduri.toString());

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();




                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuffer stringBuffer = new StringBuffer();

                if (inputStream == null) {
                    return null;
                }

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line+"/n");

                }

                if (stringBuffer.length() == 0) {
                    return null;
                }
                forecastJson = stringBuffer.toString();
                Log.d("Json data", forecastJson);
            } catch (IOException e) {
                Log.e("Placeholder fragment", "Error", e);
//                e.printStackTrace();
                return null;
            }
            finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        Log.e("Placeholder fragment", "closing stream", e);
                    }
                }
            }
            return null;
        }
    }

    }