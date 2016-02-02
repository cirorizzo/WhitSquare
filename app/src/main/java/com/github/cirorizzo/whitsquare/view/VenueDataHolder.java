package com.github.cirorizzo.whitsquare.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.cirorizzo.whitsquare.R;

public class VenueDataHolder extends RecyclerView.ViewHolder {
    private final String TAG = VenueDataHolder.class.getSimpleName();

    public ImageView imgVw_icon_venue;
    public TextView txtVw_name_venue;
    public TextView txtVw_location_venue;
    public TextView txtVw_url_venue;
    public TextView txtVw_rating_venue;

    public VenueDataHolder(View itemView) {
        super(itemView);

        imgVw_icon_venue = (ImageView) itemView.findViewById(R.id.imgVw_icon_venue);
        txtVw_name_venue = (TextView) itemView.findViewById(R.id.txtVw_name_venue);
        txtVw_location_venue = (TextView) itemView.findViewById(R.id.txtVw_location_venue);
        txtVw_url_venue = (TextView) itemView.findViewById(R.id.txtVw_url_venue);
        txtVw_rating_venue = (TextView) itemView.findViewById(R.id.txtVw_rating_venue);
    }
}
