package models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product_pizza")
public final class Pizza extends Product {

    @NotNull
    @Size(min = 0)
    public Rank rank;

    //public List<Topping> topping; //Some kind of attributes

}
