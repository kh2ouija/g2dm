package g2dm;

import g2dm.dto.UserAndWeight;

import java.util.List;

/**
 * @author sp
 */
public interface SimilarityStrategy {

    public List<UserAndWeight> getKNearestPercentileWeightedUsers(String user, Dataset dataset, int k);

}
