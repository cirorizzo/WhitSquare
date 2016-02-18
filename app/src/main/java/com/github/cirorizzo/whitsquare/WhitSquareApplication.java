package com.github.cirorizzo.whitsquare;

import android.app.Application;

import com.github.cirorizzo.whitsquare.injection.InjectionWhitSquarePresenter;

public class WhitSquareApplication extends Application {
    private final InjectionWhitSquarePresenter injection = createInjection();

    protected InjectionWhitSquarePresenter createInjection() {
        return new InjectionWhitSquarePresenter();
    }

    public InjectionWhitSquarePresenter getInjection() {
        return injection;
    }
}