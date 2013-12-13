package fixtures;

import g2dm.Dataset;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by sp on 12/10/13.
 */
public class BXDumpDataset implements Dataset {

    private static final String USERS_FILE = "BX-Users.csv";
    private static final String BOOKS_FILE = "BX-Books.csv";
    private static final String RATINGS_FILE = "BX-Book-Ratings.csv";
    private Set<String> users;

    void load(String path) throws IOException {
        users = new HashSet<>();
        Files.lines(Paths.get(path, USERS_FILE)).forEach(line -> {
            users.add(line.split(";")[0].split("\"")[1]);
        });
    }

    @Override
    public Map<String, Double> getRatings(String user) {
        return null;
    }

    @Override
    public Set<String> getUsers() {
        return users;
    }

    public static void main(String[] args) throws IOException {
        BXDumpDataset dataset = new BXDumpDataset();
        dataset.load("/home/sp/Documents/BX-Dump");
    }

}
