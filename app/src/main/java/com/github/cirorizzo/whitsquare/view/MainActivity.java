package com.github.cirorizzo.whitsquare.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.cirorizzo.whitsquare.R;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
