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

  /**
   * Returns the name property.
   *
   * @return A string representing the name property.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets a new name to the name property.
   *
   * @param newName - The new name to be set to the name property.
   * @throws IllegalArgumentException - If the new name is invalid.
   */
  public void setName(String newName) throws IllegalArgumentException {
    validateName(newName);
    name = newName;
  }

  /**
   * Checks if an entered name is empty or consists purely of blank spaces. If so,
   * throw an {@link IllegalArgumentException}.
   *
   * @param name - The name to check.
   * @throws IllegalArgumentException
   */
  private void validateName(String name) throws IllegalArgumentException {
    if (name.isBlank()) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Returns the date of the appointment.
   *
   * @return The date property.
   */
  public LocalDate getDate() {
    return date;
  }

  /**
   * Sets a new date to the date property. 
   *
   * @param newDate - The date to be set.
   * @throws IllegalArgumentException
   */
  public void setDate(LocalDate newDate) throws IllegalArgumentException {
    validateDate(newDate);
    date = newDate;
  }

  /**
   * Validates if the entered date is before the current date. If so,
   * throw an {@link IllegalArgumentException}
   *
   * @param date - The date to check.
   * @throws IllegalArgumentException
   */
  private void validateDate(LocalDate date) throws IllegalArgumentException {
    if (date.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException();
    }
  }
}
