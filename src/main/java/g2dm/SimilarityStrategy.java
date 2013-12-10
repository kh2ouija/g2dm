package g2dm;

import java.util.List;

/**
 * @author sp
 */
public interface SimilarityStrategy {

    public List<String> getNearestUsers(String user, Dataset dataset);

}
