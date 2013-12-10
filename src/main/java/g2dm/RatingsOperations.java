package g2dm;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * @author sp
 */
public class RatingsOperations {

    public static Set<String> commonItems(Map<String, Double> ratings1, Map<String, Double> ratings2) {
        return ratings1.keySet().stream().filter(ratings2::containsKey).collect(toSet());
    }

    public static double dotProduct(Map<String, Double> ratings1, Map<String, Double> ratings2) {
        return commonItems(ratings1, ratings2).stream()
                .reduce(0.0, (acc, item) -> acc + ratings1.get(item) * ratings2.get(item), Double::sum);
    }

    public static double vectorLength(Map<String, Double> ratings) {
        return Math.sqrt(ratings.values().stream().mapToDouble(r -> r * r).sum());
    }

    static double minkowski(Map<String, Double> ratings1, Map<String, Double> ratings2, int r) {
        Double sum = commonItems(ratings1, ratings2).stream()
                .reduce(0.0, (acc, item) -> acc + Math.pow(Math.abs(ratings1.get(item) - ratings2.get(item)), r), Double::sum);
        return Math.pow(sum, 1.0/r);
    }

    public static double pearsonCorrelation(Map<String, Double> ratings1, Map<String, Double> ratings2) {
        double sum_xy = 0;
        double sum_x = 0;
        double sum_y = 0;
        double sum_x2 = 0;
        double sum_y2 = 0;
        Set<String> commonKeys = commonItems(ratings1, ratings2);
        int n = commonKeys.size();
        for (String item : commonKeys) {
            double x = ratings1.get(item);
            double y = ratings2.get(item);
            sum_xy += x * y;
            sum_x += x;
            sum_y += y;
            sum_x2 += x * x;
            sum_y2 += y * y;
        }
        double up = sum_xy - (sum_x * sum_y) / n;
        double down = Math.sqrt(sum_x2 - Math.pow(sum_x, 2) / n)
                * Math.sqrt(sum_y2 - Math.pow(sum_y, 2) / n);
        if (down == 0) {
            return 0;
        } else {
            return up / down;
        }
    }

    public static double cosineSimilarity(Map<String, Double> ratings1, Map<String, Double> ratings2) {
        return dotProduct(ratings1, ratings2) / (vectorLength(ratings1) * vectorLength(ratings2));
    }

    public static double manhattanDistance(Map<String, Double> ratings1, Map<String, Double> ratings2) {
        return minkowski(ratings1, ratings2, 1);
    }

    public static double euclideanDistance(Map<String, Double> ratings1, Map<String, Double> ratings2) {
        return minkowski(ratings1, ratings2, 2);
    }
}
