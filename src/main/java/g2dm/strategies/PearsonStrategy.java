package g2dm.strategies;

import g2dm.RatingsOperations;
import g2dm.dto.RatingAndScore;
import g2dm.dto.UserAndScore;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author sp
 */
public class PearsonStrategy extends AbstractSimilarityStrategy {

    @Override
    public BiFunction<Map<String, Double>, Map<String, Double>, Double> getSimilarityFunction() {
        return RatingsOperations::pearsonCorrelation;
    }

    @Override
    public Comparator<UserAndScore> getUserScoreComparator() {
        return (u1, u2) -> Double.compare(u2.getScore(), u1.getScore());
    }

    @Override
    protected double computeNormalizedRating(List<RatingAndScore> ratingAndScores) {
        double totalDistance = ratingAndScores.stream().mapToDouble(RatingAndScore::getScore).sum();
        return ratingAndScores.stream().mapToDouble(ras -> ras.getRating() * (ras.getScore() / totalDistance)).sum();
    }

}
