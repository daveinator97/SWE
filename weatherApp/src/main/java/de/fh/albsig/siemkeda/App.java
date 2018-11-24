package de.fh.albsig.siemkeda;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/** main class of weatherApp. */
public final class App {
  /** logger. */
  private static Logger log = Logger.getLogger(App.class.getName());

  /** to avoid HideUtilityClassConstructorCheck. */
  private App() {
  }

  /**
   * main method. input the city name for which city weather data should be
   * retrieved creates a xml file containing the data in the resources folder of
   * the project, named "weather_city_timeofmeseaurement.xml"
   *
   * @param args input for which city weather data should be retrieved
   */
  public static void main(final String[] args) {
    log.debug("weatherApp main() started");
    String inputCity = "Albstadt";
    try {
      inputCity = args[0];
      log.debug("user input: " + inputCity);
    } catch (ArrayIndexOutOfBoundsException oobe) {
      log.info("no userinput, using default input \"Albstadt\"");
    }
    String weatherXmlData = new DataRequest().getData(inputCity);
    log.debug("weatherXmlData empty: " + weatherXmlData.isEmpty());
    if (StringUtils.isNotEmpty(weatherXmlData)) {
      new WeatherXmlCreator().createXmlFile(weatherXmlData);
      log.debug("weatherApp main() finished");
    } else {
      log.info("city not found");
    }
  }

}
