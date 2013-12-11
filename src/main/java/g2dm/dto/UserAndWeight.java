package g2dm.dto;

/**
 * Created by sp on 12/10/13.
 */
public class UserAndWeight extends ImmutablePair<String, Double> {

    public UserAndWeight(String user, Double weight) {
        super(user, weight);
    }

    public String getUser() {
        return left;
    }

    public Double getWeight() {
        return right;
    }

}