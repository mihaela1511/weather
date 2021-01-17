package ro.mta.se.lab.controller;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ro.mta.se.lab.model.LocationModel;
import ro.mta.se.lab.parser.InputFileParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class WeatherControllerTest {

    @Test
    public void getCountriesTest() {
        List<LocationModel> locationModels = new ArrayList<>();
        LocationModel locationModel = new LocationModel();
        locationModel.setCountryCode("RO");
        locationModel.setCityName("TestCity");
        locationModels.add(locationModel);

        InputFileParser inputFileParser = Mockito.mock(InputFileParser.class);
        when(inputFileParser.loadLocationsFromFile(anyString())).thenReturn(locationModels);


        WeatherController weatherController = new WeatherController();
        Set<String> countries = weatherController.getCountries();
        Assert.assertTrue("Countries not get corectly", countries.size() == 1);
        Assert.assertTrue("Country name not correct", countries.stream().findFirst().get().equals("Romania"));
    }
}