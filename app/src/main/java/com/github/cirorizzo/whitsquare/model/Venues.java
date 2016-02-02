package com.github.cirorizzo.whitsquare.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to deserialize Response API
 */
public class Venues {
    private final String TAG = Venues.class.getSimpleName();

    public List<Venue> venueList;
    private Context context;

    public Venues(Context context) {
        this.context = context;

        venueList = new ArrayList<>();
    }
}
