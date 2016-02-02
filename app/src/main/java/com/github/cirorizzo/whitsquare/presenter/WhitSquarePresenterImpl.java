package com.github.cirorizzo.whitsquare.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.github.cirorizzo.whitsquare.BuildConfig;
import com.github.cirorizzo.whitsquare.model.Venues;
import com.github.cirorizzo.whitsquare.view.MainViewInterface;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class WhitSquarePresenterImpl implements WhitSquarePresenter {
    private final String TAG = WhitSquarePresenterImpl.class.getSimpleName();

    private static WhitSquarePresenterImpl instance = null;
    private AtomicBoolean isExecuting;
    private OkHttpClient client;
    private MainViewInterface mainViewInterface;

    protected WhitSquarePresenterImpl() {
        isExecuting = new AtomicBoolean(false);
        client = new OkHttpClient();
    }


    public static WhitSquarePresenterImpl getInstance() {
        if (instance == null) {
            instance = new WhitSquarePresenterImpl();
        }

        return instance;
    }

    @Override
    public void connect(@NonNull MainViewInterface mainViewInterface) {
        this.mainViewInterface = mainViewInterface;
    }

    @Override
    @NonNull
    public void getExplore(final Context context, String place) {
        if (isExecuting.get() == false) {

            isExecuting.set(true);

            final Request request = new Request.Builder()
                    .url(getExploreURL(place))
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    setMessageUI("Network Error - Failure" + e.getMessage());
                    isExecuting.set(false);
                }


                @Override
                public void onResponse(Response response) throws IOException {
                    isExecuting.set(false);

                    if (!response.isSuccessful()) {
                        setMessageUI("Network Error - Unexpected code ");
                    }

                    // Deserializing Data
                    mainViewInterface.setData(new Venues(context).setVenues(response.body().charStream()));
                }
            });
        }
    }

    public String getExploreURL(@NonNull String place) {
        return String.format("%s?client_id=%s&client_secret=%s&v=20130815&near=%s",
                BuildConfig.EXPLORE_API_ENDPOINT,
                BuildConfig.CLIENT_ID,
                BuildConfig.CLIENT_SECRET,
                place);
    }

    private void setMessageUI(String message) {
        mainViewInterface.setMessage(message);
    }
}
