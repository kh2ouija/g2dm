package g2dm;

import fixtures.MusicSource;
import g2dm.strategies.ManhattanStrategy;
import org.junit.Before;
import org.junit.Test;

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
    public void testNearestNeighbours() throws Exception {
        assertThat(
                new ManhattanStrategy().getKNearestPercentileWeightedUsers(HAILEY, ds, 7).stream()
                        .map(UserWithWeight::getUser).collect(toList()),
                contains("Veronica", "Chan", "Sam", "Dan", "Angelica", "Bill", "Jordyn"));
    }

    @Test
    public void testRecommendMusic() throws Exception {
        recommender = new Recommender(ds, new ManhattanStrategy(), 1);
        assertThat(
                recommender.recommendItems(HAILEY),
                containsInAnyOrder("Phoenix", "Blues Traveler", "Slightly Stoopid"));
        assertThat(
                recommender.recommendItems("Chan"),
                containsInAnyOrder("The Strokes", "Vampire Weekend"));
        assertThat(
                recommender.recommendItems("Sam"),
                containsInAnyOrder("Deadmau5"));
        assertThat(
                recommender.recommendItems(ANGELICA),
                is(empty()));
    }

}
