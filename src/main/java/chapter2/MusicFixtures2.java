package chapter2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sp on 12/8/13.
 */
public class MusicFixtures2 implements RatingsDataSource {

    private final Map<String,Map<String,Double>> map;

    public MusicFixtures2() {
        map = new HashMap<>();
        Map<String, Double> clara = new HashMap<>();
        clara.put("Blues Traveler", Double.valueOf(4.75));
        clara.put("Norah Jones", Double.valueOf(4.5));
        clara.put("Phoenix", Double.valueOf(5.0));
        clara.put("The Strokes", Double.valueOf(4.25));
        clara.put("Weird Al", Double.valueOf(4.0));
        map.put("Clara", clara);
        Map<String, Double> robert = new HashMap<>();
        robert.put("Blues Traveler", Double.valueOf(4));
        robert.put("Norah Jones", Double.valueOf(3.0));
        robert.put("Phoenix", Double.valueOf(5.0));
        robert.put("The Strokes", Double.valueOf(2.0));
        robert.put("Weird Al", Double.valueOf(1.0));
        map.put("Robert", robert);
    }

    @Override
    public Map<String, Double> getRatings(String user) {
        return map.get(user);
    }

    @Override
    public List<String> getUsers() {
        return new ArrayList<>(map.keySet());
    }
}
