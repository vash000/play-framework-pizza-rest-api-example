package repositories;

import com.google.inject.ImplementedBy;
import models.Restaurant;

import java.util.List;
import java.util.Optional;

@ImplementedBy(EbeanRestaurantRepository.class)
public interface RestaurantRepository {

    List<Restaurant> findAll();

    Optional<Restaurant> findById(Long id);
}
