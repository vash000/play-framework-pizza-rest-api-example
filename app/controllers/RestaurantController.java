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

/**
 * Manage a database of restaurants
 */
@With(VersionedAction.class)
public final class RestaurantController extends Controller {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final RestaurantService service;

    @Inject
    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    public Result list() {
        log.info("Requested list");
        final Result result = okJson(service.findAll());
        log.info("Response  ok");
        return result;
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
}
