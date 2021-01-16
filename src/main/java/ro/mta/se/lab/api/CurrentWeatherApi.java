package ro.mta.se.lab.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import ro.mta.se.lab.constants.ApiConstants;
import ro.mta.se.lab.constants.ApplicationConstants;
import ro.mta.se.lab.logger.WeatherFileLogger;
import ro.mta.se.lab.model.CityCurrentWeatherModel;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class CurrentWeatherApi {

    private static String apiKey = "3630684f40fde64c2617baebad873ebe";


    public static CityCurrentWeatherModel getCurrentWatherForCity(Long cityId) {
        try {
            URL url = new URL(ApiConstants.OPENWEATHERMAP_ENDPOINT.concat(cityId.toString())
                    .concat(ApiConstants.APPID_PARAMETER).concat(apiKey));
            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            httpcon.addRequestProperty(ApiConstants.USER_AGENT, ApiConstants.MOZILLA_4_0);
            InputStream is = httpcon.getInputStream();

            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(is, Map.class);
            JSONObject jsonObject = new JSONObject(map);

            CityCurrentWeatherModel cityCurrentWeatherModel = new CityCurrentWeatherModel();
            cityCurrentWeatherModel.setName(jsonObject.get(ApiConstants.NAME).toString());
            String pattern = ApplicationConstants.DATE_FORMAT;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


            cityCurrentWeatherModel.setDatetime(simpleDateFormat.format(new Date()));
            cityCurrentWeatherModel.setWind(Float.valueOf((((JSONObject) jsonObject.get(ApiConstants.WIND))
                    .get(ApiConstants.SPEED).toString())));
            cityCurrentWeatherModel.setHumidity(Float.valueOf(((JSONObject) jsonObject.get(ApiConstants.MAIN))
                    .get(ApiConstants.HUMIDITY).toString()));
            cityCurrentWeatherModel.setTemperature((float) (Double.valueOf(((JSONObject) jsonObject.get(
                    ApiConstants.MAIN)).get(ApiConstants.TEMP).toString()) - 273.15));
            cityCurrentWeatherModel.setDescription(
                    ((JSONObject) ((JSONArray) jsonObject.get(ApiConstants.WEATHER))
                            .get(0)).get(ApiConstants.DESCRIPTION).toString());
            cityCurrentWeatherModel.setIcon(ApiConstants.ICON_ENDPOINT.concat(
                    ((JSONObject) ((JSONArray) jsonObject.get(ApiConstants.WEATHER))
                            .get(0)).get(ApiConstants.ICON).toString()).concat(ApplicationConstants.PNG));

            WeatherFileLogger.logEntryToFile(cityCurrentWeatherModel);

            return cityCurrentWeatherModel;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
