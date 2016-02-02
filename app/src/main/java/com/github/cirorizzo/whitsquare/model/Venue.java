package com.github.cirorizzo.whitsquare.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Foursquare simplified Class to deserialize response data from API
 */
public class Venue {
    public String name;
    public String phone;
    public List<String> address = new ArrayList<>();
    public String url;
    public double rating;
    public String icon;
}
