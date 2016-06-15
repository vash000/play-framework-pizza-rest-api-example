package repositories;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Model;
import models.RestaurantMenuItem;

import java.util.Optional;
import java.util.stream.Stream;

public class EbeanRestaurantMenuRepository implements RestaurantMenuRepository {

    public static final Model.Find<Long, RestaurantMenuItem> find = new Model.Find<Long, RestaurantMenuItem>() {};

   

    @Override
    public Stream<RestaurantMenuItem> findRestaurantMenuItems(long id, Optional<String> category, Optional<String> orderBy) {

        final ExpressionList<RestaurantMenuItem> expression = find.where().eq("restaurant_id", id);

        if(category.isPresent()) expression.eq("product.category", category.get());

        if(orderBy.isPresent()) expression.orderBy(orderBy.get());

        return expression.findList().stream();
    }

}
