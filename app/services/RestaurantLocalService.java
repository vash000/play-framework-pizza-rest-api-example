package services;

import models.Restaurant;
import repositories.RestaurantRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Service in shared machine space
 */
@Singleton
/* default */final class RestaurantLocalService implements RestaurantService {

    private final RestaurantRepository repository;

    @Inject
    public RestaurantLocalService(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Restaurant> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Restaurant> findById(Integer id) {

        if(id == null) return Optional.empty();

        return repository.findById(id.longValue());
    }
}
