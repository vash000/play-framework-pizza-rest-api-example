package functional;


import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Module;
import models.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.ApplicationLoader;
import play.Environment;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceApplicationLoader;
import play.mvc.Result;
import play.test.Helpers;
import repositories.RestaurantRepository;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class FunctionalTimeOutTest {

    @Inject
    Application application;

    @Before
    public void setup() {
        Module testModule = new AbstractModule() {
            @Override
            public void configure() {
                bind(RestaurantRepository.class).to(SlowRepository.class);
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
    public void shouldFailedToReadAllRestaurantsFromSlowDatabase() throws IOException {

        //Given

        //When
        Result result = Helpers.route(application, controllers.routes.RestaurantController.list());

        //Then
        assertNotNull(result);
        assertThat(result.status(), equalTo(Helpers.TOO_MANY_REQUESTS));
    }



    private static class SlowRepository implements RestaurantRepository {

        @Override
        public List<Restaurant> findAll() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignore) {
            }
            return Collections.emptyList();
        }

        @Override
        public Optional<Restaurant> findById(Long id) {
            return Optional.empty();
        }
    }

}
