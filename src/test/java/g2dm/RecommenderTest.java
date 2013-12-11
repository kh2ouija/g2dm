package g2dm;

import fixtures.MusicSource;
import g2dm.strategies.ManhattanStrategy;
import g2dm.strategies.PearsonStrategy;
import g2dm.strategies.UserWithScore;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static fixtures.MusicSource.ANGELICA;
import static fixtures.MusicSource.HAILEY;
import static java.util.stream.Collectors.toList;
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
                        new ItemWithRating("Phoenix", 4.0),
                        new ItemWithRating("Blues Traveler", 3.0),
                        new ItemWithRating("Slightly Stoopid", 2.5)));
        assertThat(
                recommender.recommendItems("Chan"),
                containsInAnyOrder(
                        new ItemWithRating("The Strokes", 4.0),
                        new ItemWithRating("Vampire Weekend", 1.0)));
        assertThat(
                recommender.recommendItems("Sam"),
                containsInAnyOrder(
                        new ItemWithRating("Deadmau5", 1.0)));
        assertThat(
                recommender.recommendItems(ANGELICA),
                is(empty()));
    }

}