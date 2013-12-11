package g2dm.dto;

/**
 * Created by sp on 12/10/13.
 */
public class ItemAndRating extends ImmutablePair<String, Double> {

    public ItemAndRating(String item, Double rating) {
        super(item, rating);
    }

    public String getItem() {
        return left;
    }

    public Double getRating() {
        return right;
    }

}
