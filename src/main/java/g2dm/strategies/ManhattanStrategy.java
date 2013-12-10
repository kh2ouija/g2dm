package g2dm.strategies;

import g2dm.RatingsOperations;
import g2dm.UserScore;

import java.util.Comparator;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author sp
 */
public class ManhattanStrategy extends AbstractSimilarityStrategy {

    @Override
    public BiFunction<Map<String, Double>, Map<String, Double>, Double> getSimilarityFunction() {
        return RatingsOperations::manhattanDistance;
    }

    @Override
    public Comparator<UserScore> getUserScoreComparator() {
        return (u1, u2) -> Double.compare(u1.getScore(), u2.getScore());
    }

}
