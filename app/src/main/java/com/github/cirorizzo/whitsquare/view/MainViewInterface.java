package com.github.cirorizzo.whitsquare.view;

import com.github.cirorizzo.whitsquare.model.Venue;

import java.util.List;

public interface MainViewInterface {
    void setMessage(final String message);
    void setData(List<Venue> venues);
}
