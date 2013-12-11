package g2dm.dto;

/**
 * Created by sp on 12/10/13.
 */
public class UserAndScore extends ImmutablePair<String, Double> {

    public UserAndScore(String user, Double score) {
        super(user, score);
    }

    public String getUser() {
        return left;
    }

    public Double getScore() {
        return right;
    }

}