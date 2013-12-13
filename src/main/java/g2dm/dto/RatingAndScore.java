package g2dm.dto;

/**
 * Created by sp on 12/10/13.
 */
public class RatingAndScore extends ImmutablePair<Double, Double> {

    public RatingAndScore(Double rating, Double score) {
        super(rating, score);
    }

    public Double getRating() {
        return left;
    }

    public Double getScore() {
        return right;
    }

}