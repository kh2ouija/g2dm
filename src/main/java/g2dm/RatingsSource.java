package g2dm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sp on 12/8/13.
 */
public interface RatingsSource {

    Map<String, Double> getRatings(String user);

    List<String> getUsers();

    default List<String> getOtherUsers(String user) {
        List<String> others = new ArrayList<>(getUsers());
        others.remove(user);
        return others;
    }
}
