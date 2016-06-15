package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Product {

    @Id
    public Long id;

    @NotNull
    @Size(max = 255)
    public String name;

    @NotNull @Embedded
    public Category category;


}
