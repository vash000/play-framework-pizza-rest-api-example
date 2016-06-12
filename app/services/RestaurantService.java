package services;

import com.google.inject.ImplementedBy;
import models.Restaurant;

import java.util.List;
import java.util.Optional;

@ImplementedBy(RestaurantLocalService.class)
public interface RestaurantService {
    List<Restaurant> findAll();

    Optional<Restaurant> findById(Integer id);
}
