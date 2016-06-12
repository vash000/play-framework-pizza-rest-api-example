package services;

import com.google.inject.ImplementedBy;
import models.Restaurant;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

@ImplementedBy(RestaurantLocalService.class)
public interface RestaurantService {

    CompletionStage<List<Restaurant>> findAll();

    Optional<Restaurant> findById(Integer id);
}
