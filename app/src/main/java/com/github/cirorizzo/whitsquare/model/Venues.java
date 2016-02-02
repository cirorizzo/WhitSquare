package com.github.cirorizzo.whitsquare.model;

import android.content.Context;
import android.util.Log;

import com.github.cirorizzo.whitsquare.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public List<Venue> setVenues(String reader) {
        venueList.clear();

        try {
            JSONArray groups = (new JSONObject(reader)).getJSONObject("response").getJSONArray("groups");

            JSONArray items;
            for (int i = 0; i < groups.length(); i++) {

                items = ((JSONObject) groups.get(i)).getJSONArray("items");

                for (int idx = 0; idx < items.length(); idx++) {
                    venueList.add(deserializeVenueItem(((JSONObject) items.get(idx)).getJSONObject("venue")));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        } finally {
            return venueList;
        }
    }

    private Venue deserializeVenueItem(JSONObject item) throws JSONException {

        Venue venue = new Venue();

        venue.name = item.getString("name");
        venue.phone = item.getJSONObject("contact").getString("phone");

        for (int idx = 0; idx < item.getJSONObject("location").getJSONArray("formattedAddress").length(); idx++) {
            venue.address.add(item.getJSONObject("location").getJSONArray("formattedAddress").get(idx).toString());
        }

        venue.url = item.getString("url");

        venue.rating = item.getDouble("rating");

        if (item.getJSONArray("categories").length() > 0) {
            // Take just the first Category
            venue.icon = getIconURL(
                    item.getJSONArray("categories").getJSONObject(0).getJSONObject("icon").getString("prefix"),
                    item.getJSONArray("categories").getJSONObject(0).getJSONObject("icon").getString("suffix"));

        }
        return venue;
    }

    private String getIconURL(String iconStr, String suffix) {
        return String.format("%sbg_%d%s", iconStr, context.getResources().getInteger(R.integer.icon_dim_px), suffix);
    }
}
