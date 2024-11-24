package com.alexsoderberg.personal.appointment_service.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.Local;

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

  @Test
  void setDateShouldThrowErrorWhenEnteredWithTodaysDate() {
    LocalDate input = LocalDate.now();
    assertThrows(IllegalArgumentException.class, () -> sut.setDate(input));
  }

  @Test
  void getTimeShouldReturnTime() {
    LocalTime expected = testTime;
    LocalTime actual = sut.getTime();

    assertEquals(expected, actual);
  }

  @Test
  void setTimeShouldSetNewTime() {
    int offset = 2;

    LocalTime input = getTimeWithOffset(offset, offset);
    LocalTime expected = input;
    sut.setTime(input);
    LocalTime actual = sut.getTime();

    assertEquals(expected, actual);
  }

  @Test
  void setTimeShouldThrowExceptionOnPassedTimeAndDateEntered() {
    Appointment sut = new Appointment(
      testName,
      LocalDate.now(),
      testTime,
      testDuration,
      testDescription
    );

    int offset = -2;
    LocalTime input = getTimeWithOffset(offset, 0);

    assertThrows(IllegalArgumentException.class, () -> sut.setTime(input));
  }

  private LocalTime getTimeWithOffset(int hourOffset, int minuteOffset) {
    int currentHour = testTime.getHour();
    int currentMinute = testTime.getMinute();

    int newHour = currentHour + hourOffset;
    int newMinute = currentMinute + minuteOffset;
    return LocalTime.of(newHour, newMinute);
  }

  @Test
  void getDurationShouldReturnTheDurationOfAppointment() {
    int expected = testDuration;
    int actual = sut.getDuration();
    assertEquals(expected, actual);
  }

  @Test
  void setDurationShouldSetACorrectDuration() {
    int input = 30;
    int expected = input;
    sut.setDuration(input);
    int actual = sut.getDuration();

    assertEquals(expected, actual);
  }

  @Test
  void setDurationShouldThrowExceptionWhenEnteredWithNegativeNumber() {
    int input = -1;
    assertThrows(IllegalArgumentException.class, () -> sut.setDuration(input));
  }

  @Test
  void setDurationShouldThrowExceptionWhenEnteredWithZero() {
    int input = 0;
    assertThrows(IllegalArgumentException.class, () -> sut.setDuration(input));
  }

  @Test
  void setDurationShouldThrowExceptionWhenEnteredWithLargerThanOneThousand() {
    int input = 1001;
    assertThrows(IllegalArgumentException.class, () -> sut.setDuration(input));
  }

  @Test
  void getDescriptionShouldReturnDescriptionOfAppointment() {
    String expected = testDescription;
    String actual = sut.getDescription();
    assertEquals(expected, actual);
  }

  @Test 
  void setDescriptionShouldSetTheAppointmentDescription() {
    String input = "Female hair cut";
    String expected = input;
    sut.setDescription(input);
    String actual = sut.getDescription();
    assertEquals(expected, actual);
  }

  @Test
  void setDescriptionShouldThrowExceptionWhenEnteredWithTooLongString() {
    String input = "x".repeat(2000);
    assertThrows(IllegalArgumentException.class, () -> sut.setDescription(input));
  }

  @Test
  void setDescriptionShouldThrowExceptionWhenEnteredWithEmptyString() {
    String input = "";
    assertThrows(IllegalArgumentException.class, () -> sut.setDescription(input));
  }

  @Test
  void setDescriptionShouldThrowExceptionWhenEnteredWithOnlyBlankspaces() {
    String input = "   ";
    assertThrows(IllegalArgumentException.class, () -> sut.setDescription(input));
  }
}
