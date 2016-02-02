package com.github.cirorizzo.whitsquare.presenter;

import android.content.Context;

import com.github.cirorizzo.whitsquare.view.MainViewInterface;

public interface WhitSquarePresenter {
    void connect(MainViewInterface mainViewInterface);
    void getExplore(final Context context, String place);
}
