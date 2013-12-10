package g2dm;

import fixtures.MusicSource;
import g2dm.strategies.CosineStrategy;
import g2dm.strategies.EuclideanStrategy;
import g2dm.strategies.ManhattanStrategy;
import g2dm.strategies.PearsonStrategy;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static fixtures.MusicSource.ANGELICA;
import static fixtures.MusicSource.HAILEY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
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
        String[] expected = new String[]{"Veronica", "Chan", "Sam", "Dan", "Angelica", "Bill", "Jordyn"};
        assertThat(new ManhattanStrategy().getNearestUsers(HAILEY, ds), contains(expected));
    }

    @Test
    public void testRecommendMovies() throws Exception {
        recommender = new Recommender(ds, new EuclideanStrategy());
        assertThat(recommender.recommendItems(HAILEY),
                containsInAnyOrder("Phoenix", "Blues Traveler", "Slightly Stoopid"));
        assertThat(recommender.recommendItems(ANGELICA), is(empty()));
    }

    @Test @Ignore
    public void testSimilarityResults() throws Exception {
        System.out.println("manhattan");
        recommender = new Recommender(ds, new ManhattanStrategy());
        recommender.recommendItems(HAILEY);
        System.out.println("euclidean");
        recommender = new Recommender(ds, new EuclideanStrategy());
        recommender.recommendItems(HAILEY);
        System.out.println("cosine");
        recommender = new Recommender(ds, new CosineStrategy());
        recommender.recommendItems(HAILEY);
        System.out.println("pearson");
        recommender = new Recommender(ds, new PearsonStrategy());
        recommender.recommendItems(HAILEY);
    }

}
