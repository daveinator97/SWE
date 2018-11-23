package de.fh.albsig.siemkeda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

/** . */
public class DataRequest {
  /** logger. */
  private Logger log = Logger.getLogger(DataRequest.class.getName());

  /**
   * gets weather data from openweathermap.org.
   *
   * @param city input city for data basis
   * @return retrieved weather data in xml format or in error case empty string
   */
  public final String getData(final String city) {
    /** apikey for openweathermap.org. */
    String apikey = "de863058958118da2371539ca1f6d600";
    String reqUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city
        + "&appid=" + apikey + "&units=metric&mode=xml";
    log.debug("reqUrl: " + reqUrl);
    StringBuilder xmlData = new StringBuilder();
    try {
      // get data
      URL url = new URL(reqUrl);
      URLConnection conn = url.openConnection();
      log.debug("create BufferedReader, InputStreamReader");
      BufferedReader rd = new BufferedReader(
          new InputStreamReader(conn.getInputStream()));
      String dataline;
      while ((dataline = rd.readLine()) != null) {
        xmlData.append(dataline);
        log.debug("dataline: " + dataline);
      }
      rd.close();
      log.debug("xmlData: " + xmlData);
    } catch (IOException ioe) {
      log.error(ioe.getMessage(), ioe);
      return "";
    } catch (NullPointerException npe) {
      log.error(npe.getMessage(), npe);
      return "";
    }
    return xmlData.toString();
  }
}
