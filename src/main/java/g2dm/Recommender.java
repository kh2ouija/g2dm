package g2dm;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * Created by sp on 12/8/13.
 */
public class Recommender {

    private Dataset dataset;
    private SimilarityStrategy similarityStrategy;

    public Recommender(Dataset dataset, SimilarityStrategy similarityStrategy) {
        this.dataset = dataset;
        this.similarityStrategy = similarityStrategy;
    }

    public List<String> recommendItems(String user) {
        String nearest = similarityStrategy.getNearestUsers(user, dataset).get(0);
        Set<String> alreadyRated = dataset.getRatings(user).keySet();
        List<String> result = new ArrayList<>(dataset.getRatings(nearest).keySet());
        result.removeAll(alreadyRated);
        return result;
    }

}
