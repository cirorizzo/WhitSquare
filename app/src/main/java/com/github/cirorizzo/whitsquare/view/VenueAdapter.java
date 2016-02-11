package com.github.cirorizzo.whitsquare.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.cirorizzo.whitsquare.R;
import com.github.cirorizzo.whitsquare.model.Venue;

import java.util.List;

public class VenueAdapter extends RecyclerView.Adapter<VenueDataHolder> {
    private final String TAG = VenueAdapter.class.getSimpleName();

    private Context context;
    private List<Venue> venueList;

    public VenueAdapter(Context context) {
        this.context = context;
    }

    public void setDataSet(List<Venue> venueList) {
        this.venueList = venueList;
    }

    @Override
    public VenueDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_cardview, parent, false);

        return new VenueDataHolder(view);
    }

    @Override
    public void onBindViewHolder(VenueDataHolder holder, int position) {
        holder.txtVw_name_venue.setText(venueList.get(position).name);
        holder.txtVw_location_venue.setText("");

        for (String addressLine: venueList.get(position).address) {
            holder.txtVw_location_venue.append(addressLine + "\n");
        }

        holder.txtVw_location_venue.append(venueList.get(position).phone);
        holder.txtVw_url_venue.setText(venueList.get(position).url);
        Linkify.addLinks(holder.txtVw_url_venue , Linkify.WEB_URLS);

        holder.txtVw_rating_venue.setText(String.valueOf(venueList.get(position).rating));

        Glide.with(context)
                .load(venueList.get(position).icon)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(holder.imgVw_icon_venue);
    }

    @Override
    public int getItemCount() {
        return (venueList != null) ? venueList.size() : 0;
    }
}
