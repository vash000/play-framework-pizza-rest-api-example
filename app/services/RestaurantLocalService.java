package services;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import models.Restaurant;
import repositories.RestaurantRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * Service in shared machine space
 */
@Singleton
/* default */final class RestaurantLocalService implements RestaurantService {

    private final RestaurantRepository repository;
    private final ExecutorService exec = Executors.newWorkStealingPool(); //This will have to tuned

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1,
                    new ThreadFactoryBuilder()
                            .setDaemon(true)
                            .setNameFormat("delayThrow-%d")
                            .build());

    @Inject
    public RestaurantLocalService(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompletableFuture<List<Restaurant>> findAll() {
        final CompletableFuture<List<Restaurant>> promise = CompletableFuture.supplyAsync(repository::findAll, exec);
        return timeout(promise, Duration.ofSeconds(1));
    }

    @Override
    public Optional<Restaurant> findById(Integer id) {

        if(id == null) return Optional.empty();

        return repository.findById(id.longValue());
    }


    private static <T> CompletableFuture<T> timeout(CompletableFuture<T> promise, Duration after) {
        return promise.applyToEither(delayThrow(after), Function.<T>identity());
    }

    private static <T> CompletionStage<T> delayThrow(Duration period) {
        final CompletableFuture<T> promise = new CompletableFuture<>();
        scheduler.schedule( () ->
           promise.completeExceptionally(new TimeoutException("Timeout after: " + period)) //return
          ,period.toMillis() //after
          ,TimeUnit.MILLISECONDS //unit
        );
        return promise;
    }
}
