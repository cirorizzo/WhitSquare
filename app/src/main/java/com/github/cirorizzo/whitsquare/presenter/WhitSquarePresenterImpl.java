package com.github.cirorizzo.whitsquare.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.github.cirorizzo.whitsquare.BuildConfig;
import com.github.cirorizzo.whitsquare.view.MainViewInterface;

public class WhitSquarePresenterImpl implements WhitSquarePresenter {
    private final String TAG = WhitSquarePresenterImpl.class.getSimpleName();

    private static WhitSquarePresenterImpl instance = null;

    protected WhitSquarePresenterImpl() {
        // Just to avoid instantiation

    }


    public static WhitSquarePresenterImpl getInstance() {
        if (instance == null) {
            instance = new WhitSquarePresenterImpl();
        }

        return instance;
    }

    @Override
    public void connect(MainViewInterface mainViewInterface) {

    }

    @Override
    public void getExplore(Context context, String place) {

    }

    public String getExploreURL(@NonNull String place) {

        return String.format("%s?client_id=%s&client_secret=%s&v=20130815&near=%s",
                BuildConfig.EXPLORE_API_ENDPOINT,
                BuildConfig.CLIENT_ID,
                BuildConfig.CLIENT_SECRET,
                place);
    }
}
