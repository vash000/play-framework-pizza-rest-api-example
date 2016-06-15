package repositories;

import com.google.inject.ImplementedBy;
import models.RestaurantMenu;
import models.RestaurantMenuItem;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

@ImplementedBy(EbeanRestaurantMenuRepository.class)
public interface RestaurantMenuRepository {

    /**
     * Find Menu Items by criteria
     * @param restaurantId - Restaurant Id
     * @param category - Optional search criteria
     * @param orderBy - Optional order of resolved elements
     * @return Stream of restaurant menu Item
     */
    Stream<RestaurantMenuItem> findRestaurantMenuItems(long restaurantId, Optional<String> category, Optional<String> orderBy);
}
