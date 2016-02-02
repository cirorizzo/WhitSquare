package com.github.cirorizzo.whitsquare.view;

import com.github.cirorizzo.whitsquare.model.Venue;

import java.util.List;

/**
 * View's interface
 */
public interface MainViewInterface {
    void setMessage(final String message);
    void setData(final List<Venue> venues);
}
