package g2dm;

import fixtures.MusicSource;
import fixtures.PieSource;
import g2dm.dto.ItemAndRating;
import g2dm.dto.UserAndScore;
import g2dm.dto.UserAndWeight;
import g2dm.strategies.ManhattanStrategy;
import g2dm.strategies.PearsonStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static fixtures.MusicSource.ANGELICA;
import static fixtures.MusicSource.HAILEY;
import static fixtures.MusicSource.JORDYN;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
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
    public void testRecommendMusicWithManhattan() throws Exception {
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

    @Test
    public void testRecommendMusicWithPearson() throws Exception {
        recommender = new Recommender(ds, new PearsonStrategy(), 1);
        assertThat(
                recommender.recommendItems(HAILEY),
                containsInAnyOrder(
                        new ItemAndRating("Phoenix", 5.0),
                        new ItemAndRating("Slightly Stoopid", 4.5)));
        assertThat(
                recommender.recommendItems(JORDYN),
                containsInAnyOrder(
                        new ItemAndRating("Blues Traveler", 5.0)));
    }

}