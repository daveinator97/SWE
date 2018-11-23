package de.fh.albsig.siemkeda;

import org.apache.log4j.Logger;

/** main class of weatherApp. */
public final class App {
  /** logger. */
  private static Logger log = Logger.getLogger(App.class.getName());

  /** to avoid HideUtilityClassConstructorCheck. */
  private App() {
  }

  /**
   * main method.
   *
   * @param args input
   */
  public static void main(final String[] args) {
    log.debug("weatherApp main() started");
    String inputCity = "Berlin";
    try {
      inputCity = args[0];
      log.debug("user input: " + inputCity);
    } catch (ArrayIndexOutOfBoundsException oobe) {
      log.info("no userinput, using default input \"Albstadt\"");
    }
    String weatherXmlData = new DataRequest().getData(inputCity);
    log.debug("weatherXmlData empty: " + weatherXmlData.isEmpty());
    if (!weatherXmlData.isEmpty()) {
      new WeatherXmlCreator().createXmlFile(weatherXmlData);
      log.debug("weatherApp main() finished");
    } else {
      log.info("city not found");
    }
  }

}
