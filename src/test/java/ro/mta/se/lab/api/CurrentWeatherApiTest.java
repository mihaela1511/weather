package ro.mta.se.lab.api;

import org.junit.Assert;
import org.junit.Test;
import ro.mta.se.lab.model.CityCurrentWeatherModel;

public class CurrentWeatherApiTest {

    @Test
    public void testApi() {
        CityCurrentWeatherModel currentWatherForCity = CurrentWeatherApi.getCurrentWatherForCity(683506L);
        Assert.assertTrue("Api is not working corectly", currentWatherForCity.getName().equals("Bucharest"));
    }

}