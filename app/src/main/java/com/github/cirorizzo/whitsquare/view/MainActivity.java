package com.github.cirorizzo.whitsquare.view;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.widget.Toast;

import com.github.cirorizzo.whitsquare.R;
import com.github.cirorizzo.whitsquare.model.Venue;
import com.github.cirorizzo.whitsquare.presenter.WhitSquarePresenterImpl;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainViewInterface {
    private final String TAG = MainActivity.class.getSimpleName();

    private WeakReference<WhitSquarePresenterImpl> whitSquarePresenter;
    private VenueAdapter venueAdapter;
    private RecyclerView containerRecyclerView;
    private SearchView searchVw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectingToPresenter();

        initRecyclerView();

        searchVwSetUp();
    }

    @Override
    public void setMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void setData(final List<Venue> venues) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                venueAdapter.setDataSet(venues);
                venueAdapter.notifyDataSetChanged();
            }
        });
    }

    private void connectingToPresenter() {
        // Using WeakReference to reduce Memory Leaks
        whitSquarePresenter = new WeakReference(WhitSquarePresenterImpl.getInstance());
        whitSquarePresenter.get().connect(this);
    }

    private void initRecyclerView() {
        containerRecyclerView = (RecyclerView) findViewById(R.id.data_recycler_view);
        containerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        venueAdapter = new VenueAdapter(this);
        containerRecyclerView.setAdapter(venueAdapter);
    }

    private void searchVwSetUp() {
        searchVw = (SearchView) findViewById(R.id.searchVw);
        searchVw.setSearchableInfo(((SearchManager) getSystemService(Context.SEARCH_SERVICE)).getSearchableInfo(getComponentName()));
        searchVw.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                whitSquarePresenter.get().getExplore(getApplicationContext(), query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
