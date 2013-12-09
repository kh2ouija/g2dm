package chapter2;

import org.junit.Before;
import org.junit.Test;

import static chapter2.MusicFixtures.*;
import static chapter2.RatingFunctions.*;
import static chapter2.RatingFunctions.EUCLIDEAN;
import static chapter2.RatingFunctions.minkowski;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Created by sp on 12/10/13.
 */
public class RatingFunctionsTest {

    private RatingsDataSource ds;

    @Before
    public void setUp() throws Exception {
        ds = new MusicFixtures();
    }

    @Test
    public void testMinkowskiDistance() throws Exception {
        assertThat(minkowski(ds.getRatings(HAILEY), ds.getRatings(VERONICA), MANHATTAN), is(equalTo(2.0)));
        assertThat(minkowski(ds.getRatings(HAILEY), ds.getRatings(JORDYN), MANHATTAN), is(equalTo(7.5)));
        assertThat(minkowski(ds.getRatings(HAILEY), ds.getRatings(VERONICA), EUCLIDEAN), is(closeTo(1.414, 1E-3)));
        assertThat(minkowski(ds.getRatings(HAILEY), ds.getRatings(JORDYN), EUCLIDEAN), is(closeTo(4.387, 1E-3)));
    }

    @Test
    public void testPearson() throws Exception {
        assertThat(pearson(ds.getRatings(ANGELICA), ds.getRatings(BILL)), is(closeTo(-0.90405349906826993, 1E-7)));
        assertThat(pearson(ds.getRatings(ANGELICA), ds.getRatings(HAILEY)), is(closeTo(0.42008402520840293, 1E-7)));
        assertThat(pearson(ds.getRatings(ANGELICA), ds.getRatings(JORDYN)), is(closeTo(0.76397486054754316, 1E-7)));
    }

    @Test
    public void testCosine() throws Exception {

    }

    @Test
    public void testVectorLength() throws Exception {
        ds = new MusicFixtures2();
        assertThat(vectorLength(ds.getRatings("Clara")), is(closeTo(10.09, 1E-2)));
        assertThat(vectorLength(ds.getRatings("Robert")), is(closeTo(7.416, 1E-3)));
    }
}
