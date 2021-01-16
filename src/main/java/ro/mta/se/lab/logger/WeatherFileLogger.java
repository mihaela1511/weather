package ro.mta.se.lab.logger;

import ro.mta.se.lab.constants.ApplicationConstants;
import ro.mta.se.lab.model.CityCurrentWeatherModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Path;

public class WeatherFileLogger {

    public static void logEntryToFile(CityCurrentWeatherModel cityCurrentWeatherModel) {
        try {
            FileWriter fileWriter = new FileWriter(Path.of(
                    System.getProperty(ApplicationConstants.USER_DIR).concat(ApplicationConstants.LOGFILE)).toString(), true);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            bw.write(cityCurrentWeatherModel.getDatetime().concat(ApplicationConstants.EMPTY_SPACE));
            bw.write(cityCurrentWeatherModel.getName().concat(ApplicationConstants.EMPTY_SPACE));
            bw.write(cityCurrentWeatherModel.getDescription().concat(ApplicationConstants.EMPTY_SPACE));
            bw.write(String.valueOf(Math.round(cityCurrentWeatherModel.getTemperature()))
                    .concat(ApplicationConstants.CELSIUS_DEGREE));
            bw.write(ApplicationConstants.HUMIDITY.concat(String.valueOf(cityCurrentWeatherModel.getHumidity())
                    .concat(ApplicationConstants.PERCENT)).concat(ApplicationConstants.EMPTY_SPACE));
            bw.write(ApplicationConstants.WIND_LABEL_START.concat(String.valueOf(cityCurrentWeatherModel.getWind())
                    .concat(ApplicationConstants.WIND_MEASURE)));

            bw.newLine();
            bw.close();
            fileWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
