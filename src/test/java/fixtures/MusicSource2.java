package fixtures;

import g2dm.Dataset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sp on 12/8/13.
 */
public class MusicSource2 implements Dataset {

    private final Map<String,Map<String,Double>> map;

    public MusicSource2() {
        map = new HashMap<>();
        Map<String, Double> clara = new HashMap<>();
        clara.put("Blues Traveler", 4.75);
        clara.put("Norah Jones", 4.5);
        clara.put("Phoenix", 5.0);
        clara.put("The Strokes", 4.25);
        clara.put("Weird Al", 4.0);
        map.put("Clara", clara);
        Map<String, Double> robert = new HashMap<>();
        robert.put("Blues Traveler", (double) 4);
        robert.put("Norah Jones", 3.0);
        robert.put("Phoenix", 5.0);
        robert.put("The Strokes", 2.0);
        robert.put("Weird Al", 1.0);
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
