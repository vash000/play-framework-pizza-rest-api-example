package controllers;

import controllers.actions.VersionedAction;
import models.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;
import play.mvc.With;
import services.RestaurantService;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

/**
 * Manage a database of restaurants
 */
@With(VersionedAction.class)
public final class RestaurantController extends Controller {

    private final static Logger log = LoggerFactory.getLogger(RestaurantController.class);
    private final RestaurantService service;

    @Inject
    public RestaurantController(RestaurantService service) {
        this.service = service;
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
        final Optional<Restaurant> restaurant = service.findById(id);

        final Result result;
        if(restaurant.isPresent()) {
            result = okJson(restaurant.get());
            log.info("Response ok id: " + String.valueOf(id));
        } else {
            result = notFound(); //No body
            log.info("Response notfound " + String.valueOf(id));
        }
        return result;
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
