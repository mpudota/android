package sunshine.android.weather.com.sunshine;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static class PlaceHolderFragment extends Fragment {

        public PlaceHolderFragment () {

        }

        public View onCreateview(LayoutInflater inflater, ViewGroup group, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, group, false);
            return rootView;
        }

    }
}
