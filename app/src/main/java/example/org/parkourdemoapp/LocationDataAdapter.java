package example.org.parkourdemoapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Daniel on 7/11/2016.
 */
public class LocationDataAdapter extends RecyclerView.Adapter<LocationDataAdapter.LocationDataViewHolder> {
    private List<LocationEntry> mLocationDataSet;
    private Context mContext; // find out more about this

    public static class LocationDataViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public CardView mCardView;
        public LocationDataViewHolder(View v) {
            super(v);
            this.mTextView = (TextView) v.findViewById(R.id.textview);
            this.mCardView = (CardView) v.findViewById(R.id.card_view);
        }
    }

    public LocationDataAdapter(List<LocationEntry> mLocationDataSet, Context mContext) {
        this.mLocationDataSet = mLocationDataSet;
        this.mContext = mContext;
    }

    @Override
    public LocationDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()) /// need to fix this to add textview
                                .inflate(R.layout.cardview, null);
        // set view's size, margins, paddings and layout params
        LocationDataViewHolder locationDataViewHolder = new LocationDataViewHolder(v);
        return locationDataViewHolder;
    }

    @Override
    public void onBindViewHolder(LocationDataViewHolder locationDataViewHolder, int position) {
        LocationEntry locationEntry = mLocationDataSet.get(position);

        // gets element from dataset at position
        // replaces contents of view with that element
        locationDataViewHolder.mTextView.setText(locationEntry.getLocation().toString());
    }

    @Override
    public int getItemCount() {
        return mLocationDataSet.size();
    }
}
