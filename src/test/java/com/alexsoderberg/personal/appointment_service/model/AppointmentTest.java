package com.alexsoderberg.personal.appointment_service.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;

public class AppointmentTest {

  private Appointment sut;

  @BeforeEach
  void setUp() {
    sut = new Appointment(
      "Test Testsson",
      LocalDate.of(2024, 1, 1),
      LocalTime.of(10, 0),
      90,
      "Male hair cut"
    );
  }
}
