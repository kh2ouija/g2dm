package g2dm;

import java.util.Comparator;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author sp
 */
public interface SimilarityStrategy {

    BiFunction<Map<String, Double>, Map<String, Double>, Double> getSimilarityFunction();
    Comparator<Double> getDistanceComparator();

}
