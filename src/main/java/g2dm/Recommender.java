package g2dm;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * Created by sp on 12/8/13.
 */
public class Recommender {

    private RatingsSource ratingsSource;
    private SimilarityStrategy similarityStrategy;

    public Recommender(RatingsSource ratingsSource, SimilarityStrategy similarityStrategy) {
        this.ratingsSource = ratingsSource;
        this.similarityStrategy = similarityStrategy;
    }

    public List<String> getNearestUsers(String user) {
        List<String> otherUsers = ratingsSource.getOtherUsers(user);
        Map<String, Double> userScores = getUserSimilarityMap(user, otherUsers);
        List<String> result = otherUsers.stream().sorted(
                (u1, u2) -> similarityStrategy.getDistanceComparator().compare(userScores.get(u1), userScores.get(u2))
        ).collect(toList());
        return result;
    }

    private Map<String, Double> getUserSimilarityMap(String user, List<String> otherUsers) {
        Map<String, Double> userScores = new HashMap<>();
        otherUsers.forEach(other -> userScores.put(other, computeSimilarity(user, other)));
        return userScores;
    }

    private double computeSimilarity(String user, String other) {
        return similarityStrategy.getSimilarityFunction().apply(ratingsSource.getRatings(user), ratingsSource.getRatings(other));
    }

    public List<String> recommendItems(String user) {
        List<String> orderedSimilarUsers = getNearestUsers(user);
        String nearest = orderedSimilarUsers.get(0);
        Set<String> alreadyRated = ratingsSource.getRatings(user).keySet();
        return ratingsSource.getRatings(nearest).keySet().stream()
                .filter(item -> !alreadyRated.contains(item)).collect(toList());
    }

}
