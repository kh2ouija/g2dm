package g2dm;

/**
 * Created by sp on 12/10/13.
 */
public class UserWithScore {

    private String user;
    private Double score;

    public UserWithScore(String user, Double score) {
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
