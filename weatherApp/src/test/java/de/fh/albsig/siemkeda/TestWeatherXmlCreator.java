package de.fh.albsig.siemkeda;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestWeatherXmlCreator {

  @DisplayName("file created")
  @Test
  public void testWeatherXmlCreatorFileExists() {
    String rtrn = "<current><city name=\"Albstadt\"></city><lastupdated value=\"test\"/></current>";
    WeatherXmlCreator wxc = new WeatherXmlCreator();
    Assertions.assertTrue(wxc.createXmlFile(rtrn));
    File f = new File("weather_Albstadt_test.xml");
    Assertions.assertTrue(f.exists() && !f.isDirectory() && f.length() != 0);
  }

  @DisplayName("wrong input format")
  @Test
  public void testWeatherXmlCreatorWrongInputFormat() {
    String wrongFormat = "<current><city>Albstadt</city><lastupdated>test</lastupdated></current>";
    WeatherXmlCreator wxc = new WeatherXmlCreator();
    Assertions.assertFalse(wxc.createXmlFile(wrongFormat));
  }
}