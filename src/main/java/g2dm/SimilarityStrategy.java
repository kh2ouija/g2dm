package g2dm;

import g2dm.dto.ItemAndRating;

import java.util.List;

/**
 * @author sp
 */
public interface SimilarityStrategy {

    List<ItemAndRating> recommendItems(String user, Dataset dataset, int k);

}
