package com.alexsoderberg.personal.appointment_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alexsoderberg.personal.appointment_service.model.Appointment;
import com.alexsoderberg.personal.appointment_service.repository.AppointmentRepository;

@Service
public class AppointmentService {
  
  private AppointmentRepository repository;

  public AppointmentService(AppointmentRepository repository) {
    this.repository = repository;
  }

  public Appointment saveAppointment(Appointment appointment) {
    return repository.save(appointment);
  }

  public List<Appointment> getAppointments() {
    return repository.findAll();
  }

  public Optional<Appointment> deleteAppointment(Long appointmentId) {
    Optional<Appointment> appointment = repository.findById(appointmentId);
    repository.deleteById(appointmentId);
    return appointment;
  }
}
