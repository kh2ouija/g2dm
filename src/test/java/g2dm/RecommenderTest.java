package g2dm;

import fixtures.MusicSource;
import org.junit.Before;
import org.junit.Test;

import static fixtures.MusicSource.ANGELICA;
import static fixtures.MusicSource.HAILEY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by sp on 12/8/13.
 */
public class RecommenderTest {

    private RatingsSource ds;
    private Recommender recommender;

    @Before
    public void setUp() throws Exception {
        ds = new MusicSource();
    }

    @Test
    public void testNearestNeighbours() throws Exception {
        recommender = new Recommender(ds, SimilarityFunctions::manhattanDistance);
        String[] expected = new String[]{"Veronica", "Chan", "Sam", "Dan", "Angelica", "Bill", "Jordyn"};
        assertThat(recommender.getOrderedSimilarUsers(HAILEY), contains(expected));
    }

    @Test
    public void testRecommendMovies() throws Exception {
        recommender = new Recommender(ds, SimilarityFunctions::euclideanDistance);
        assertThat(recommender.recommendItems(HAILEY),
                containsInAnyOrder(new String[]{"Phoenix", "Blues Traveler", "Slightly Stoopid"}));
        assertThat(recommender.recommendItems(ANGELICA), is(empty()));
    }

}
