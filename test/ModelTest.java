import java.math.BigDecimal;
import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static play.test.Helpers.*;

import models.*;

import com.avaje.ebean.*;

public class ModelTest {
    

    @Test
    public void shouldFindSingleRestaurantByItsIdInDataBase() {
        running(fakeApplication(inMemoryDatabase()), () -> {
            //When
            final int restaurantId = 1;

            //Given
            Restaurant heaven = Restaurant.find.byId(restaurantId);

            //Then
            assertNotNull(heaven);
            assertThat(heaven.name, equalTo("Pizza Heaven"));
            assertThat(heaven.latitude, equalTo(new BigDecimal("59.336078")));
            assertThat(heaven.longitude, equalTo(new BigDecimal("18.071807")));
        });
    }
    
}
