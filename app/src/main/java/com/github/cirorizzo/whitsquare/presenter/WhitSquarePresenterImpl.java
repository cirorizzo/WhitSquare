package com.github.cirorizzo.whitsquare.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.github.cirorizzo.whitsquare.BuildConfig;
import com.github.cirorizzo.whitsquare.R;
import com.github.cirorizzo.whitsquare.model.Venue;
import com.github.cirorizzo.whitsquare.model.Venues;
import com.github.cirorizzo.whitsquare.view.MainViewInterface;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WhitSquarePresenterImpl implements WhitSquarePresenter {
    private final String TAG = WhitSquarePresenterImpl.class.getSimpleName();

    private AtomicBoolean isExecuting;
    private OkHttpClient client;
    private MainViewInterface mainViewInterface;

    public WhitSquarePresenterImpl() {
        isExecuting = new AtomicBoolean(false);
        client = new OkHttpClient();
    }

    @Override
    public void connect(@NonNull MainViewInterface mainViewInterface) {
        this.mainViewInterface = mainViewInterface;
    }

    @Override
    @NonNull
    public void getExplore(final Context context, String place) {
        if (isConnected(context)) {

            if (isExecuting.get() == false) {

                isExecuting.set(true);

                final Request request = new Request.Builder()
                        .url(getExploreURL(place))
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        isExecuting.set(false);
                        setMessageUI(context.getString(R.string.general_network_error) + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        isExecuting.set(false);

                        if (!response.isSuccessful()) {
                            setMessageUI(getMessageErrore(context, response.code()));
                        } else {
                            // Deserializing Data
                            List<Venue> venues = new Venues(context).setVenues(response.body().charStream());
                            if (!venues.isEmpty()) {
                                mainViewInterface.setData(venues);
                            } else {
                                setMessageUI(context.getString(R.string.no_venues_found));
                            }
                        }
                    }
                });
            }
        } else {
            setMessageUI(context.getString(R.string.not_connected));
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

    private String getMessageErrore(Context context, int responseCode) {
        if (responseCode == 400) {
            return context.getString(R.string.error_code_400);
        } else if (responseCode == 500) {
            return context.getString(R.string.error_code_500);
        }

        return context.getResources().getString(R.string.error_code_4xx);
    }

    private boolean isConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
