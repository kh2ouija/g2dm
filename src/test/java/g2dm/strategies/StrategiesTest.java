package g2dm.strategies;

import fixtures.MusicSource;
import g2dm.Dataset;
import g2dm.Recommender;
import g2dm.UserWithWeight;
import g2dm.strategies.PearsonStrategy;
import g2dm.strategies.UserWithScore;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static fixtures.MusicSource.HAILEY;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.contains;
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
                new ManhattanStrategy().getKNearestPercentileWeightedUsers(HAILEY, ds, 7).stream()
                        .map(UserWithWeight::getUser).collect(toList()),
                contains("Veronica", "Chan", "Sam", "Dan", "Angelica", "Bill", "Jordyn"));
    }

    @Test
    public void testPearsonComputePie() throws Exception {
        List<UserWithScore> usersWithScores = Arrays.asList(
                new UserWithScore("Sally", 0.8),
                new UserWithScore("Eric", 0.7),
                new UserWithScore("Amanda", 0.5));
        Map<String,List<UserWithWeight>> pieMap = new PearsonStrategy().computePie(usersWithScores).stream()
                .collect(groupingBy(UserWithWeight::getUser));
        assertThat(pieMap.get("Sally").get(0).getWeight(), is(closeTo(0.4, 1E-3)));
        assertThat(pieMap.get("Eric").get(0).getWeight(), is(closeTo(0.35, 1E-3)));
        assertThat(pieMap.get("Amanda").get(0).getWeight(), is(closeTo(0.25, 1E-3)));
    }

}