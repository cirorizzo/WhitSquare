package com.github.cirorizzo.whitsquare;

import com.github.cirorizzo.whitsquare.injection.InjectionWhitSquarePresenter;
import com.github.cirorizzo.whitsquare.presenter.WhitSquarePresenterImpl;

import org.mockito.Mockito;

public class MockInjectionWhitSquarePresenter extends InjectionWhitSquarePresenter {

    @Override
    protected WhitSquarePresenterImpl createWhitSquarePresenterImpl() {
        return Mockito.mock(WhitSquarePresenterImpl.class);
    }
}
