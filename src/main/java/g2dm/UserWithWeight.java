package g2dm;

/**
 * Created by sp on 12/10/13.
 */
public class UserWithWeight {

    private String user;
    private Double weight;

    public UserWithWeight(String user, Double weight) {
        this.user = user;
        this.weight = weight;
    }

    public String getUser() {
        return user;
    }

    public Double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserWithWeight that = (UserWithWeight) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }
}
