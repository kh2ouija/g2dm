package chapter2;

import org.junit.Before;
import org.junit.Test;

import static chapter2.MusicFixtures.ANGELICA;
import static chapter2.MusicFixtures.HAILEY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by sp on 12/8/13.
 */
public class ClassifierTest {

    private RatingsDataSource ds;
    private Classifier classifier;

    @Before
    public void setUp() throws Exception {
        ds = new MusicFixtures();
        classifier = new Classifier(ds);
    }

    @Test
    public void testNearestNeighbours() throws Exception {
        String[] expected = new String[]{"Veronica", "Sam", "Angelica", "Chan", "Dan", "Bill", "Jordyn"};
        assertThat(classifier.computeNearestNeighbours(HAILEY), contains(expected));
    }

    @Test
    public void testRecommendMovies() throws Exception {
        assertThat(classifier.recommendItems(HAILEY),
                containsInAnyOrder(new String[]{"Phoenix", "Blues Traveler", "Slightly Stoopid"}));
        assertThat(classifier.recommendItems(ANGELICA), is(empty()));
    }

}
