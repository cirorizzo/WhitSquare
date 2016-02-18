package com.github.cirorizzo.whitsquare;

import com.github.cirorizzo.whitsquare.injection.InjectionWhitSquarePresenter;

public class MockWhitSquareApplication extends WhitSquareApplication {
    @Override
    protected InjectionWhitSquarePresenter createInjection() {
        return new MockInjectionWhitSquarePresenter();
    }
}
