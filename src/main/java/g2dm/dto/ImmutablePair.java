package g2dm.dto;

/**
 * Created by sp on 12/12/13.
 */
public class ImmutablePair<U, V> {

    U left;
    V right;

    public ImmutablePair(U left, V right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImmutablePair that = (ImmutablePair) o;

        if (left != null ? !left.equals(that.left) : that.left != null) return false;
        if (right != null ? !right.equals(that.right) : that.right != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ImmutablePair{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
