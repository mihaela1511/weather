package ro.mta.se.lab.parser;

import org.junit.Test;
import ro.mta.se.lab.model.LocationModel;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class InputFileParserTest {

    @Test
    public void loadLocationsFromFileTest() {
        InputFileParser inputFileParser = new InputFileParser();
        List<LocationModel> locations = inputFileParser.loadLocationsFromFile("initialData");
        assertTrue("Initial data was not read corectly", locations.size() == 2);
        assertTrue("City name is not read correctly", locations.get(0).getCityName().equals("Cluj-Napoca"));
        assertTrue("City id is not read correctly", locations.get(0).getCityId().longValue() == 681290);
        assertTrue("City laltitude is not read correctly", locations.get(0).getCityLatitude().doubleValue() == 46.76667);
        assertTrue("City longitude is not read correctly", locations.get(0).getCityLongitude().doubleValue() == 23.6);
        assertTrue("Country code is not read correctly", locations.get(0).getCountryCode().equals("RO"));
    }
}