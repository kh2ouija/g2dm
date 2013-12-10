package g2dm;

import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author sp
 */
@FunctionalInterface
public interface SimilarityFunction extends BiFunction<Map<String, Double>, Map<String, Double>, Double> {}
