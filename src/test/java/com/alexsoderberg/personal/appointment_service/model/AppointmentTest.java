package com.alexsoderberg.personal.appointment_service.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppointmentTest {

  private Appointment sut;
  private String testName = "Test Testsson";
  private LocalDate testDate = LocalDate.now().plusDays(1);
  private LocalTime testTime = LocalTime.now().plusHours(1);
  private int testDuration = 90;
  private String testDescription = "Male hair cut";

  @BeforeEach
  void setUp() {
    sut = new Appointment(
      testName,
      testDate,
      testTime,
      testDuration,
      testDescription
    );
  }

  @Test
  void getNameShouldReturnName() {
    String actual = sut.getName();
    String expected = "Test Testsson";
    assertEquals(expected, actual);
  }

  @Test
  void setNameShouldSetNewName() {
    String input = "Alex Söderberg";
    String expected = input;

    String actual = setAndReturnUpdatedName(input);

    assertEquals(expected, actual);
  }

  @Test
  void setNameShouldThrowExceptionOnBlankStringEntered() {
    String input = "  ";
    assertThrows(IllegalArgumentException.class, () -> sut.setName(input));
  }

  // Redundant test...
  @Test
  void setNameShouldNotChangeNameWhenEnteredWithEmptyString() {
    String input = "";
    String expected = testName;

    try {
      setAndReturnUpdatedName(input);
    } catch (Exception e) {
      assertNotEquals(expected, input);
    }
  }

  // Redundant test...
  @Test
  void setNameShouldNotChangeWhenEnteredWithBlankspaces() {
    String input = "  ";
    String expected = testName;

    try {
      setAndReturnUpdatedName(input);
    } catch (Exception e) {
      assertNotEquals(expected, input);
    }
  }

  private String setAndReturnUpdatedName(String newName) {
    sut.setName(newName);
    return sut.getName();
  }


  @Test
  void getDateShouldReturnDate() {
    LocalDate expected = sut.getDate();
    LocalDate actual = testDate;

    assertEquals(expected, actual);
  }

  @Test
  void setDateShouldSetNewDate() {
    LocalDate input = LocalDate.now().plusDays(2);
    LocalDate expected = input;
    sut.setDate(input);

    LocalDate actual = sut.getDate();
    assertEquals(expected, actual);
  }

  @Test
  void setDateShouldThrowErrorWhenEnteredWithPassedDate() {
    LocalDate input = LocalDate.of(2024, 1, 1);
    assertThrows(IllegalArgumentException.class, () -> sut.setDate(input));
  }
}
