package chapter2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sp on 12/8/13.
 */
public class MusicFixtures implements RatingsDataSource {

    private final Map<String,Map<String,Double>> map;

    public MusicFixtures() {
        map = new HashMap<>();
        Map<String, Double> angelica = new HashMap<>();
        angelica.put("Blues Traveler", Double.valueOf(3.5));
        angelica.put("Broken Bells", Double.valueOf(2.0));
        angelica.put("Norah Jones", Double.valueOf(4.5));
        angelica.put("Phoenix", Double.valueOf(5.0));
        angelica.put("Slightly Stoopid", Double.valueOf(1.5));
        angelica.put("The Strokes", Double.valueOf(2.5));
        angelica.put("Vampire Weekend", Double.valueOf(2.0));
        map.put("Angelica", angelica);
        Map<String, Double> bill = new HashMap<>();
        bill.put("Blues Traveler", Double.valueOf(2.0));
        bill.put("Broken Bells", Double.valueOf(3.5));
        bill.put("Deadmau5", Double.valueOf(4.0));
        bill.put("Phoenix", Double.valueOf(2.0));
        bill.put("Slightly Stoopid", Double.valueOf(3.5));
        bill.put("Vampire Weekend", Double.valueOf(3.0));
        map.put("Bill", bill);
        Map<String, Double> chan = new HashMap<>();
        chan.put("Blues Traveler", Double.valueOf(5.0));
        chan.put("Broken Bells", Double.valueOf(1.0));
        chan.put("Deadmau5", Double.valueOf(1.0));
        chan.put("Norah Jones", Double.valueOf(3.0));
        chan.put("Phoenix", Double.valueOf(5));
        chan.put("Slightly Stoopid", Double.valueOf(1.0));
        map.put("Chan", chan);
        Map<String, Double> dan = new HashMap<>();
        dan.put("Blues Traveler", Double.valueOf(3.0));
        dan.put("Broken Bells", Double.valueOf(4.0));
        dan.put("Deadmau5", Double.valueOf(4.5));
        dan.put("Phoenix", Double.valueOf(3.0));
        dan.put("Slightly Stoopid", Double.valueOf(4.5));
        dan.put("The Strokes", Double.valueOf(4.0));
        dan.put("Vampire Weekend", Double.valueOf(2.0));
        map.put("Dan", dan);
        Map<String, Double> hailey = new HashMap<>();
        hailey.put("Broken Bells", Double.valueOf(4.0));
        hailey.put("Deadmau5", Double.valueOf(1.0));
        hailey.put("Norah Jones", Double.valueOf(4.0));
        hailey.put("The Strokes", Double.valueOf(4.0));
        hailey.put("Vampire Weekend", Double.valueOf(1.0));
        map.put("Hailey", hailey);
        Map<String, Double> jordyn = new HashMap<>();
        jordyn.put("Broken Bells", Double.valueOf(4.5));
        jordyn.put("Deadmau5", Double.valueOf(4.0));
        jordyn.put("Norah Jones", Double.valueOf(5.0));
        jordyn.put("Phoenix", Double.valueOf(5.0));
        jordyn.put("Slightly Stoopid", Double.valueOf(4.5));
        jordyn.put("The Strokes", Double.valueOf(4.0));
        jordyn.put("Vampire Weekend", Double.valueOf(4.0));
        map.put("Jordyn", jordyn);
        Map<String, Double> sam = new HashMap<>();
        sam.put("Blues Traveler", Double.valueOf(5.0));
        sam.put("Broken Bells", Double.valueOf(2.0));
        sam.put("Norah Jones", Double.valueOf(3.0));
        sam.put("Phoenix", Double.valueOf(5.0));
        sam.put("Slightly Stoopid", Double.valueOf(4.0));
        sam.put("The Strokes", Double.valueOf(5.0));
        map.put("Sam", sam);
        Map<String, Double> veronica = new HashMap<>();
        veronica.put("Blues Traveler", Double.valueOf(3.0));
        veronica.put("Norah Jones", Double.valueOf(5.0));
        veronica.put("Phoenix", Double.valueOf(4.0));
        veronica.put("Slightly Stoopid", Double.valueOf(2.5));
        veronica.put("The Strokes", Double.valueOf(3.0));
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
