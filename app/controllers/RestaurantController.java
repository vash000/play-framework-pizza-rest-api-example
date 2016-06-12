package controllers;

import controllers.actions.VersionedAction;
import models.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.cache.CacheApi;
import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;
import play.mvc.With;
import services.RestaurantService;

import javax.inject.Inject;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

/**
 * Manage a database of restaurants
 */
@With(VersionedAction.class)
public final class RestaurantController extends Controller {

    private final static Logger log = LoggerFactory.getLogger(RestaurantController.class);
    private final RestaurantService service;
    private final CacheApi cache;
    @Inject
    public RestaurantController(RestaurantService service, CacheApi cache) {
        this.service = service;
        this.cache = cache;
    }

    public CompletionStage<Result> list() {
        log.info("Requested list");
        return promiseJson(service.findAll()); //Play says that is ok to return this, wonder what wil return
    }

    /**
     * @param id Id of the restaurant to edit
     */
    public Result getById(Integer id) {
        log.info("Requested by id: " + String.valueOf(id));

        final Optional<Restaurant> restaurant = fetchById(id);

        final Result result;
        if(restaurant.isPresent()) {
            final Restaurant fresh = restaurant.get();
            cache.set("restaurant."+ fresh.id,fresh, 5);
            result = okJson(fresh);
            log.info("Response ok id: " + String.valueOf(id));
        } else {
            result = notFound(); //No body
            log.info("Response notfound " + String.valueOf(id));
        }
        return result;
    }

    private Optional<Restaurant> fetchById(Integer id) {

        final Optional<Restaurant> opt = Optional.ofNullable(cache.get("restaurant." + String.valueOf(id)));

        if(opt.isPresent()) {
            return opt;
        } else {
            return service.findById(id);
        }
    }

    private static Result okJson(Object o) {
        return ok(Json.toJson(o));
    }
    private static <T> CompletionStage<Result> promiseJson(CompletionStage<T> t) {
        return t.thenApply(RestaurantController::okJson)
                .exceptionally(ex-> {
                    log.error("Unrecoverable error",ex);
                    return null;
                });
    }
}
