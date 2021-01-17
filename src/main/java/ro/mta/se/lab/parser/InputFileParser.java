package ro.mta.se.lab.parser;

import ro.mta.se.lab.constants.ApplicationConstants;
import ro.mta.se.lab.model.LocationModel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFileParser {

    public List<LocationModel> loadLocationsFromFile(String filename) {

        List<LocationModel> locations = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
        try (Scanner scanner = new Scanner(inputStream)) {

            scanner.nextLine();

            while (scanner.hasNext()) {
                String row = scanner.nextLine();
                locations.add(parseRow(row));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return locations;
    }

    private LocationModel parseRow(String row) {
        String[] rowElements = row.split(ApplicationConstants.SPACE_REGEX);
        LocationModel locationModel = new LocationModel();
        int i = 0;
        locationModel.setCityId(Long.valueOf(rowElements[i]));
        i++;
        locationModel.setCityName(ApplicationConstants.EMPTY_STRING);
        boolean namePart = true;
        while (namePart) {
            try {
                Double.valueOf(rowElements[i]);
                namePart = false;
            } catch (NumberFormatException ex) {
                namePart = true;
                if(!locationModel.getCityName().isEmpty()){
                    locationModel.setCityName(locationModel.getCityName().concat(ApplicationConstants.EMPTY_SPACE));
                }
                locationModel.setCityName(locationModel.getCityName().concat(rowElements[i]));
                i++;
            }
        }
        locationModel.setCityLatitude(Double.valueOf(rowElements[i]));
        i++;
        locationModel.setCityLongitude(Double.valueOf(rowElements[i]));
        i++;
        locationModel.setCountryCode(rowElements[i]);
        return locationModel;
    }
}
