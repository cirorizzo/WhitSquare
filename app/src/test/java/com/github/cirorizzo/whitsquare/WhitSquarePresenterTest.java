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

import java.lang.ref.WeakReference;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
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
        String fakeResponse = "{\"meta\":{\"code\":200,\"requestId\":\"56aff62e498e0f9cd93648d6\"},\"response\":{\"suggestedFilters\":{\"header\":\"Tap to show:\",\"filters\":[{\"name\":\"Open now\",\"key\":\"openNow\"}]},\"geocode\":{\"what\":\"\",\"where\":\"napoli\",\"center\":{\"lat\":40.85631,\"lng\":14.24641},\"displayString\":\"Naples, Campania, Italy\",\"cc\":\"IT\",\"geometry\":{\"bounds\":{\"ne\":{\"lat\":40.92106098201073,\"lng\":14.353831988010858},\"sw\":{\"lat\":40.79091701016618,\"lng\":14.117484009957094}}},\"slug\":\"napoli-italy\",\"longId\":\"72057594041100330\"},\"warning\":{\"text\":\"There aren't a lot of results near you. Try something more general, reset your filters, or expand the search area.\"},\"headerLocation\":\"Naples\",\"headerFullLocation\":\"Naples\",\"headerLocationGranularity\":\"city\",\"totalResults\":140,\"suggestedBounds\":{\"ne\":{\"lat\":40.85448061233616,\"lng\":14.267642609105735},\"sw\":{\"lat\":40.85178095255071,\"lng\":14.270342477335305}},\"groups\":[{\"type\":\"Recommended Places\",\"name\":\"recommended\",\"items\":[{\"reasons\":{\"count\":0,\"items\":[{\"summary\":\"This spot is popular\",\"type\":\"general\",\"reasonName\":\"globalInteractionReason\"}]},\"venue\":{\"id\":\"4b92fee5f964a5206c2d34e3\",\"name\":\"Pasticceria Attanasio\",\"contact\":{\"phone\":\"+39081285675\",\"formattedPhone\":\"+39 081 285675\"},\"location\":{\"address\":\"Vico Ferrovia, 2-4\",\"lat\":40.853130782443436,\"lng\":14.26899254322052,\"postalCode\":\"80142\",\"cc\":\"IT\",\"city\":\"Napoli\",\"state\":\"Campania\",\"country\":\"Italia\",\"formattedAddress\":[\"Vico Ferrovia, 2-4\",\"80142 Napoli Campania\",\"Italia\"]},\"categories\":[{\"id\":\"4bf58dd8d48988d1d0941735\",\"name\":\"Dessert Shop\",\"pluralName\":\"Dessert Shops\",\"shortName\":\"Desserts\",\"icon\":{\"prefix\":\"https:\\/\\/ss3.4sqi.net\\/img\\/categories_v2\\/food\\/dessert_\",\"suffix\":\".png\"},\"primary\":true}],\"verified\":false,\"stats\":{\"checkinsCount\":779,\"usersCount\":531,\"tipCount\":76},\"url\":\"http:\\/\\/www.sfogliatelleattanasio.it\",\"price\":{\"tier\":1,\"message\":\"Cheap\",\"currency\":\"\\u20ac\"},\"rating\":9.4,\"ratingSignals\":184,\"allowMenuUrlEdit\":true,\"hours\":{\"isOpen\":false,\"isLocalHoliday\":false},\"photos\":{\"count\":0,\"groups\":[]},\"hereNow\":{\"count\":0,\"summary\":\"Nobody here\",\"groups\":[]}},\"tips\":[{\"id\":\"4c2b51cbd1a10f47f3b5f764\",\"createdAt\":1277907403,\"text\":\"Pare che sia la migliore sfogliatella di Napoli\",\"type\":\"user\",\"canonicalUrl\":\"https:\\/\\/foursquare.com\\/item\\/4c2b51cbd1a10f47f3b5f764\",\"likes\":{\"count\":22,\"groups\":[],\"summary\":\"22 likes\"},\"logView\":true,\"todo\":{\"count\":5},\"user\":{\"id\":\"594559\",\"firstName\":\"Caterina\",\"lastName\":\"Policaro\",\"gender\":\"female\",\"photo\":{\"prefix\":\"https:\\/\\/irs0.4sqi.net\\/img\\/user\\/\",\"suffix\":\"\\/DOSULD1NB5CJO0P0.jpg\"}}}],\"referralId\":\"e-0-4b92fee5f964a5206c2d34e3-0\"}]}]}}";
        Venues venues = new Venues(context);
        List<Venue> venueList = venues.setVenues(fakeResponse);

        assertNotNull(venueList);
        assertTrue((venueList.size() > 0));
    }
}