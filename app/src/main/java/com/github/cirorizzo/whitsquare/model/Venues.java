package com.github.cirorizzo.whitsquare.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to deserialize Response API
 */
public class Venues {
    private final String TAG = Venues.class.getSimpleName();

    public List<Venue> venueList;

    public Venues() {

        venueList = new ArrayList<>();
    }
}
