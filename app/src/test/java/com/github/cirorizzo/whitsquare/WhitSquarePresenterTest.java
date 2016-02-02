package com.github.cirorizzo.whitsquare;

import com.github.cirorizzo.whitsquare.presenter.WhitSquarePresenterImpl;
import com.github.cirorizzo.whitsquare.view.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.ref.WeakReference;

import static org.junit.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class WhitSquarePresenterTest {
    private WeakReference<WhitSquarePresenterImpl> whitSquarePresenter;

    @Before
    public void setUp() {
        whitSquarePresenter = new WeakReference(WhitSquarePresenterImpl.getInstance());
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
}