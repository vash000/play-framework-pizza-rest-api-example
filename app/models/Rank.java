package models;


import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Rank {

    @Size(min = 0)
    public Integer rank;

    @Override
    public String toString() {
        return "rank='" + rank + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rank rank1 = (Rank) o;

        return !(rank != null ? !rank.equals(rank1.rank) : rank1.rank != null);

    }

    @Override
    public int hashCode() {
        return rank != null ? rank.hashCode() : 0;
    }
}
