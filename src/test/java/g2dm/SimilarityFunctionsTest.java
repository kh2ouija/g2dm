package g2dm;

import fixtures.MusicSource;
import org.junit.Before;
import org.junit.Test;

import static fixtures.MusicSource.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Created by sp on 12/10/13.
 */
public class SimilarityFunctionsTest {

    private Dataset ds;

    @Before
    public void setUp() throws Exception {
        ds = new MusicSource();
    }

    @Test
    public void testManhattan() {
        assertThat(RatingsOperations.euclideanDistance(ds.getRatings(HAILEY), ds.getRatings(VERONICA)), is(closeTo(1.414, 1E-3)));
        assertThat(RatingsOperations.euclideanDistance(ds.getRatings(HAILEY), ds.getRatings(JORDYN)), is(closeTo(4.387, 1E-3)));
    }

    @Test
    public void testEuclidean() throws Exception {
        assertThat(RatingsOperations.manhattanDistance(ds.getRatings(HAILEY), ds.getRatings(VERONICA)), is(equalTo(2.0)));
        assertThat(RatingsOperations.manhattanDistance(ds.getRatings(HAILEY), ds.getRatings(JORDYN)), is(equalTo(7.5)));
        testManhattan();
    }

    @Test
    public void testPearson() throws Exception {
        assertThat(RatingsOperations.pearsonCorrelation(ds.getRatings(ANGELICA), ds.getRatings(BILL)), is(closeTo(-0.90405349906826993, 1E-7)));
        assertThat(RatingsOperations.pearsonCorrelation(ds.getRatings(ANGELICA), ds.getRatings(HAILEY)), is(closeTo(0.42008402520840293, 1E-7)));
        assertThat(RatingsOperations.pearsonCorrelation(ds.getRatings(ANGELICA), ds.getRatings(JORDYN)), is(closeTo(0.76397486054754316, 1E-7)));
    }

}
