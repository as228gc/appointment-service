package com.alexsoderberg.personal.appointment_service.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppointmentTest {

  private Appointment sut;
  private String testName = "Test Testsson";

  @BeforeEach
  void setUp() {
    sut = new Appointment(
      testName,
      LocalDate.of(2024, 1, 1),
      LocalTime.of(10, 0),
      90,
      "Male hair cut"
    );
  }

  @Test
  void shouldGetNameProperty() {
    String actual = sut.getName();
    String expected = "Test Testsson";
    assertEquals(expected, actual);
  }

  @Test
  void shouldSetNameProperty() {
    String input = "Alex Söderberg";
    String expected = input;

    String actual = setAndReturnUpdatedName(input);

    assertEquals(expected, actual);
  }

  @Test
  void nameShouldBeUnchangedOnEmptyStringSet() {
    String input = "";
    String expected = testName;

    String actual = setAndReturnUpdatedName(input);

    assertEquals(expected, actual);
  }

  @Test
  void nameShouldBeUnchangedWhenNameSetToBlankspace() {
    String input = "  ";
    String expected = testName;

    String actual = setAndReturnUpdatedName(input);
    assertEquals(expected, actual);
  }

  private String setAndReturnUpdatedName(String newName) {
    sut.setName(newName);
    return sut.getName();
  }
}
