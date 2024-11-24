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

  /**
   * Returns the value of the duration property.
   *
   * @return An integer representing the duration in minutes.
   */
  public int getDuration() {
    return duration;
  }

  /**
   * Sets a new value to the duration property. If entered with
   * and invalid value, throw {@link IllegalArgumentException}
   *
   * @param newDuration The new value to be set to the duration property.
   * @throws IllegalArgumentException
   */
  public void setDuration(int newDuration) throws IllegalArgumentException {
    validateDuration(newDuration);
    duration = newDuration;
  }

  /**
   * Checks if an entered duration is a positive value between 1 and 1000. If not,
   * throw an {@link IllegalArgumentException}
   *
   * @param newDuration The new value to check.
   * @throws IllegalArgumentException
   */
  private void validateDuration(int newDuration) throws IllegalArgumentException {
    if (newDuration < 1 || newDuration > 1000) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Returns the value of the description property.
   *
   * @return A string containing the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets a new value to the description property. If entered
   * with an invalid string, throw an {@link IllegalArgumentException}
   *
   * @param newDescription - The new description to be set.
   * @throws IllegalArgumentException
   */
  public void setDescription(String newDescription) throws IllegalArgumentException {
    validateDescription(newDescription);
    description = newDescription;
  }

  /**
   * Checks that the entered description is not empty, contains only blankspaces,
   * or is not longer than 1000 characters. If one of the rules are broken, throw an
   * {@link IllegalArgumentException}
   *
   * @param newDescription The string to check
   * @throws IllegalArgumentException
   */
  private void validateDescription(String newDescription) throws IllegalArgumentException {
    if(newDescription.isBlank() || newDescription.isEmpty() || newDescription.length() > 1000) {
      throw new IllegalArgumentException();
    }
  }
}
