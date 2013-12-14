package g2dm;

import g2dm.dto.ItemAndRating;

import java.util.List;

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
        return similarityStrategy.recommendItems(user, dataset, k);
    }

}
