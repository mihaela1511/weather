package ro.mta.se.lab.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ro.mta.se.lab.api.CurrentWeatherApi;
import ro.mta.se.lab.constants.ApplicationConstants;
import ro.mta.se.lab.model.CityCurrentWeatherModel;
import ro.mta.se.lab.model.LocationModel;
import ro.mta.se.lab.parser.InputFileParser;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class WeatherController {

    @FXML
    public ComboBox<String> countryCombobox;

    @FXML
    public ComboBox<String> cityCombobox;

    @FXML
    public Label windLabel;

    @FXML
    public Label humidityLabel;

    @FXML
    public ImageView weatherStatusImage;

    @FXML
    public Label temperatureLabel;

    @FXML
    public Label descriptionLabel;

    @FXML
    public Label dateLabel;

    @FXML
    public Label cityLabel;

    @FXML
    public Label gradeSymbolLabel;

    @FXML
    public Label gradeLabel;

    @FXML
    public ImageView refreshImageView;


    private List<LocationModel> locations;

    public void loadInitialData() {
        gradeLabel.setVisible(false);
        gradeSymbolLabel.setVisible(false);
        refreshImageView.setVisible(false);
        countryCombobox.getItems().addAll(getCountries());
    }

    @FXML
    private void onChangeCountry() {
        windLabel.setText(ApplicationConstants.EMPTY_STRING);
        temperatureLabel.setText(ApplicationConstants.EMPTY_STRING);
        humidityLabel.setText(ApplicationConstants.EMPTY_STRING);
        descriptionLabel.setText(ApplicationConstants.EMPTY_STRING);
        dateLabel.setText(ApplicationConstants.EMPTY_STRING);
        cityLabel.setText(ApplicationConstants.EMPTY_STRING);
        weatherStatusImage.setImage(null);
        gradeLabel.setVisible(false);
        gradeSymbolLabel.setVisible(false);
        refreshImageView.setVisible(false);
        String selectedCountry = countryCombobox.getSelectionModel().getSelectedItem();
        cityCombobox.getItems().setAll(getCities(selectedCountry));
    }

    @FXML
    private void onChangeCity() {
        String selectedCity = cityCombobox.getSelectionModel().getSelectedItem();
        LocationModel locationModel = locations.stream().filter(locationModelFiltered ->
                locationModelFiltered.getCityName().equals(selectedCity)).findFirst().get();
        CityCurrentWeatherModel cityCurrentWeatherModel = CurrentWeatherApi
                .getCurrentWatherForCity(locationModel.getCityId());
        if (cityCurrentWeatherModel != null) {
            windLabel.setText(ApplicationConstants.WIND_LABEL_START.concat(String.valueOf(
                    cityCurrentWeatherModel.getWind()).concat(ApplicationConstants.WIND_MEASURE)));
            temperatureLabel.setText(String.valueOf(Math.round(cityCurrentWeatherModel.getTemperature())));
            humidityLabel.setText(ApplicationConstants.HUMIDITY.concat(String.valueOf(
                    cityCurrentWeatherModel.getHumidity()).concat(ApplicationConstants.PERCENT)));
            descriptionLabel.setText(cityCurrentWeatherModel.getDescription());
            dateLabel.setText(cityCurrentWeatherModel.getDatetime());
            cityLabel.setText(cityCurrentWeatherModel.getName());
            weatherStatusImage.setImage(new Image(cityCurrentWeatherModel.getIcon()));
            gradeLabel.setVisible(true);
            gradeSymbolLabel.setVisible(true);
            refreshImageView.setVisible(true);
        }
    }

    @FXML
    private void refreshSelectedData() {
        onChangeCity();
    }

    public Set<String> getCountries() {
        InputFileParser inputFileParser = new InputFileParser();
        locations = inputFileParser.loadLocationsFromFile(ApplicationConstants.INITIAL_DATA_FILE);
        return locations.stream()
                .map(locationModel -> {
                    Locale locale = new Locale(ApplicationConstants.LANGUAGE, locationModel.getCountryCode());
                    return locale.getDisplayCountry();
                })
                .collect(Collectors.toSet());
    }

    public List<String> getCities(String selectedCountry) {
        return locations.stream()
                .filter(locationModel -> {
                    Locale locale = new Locale(ApplicationConstants.LANGUAGE, locationModel.getCountryCode());
                    return locale.getDisplayCountry().equals(selectedCountry);
                })
                .map(locationModel -> locationModel.getCityName())
                .collect(Collectors.toList());
    }

}
