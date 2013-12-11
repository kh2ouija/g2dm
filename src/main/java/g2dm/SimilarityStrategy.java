package g2dm;

import java.util.List;

/**
 * @author sp
 */
public interface SimilarityStrategy {

    public List<UserWithWeight> getKNearestPercentileWeightedUsers(String user, Dataset dataset, int k);

}
