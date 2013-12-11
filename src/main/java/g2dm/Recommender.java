package g2dm;

import g2dm.dto.ItemAndRating;
import g2dm.dto.UserAndWeight;

import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * Created by sp on 12/8/13.
 */
public class Recommender {

    private int k;
    private Dataset dataset;
    private SimilarityStrategy similarityStrategy;

    public Recommender(Dataset dataset, SimilarityStrategy similarityStrategy, int k) {
        this.dataset = dataset;
        this.similarityStrategy = similarityStrategy;
        this.k = k;
    }

    public List<ItemAndRating> recommendItems(String user) {
        List<UserAndWeight> pie = similarityStrategy.getKNearestPercentileWeightedUsers(user, dataset, k);
        if (pie.isEmpty()) {
            return Collections.emptyList();
        }
        else {
            UserAndWeight nearest = pie.get(0);
            Set<String> alreadyRated = dataset.getRatings(user).keySet();
            return dataset.getRatings(nearest.getUser()).entrySet().stream()
                    .filter(e -> ! alreadyRated.contains(e.getKey()))
                    .map(e -> new ItemAndRating(e.getKey(), e.getValue()))
                    .collect(toList());
        }
    }

}
