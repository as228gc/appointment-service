package com.alexsoderberg.personal.appointment_service.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
