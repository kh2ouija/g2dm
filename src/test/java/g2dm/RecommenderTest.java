package g2dm;

import fixtures.MusicSource;
import g2dm.dto.ItemAndRating;
import g2dm.strategies.ManhattanStrategy;
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

    private Dataset ds;
    private Recommender recommender;

    @Before
    public void setUp() throws Exception {
        ds = new MusicSource();
    }

    @Test
    public void testRecommendMusic() throws Exception {
        recommender = new Recommender(ds, new ManhattanStrategy(), 1);
        assertThat(
                recommender.recommendItems(HAILEY),
                containsInAnyOrder(
                        new ItemAndRating("Phoenix", 4.0),
                        new ItemAndRating("Blues Traveler", 3.0),
                        new ItemAndRating("Slightly Stoopid", 2.5)));
        assertThat(
                recommender.recommendItems("Chan"),
                containsInAnyOrder(
                        new ItemAndRating("The Strokes", 4.0),
                        new ItemAndRating("Vampire Weekend", 1.0)));
        assertThat(
                recommender.recommendItems("Sam"),
                containsInAnyOrder(
                        new ItemAndRating("Deadmau5", 1.0)));
        assertThat(
                recommender.recommendItems(ANGELICA),
                is(empty()));
    }

}