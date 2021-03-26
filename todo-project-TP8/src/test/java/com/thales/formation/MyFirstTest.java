package com.thales.formation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MyFirstTest {

  private static int number = 4;

  //  @BeforeAll
  //  static void initAll() {
  //    number = 5;
  //  }
  //
  //  @BeforeEach
  //  void init() {
  //    number = 4;
  //  }
  //
  //  @AfterEach
  //  void after() {
  //    number = 4;
  //  }
  //
  //  @AfterAll
  //  static void afterAll() {
  //    number = 4;
  //  }

  @Test
  //  @DisplayName("J'aime les chiffres!")
  void myFirstTest() {
    assertEquals(number, 2 + 2);
  }
}
