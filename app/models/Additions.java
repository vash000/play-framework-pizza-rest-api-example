package models;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("ADDITION")
@Table(name = "product_addition")
public final class Additions extends Product {

}
