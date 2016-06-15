package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("DRINK")
@Table(name = "product_drink")
public final class Drink extends Product {
}
