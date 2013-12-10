package g2dm;

import fixtures.MusicSource;
import fixtures.MusicSource2;
import org.junit.Test;

import static fixtures.MusicSource.ANGELICA;
import static fixtures.MusicSource.VERONICA;
import static g2dm.RatingsOperations.dotProduct;
import static g2dm.RatingsOperations.vectorLength;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;
import static g2dm.RatingsOperations.cosineSimilarity;

/**
 * @author sp
 */
public class RatingsOperationsTest {

    private RatingsSource ds;

    @Test
    public void testVectorLength() throws Exception {
        ds = new MusicSource2();
        assertThat(vectorLength(ds.getRatings("Clara")), is(closeTo(10.09, 1E-2)));
        assertThat(vectorLength(ds.getRatings("Robert")), is(closeTo(7.416, 1E-3)));
    }

    @Test
    public void testDotProduct() throws Exception {
        ds = new MusicSource2();
        assertThat(dotProduct(ds.getRatings("Clara"), ds.getRatings("Robert")), is(closeTo(70.0, 1E-7)));
    }

    @Test
    public void testCosine() throws Exception {
        ds = new MusicSource();
        assertThat(cosineSimilarity(ds.getRatings(ANGELICA), ds.getRatings(VERONICA)), is(closeTo(0.9246, 1E-4)));
    }

}
