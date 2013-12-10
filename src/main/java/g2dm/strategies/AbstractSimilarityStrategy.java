package g2dm.strategies;

import g2dm.SimilarityStrategy;

import java.util.Comparator;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author sp
 */
public class AbstractSimilarityStrategy implements SimilarityStrategy {

    @Override
    public BiFunction<Map<String, Double>, Map<String, Double>, Double> getSimilarityFunction() {
        return null;
    }

    @Override
    public Comparator<Double> getDistanceComparator() {
        return null;
    }

    @Override
    public double computeSimilarity(Map<String, Double> ratings1, Map<String, Double> ratings2) {
        return getSimilarityFunction().apply(ratings1, ratings2);
    }
}
