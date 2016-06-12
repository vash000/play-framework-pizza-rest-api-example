package models;

import com.avaje.ebean.Model;
import com.avaje.ebean.PagedList;
import play.api.libs.json.Writes;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Represent one to one database mapping
 */
@Entity
public class Restaurant extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String address1;

    @Constraints.Required
    public String address2;

    @Constraints.Required
    public BigDecimal latitude;

    @Constraints.Required
    public BigDecimal longitude;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "  id=" + id +
                ", name='" + name + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
