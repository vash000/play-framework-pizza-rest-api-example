package models;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Category {

    @NotNull @Size(max = 255)
    public String category;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category1 = (Category) o;

        return !(category != null ? !category.equals(category1.category) : category1.category != null);

    }

    @Override
    public int hashCode() {
        return category != null ? category.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "category='" + category + '\'';
    }
}
