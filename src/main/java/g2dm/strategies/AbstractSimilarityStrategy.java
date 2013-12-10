package g2dm.strategies;

import g2dm.Dataset;
import g2dm.SimilarityStrategy;
import g2dm.UserScore;

import java.util.ArrayList;
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
        List<UserScore> userScores = computeUserSimilarities(user, dataset);
        List<String> result = userScores.stream()
                .sorted(getUserScoreComparator())
                .map(UserScore::getUser)
                .collect(toList());
        return result;
    }

    private List<UserScore> computeUserSimilarities(String user, Dataset dataset) {
        List<UserScore> result = dataset.getOtherUsers(user).stream().reduce(new ArrayList<>(),
                (list, other) -> {
                    UserScore us = new UserScore(other, computeSimilarityScore(dataset.getRatings(user), dataset.getRatings(other)));
                    list.add(us);
                    return list;
                },
                (list1, list2) -> {
                    list1.addAll(list2);
                    return list1;
                });
        return result;
    }

    private double computeSimilarityScore(Map<String, Double> ratings1, Map<String, Double> ratings2) {
        return getSimilarityFunction().apply(ratings1, ratings2);
    }

}
