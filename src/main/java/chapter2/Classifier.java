package chapter2;

import java.util.*;
import java.util.stream.Collectors;

import static chapter2.RatingFunctions.EUCLIDEAN;
import static chapter2.RatingFunctions.minkowski;

/**
 * Created by sp on 12/8/13.
 */
public class Classifier {

    private RatingsDataSource ds = new MusicFixtures();

    public Classifier(RatingsDataSource ratingsDataSource) {
        this.ds = ratingsDataSource;
    }

    public List<String> computeNearestNeighbours(String user) {
        List<String> otherUsers = getOtherUsers(user);
        Map<String, Double> userScores = getSimilarityVector(user, otherUsers);
        return otherUsers.stream().sorted(
                (u1, u2) -> Double.compare(userScores.get(u1), userScores.get(u2))
        ).collect(Collectors.toList());
    }

    private Map<String, Double> getSimilarityVector(String user, List<String> otherUsers) {
        Map<String, Double> userScores = new HashMap<>();
        otherUsers.forEach(other -> userScores.put(other, similarity(user, other)));
        return userScores;
    }

    private List<String> getOtherUsers(String user) {
        List<String> otherUsers = new ArrayList<>(ds.getUsers());
        otherUsers.remove(user);
        return otherUsers;
    }

    private double similarity(String user, String other) {
        return minkowski(ds.getRatings(user), ds.getRatings(other), EUCLIDEAN);
    }

    public List<String> recommendItems(String user) {
        String nearest = computeNearestNeighbours(user).get(0);
        Set<String> rated = ds.getRatings(user).keySet();
        List<String> toRecommend = new ArrayList<>(ds.getRatings(nearest).keySet());
        toRecommend.removeAll(rated);
        return toRecommend;
    }

}
