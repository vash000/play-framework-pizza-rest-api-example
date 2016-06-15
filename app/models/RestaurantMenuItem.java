package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class RestaurantMenuItem extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    public Long id;

    @NotNull
    @OneToMany
    public Product product;

    @NotNull @Embedded
    public Money price;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantMenuItem that = (RestaurantMenuItem) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RestaurantMenuItem{" +
                "id=" + id +
                ", product=" + product +
                ", price=" + price +
                '}';
    }
}