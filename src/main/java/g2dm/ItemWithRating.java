package g2dm;

/**
 * Created by sp on 12/10/13.
 */
public class ItemWithRating {

    private String item;
    private Double rating;

    public ItemWithRating(String item, Double rating) {
        this.item = item;
        this.rating = rating;
    }

    public String getItem() {
        return item;
    }

    public Double getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemWithRating that = (ItemWithRating) o;

        if (item != null ? !item.equals(that.item) : that.item != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = item != null ? item.hashCode() : 0;
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ItemWithRating{" +
                "item='" + item + '\'' +
                ", rating=" + rating +
                '}';
    }
}
