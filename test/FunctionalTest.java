import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.inject.*;

import java.io.IOException;
import java.util.*;

import models.Restaurant;
import org.junit.runners.MethodSorters;
import play.*;
import play.inject.guice.*;
import play.libs.Json;
import play.mvc.*;

import play.test.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.*;
import repositories.RestaurantRepository;

import javax.inject.Inject;

// Use FixMethodOrder to run the tests sequentially
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FunctionalTest {

    @Inject
    Application application;

    @Before
    public void setup() {
        Module testModule = new AbstractModule() {
            @Override
            public void configure() {


                // Install custom test binding here
            }
        };

        GuiceApplicationBuilder builder = new GuiceApplicationLoader()
                .builder(new ApplicationLoader.Context(Environment.simple()))
                .overrides(testModule);
        Guice.createInjector(builder.applicationModule()).injectMembers(this);

        Helpers.start(application);
    }

    @After
    public void teardown() {
        Helpers.stop(application);
    }

    @Test
    public void shouldFindAllRestaurantsAfterRequest() throws IOException {

        //Given



        //When
        Result result = Helpers.route(application, controllers.routes.RestaurantController.list());

        //Then
        assertThat(result.status(), equalTo(Helpers.OK));

        final List<Restaurant> restaurants = readListValue(result, Restaurant.class);
        assertNotNull(restaurants);
        assertEquals(restaurants.size(),2);

    }

    @Test
    public void shouldFindSingleRestaurantByItsIdAfterRequest() throws IOException {

        int givenRestaurantId = 1;

        //When
        Result result = Helpers.route(application, controllers.routes.RestaurantController.getById(givenRestaurantId));

        //Then
        assertThat(result.status(), equalTo(Helpers.OK));
        final Restaurant r = readValue(result, Restaurant.class);
        assertNotNull(r);
        assertEquals(r.name, "Pizza Heaven");

    }

    private static <T> T readValue(Result result, Class<T> type) throws IOException {
        final String response = Helpers.contentAsString(result);
        return mapper().readValue(response, type);
    }


    private static <T> List<T> readListValue(Result result, Class<T> type) throws IOException {
        final String response = Helpers.contentAsString(result);
        final ObjectMapper mapper = mapper();
        final CollectionType cType = mapper.getTypeFactory().constructCollectionType(List.class, type);
        return mapper.readValue(response, cType);
    }

    private static ObjectMapper mapper() {
        return  Json.mapper();
    }



}
