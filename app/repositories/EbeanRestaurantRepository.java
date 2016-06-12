package repositories;

import com.avaje.ebean.Model;
import models.Restaurant;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
/* default */ final class EbeanRestaurantRepository implements RestaurantRepository {

    /**
     * Generic query helper for entity Computer with id Long
     */
    public static Model.Find<Long,Restaurant> find = new Model.Find<Long,Restaurant>(){};


    @Override
    public List<Restaurant> findAll() {
        return find.all();
    }

    @Override
    public Optional<Restaurant> findById(Long id) {

        if(id == null) return Optional.empty();

        return Optional.ofNullable(find.byId(id));
    }
}
