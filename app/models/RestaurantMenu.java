package models;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Stream;


public class RestaurantMenu implements Iterable<RestaurantMenuItem> {

    public Restaurant restaurant;

    private Collection<RestaurantMenuItem> items;

    public int size() {
        return items.size();
    }

    public Stream<RestaurantMenuItem> stream() {
        return items.stream();
    }

    public Collection<RestaurantMenuItem> items() {
        return Collections.unmodifiableCollection(items);
    }

    @Override
    public Iterator<RestaurantMenuItem> iterator() {
        return items.iterator();
    }
}
