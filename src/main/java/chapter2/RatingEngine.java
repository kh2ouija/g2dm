package chapter2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sp on 12/8/13.
 */
public class RatingEngine {

    private final RatingFunctions ratingFunctions = new RatingFunctions();
    private RatingsDataSource ds = new MusicFixtures();

    public RatingEngine(RatingsDataSource ratingsDataSource) {
        this.ds = ratingsDataSource;
    }

    public List<String> computeNearestNeighbours(String user) {
        Map<String, Double> ratings = ds.getRatings(user);
        Map<String, Double> userScores = new HashMap<>();
        List<String> otherUsers = new ArrayList<>(ds.getUsers());
        otherUsers.remove(user);
        for (String other : otherUsers) {
            userScores.put(other, ratingFunctions.minkowski(ratings, ds.getRatings(other), 2));
        }
        return otherUsers.stream().sorted(
                (a, b) -> Double.compare(userScores.get(a), userScores.get(b))
        ).collect(Collectors.toList());
    }

    public List<String> recommendItems(String user) {
        String nearest = computeNearestNeighbours(user).get(0);
        Set<String> rated = ds.getRatings(user).keySet();
        List<String> toRecommend = new ArrayList<>(ds.getRatings(nearest).keySet());
        toRecommend.removeAll(rated);
        return toRecommend;
    }

    public double pearson(String user1, String user2) {
        Map<String, Double> ratings1 = ds.getRatings(user1);
        Map<String, Double> ratings2 = ds.getRatings(user2);
        return ratingFunctions.pearson(ratings1, ratings2);
     }

}
