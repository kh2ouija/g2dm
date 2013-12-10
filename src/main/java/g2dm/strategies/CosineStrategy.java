package g2dm.strategies;

import g2dm.RatingsOperations;
import g2dm.SimilarityStrategy;
import g2dm.UserScore;

import java.util.Comparator;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author sp
 */
public class CosineStrategy extends AbstractSimilarityStrategy {

    @Override
    public BiFunction<Map<String, Double>, Map<String, Double>, Double> getSimilarityFunction() {
        return RatingsOperations::cosineSimilarity;
    }

    @Override
    public Comparator<UserScore> getUserScoreComparator() {
        return (u1, u2) -> Double.compare(u2.getScore(), u1.getScore());
    }

}
