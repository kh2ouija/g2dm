package g2dm;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * @author sp
 */
public class RatingsOperations {

    public static Set<String> commonItems(Map<String, Double> ratings1, Map<String, Double> ratings2) {
        return ratings1.keySet().stream().filter(s -> ratings2.containsKey(s)).collect(toSet());
    }

    public static double dotProduct(Map<String, Double> ratings1, Map<String, Double> ratings2) {
        return commonItems(ratings1, ratings2).stream()
                .reduce(0.0, (acc, item) -> acc + ratings1.get(item) * ratings2.get(item), Double::sum);
    }

    public static double vectorLength(Map<String, Double> ratings) {
        return Math.sqrt(ratings.values().stream().mapToDouble(r -> r * r).sum());
    }
}
