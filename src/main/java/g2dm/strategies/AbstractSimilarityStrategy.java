package g2dm.strategies;

import g2dm.Dataset;
import g2dm.SimilarityStrategy;
import g2dm.UserScore;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static java.util.stream.Collectors.toList;

/**
 * Created by sp on 12/10/13.
 */
public abstract class AbstractSimilarityStrategy implements SimilarityStrategy {

    abstract BiFunction<Map<String, Double>, Map<String, Double>, Double> getSimilarityFunction();
    abstract Comparator<UserScore> getUserScoreComparator();

    @Override
    public List<String> getNearestUsers(String user, Dataset dataset) {
        return computeUserSimilarities(user, dataset).stream()
                .sorted(getUserScoreComparator())
                .map(UserScore::getUser)
                .collect(toList());
    }

    private List<UserScore> computeUserSimilarities(String user, Dataset dataset) {
        return dataset.getOtherUsers(user).stream()
                .map(other -> new UserScore(other, computeSimilarityScore(dataset.getRatings(user), dataset.getRatings(other))))
                .collect(toList());
    }

    private double computeSimilarityScore(Map<String, Double> ratings1, Map<String, Double> ratings2) {
        return getSimilarityFunction().apply(ratings1, ratings2);
    }

}
