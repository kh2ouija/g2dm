package g2dm;

import g2dm.dto.ItemAndRating;
import g2dm.dto.RatingAndScore;
import g2dm.dto.UserAndScore;
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
        return similarityStrategy.recommendItems(user, dataset, k);
    }

}
