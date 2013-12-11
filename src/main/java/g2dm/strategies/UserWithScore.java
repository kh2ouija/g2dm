package g2dm.strategies;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserWithScore that = (UserWithScore) o;

        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }
}
