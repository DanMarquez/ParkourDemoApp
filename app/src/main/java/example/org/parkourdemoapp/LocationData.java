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
public class LocationData extends AppCompatActivity {
    private final String LOG_TAG = "LocationData";
    private final String LOCATION_FILENAME = "user_location_data.txt";

    private TextView locDataTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        locDataTextView = (TextView) findViewById(R.id.locDataTextView);
        String data = "";
        String temp;
        try {
            FileInputStream fis = openFileInput(LOCATION_FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();

            while((temp = bufferedReader.readLine()) != null) {
                sb.append(temp + "\n");
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

        locDataTextView.setText(data);




    }



}
