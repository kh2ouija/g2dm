package fixtures;

import g2dm.Dataset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sp on 12/8/13.
 */
public class MusicSource implements Dataset {

    public static final String HAILEY = "Hailey";
    public static final String VERONICA = "Veronica";
    public static final String JORDYN = "Jordyn";
    public static final String ANGELICA = "Angelica";
    public static final String BILL = "Bill";
    private final Map<String,Map<String,Double>> map;

    public MusicSource() {
        map = new HashMap<>();
        Map<String, Double> angelica = new HashMap<>();
        angelica.put("Blues Traveler", 3.5);
        angelica.put("Broken Bells", 2.0);
        angelica.put("Norah Jones", 4.5);
        angelica.put("Phoenix", 5.0);
        angelica.put("Slightly Stoopid", 1.5);
        angelica.put("The Strokes", 2.5);
        angelica.put("Vampire Weekend", 2.0);
        map.put("Angelica", angelica);
        Map<String, Double> bill = new HashMap<>();
        bill.put("Blues Traveler", 2.0);
        bill.put("Broken Bells", 3.5);
        bill.put("Deadmau5", 4.0);
        bill.put("Phoenix", 2.0);
        bill.put("Slightly Stoopid", 3.5);
        bill.put("Vampire Weekend", 3.0);
        map.put("Bill", bill);
        Map<String, Double> chan = new HashMap<>();
        chan.put("Blues Traveler", 5.0);
        chan.put("Broken Bells", 1.0);
        chan.put("Deadmau5", 1.0);
        chan.put("Norah Jones", 3.0);
        chan.put("Phoenix", 5.0);
        chan.put("Slightly Stoopid", 1.0);
        map.put("Chan", chan);
        Map<String, Double> dan = new HashMap<>();
        dan.put("Blues Traveler", 3.0);
        dan.put("Broken Bells", 4.0);
        dan.put("Deadmau5", 4.5);
        dan.put("Phoenix", 3.0);
        dan.put("Slightly Stoopid", 4.5);
        dan.put("The Strokes", 4.0);
        dan.put("Vampire Weekend", 2.0);
        map.put("Dan", dan);
        Map<String, Double> hailey = new HashMap<>();
        hailey.put("Broken Bells", 4.0);
        hailey.put("Deadmau5", 1.0);
        hailey.put("Norah Jones", 4.0);
        hailey.put("The Strokes", 4.0);
        hailey.put("Vampire Weekend", 1.0);
        map.put("Hailey", hailey);
        Map<String, Double> jordyn = new HashMap<>();
        jordyn.put("Broken Bells", 4.5);
        jordyn.put("Deadmau5", 4.0);
        jordyn.put("Norah Jones", 5.0);
        jordyn.put("Phoenix", 5.0);
        jordyn.put("Slightly Stoopid", 4.5);
        jordyn.put("The Strokes", 4.0);
        jordyn.put("Vampire Weekend", 4.0);
        map.put("Jordyn", jordyn);
        Map<String, Double> sam = new HashMap<>();
        sam.put("Blues Traveler", 5.0);
        sam.put("Broken Bells", 2.0);
        sam.put("Norah Jones", 3.0);
        sam.put("Phoenix", 5.0);
        sam.put("Slightly Stoopid", 4.0);
        sam.put("The Strokes", 5.0);
        map.put("Sam", sam);
        Map<String, Double> veronica = new HashMap<>();
        veronica.put("Blues Traveler", 3.0);
        veronica.put("Norah Jones", 5.0);
        veronica.put("Phoenix", 4.0);
        veronica.put("Slightly Stoopid", 2.5);
        veronica.put("The Strokes", 3.0);
        map.put("Veronica", veronica);
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
