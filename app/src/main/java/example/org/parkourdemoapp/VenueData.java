package example.org.parkourdemoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Daniel on 7/9/2016.
 */
public class VenueData extends AppCompatActivity {
    private final String LOG_TAG = "VenueData";
    private final String VENUE_FILENAME = "user_venue_data.txt";

    private TextView venDataTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);

        venDataTextView = (TextView) findViewById(R.id.venDataTextView);
        String data = "";
        String temp;
        try {
            FileInputStream fis = openFileInput(VENUE_FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();

            while((temp = bufferedReader.readLine()) != null) {
                sb.append(temp);
            }
            if(sb!=null){
                data = sb.toString();
            } else {
                data = "No data yet";
            }
            try {
                fis.close();
            }catch(IOException e) {
                Log.e(LOG_TAG, "Unable to close FileInputStream");
            }

        } catch(FileNotFoundException e) {
            Log.e(LOG_TAG, "File was not found.");
        } catch(IOException e) {
            Log.e(LOG_TAG, "Unable to read line");
        }

        venDataTextView.setText(data);
    }
}

