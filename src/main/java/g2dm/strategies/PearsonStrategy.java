package g2dm.strategies;

import g2dm.RatingsOperations;
import g2dm.SimilarityStrategy;

import java.util.Comparator;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author sp
 */
public class PearsonStrategy implements SimilarityStrategy {

    @Override
    public BiFunction<Map<String, Double>, Map<String, Double>, Double> getSimilarityFunction() {
        return RatingsOperations::pearsonCorrelation;
    }

    @Override
    public Comparator<Double> getDistanceComparator() {
        return (a,b) -> Double.compare(b,a);
    }

    @Override
    public double computeSimilarity(Map<String, Double> ratings1, Map<String, Double> ratings2) {
        return getSimilarityFunction().apply(ratings1, ratings2);
    }
}
