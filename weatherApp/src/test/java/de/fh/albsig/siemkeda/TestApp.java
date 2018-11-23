package de.fh.albsig.siemkeda;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestApp {

  @BeforeAll
  static void setup() {

  }

  @BeforeEach
  void setupThis() {

  }

  @DisplayName("richtig")
  @Test
  public void testt() {

  }

  @Test
  public void testCalcTwo() {
    Assertions.assertEquals(1, 1);
  }

  @AfterEach
  void tearThis() {

  }

  @AfterAll
  static void tear() {
    System.out.println("@AfterAll executed");
  }
}
