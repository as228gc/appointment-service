package com.alexsoderberg.personal.appointment_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexsoderberg.personal.appointment_service.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
