package chapter2;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by sp on 12/8/13.
 */
public class RatingEngineTest {

    public static final double EPS3 = 1E-3;
    public static final double EPS7 = 1E-7;

    private RatingsDataSource ds;
    private RatingEngine ratingEngine;
    private RatingFunctions ratingFunctions;

    public static final String HAILEY = "Hailey";
    public static final String VERONICA = "Veronica";
    public static final String JORDYN = "Jordyn";
    public static final String ANGELICA = "Angelica";
    public static final String BILL = "Bill";

    @Before
    public void setUp() throws Exception {
        ds = new MusicFixtures();
        ratingEngine = new RatingEngine(ds);
        ratingFunctions = new RatingFunctions();
    }

    @Test
    public void testManhattan() throws Exception {
        assertThat(ratingFunctions.manhattan(ds.getRatings(HAILEY), ds.getRatings(VERONICA)), is(equalTo(2.0)));
        assertThat(ratingFunctions.manhattan(ds.getRatings(HAILEY), ds.getRatings(JORDYN)), is(equalTo(7.5)));
    }

    @Test
    public void testEuclidean() throws Exception {
        assertThat(ratingFunctions.euclidean(ds.getRatings(HAILEY), ds.getRatings(VERONICA)), is(closeTo(1.414, EPS3)));
        assertThat(ratingFunctions.euclidean(ds.getRatings(HAILEY), ds.getRatings(JORDYN)), is(closeTo(4.387, EPS3)));
    }

    @Test
    public void testNearestNeighbours() throws Exception {
        String[] expected = new String[]{"Veronica", "Sam", "Angelica", "Chan", "Dan", "Bill", "Jordyn"};
        assertThat(ratingEngine.computeNearestNeighbours(HAILEY), contains(expected));
    }

    @Test
    public void testRecommendMovies() throws Exception {
        assertThat(ratingEngine.recommendItems(HAILEY),
                containsInAnyOrder(new String[]{"Phoenix", "Blues Traveler", "Slightly Stoopid"}));
        assertThat(ratingEngine.recommendItems(ANGELICA), is(empty()));
    }

    @Test
    public void testPearson() throws Exception {
        assertThat(ratingEngine.pearson(ANGELICA, BILL), is(closeTo(-0.90405349906826993, EPS7)));
        assertThat(ratingEngine.pearson(ANGELICA, HAILEY), is(closeTo(0.42008402520840293, EPS7)));
        assertThat(ratingEngine.pearson(ANGELICA, JORDYN), is(closeTo(0.76397486054754316, EPS7)));
    }

}
