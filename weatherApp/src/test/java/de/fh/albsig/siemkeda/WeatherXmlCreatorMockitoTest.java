package de.fh.albsig.siemkeda;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class WeatherXmlCreatorMockitoTest {

  @InjectMocks
  private WeatherXmlCreator wxc;

  @Mock
  private DataRequest dr;

  @DisplayName("file created")
  @Test
  public void weatherXmlCreatorTest() {

    DataRequest mockedDr = Mockito.mock(DataRequest.class);
    String rtrn = "<current><city name=\"Albstadt\"></city> + "
        + "<lastupdated value=\"test\"/></current>";
    Mockito.when(mockedDr.getData("Albstadt")).thenReturn(rtrn);

    wxc = new WeatherXmlCreator();
    wxc.createXmlFile(mockedDr.getData("Albstadt"));

    File f = new File("./src/main/resources/weather_Albstadt_test.xml");
    Assertions.assertTrue(f.exists() && !f.isDirectory());
  }
}