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
public class VenueDataAdapter extends RecyclerView.Adapter<VenueDataAdapter.VenueDataViewHolder> {
    private List<VenueEntry> mVenueDataSet;
    private Context mContext; // find out more about this

    public static class VenueDataViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public CardView mCardView;
        public VenueDataViewHolder(View v) {
            super(v);
            this.mTextView = (TextView) v.findViewById(R.id.textview);
            this.mCardView = (CardView) v.findViewById(R.id.card_view);
        }
    }

    public VenueDataAdapter(List<VenueEntry> mVenueDataSet, Context mContext) {
        this.mVenueDataSet = mVenueDataSet;
        this.mContext = mContext;
    }

    @Override
    public VenueDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()) /// need to fix this to add textview
                .inflate(R.layout.cardview, null);
        // set view's size, margins, paddings and layout params
        VenueDataViewHolder venueDataViewHolder = new VenueDataViewHolder(v);
        return venueDataViewHolder;
    }

    @Override
    public void onBindViewHolder(VenueDataViewHolder venueDataViewHolder, int position) {
        VenueEntry venueEntry = mVenueDataSet.get(position);

        // gets element from dataset at position
        // replaces contents of view with that element
        venueDataViewHolder.mTextView.setText(venueEntry.getVenue().toString());
    }

    @Override
    public int getItemCount() {
        return mVenueDataSet.size();
    }
}