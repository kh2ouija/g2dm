package g2dm.strategies;

import g2dm.Dataset;
import g2dm.SimilarityStrategy;
import g2dm.dto.ItemAndRating;
import g2dm.dto.RatingAndScore;
import g2dm.dto.UserAndScore;
import g2dm.dto.UserAndWeight;

import java.util.*;
import java.util.function.BiFunction;

import static java.util.stream.Collectors.toList;

/**
 * Created by sp on 12/10/13.
 */
public abstract class AbstractSimilarityStrategy implements SimilarityStrategy {

    abstract BiFunction<Map<String, Double>, Map<String, Double>, Double> getSimilarityFunction();
    abstract Comparator<UserAndScore> getUserScoreComparator();

    @Override
    public List<ItemAndRating> recommendItems(String user, Dataset dataset, int k) {
        List<UserAndScore> scoredUsers = computeKNearestPercentileWeightedUsers(user, dataset, k);
        return computeNormalizedRecommendations(user, scoredUsers, dataset);
    }

    protected List<UserAndScore> computeKNearestPercentileWeightedUsers(String user, Dataset dataset, int k) {
        List<UserAndScore> kNearestUsersWithScores = computeAllUsersScores(user, dataset).stream()
                .sorted(getUserScoreComparator())
                .limit(k)
                .collect(toList());
        return kNearestUsersWithScores;
    }

    private List<UserAndScore> computeAllUsersScores(String user, Dataset dataset) {
        return dataset.getOtherUsers(user).stream()
                .map(other -> new UserAndScore(other, computeSimilarityScore(user, other, dataset)))
                .collect(toList());
    }

    private double computeSimilarityScore(String user, String other, Dataset dataset) {
        return getSimilarityFunction().apply(dataset.getRatings(user), dataset.getRatings(other));
    }

    protected List<ItemAndRating> computeNormalizedRecommendations(String user, List<UserAndScore> scoredUsers, Dataset dataset) {
        Map<String, List<RatingAndScore>> collectedRatingsWithScore = collectUnnormalizedScoredRatings(scoredUsers, user, dataset);
        return collectedRatingsWithScore.entrySet().stream()
                .map(e -> new ItemAndRating(e.getKey(), computeNormalizedRating(e.getValue())))
                .sorted((ir1, ir2) -> -Double.compare(ir1.getRating(), ir2.getRating()))
                .collect(toList());
    }

    private Map<String, List<RatingAndScore>> collectUnnormalizedScoredRatings(List<UserAndScore> scoredUsers, String user, Dataset dataset) {
        Map<String, Double> userRatings = dataset.getRatings(user);
        Map<String, List<RatingAndScore>> collectedRatingsWithScore = new HashMap<>();
        scoredUsers.stream().forEach(uas ->
                dataset.getRatings(uas.getUser()).forEach((item, rating) -> {
                    if (!userRatings.keySet().contains(item)) {
                        List<RatingAndScore> scoredRatings = collectedRatingsWithScore.getOrDefault(item, new ArrayList<>());
                        scoredRatings.add(new RatingAndScore(rating, uas.getScore()));
                        collectedRatingsWithScore.put(item, scoredRatings);
                    }
                }));
        return collectedRatingsWithScore;
    }

    protected double computeNormalizedRating(List<RatingAndScore> ratingsAndScores) {
        return ratingsAndScores.stream().mapToDouble(RatingAndScore::getRating).sum() / ratingsAndScores.size();
    }
}
