package com.alexsoderberg.personal.appointment_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexsoderberg.personal.appointment_service.model.Appointment;
import com.alexsoderberg.personal.appointment_service.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
  private AppointmentService service;

  public AppointmentController(AppointmentService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
    Appointment savedAppointment = service.saveAppointment(appointment);
    return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Appointment>> getAppointments() {
    List<Appointment> appointments = service.getAppointments();
    return new ResponseEntity<>(appointments, HttpStatus.OK);
  }
}
