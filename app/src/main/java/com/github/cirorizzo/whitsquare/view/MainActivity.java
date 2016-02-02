package com.github.cirorizzo.whitsquare.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.cirorizzo.whitsquare.R;

public class MainActivity extends AppCompatActivity implements MainViewInterface {
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setMessage(String message) {

    }
}
