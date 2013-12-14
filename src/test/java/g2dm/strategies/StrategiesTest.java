package g2dm.strategies;

import fixtures.MusicSource;
import fixtures.PieSource;
import g2dm.Dataset;
import g2dm.dto.ItemAndRating;
import g2dm.dto.UserAndScore;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static fixtures.MusicSource.HAILEY;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by sp on 12/11/13.
 */
public class StrategiesTest {

    private Dataset ds;

    @Before
    public void setUp() throws Exception {
        ds = new MusicSource();
    }

    @Test
    public void testNearestNeighbours() throws Exception {
        assertThat(
                new ManhattanStrategy().computeKNearestPercentileWeightedUsers(HAILEY, ds, 7).stream()
                        .map(UserAndScore::getUser).collect(toList()),
                contains("Veronica", "Chan", "Sam", "Dan", "Angelica", "Bill", "Jordyn"));
    }

    @Test
    public void testComputeNormalizedRecommendationsK3() {
        ds = new PieSource() {
            @Override
            public Map<String, Double> getRatings(String user) {
                if ("Nobody".equals(user)) {
                    return Collections.<String, Double>emptyMap();
                } else {
                    return super.getRatings(user);
                }
            }
        };

        List<UserAndScore> scoredUsers = Arrays.asList(
                new UserAndScore("Sally", 0.8),
                new UserAndScore("Eric", 0.7),
                new UserAndScore("Amanda", 0.5));

        List<ItemAndRating> recommendations = new PearsonStrategy().computeNormalizedRecommendations("Nobody", scoredUsers, ds);
        assertEquals(recommendations.size() , 1);
        assertThat(recommendations, hasItem(new ItemAndRating("Grey Wardens", 4.275)));
    }


    @Test
    public void testComputeNormalizedRecommendationsK2() {
        ds = new PieSource() {
            @Override
            public Map<String, Double> getRatings(String user) {
                if ("Nobody".equals(user)) {
                    return Collections.<String, Double>emptyMap();
                } else {
                    return super.getRatings(user);
                }
            }
        };

        List<UserAndScore> scoredUsers = Arrays.asList(
                new UserAndScore("Sally", 0.8),
                new UserAndScore("Eric", 0.7));

        List<ItemAndRating> recommendations = new PearsonStrategy().computeNormalizedRecommendations("Nobody", scoredUsers, ds);
        assertEquals(recommendations.size() , 1);
        assertThat(recommendations.get(0).getItem(), is(equalTo("Grey Wardens")));
        assertThat(recommendations.get(0).getRating(), is(closeTo(4.2, 1E-7)));
    }


}
