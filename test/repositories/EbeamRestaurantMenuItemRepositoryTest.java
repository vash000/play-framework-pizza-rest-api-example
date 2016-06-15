package repositories;

import com.avaje.ebean.Ebean;
import models.RestaurantMenuItem;
import org.junit.Before;
import org.junit.Test;
import play.Application;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

public class EbeamRestaurantMenuItemRepositoryTest {

    Application application;

    @Before
    public void setup() {
       application = fakeApplication(inMemoryDatabase());
    }

    @Test
    public void shouldFindAllRestaurantMenuItemsRepository() {
        running(application, () -> {
            //When
            final long restaurantId = 2L;

            //Given
            Stream<RestaurantMenuItem> items = new EbeanRestaurantMenuRepository()
                                .findRestaurantMenuItems(restaurantId,
                                        Optional.<String>empty(),
                                        Optional.<String>empty());

            //Then
            assertNotNull(items);
            assertEquals(items.count(), 7);
        });
    }

    @Test
    public void shouldFindAllRestaurantMenuItemsRepository2() {
        running(application, () -> {
            //When
            final long restaurantId = 2L;

            //Given
            Stream<RestaurantMenuItem> items = new EbeanRestaurantMenuRepository()
                                .findRestaurantMenuItems(restaurantId,
                                        Optional.<String>empty(),
                                        Optional.<String>empty());

            //Then
            assertNotNull(items);
            assertEquals(items
                    .map( r -> r.price)
                    .map( m -> m.amount)
                    .mapToInt(Number::intValue)
                    .summaryStatistics()
                    .getSum(),277);
        });
    }

    @Test
    public void shouldFindAllRestaurantMenuPizzaRepository() {
        running(application, () -> {
            //When
            final long restaurantId = 2L;

            //Given
            Stream<RestaurantMenuItem> items = new EbeanRestaurantMenuRepository()
                                .findRestaurantMenuItems(restaurantId,
                                        Optional.<String>empty(),
                                        Optional.<String>empty());

            //Then
            assertNotNull(items);
            assertEquals(items
                    .map(r -> r.product)
                    .map(m -> m.category)
                    .filter("Pizza"::equals)
                    .count(),3);
        });
    }
}
