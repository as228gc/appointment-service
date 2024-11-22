package com.alexsoderberg.personal.appointment_service.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
  private String name;
  private LocalDate date;
  private LocalTime time;
  private int duration;
  private String description;

  public Appointment(String name, LocalDate date, LocalTime time, int duration, String description) {
    this.name = name;
    this.date = date;
    this.time = time;
    this.duration = duration;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String newName) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setName'");
  }
}
