package com.github.cirorizzo.whitsquare;

import android.content.Context;
import android.test.mock.MockContext;

import com.github.cirorizzo.whitsquare.model.Venue;
import com.github.cirorizzo.whitsquare.model.Venues;
import com.github.cirorizzo.whitsquare.presenter.WhitSquarePresenterImpl;
import com.github.cirorizzo.whitsquare.view.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class WhitSquarePresenterTest {
    private WeakReference<WhitSquarePresenterImpl> whitSquarePresenter;
    private Context context;

    @Before
    public void setUp() {

        context = new MockContext();
        assertNotNull(context);

        whitSquarePresenter = new WeakReference(WhitSquarePresenterImpl.getInstance());
        assertNotNull(whitSquarePresenter);
    }

    @After
    public void tearDown() {
        whitSquarePresenter = null;
    }


    @Test
    public void connect() throws Exception {
        try {
            whitSquarePresenter.get().connect(new MainActivity());
        } catch (Exception e) {
            assertTrue(e.getClass().isInstance(IllegalArgumentException.class));
        }
    }

    @Test
    public void getExploreURLTest() {
        System.out.println(whitSquarePresenter.get().getExploreURL("Napoli"));
        assertTrue(!whitSquarePresenter.get().getExploreURL("Napoli").contains("%s"));
    }

    @Test
    public void setVenuesTest() {
        OkHttpClient client = new OkHttpClient();

        String endPointURL = whitSquarePresenter.get().getExploreURL("Napoli");
        assertTrue(!endPointURL.contains("%s"));

        final Request request = new Request.Builder()
                .url(endPointURL)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                assertNull(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                List<Venue> venueList = null;
                if (response.isSuccessful()) {
                    venueList = new Venues(context).setVenues(response.body().charStream());
                }

                assertNotNull(venueList);
                assertTrue((venueList.size() > 0));
            }
        });
    }
}