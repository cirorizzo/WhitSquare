package com.github.cirorizzo.whitsquare.injection;

import com.github.cirorizzo.whitsquare.presenter.WhitSquarePresenterImpl;

public class InjectionWhitSquarePresenter {
    private WhitSquarePresenterImpl whitSquarePresenterImpl = null;

    protected WhitSquarePresenterImpl createWhitSquarePresenterImpl() {
        return new WhitSquarePresenterImpl();
    }

    public WhitSquarePresenterImpl provideWhitSquarePresenterImpl() {
        if (whitSquarePresenterImpl == null) {
            whitSquarePresenterImpl = createWhitSquarePresenterImpl();
        }

        return  whitSquarePresenterImpl;
    }
}
