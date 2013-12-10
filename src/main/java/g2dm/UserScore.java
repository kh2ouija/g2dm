package g2dm;

/**
 * Created by sp on 12/10/13.
 */
public class UserScore {

    private String user;
    private Double score;

    public UserScore(String user, Double score) {
        this.user = user;
        this.score = score;
    }

    public String getUser() {
        return user;
    }

    public Double getScore() {
        return score;
    }
}
