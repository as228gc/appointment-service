package com.alexsoderberg.personal.appointment_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alexsoderberg.personal.appointment_service.model.Appointment;
import com.alexsoderberg.personal.appointment_service.repository.AppointmentRepository;

public class AppointmentServiceTest {
  private AppointmentRepository repository;
  private AppointmentService sut;

  @BeforeEach
  void setUp() {
    repository = mock(AppointmentRepository.class);
    sut = new AppointmentService(repository);
  }

  @Test
  void saveAppointmentShouldReturnAppointmentOnValidInput() {
    Appointment appointment = generateTestAppointment();

    when(repository.save(appointment)).thenReturn(appointment);

    Appointment savedAppointment = sut.saveAppointment(appointment);

    assertEquals(appointment, savedAppointment);
    verify(repository, times(1)).save(appointment);
  }

  @Test
  void getAppointmentsShouldReturnAllAppointments() {
    Appointment appointment = generateTestAppointment();
    List<Appointment> appointmentList = new ArrayList<>();
    appointmentList.add(appointment);

    when(repository.findAll()).thenReturn(appointmentList);

    List<Appointment> actual = sut.getAppointments();

    assertEquals(appointmentList, actual);
    verify(repository, times(1)).findAll();
  }

  private Appointment generateTestAppointment() {
    return new Appointment(
      "Test Testsson",
      LocalDate.now().plusDays(2),
      LocalTime.now(),
      90,
      "Male hair cut");
  }

  @Test
  void deleteAppointmentShouldDeleteAppointmentOnValidInput() {
    Appointment appointment = generateTestAppointment();
    Long appointmentId = (long) 1;

    when(repository.findById(appointmentId)).thenReturn(Optional.of(appointment));

    Appointment deletedAppointment = sut.deleteAppointment(appointmentId).orElseThrow();

    assertEquals(appointment, deletedAppointment);
    verify(repository, times(1)).findById(appointmentId);
    verify(repository, times(1)).deleteById(appointmentId);
  }
}
