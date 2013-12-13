package fixtures;

import g2dm.Dataset;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Stefan on 12/13/13.
 */
public class PieSource implements Dataset {

    private final Map<String,Map<String,Double>> map;

    public PieSource() {
        map = new HashMap<>();
        Map<String, Double> amanda = new HashMap<>();
        amanda.put("Grey Wardens", 4.5);
        map.put("Amanda", amanda);
        Map<String, Double> eric = new HashMap<>();
        eric.put("Grey Wardens", 5.0);
        map.put("Eric", eric);
        Map<String, Double> sally = new HashMap<>();
        sally.put("Grey Wardens", 3.5);
        map.put("Sally", sally);
    }

    @Override
    public Map<String, Double> getRatings(String user) {
        return map.get(user);
    }

    @Override
    public Set<String> getUsers() {
        return map.keySet();
    }

}
