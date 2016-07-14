package example.org.parkourdemoapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/9/2016.
 */
public class VenueActivity extends AppCompatActivity
//        implements GoogleApiClient.OnConnectionFailedListener
{

    private final String LOG_TAG = "VenueActivity";
    private RecyclerView mRecyclerView;
    private static RecyclerView.Adapter mVenueAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static List<VenueEntry> venueDataSet = new ArrayList<VenueEntry>();

    // Test code
    private String latNLong="38.9605465,-77.0161896"; // will be replaced with coordinates from venue listener
    private String API_KEY = "AIzaSyAvZuEtl3ieNPHDGTkhlyAZgRG-qyYHKdU"; // api key from places api, for web purposes
    private GoogleApiClient mGoogleApiClient;
    private String searchContents;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
//        mGoogleApiClient = new GoogleApiClient
//                .Builder(this)
//                .addApi(Places.GEO_DATA_API)
//                .enableAutoManage(this, this)
//                .build();
        FindPlaceID findPlaceID = new FindPlaceID();
        findPlaceID.execute(buildSearchUrl(latNLong));
        mRecyclerView =(RecyclerView) findViewById(R.id.location_RecyclerView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mVenueAdapter =new VenueDataAdapter(venueDataSet, VenueActivity.this);

        mRecyclerView.setAdapter(mVenueAdapter);
    }

    public static RecyclerView.Adapter getmVenueAdapter() {
        return mVenueAdapter;
    }

    //    @Override
//    public void onConnectionFailed(ConnectionResult result) {
//        Log.e(LOG_TAG, "Unable to connect to Google APIs");
//    }



    public String buildSearchUrl(String latNLong) {
        StringBuilder searchRequest =
                new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/");
        searchRequest.append("json?");
        searchRequest.append("location="+latNLong);
        searchRequest.append("&radius=50");
        searchRequest.append("&key="+API_KEY);

        //TODO change this to intialize asynctask, also rename method
        return searchRequest.toString();

    }

    // might want to add this to mainactivity
    private class FindPlaceID extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            searchContents = downloadJSONFile(params[0]);
            if(searchContents == null) {
                Log.d(LOG_TAG, "Downloaded file is null");

            }
            return searchContents;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("findPlaceID", "Result was " + searchContents);
        }

        private String downloadJSONFile(String urlPath) {
            StringBuilder buffer = new StringBuilder();
            try{
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int response = connection.getResponseCode();
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                int charRead;
                char[] inputBuffer = new char[500];

                while(true) {
                    charRead = isr.read(inputBuffer);
                    if(charRead <=0) {
                        break;
                    }
                    buffer.append(String.copyValueOf(inputBuffer, 0 , charRead));
                }
                return buffer.toString();

            } catch(IOException e){
                //TODO add log here e.getMEssage()
            }

            return null;
        }
    }
}

