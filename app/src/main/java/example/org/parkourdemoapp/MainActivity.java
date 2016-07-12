package example.org.parkourdemoapp;

import android.Manifest;
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

import java.util.List;

public class MainActivity extends Parkour
        implements View.OnClickListener
{
    private final int REQUEST_PERMISSION_FINE_LOCATION=1;
    //private TextView locationData;
    private Button startDataCollection;
    private Button stopDataCollection;
    private Button viewLocationData;
    private Button viewVenueData;

    private boolean collectionStatus = false;

    private List<LocationEntry> mLocationEntryDataSet;



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
                if(collectionStatus) {
                    LocationEntry locationEntry = new LocationEntry(newLoc, motionType, positionType);
                    LocationActivity.locationDataSet.add(locationEntry);
                }
                Log.d(LOG_TAG, data);

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
                if(collectionStatus) {
                    VenueEntry venueEntry = new VenueEntry(ven, add, catOne, catTwo, dist, userLoc);
                    VenueActivity.venueDataSet.add(venueEntry);
                }
                Log.d(LOG_TAG, data);
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
                if(!collectionStatus) {
                    collectionStatus = true;
                    parkourSetInterval(5);
                    parkourStart();
                }
                break;
            case R.id.stopDataCollection:
                if(collectionStatus) {
                    collectionStatus = false;
                    parkourStop();
                }
                break;
            case R.id.viewLocationData:
                intent = new Intent(MainActivity.this, LocationActivity.class);
                break;
            case R.id.viewVenueData:
                intent = new Intent(MainActivity.this, VenueActivity.class);
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
