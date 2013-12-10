package g2dm.strategies;

import g2dm.RatingsOperations;
import g2dm.SimilarityStrategy;

import java.util.Comparator;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author sp
 */
public class CosineStrategy implements SimilarityStrategy {

    @Override
    public BiFunction<Map<String, Double>, Map<String, Double>, Double> getSimilarityFunction() {
        return RatingsOperations::cosineSimilarity;
    }

    @Override
    public Comparator<Double> getDistanceComparator() {
        return (a,b) -> Double.compare(b,a);
    }
}
