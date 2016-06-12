package controllers;

import controllers.actions.VersionedAction;
import models.Restaurant;
import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;
import play.mvc.With;

/**
 * Manage a database of computers
 */
@With(VersionedAction.class)
public final class RestaurantController extends Controller {

    public Result list() {
        return ok(
                Json.toJson(
                        Restaurant.find.all()
                )
        );
    }
    
    /**
     * @param id Id of the restaurant to edit
     */
    public Result getById(Integer id) {

        final Restaurant restaurant = Restaurant.find.byId(id);
        if(restaurant == null) {
            return notFound();
        }
        return ok(
                Json.toJson(restaurant
                )
        );
    }
}
