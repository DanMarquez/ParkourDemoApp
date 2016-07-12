package example.org.parkourdemoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/9/2016.
 */
public class VenueActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static List<VenueEntry> venueDataSet = new ArrayList<VenueEntry>();

//    public VenueActivity(List<VenueEntry> venueDataSet) {
//        this.venueDataSet = venueDataSet;
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mRecyclerView =(RecyclerView) findViewById(R.id.location_RecyclerView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter =new VenueDataAdapter(venueDataSet, VenueActivity.this);

        mRecyclerView.setAdapter(mAdapter);


    }
}

