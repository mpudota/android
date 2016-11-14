package sunshine.android.weather.com.sunshine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    }