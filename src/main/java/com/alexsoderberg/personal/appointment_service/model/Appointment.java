package com.alexsoderberg.personal.appointment_service.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.cglib.core.Local;

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
   * @param newName - The name to check.
   * @throws IllegalArgumentException
   */
  private void validateName(String newName) throws IllegalArgumentException {
    if (newName.isBlank()) {
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
   * @param newDate - The date to check.
   * @throws IllegalArgumentException
   */
  private void validateDate(LocalDate newDate) throws IllegalArgumentException {
    if (newDate.isBefore(LocalDate.now()) || newDate.equals(LocalDate.now())) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Returns the time property.
   *
   * @return A LocalTime object that represents the current time property.
   */
  public LocalTime getTime() {
    return time;
  }

  /**
   * Sets a new time to the time property.
   *
   * @param newTime
   * @throws IllegalArgumentException
   */
  public void setTime(LocalTime newTime) throws IllegalArgumentException {
    validateTime(newTime);
    time = newTime;
  }

  /**
   * Validates if the entered time is before the current time and date. If so,
   * throw an {@link IllegalArgumentException}
   *
   * @param newTime - The date to check.
   * @throws IllegalArgumentException
   */
  private void validateTime(LocalTime newTime) throws IllegalArgumentException {
    if(date.isEqual(LocalDate.now()) && newTime.isBefore(LocalTime.now())) {
      throw new IllegalArgumentException();
    }
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int newDuration) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setDuration'");
  }
}
