package de.fh.albsig.siemkeda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DataRequestTest {

  private DataRequest dr;

  @BeforeEach
  public void setUp() {
    this.dr = new DataRequest();
  }

  @DisplayName("data retrieval successful")
  @Test
  public void dataRequestPositiveTest() {
    String reqDat = dr.getData("Albstadt");
    Assertions.assertNotEquals("", reqDat);
  }

  @DisplayName("no city found")
  @Test
  public void dataRequestNegativeTest() {
    Assertions.assertEquals("", dr.getData("Coruscant"));
  }
}
