package com.alexsoderberg.personal.appointment_service.controller;

import com.alexsoderberg.personal.appointment_service.model.Appointment;
import com.alexsoderberg.personal.appointment_service.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; // Import for POST
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; // Import for status assertions

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AppointmentService service;

    @Test
    void createAppointmentShouldReturn201CreatedWhenInputIsValid() throws Exception {
        // Arrange
        Appointment mockAppointment = new Appointment(
            "John Doe",
            LocalDate.of(2024, 12, 1),
            LocalTime.of(10, 0),
            60,
            "Consultation"
        );

        Mockito.when(service.saveAppointment(any(Appointment.class))).thenReturn(mockAppointment);

        mockMvc.perform(post("/api/appointments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "name": "John Doe",
                        "date": "2024-12-01",
                        "time": "10:00",
                        "duration": 60,
                        "description": "Consultation"
                    }
                """))
                .andExpect(status().isCreated());
    }
}

