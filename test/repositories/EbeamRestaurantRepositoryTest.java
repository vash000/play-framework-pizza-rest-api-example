package repositories;

import java.math.BigDecimal;
import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static play.test.Helpers.*;

import models.*;

import com.avaje.ebean.*;
import repositories.EbeanRestaurantRepository;

public class EbeamRestaurantRepositoryTest {
    

    @Test
    public void shouldFindSingleRestaurantByItsIdInRepository() {
        running(fakeApplication(inMemoryDatabase()), () -> {
            //When
            final long restaurantId = 1L;

            //Given
            Optional<Restaurant> option = new EbeanRestaurantRepository().findById(restaurantId);

            //Then
            assertNotNull(option);
            assertTrue(option.isPresent());

            final Restaurant restaurant = option.get();

            assertThat(restaurant.name, equalTo("Pizza Heaven"));
            assertThat(restaurant.latitude, equalTo(new BigDecimal("59.336078")));
            assertThat(restaurant.longitude, equalTo(new BigDecimal("18.071807")));
        });
    }
    
}
