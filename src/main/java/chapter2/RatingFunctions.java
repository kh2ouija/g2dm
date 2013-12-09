package chapter2;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RatingFunctions {

    double manhattan(Map<String, Double> ratings1, Map<String, Double> ratings2) {
        Set<String> commonKeys = commonKeys(ratings1, ratings2);
        double sum = 0;
        for (String key : commonKeys) {
            sum += Math.abs(ratings1.get(key) - ratings2.get(key));
        }
        return sum;
    }

    double euclidean(Map<String, Double> ratings1, Map<String, Double> ratings2) {
        Set<String> commonKeys = commonKeys(ratings1, ratings2);
        double sum = 0;
        for (String key : commonKeys) {
            sum += Math.pow(ratings1.get(key) - ratings2.get(key), 2);
        }
        return Math.sqrt(sum);
    }

    double minkowski(Map<String, Double> ratings1, Map<String, Double> ratings2, int r) {
        Set<String> commonKeys = commonKeys(ratings1, ratings2);
        double sum = 0;
        for (String key : commonKeys) {
            sum += Math.pow(Math.abs(ratings1.get(key) - ratings2.get(key)), r);
        }
        return Math.pow(sum, 1.0/r);
    }

    Set<String> commonKeys(Map<String, Double> ratings1, Map<String, Double> ratings2) {
        Set<String> commonKeys = new HashSet<>(ratings1.keySet());
        commonKeys.retainAll(ratings2.keySet());
        return commonKeys;
    }

    public double pearson(Map<String, Double> ratings1, Map<String, Double> ratings2) {
        double sum_xy = 0;
        double sum_x = 0;
        double sum_y = 0;
        double sum_x2 = 0;
        double sum_y2 = 0;
        Set<String> commonKeys = commonKeys(ratings1, ratings2);
        int n = commonKeys.size();
        for (String movie : commonKeys) {
            double x = ratings1.get(movie);
            double y = ratings2.get(movie);
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
}