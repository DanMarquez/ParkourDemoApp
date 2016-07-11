package example.org.parkourdemoapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parkourmethod.parkour.Listeners.OnLocationChangeListener;
import com.parkourmethod.parkour.Listeners.OnVenueChangeListener;
import com.parkourmethod.parkour.Parkour;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Parkour
        implements View.OnClickListener
{
    private final int REQUEST_PERMISSION_FINE_LOCATION=1;
    //private TextView locationData;
    private Button startDataCollection;
    private Button stopDataCollection;
    private Button viewLocationData;
    private Button viewVenueData;
    private final String LOCATION_FILENAME = "user_location_data.txt";
    private final String VENUE_FILENAME = "user_venue_data.txt";
    private boolean collectionStatus = false;
    FileOutputStream fos1;
    FileOutputStream fos2;



    private final String LOG_TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        startDataCollection = (Button) findViewById(R.id.startDataCollection);
        stopDataCollection = (Button) findViewById(R.id.stopDataCollection);
        viewLocationData = (Button) findViewById(R.id.viewLocationData);
        viewVenueData = (Button) findViewById(R.id.viewVenueData);
        startDataCollection.setOnClickListener(this);
        stopDataCollection.setOnClickListener(this);
        viewLocationData.setOnClickListener(this);
        viewVenueData.setOnClickListener(this);
        //locationData = (TextView) findViewById(R.id.locationData);



        // Request Permission, needs update
        int permissionCheck =
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if(permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_PERMISSION_FINE_LOCATION);
        }



        pkListener.setOnLocationChangeListener(new OnLocationChangeListener() {
            @Override
            public void onLocationChanged(Location newLoc, String motionType, String positionType) {
                String data = "Arrived here: " + newLoc + " motion: " + motionType + " position: " +positionType + "\n";
                if(collectionStatus){
                    try{
                        fos1.write(data.getBytes());
                    } catch (IOException e) {
                        Log.e(LOG_TAG, "Unable to write to location file");
                    }

                }
                Log.d(LOG_TAG, "Arrived here: " + newLoc + " motion: " + motionType + " position: " +positionType);
                //locationData.setText("Arrived here: " + newLoc + " motion: " + motionType + " position: " +positionType);
            }
        });

        pkVenListener.setOnVenueChangeListener(new OnVenueChangeListener() {
            @Override
            public void onVenueChanged(String ven, String add, String catOne, String catTwo, double dist, Location userLoc) {
                String data = "Venue: " + ven +
                        " Address: " + add +
                        " Category 1: " + catOne +
                        " Category 2: " + catTwo +
                        " Distance: " + dist +
                        " Location:" + userLoc +
                        "\n";
                if(collectionStatus){
                    try{
                        fos2.write(data.getBytes());
                    } catch(IOException e) {
                        Log.e(LOG_TAG, "Unable to write to venue file");
                    }
                }
                Log.d(LOG_TAG, "Venue: " + ven);
            }
        });

//        parkourStart();

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()) {
            case R.id.startDataCollection:
                collectionStatus=true;
                try {
                    fos1 = openFileOutput(LOCATION_FILENAME, Context.MODE_PRIVATE);
                }catch(IOException e) {
                    Log.e(LOG_TAG, LOCATION_FILENAME + " was not found");
                }
                try {
                    fos2 = openFileOutput(VENUE_FILENAME, Context.MODE_PRIVATE);
                } catch(IOException e){
                    Log.e(LOG_TAG, VENUE_FILENAME + " was not found.");
                }
                parkourSetInterval(5);
                parkourStart();
                break;
            case R.id.stopDataCollection:
                collectionStatus=false;
                try {
                    fos1.write("Here is your data collected: \n".getBytes());
                    fos2.write("Here is your data collected: \n".getBytes());
                    fos1.close();
                    fos2.close();
                }catch(IOException e){
                    Log.e(LOG_TAG, "Unable to close data collection.");
                }
                break;
            case R.id.viewLocationData:
                if(collectionStatus) {
                    collectionStatus = false;
                    try {
                        fos1.write("Here is your data collected: \n".getBytes());
                        fos2.write("Here is your data collected: \n".getBytes());
                        fos1.close();
                        fos2.close();
                    } catch (IOException e) {
                        Log.e(LOG_TAG, "Unable to close data collection.");
                    }
                }
                intent = new Intent(MainActivity.this, LocationData.class);

                break;
            case R.id.viewVenueData:
                if(collectionStatus) {
                    collectionStatus = false;
                    try {
                        fos1.write("Here is your data collected: \n".getBytes());
                        fos2.write("Here is your data collected: \n".getBytes());
                        fos1.close();
                        fos2.close();
                    } catch (IOException e) {
                        Log.e(LOG_TAG, "Unable to close data collection.");
                    }
                }
                intent = new Intent(MainActivity.this, VenueData.class);
                break;
            default:
        }
        if(intent!=null){
            startActivity(intent);
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        parkourStart();
//        parkourSetInterval(5);
//        Log.e(LOG_TAG, "Is it even getting to here????????");
//
//    }
//
//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        parkourStart();
//    }
}
