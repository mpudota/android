package sunshine.android.weather.com.sunshine;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceHolderFragment()).commit();
        }
    }

    public static class PlaceHolderFragment extends Fragment {

        public PlaceHolderFragment () {

        }


        public View onCreateview(LayoutInflater inflater, ViewGroup group, Bundle savedInstanceState) {



        String [] forecastArray = {"Today-Sunny-88/53",
                "Tomorrow-cloud-77/65",
                "Wed-rainy-69/61",
                "Thu-Sunny-81/63",
                "Fri-Snow-85/55",
                "Sat-Rain-83/57",
                "Sun-Sunny-85/78"};



                    List<String> weatherArray = new ArrayList<String>(Arrays.asList(forecastArray));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast, R.id.list_item_forecast_textview, weatherArray);

            View rootView = inflater.inflate(R.layout.fragment_main, group, false);

         ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
            listView.setAdapter(adapter);
            return rootView;
        }
    }
}
