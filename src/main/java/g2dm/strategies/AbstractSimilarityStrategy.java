package g2dm.strategies;

import g2dm.Dataset;
import g2dm.SimilarityStrategy;
import g2dm.dto.UserAndScore;
import g2dm.dto.UserAndWeight;

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
    abstract Comparator<UserAndScore> getUserScoreComparator();

    @Override
    public final List<UserAndWeight> getKNearestPercentileWeightedUsers(String user, Dataset dataset, int k) {
        List<UserAndScore> usersWithScores = computeAllUsersScores(user, dataset).stream()
                .sorted(getUserScoreComparator())
                .limit(k)
                .collect(toList());
        return computePie(usersWithScores);
    }

    protected List<UserAndWeight> computePie(List<UserAndScore> usersWithScores) {
        double equalShare = 1.0 / usersWithScores.size();
        return usersWithScores.stream()
                .map(uws -> new UserAndWeight(uws.getUser(), equalShare))
                .collect(toList());
    }

    private List<UserAndScore> computeAllUsersScores(String user, Dataset dataset) {
        return dataset.getOtherUsers(user).stream()
                .map(other -> new UserAndScore(other, computeSimilarityScore(user, other, dataset)))
                .collect(toList());
    }

    private double computeSimilarityScore(String user, String other, Dataset dataset) {
        return getSimilarityFunction().apply(dataset.getRatings(user), dataset.getRatings(other));
    }

}
