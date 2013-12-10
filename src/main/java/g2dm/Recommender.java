package g2dm;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * Created by sp on 12/8/13.
 */
public class Recommender {

    private RatingsSource ratingsSource;
    private SimilarityFunction similarityFunction;

    public Recommender(RatingsSource ratingsSource, SimilarityFunction similarityFunction) {
        this.ratingsSource = ratingsSource;
        this.similarityFunction = similarityFunction;
    }

    public List<String> getOrderedSimilarUsers(String user) {
        new Recommender(null, SimilarityFunctions::pearsonCorrelation);
        List<String> otherUsers = getOtherUsers(user);
        Map<String, Double> userScores = getUserSimilarityMap(user, otherUsers);
        return otherUsers.stream().sorted(
                (u1, u2) -> Double.compare(userScores.get(u1), userScores.get(u2))
        ).collect(toList());
    }

    private Map<String, Double> getUserSimilarityMap(String user, List<String> otherUsers) {
        Map<String, Double> userScores = new HashMap<>();
        otherUsers.forEach(other -> userScores.put(other, computeSimilarity(user, other)));
        return userScores;
    }

    private List<String> getOtherUsers(String user) {
        List<String> otherUsers = new ArrayList<>(ratingsSource.getUsers());
        otherUsers.remove(user);
        return otherUsers;
    }

    private double computeSimilarity(String user, String other) {
        return similarityFunction.apply(ratingsSource.getRatings(user), ratingsSource.getRatings(other));
    }

    public List<String> recommendItems(String user) {
        String nearest = getOrderedSimilarUsers(user).get(0);
        Set<String> alreadyRated = ratingsSource.getRatings(user).keySet();
        return ratingsSource.getRatings(nearest).keySet().stream()
                .filter(item -> !alreadyRated.contains(item)).collect(toList());
    }

}
