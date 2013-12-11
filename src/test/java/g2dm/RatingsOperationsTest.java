package g2dm;

import fixtures.MusicSource2;
import org.junit.Test;

import static g2dm.RatingsOperations.dotProduct;
import static g2dm.RatingsOperations.vectorLength;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

/**
 * @author sp
 */
public class RatingsOperationsTest {

    private Dataset ds;

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

}
